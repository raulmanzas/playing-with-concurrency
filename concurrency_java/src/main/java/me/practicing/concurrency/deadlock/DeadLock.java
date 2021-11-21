package me.practicing.concurrency.deadlock;

public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        var randomClass = new RandomClass();

        Runnable r1 = () -> randomClass.a();
        Runnable r2 = () -> randomClass.b();

        var t1 = new Thread(r1);
        t1.start();

        var t2 = new Thread(r2);
        t2.start();

        // Execution never stops by itself due to deadlock between keys a and b
        t1.join();
        t2.join();
    }
}
