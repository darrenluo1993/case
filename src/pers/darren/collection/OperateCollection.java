package pers.darren.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 集合操作案例
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jun 1, 2021 5:19:36 PM
 */
public class OperateCollection {

    public static void main(final String[] args) {
        // HashMap操作
        final Map<String, Long> map = new HashMap<>();
        map.put("1", 1L);
        map.put("2", 2L);
        map.put("3", 3L);
        map.put("4", 4L);
        // 获取Map的EntrySet迭代器进行Key-Value遍历
        final Set<Entry<String, Long>> entrySet = map.entrySet();
        final Iterator<Entry<String, Long>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            final Entry<String, Long> entry = iterator.next();
            System.out.println("key>>>" + entry.getKey() + ", value>>>" + entry.getValue());
        }
        System.out.println();
        // 获取Map的EntrySet，然后使用foreach方式遍历
        for (final Entry<String, Long> entry : entrySet) {
            System.out.println("key>>>" + entry.getKey() + ", value>>>" + entry.getValue());
        }
        System.out.println();

        // ArrayList操作
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        // 获取List的迭代器进行元素遍历
        final Iterator<Long> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            if (iterator2.next() == 1L) {
                iterator2.remove();
            }
        }
        // 直接使用foreach方式遍历
        for (final Long long1 : list) {
            System.out.println("long1>>>" + long1);
        }
    }
}