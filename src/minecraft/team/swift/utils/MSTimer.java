package team.swift.utils;

public class MSTimer {
    private long lastMS = 0;

    public MSTimer() {
        this.reset();
    }

    private long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public boolean hasReached(double millis) {
        return this.getCurrentMS() - this.lastMS >= millis;
    }

    public void reset() {
        this.lastMS = this.getCurrentMS();
    }

    public boolean delay(float millis) {
        return this.getTime() - this.lastMS >= millis;
    }

    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
}
