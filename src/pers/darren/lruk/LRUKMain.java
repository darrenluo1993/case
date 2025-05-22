package pers.darren.lruk;

public class LRUKMain {

    public static void main(String[] args) {
        LRUKCache<String, Integer> cache = new LRUKCache<>(3, 2);

        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);

        System.out.println(cache.get("a")); // 输出: 1

        cache.put("d", 4); // 此时会触发淘汰，"b"会被淘汰（因为"a"和"c"都有至少2次访问）

        System.out.println(cache.get("b")); // 输出: null (已被淘汰)
    }
}
