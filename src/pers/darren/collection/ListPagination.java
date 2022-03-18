package pers.darren.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Java List分页操作案例
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 3/16/22 5:19 PM
 */
public class ListPagination {

    private static final int size = 1000;
    private static final List<String> list = new ArrayList<>(size);

    static {
        for (int i = 1; i <= size; i++) {
            list.add("这是列表中的第" + i + "条数据！");
        }
    }

    public static void main(String[] args) {
        pagination(1, 5).forEach(System.out::println);
        System.out.println("=======================");
        pagination(2, 5).forEach(System.out::println);
        System.out.println("=======================");
        pagination(1, 100).forEach(System.out::println);
        System.out.println("=======================");
        pagination(2, 100).forEach(System.out::println);
    }

    /**
     * List分页
     *
     * @param pageIndex 请求页码
     * @param pageSize  每页数量
     * @CreatedBy Darren Luo
     * @CreatedTime 3/16/22 5:27 PM
     */
    private static List<String> pagination(int pageIndex, int pageSize) {
        // SubList的结束索引（SubList中不包含此索引的值）
        int toIndex = pageIndex * pageSize;
        // SubList的起始索引（SubList中包含此索引的值）
        int fromIndex = toIndex - pageSize;
        // SubList其实是源List的子视图，SubList类并没有自己的元素容器；
        // 对SubList执行新增、修改、删除元素时，实际都是对源List进行了新增、修改、删除操作；
        // 此处便于此方法调用者对返回List进行操作，从而new一个新的ArrayList接收SubList后再返回。
        return new ArrayList<>(list.subList(fromIndex, toIndex));
    }
}