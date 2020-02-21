package pers.darren.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OperateCollection {

	public static void main(final String[] args) {
		final Map<String, Long> map = new HashMap<>();
		map.put("1", 1L);
		map.put("2", 2L);
		map.put("3", 3L);
		map.put("4", 4L);
		final Set<Entry<String, Long>> entrySet = map.entrySet();
		final Iterator<Entry<String, Long>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			final Entry<String, Long> entry = iterator.next();
			System.out.println("key>>>" + entry.getKey() + ", value>>>" + entry.getValue());
		}
		System.out.println();
		for (final Entry<String, Long> entry : entrySet) {
			System.out.println("key>>>" + entry.getKey() + ", value>>>" + entry.getValue());
		}
		System.out.println();
		final List<Long> list = new ArrayList<>();
		list.add(1L);
		list.add(2L);
		list.add(3L);
		list.add(4L);
		final Iterator<Long> iterator2 = list.iterator();
		while (iterator2.hasNext()) {
			if (iterator2.next() == 1L) {
				iterator2.remove();
			}
		}
		for (final Long long1 : list) {
			System.out.println("long1>>>" + long1);
		}
	}
}