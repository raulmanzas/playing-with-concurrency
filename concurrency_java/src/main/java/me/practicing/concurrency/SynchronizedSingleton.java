package me.practicing.concurrency;

public class SynchronizedSingleton {
    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {}

    private static synchronized SynchronizedSingleton getInstance() {
        // this is method is "synchronized", meaning that it's execution is locked to avoid race conditions
        // with minimal effort from developers
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}
