package pers.darren.lww;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LWWRegister<T> {
    private T value;
    private long timestamp;
    private String replicaId;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LWWRegister(T initialValue, long initialTimestamp, String initialReplicaId) {
        this.value = initialValue;
        this.timestamp = initialTimestamp;
        this.replicaId = initialReplicaId;
    }

    public void update(T newValue, long newTimestamp, String newReplicaId) {
        lock.writeLock().lock();
        try {
            if (newTimestamp > this.timestamp || (newTimestamp == this.timestamp && newReplicaId.compareTo(this.replicaId) < 0)) {
                this.value = newValue;
                this.timestamp = newTimestamp;
                this.replicaId = newReplicaId;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void merge(LWWRegister<T> other) {
        lock.writeLock().lock();
        try {
            if (other.timestamp > this.timestamp || (other.timestamp == this.timestamp && other.replicaId.compareTo(this.replicaId) < 0)) {
                this.value = other.value;
                this.timestamp = other.timestamp;
                this.replicaId = other.replicaId;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public T getValue() {
        lock.readLock().lock();
        try {
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }

    public long getTimestamp() {
        lock.readLock().lock();
        try {
            return timestamp;
        } finally {
            lock.readLock().unlock();
        }
    }

    public String getReplicaId() {
        lock.readLock().lock();
        try {
            return replicaId;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public String toString() {
        return "LWWRegister{" + "value=" + value + ", timestamp=" + timestamp + ", replicaId=\"" + replicaId + "\"" + "}";
    }
}