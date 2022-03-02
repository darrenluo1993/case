package pers.darren.java8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream将要处理的元素集合看作一种流，在流的过程中，借助Stream API对流中的元素进行操作，比如：筛选、排序、聚合等。
 * <p>
 * Stream可以由数组或集合创建，对流的操作分为两种：
 * 1. 中间操作，每次返回一个新的流，可以有多个。
 * 2. 终端操作，每个流只能进行一次终端操作，终端操作结束后流无法再次使用。终端操作会产生一个新的集合或值。
 * <p>
 * 另外，Stream有几个特性：
 * 1. stream不存储数据，而是按照特定的规则对数据进行计算，一般会输出结果。
 * 2. stream不会改变数据源，通常情况下会产生一个新的集合或一个值。
 * 3. stream具有延迟执行特性，只有调用终端操作时，中间操作才会执行。
 * <p>
 * Stream可以通过集合数组创建。
 */
public class StreamOperationCase {

    private static final List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
    }

    public static void main(String[] args) {
        // createWay();
        // foreachFindMatch();
        // filter();
        // maxMinCount();
        // mapFlatMap();
        reduce();
    }

    /**
     * 创建stream的方式
     * stream和parallelStream的简单区分：
     * stream是顺序流，由主线程按顺序对流执行操作，
     * 而parallelStream是并行流，内部以多线程并行执行的方式对流进行操作，
     * 但前提是流中的数据处理没有顺序要求，例如筛选集合中的奇数。
     * 如果流中的数据量足够大，并行流可以加快处速度，除了直接创建并行流，还可以通过parallel()把顺序流转换成并行流。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 2/24/22 11:27 AM
     */
    private static void createWay() {
        // 1、通过java.util.Collection.stream()方法用集合创建流
        List<String> list = Arrays.asList("a", "b", "c", "d", "a");
        // 创建一个顺序流
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
        System.out.println("---------------------------------------------");
        // 通过parallel()将顺序流转换成并行流
        Optional<String> findFirst = list.stream().parallel().filter(x -> "a".equals(x)).findFirst();
        findFirst.ifPresent(System.out::println);
        System.out.println("---------------------------------------------");
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEach(System.out::println);
        System.out.println("---------------------------------------------");

        // 2、使用java.util.Arrays.stream(T[] array)方法用数组创建流
        int[] array = {1, 2, 3, 4, 5, 6};
        IntStream intStream = Arrays.stream(array);
        intStream.forEach(System.out::println);
        System.out.println("---------------------------------------------");

        // 3、使用Stream的静态方法：of()、iterate()、generate()
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
        integerStream.forEach(System.out::println);
        System.out.println("---------------------------------------------");
        Stream<Integer> integerStream1 = Stream.iterate(0, x -> x + 3).limit(4);
        integerStream1.forEach(System.out::println);
        System.out.println("---------------------------------------------");
        Stream<Double> doubleStream = Stream.generate(Math::random).limit(3);
        doubleStream.forEach(System.out::println);
    }

    /**
     * stream的遍历/查找/匹配（foreach/find/match）
     * Stream也是支持类似集合的遍历和匹配元素的，只是Stream中的元素是以Optional类型存在的。
     * Stream的遍历、匹配非常简单。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 2/24/22 11:27 AM
     */
    private static void foreachFindMatch() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        System.out.println("---------------------------------------------");
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        findFirst.ifPresent(System.out::println);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("---------------------------------------------");
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        findAny.ifPresent(System.out::println);
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("---------------------------------------------");
        boolean anyMatch = list.stream().anyMatch(x -> x > 6);
        System.out.println("是否存在大于6的值：" + anyMatch);
        anyMatch = list.stream().anyMatch(x -> x > 10);
        System.out.println("是否存在大于10的值：" + anyMatch);
    }

    /**
     * stream的筛选（filter）
     * 筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 2/24/22 11:27 AM
     */
    private static void filter() {
        // 案例一：筛选出Integer集合中大于7的元素，并打印出来
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        list.stream().filter(x -> x > 7).forEach(System.out::println);
        System.out.println("---------------------------------------------");
        // 案例二：筛选员工中工资高于8000的人，并形成新的集合，形成新集合依赖collect（收集）。
        List<String> filterList = personList.stream().filter(person -> person.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println("高于8000的员工姓名：" + filterList);
    }

    /**
     * stream的聚合（max/min/count)
     * max、min、count这些字眼你一定不陌生，没错，在mysql中我们常用它们进行数据统计。
     * Java stream中也引入了这些概念和用法，极大地方便了我们对集合、数组的数据统计工作。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 2/24/22 3:40 PM
     */
    private static void maxMinCount() {
        // 案例一：获取String集合中最长的元素。
        List<String> stringList = Arrays.asList("123", "234", "3456", "456", "5678", "789");
        Optional<String> maxString = stringList.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + maxString.get());
        System.out.println("---------------------------------------------");
        // 案例二：获取Integer集合中的最大值。
        List<Integer> integerList = Arrays.asList(6, 7, 3, 5, 9, 11, 6, 4, 0);
        Optional<Integer> maxInteger = integerList.stream().max(Integer::compareTo);
        System.out.println("自然排序最大值：" + maxInteger.get());
        maxInteger = integerList.stream().max(Comparator.reverseOrder());
        System.out.println("自定义排序的最大值：" + maxInteger.get());
        System.out.println("---------------------------------------------");
        // 案例三：获取员工工资最高的人。
        Optional<Person> maxPerson = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("员工工资最大值：" + maxPerson.get().getSalary());
        Optional<Integer> maxSalary = personList.stream().map(Person::getSalary).max(Integer::compareTo);
        System.out.println("员工工资最大值：" + maxSalary.get());
        System.out.println("---------------------------------------------");
        // 案例四：计算Integer集合中大于6的元素的个数。
        long count = integerList.stream().filter(x -> x > 6).count();
        System.out.println("list中大于6的元素个数：" + count);
    }

    /**
     * stream的映射（map/flatMap）
     * 映射，可以将一个流的元素按照一定的映射规则映射到另一个流中。
     * 分为map和flatMap：
     * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 2/24/22 4:57 PM
     */
    private static void mapFlatMap() {
        // 案例一：英文字符串数组的元素全部改为大写。整数数组每个元素+3。
        String[] stringArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> stringList = Arrays.stream(stringArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("每个元素大写：" + stringList);
        List<Integer> integerList = Arrays.asList(1, 3, 5, 7, 9, 11);
        integerList = integerList.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("每个元素+3：" + integerList);
        System.out.println("---------------------------------------------");
        // 案例二：将员工的薪资全部增加1000。
        // 不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person newPerson = new Person();
            newPerson.setName(person.getName());
            newPerson.setSex(person.getSex());
            newPerson.setAge(person.getAge());
            newPerson.setArea(person.getArea());
            newPerson.setSalary(person.getSalary() + 1000);
            return newPerson;
        }).collect(Collectors.toList());
        personList.forEach(person -> System.out.println("原始数据：" + person.toString()));
        System.out.println("---------------------------------------------");
        personListNew.forEach(person -> System.out.println("改动后数据：" + person.toString()));
        System.out.println("---------------------------------------------");
        // 改变原来员工集合的方式
        personListNew = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        personList.forEach(person -> System.out.println("原始数据：" + person.toString()));
        System.out.println("---------------------------------------------");
        personListNew.forEach(person -> System.out.println("改动后数据：" + person.toString()));
        System.out.println("---------------------------------------------");
        // 案例三：将两个字符数组合并成一个新的字符数组。
        stringList = Arrays.asList("a-b-c-d", "1-3-5-7");
        List<String> stringListNew = stringList.stream().flatMap(s -> Arrays.stream(s.split("-"))).collect(Collectors.toList());
        System.out.println("处理前的字符串集合：" + stringList);
        System.out.println("处理后的字符串集合：" + stringListNew);
    }

    /**
     * 归约(reduce)
     * 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/1/22 2:53 PM
     */
    private static void reduce() {
        // 案例一：求Integer集合的元素之和、乘积和最大值。
        List<Integer> integerList = Arrays.asList(1, 3, 5, 7, 9);
        // 求和方式1
        Optional<Integer> sum = integerList.stream().reduce((x, y) -> x + y);
        System.out.println("list求和方式1：" + sum.get());
        // 求和方式2
        sum = integerList.stream().reduce(Integer::sum);
        System.out.println("list求和方式2：" + sum.get());
        // 求和方式3
        Integer sumVal = integerList.stream().reduce(0, Integer::sum);
        System.out.println("list求和方式3：" + sumVal);
        System.out.println("---------------------------------------------");
        // 求员工薪资总和
        sum = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("list求员工薪资总和：" + sum.get());
        System.out.println("---------------------------------------------");
        // 求乘积方式
        Optional<Integer> product = integerList.stream().reduce((x, y) -> x * y);
        System.out.println("list求乘积方式：" + product.get());
        System.out.println("---------------------------------------------");
        // 求最大值方式1
        Optional<Integer> max = integerList.stream().reduce((x, y) -> x > y ? x : y);
        System.out.println("list求最大值方式1：" + max.get());
        // 求最大值方式2
        max = integerList.stream().reduce(Integer::max);
        System.out.println("list求最大值方式2：" + max.get());
        // 求最大值方式3
        Integer maxVal = integerList.stream().reduce(0, Integer::max);
        System.out.println("list求最大值方式3：" + maxVal);
        // 求最大值方式4
        max = integerList.stream().max(Integer::compareTo);
        System.out.println("list求最大值方式4：" + max.get());
        System.out.println("---------------------------------------------");
        // 案例二：求所有员工的工资之和和最高工资。
        // 求工资之和方式1
        sum = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("求工资之和方式1：" + sum.get());
        // 求工资之和方式2
        sumVal = personList.stream().reduce(0, (s, person) -> s += person.getSalary(), (s1, s2) -> s1 + s2);
        System.out.println("求工资之和方式2：" + sumVal);
        // 求工资之和方式3
        sumVal = personList.stream().reduce(0, (s, person) -> s += person.getSalary(), Integer::sum);
        System.out.println("求工资之和方式3：" + sumVal);
        System.out.println("---------------------------------------------");
        // 求最高工资方式1
        max = personList.stream().map(Person::getSalary).max(Integer::compareTo);
        System.out.println("求最高工资方式1：" + max.get());
        // 求最高工资方式2
        max = personList.stream().map(Person::getSalary).reduce(Integer::max);
        System.out.println("求最高工资方式2：" + max.get());
        // 求最高工资方式3
        maxVal = personList.stream().map(Person::getSalary).reduce(0, Integer::max);
        System.out.println("求最高工资方式3：" + maxVal);
        // 求最高工资方式4
        maxVal = personList.stream().reduce(0, (m, person) -> m > person.getSalary() ? m : person.getSalary(), Integer::max);
        System.out.println("求最高工资方式4：" + maxVal);
        // 求最高工资方式5
        maxVal = personList.stream().reduce(0, (m, person) -> m > person.getSalary() ? m : person.getSalary(), (m1, m2) -> m1 > m2 ? m1 : m2);
        System.out.println("求最高工资方式5：" + maxVal);
    }
}