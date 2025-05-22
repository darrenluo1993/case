package pers.darren.lruk;

import java.util.*;

public class LRUKCache<K, V> {
    private final int capacity;
    private final int k;
    private final Map<K, V> cache;
    private final Map<K, LinkedList<Long>> accessHistory;
    private final Set<K> historyOnly;
    private final LinkedHashMap<K, Long> kAccess;

    public LRUKCache(int capacity, int k) {
        this.capacity = capacity;
        this.k = k;
        this.cache = new HashMap<>();
        this.accessHistory = new HashMap<>();
        this.historyOnly = new HashSet<>();
        this.kAccess = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, Long> eldest) {
                return false; // 我们自己控制淘汰逻辑
            }
        };
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            recordAccess(key);
            return cache.get(key);
        }
        return null;
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }

        if (cache.containsKey(key)) {
            cache.put(key, value);
            recordAccess(key);
            return;
        }

        if (cache.size() >= capacity) {
            evict();
        }

        cache.put(key, value);
        recordAccess(key);
    }

    private void recordAccess(K key) {
        long now = System.currentTimeMillis();
        LinkedList<Long> history = accessHistory.computeIfAbsent(key, k -> new LinkedList<>());
        history.add(now);

        // 保持最多k次访问记录
        if (history.size() > k) {
            history.removeFirst();
        }

        if (historyOnly.contains(key)) {
            if (history.size() >= k) {
                historyOnly.remove(key);
                kAccess.put(key, history.get(history.size() - k));
            }
        } else if (kAccess.containsKey(key)) {
            kAccess.remove(key);
            kAccess.put(key, history.get(history.size() - k));
        } else {
            if (history.size() < k) {
                historyOnly.add(key);
            } else {
                kAccess.put(key, history.get(history.size() - k));
            }
        }
    }

    private void evict() {
        // 优先淘汰访问次数小于k的项
        if (!historyOnly.isEmpty()) {
            K oldestKey = null;
            long oldestTime = Long.MAX_VALUE;

            for (K key : historyOnly) {
                LinkedList<Long> history = accessHistory.get(key);
                if (history != null && !history.isEmpty() && history.getFirst() < oldestTime) {
                    oldestKey = key;
                    oldestTime = history.getFirst();
                }
            }

            if (oldestKey != null) {
                removeKey(oldestKey);
                return;
            }
        }

        // 如果没有访问次数小于k的项，淘汰kAccess中最久未访问的
        if (!kAccess.isEmpty()) {
            K oldestKey = kAccess.keySet().iterator().next();
            removeKey(oldestKey);
        }
    }

    private void removeKey(K key) {
        cache.remove(key);
        accessHistory.remove(key);
        historyOnly.remove(key);
        kAccess.remove(key);
    }

    public int size() {
        return cache.size();
    }

    public void clear() {
        cache.clear();
        accessHistory.clear();
        historyOnly.clear();
        kAccess.clear();
    }
}