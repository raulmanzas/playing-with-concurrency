package me.practicing.concurrency.patterns.producerconsumer;

public class ProducerConsumer {

    private static Object sharedLock = new Object();

    private static int[] buffer;
    private static int bufferElementsCount;

    static class Producer {

        void produce() {
            synchronized (sharedLock) {
                if (isFull(buffer)) {
                    try {
                        sharedLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[bufferElementsCount++] = 1;
                sharedLock.notify();
            }
        }
    }


    static class Consumer {

        void consume() {
            synchronized (sharedLock) {
                if (isEmpty(buffer)) {
                    try {
                        sharedLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[--bufferElementsCount] = 0;
                sharedLock.notify();
            }
        }
    }

    static boolean isEmpty(int[] buffer) {
        return bufferElementsCount == 0;
    }

    static boolean isFull(int[] buffer) {
        return bufferElementsCount == buffer.length;
    }

    public static void main(String... strings) throws InterruptedException {

        buffer = new int[10];
        bufferElementsCount = 0;

        var producer = new Producer();
        var consumer = new Consumer();

        Runnable produceTask = () -> {
            for (int i = 0 ; i < 50 ; i++) {
                producer.produce();
            }
        };
        Runnable consumeTask = () -> {
            for (int i = 0 ; i < 45 ; i++) {
                consumer.consume();
            }
        };

        var consumerThread = new Thread(consumeTask);
        var producerThread = new Thread(produceTask);

        consumerThread.start();
        producerThread.start();

        consumerThread.join();
        producerThread.join();

        System.out.println("bufferElementsCount " + bufferElementsCount);
    }
}