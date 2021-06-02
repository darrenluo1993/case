package pers.darren.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * List-subList函数的使用方法
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jun 1, 2021 5:21:48 PM
 */
public class ListMethod_subList {

    public static void main(final String[] args) {
        final List<String> list = new ArrayList<>(5);
        list.add("Value1");
        list.add("Value2");
        list.add("Value3");
        list.add("Value4");
        list.add("Value5");
        List<String> subList = list.subList(3, 4);
        System.out.println("list===" + list);// list===[Value1, Value2, Value3, Value4, Value5]
        System.out.println("subList===" + subList); // subList===[Value4]
        list.add("Value6");
        // list在新增元素后，如果不重新获取subList，直接打印之前获取的subList会触发ConcurrentModificationException
        // 这是因为list在新增元素后，其内部变量modCount的值会自增1，但是subList的modCount值无法跟着自增1，所以会导致list.modCount与subList.modCount的值不一致，从而触发异常
        subList = list.subList(3, 4);
        System.out.println("list===" + list);// list===[Value1, Value2, Value3, Value4, Value5, Value6]
        System.out.println("subList===" + subList);// subList===[Value4]
        // 向subList中新增元素时，实际操作的是list，是向list中新增了元素
        subList.add("Value7");
        // subList.add("Value7")实际是往list的3 + subList.size()索引位置新增了Value7
        System.out.println("list===" + list);// list===[Value1, Value2, Value3, Value4, Value7, Value5, Value6]
        System.out.println("subList===" + subList);// subList===[Value4, Value7]
        subList.add(0, "Value8");
        // subList.add(0, "Value8")实际是往list的3 + 0索引位置新增了Value8
        System.out.println("list===" + list);// list===[Value1, Value2, Value3, Value8, Value4, Value7, Value5, Value6]
        System.out.println("subList===" + subList);// subList===[Value8, Value4, Value7]
        // subList其实是list的一个子视图，SubList类并没有自己的元素容器，对subList执行新增、修改、删除元素时，实际都是对list进行了新增、修改、删除操作；对subList进行变更性的操作时，subList会将自己的modCount值同步为list的modCount值
    }
}