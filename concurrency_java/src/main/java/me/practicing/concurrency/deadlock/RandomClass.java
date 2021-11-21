package me.practicing.concurrency.deadlock;

public class RandomClass {
    private Object keyForA = new Object();
    private Object keyForB = new Object();

    public void a() {
        synchronized (keyForA) {
            System.out.println("[" + getCurrentThreadName() + "] - a()");
            b();
        }
    }

    public void b() {
        synchronized (keyForB) {
            System.out.println("[" + getCurrentThreadName() + "] - b()");
            c();
        }
    }

    public void c() {
        synchronized (keyForA) {
            System.out.println("[" + getCurrentThreadName() + "] - c()");
        }
    }

    private String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }
}
