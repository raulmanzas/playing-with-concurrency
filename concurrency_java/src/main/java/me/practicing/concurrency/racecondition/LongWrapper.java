package me.practicing.concurrency.racecondition;

public class LongWrapper {
    private long l;

    public LongWrapper(long l) {
        this.l = l;
    }

    public long getValue() {
        return l;
    }

    public synchronized void incrementValue() {
        // read and write operation that can happen at the same time from different threads, causing a race condition
        // if there is no lock. The lock used here is the instance itself
        l = l + 1;
    }
}
