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
 * <p>
 * 参考文章：https://blog.csdn.net/mu_wind/article/details/109516995
 */
public final class StreamOperationCase {

    private StreamOperationCase() {
        throw new UnsupportedOperationException("This is an utility class!");
    }

    // 个税起征点
    private static final Integer point = 5000;
    // 员工信息列表
    private static final List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
        personList.add(new Person("Darren", 9500, 24, "male", "Chang Sha"));
    }

    public static void main(String[] args) {
        // createWay();
        // foreachFindMatch();
        // filter();
        // maxMinCount();
        // mapFlatMap();
        // reduce();
        // toListToSetToMap();
        // countAveraging();
        // partitioningByGroupingBy();
        // joining();
        // reducing();
        // sorted();
        // concatDistinctLimitSkip();
        // dropWhileTakeWhile();
        peek();
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
        Optional<String> findFirst = list.stream().parallel().filter("a"::equals).findFirst();
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
     * stream的聚合(max/min/count)
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
        List<Integer> integerList = Arrays.asList(6, 7, 3, 5, 9, 11, 6, 4, 2);
        Optional<Integer> maxInteger = integerList.stream().max(Integer::compareTo);
        System.out.println("取自然排序最大值方式1：" + maxInteger.get());
        maxInteger = integerList.stream().max(Comparator.naturalOrder());
        System.out.println("取自然排序最大值方式2：" + maxInteger.get());
        maxInteger = integerList.stream().max((i1, i2) -> i1.compareTo(i2));
        System.out.println("自定义排序最大值：" + maxInteger.get());
        maxInteger = integerList.stream().max((i1, i2) -> i2.compareTo(i1));
        System.out.println("自定义排序最大值（实际是最小值）：" + maxInteger.get());
        maxInteger = integerList.stream().max(Comparator.reverseOrder());
        System.out.println("反自然排序最大值（实际是最小值）：" + maxInteger.get());
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
        personList.forEach(person -> System.out.println("不改变原来员工集合的方式-原始数据：" + person.toString()));
        personListNew.forEach(person -> System.out.println("不改变原来员工集合的方式-改动后数据：" + person.toString()));
        System.out.println("---------------------------------------------");
        // 改变原来员工集合的方式1
        personListNew = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        personList.forEach(person -> System.out.println("改变原来员工集合的方式1-原始数据：" + person.toString()));
        personListNew.forEach(person -> System.out.println("改变原来员工集合的方式1-改动后数据：" + person.toString()));
        System.out.println("---------------------------------------------");
        // 改变原来员工集合的方式2
        personListNew = personList.stream().peek(person -> person.setSalary(person.getSalary() + 1000)).collect(Collectors.toList());
        personList.forEach(person -> System.out.println("改变原来员工集合的方式2-原始数据：" + person.toString()));
        personListNew.forEach(person -> System.out.println("改变原来员工集合的方式2-改动后数据：" + person.toString()));
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

    // 收集(collect)
    // collect，收集，可以说是内容最繁多、功能最丰富的部分了。从字面上去理解，就是把一个流收集起来，最终可以是收集成一个值也可以收集成一个新的集合。

    /**
     * 归集(toList/toSet/toMap)
     * 因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据重新归集到新的集合里。toList、toSet和toMap比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/2/22 11:40 AM
     */
    private static void toListToSetToMap() {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> evenList = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println("toList：" + evenList);
        Set<Integer> evenSet = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());
        System.out.println("toSet：" + evenSet);
        // 筛选出工资高于8000的人
        Map<String, Person> personMap = personList.stream().filter(person -> person.getSalary() > 8000).collect(Collectors.toMap(Person::getName, person -> person));
        System.out.println("toMap，工资高于8000的人：" + personMap);
        // 筛选出工资高于8000的人名和具体工资
        Map<String, Integer> salaryMap = personList.stream().filter(person -> person.getSalary() > 8000).collect(Collectors.toMap(Person::getName, Person::getSalary));
        System.out.println("toMap，工资高于8000的人名和具体工资：" + salaryMap);
    }

    /**
     * 统计(count/averaging)
     * Collectors提供了一系列用于数据统计的静态方法：
     * 计数：count
     * 平均值：averagingInt、averagingLong、averagingDouble
     * 最值：maxBy、minBy
     * 求和：summingInt、summingLong、summingDouble
     * 统计以上所有：summarizingInt、summarizingLong、summarizingDouble
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/2/22 3:23 PM
     */
    private static void countAveraging() {
        // 案例：统计员工人数、平均工资、工资总额、最高工资。
        // 求总数
        Long count = personList.stream().filter(person -> person.getSalary() > 8000).collect(Collectors.counting());
        System.out.println("求总数方式1：" + count);
        count = personList.stream().filter(person -> person.getSalary() > 8000).count();
        System.out.println("求总数方式2：" + count);
        System.out.println("---------------------------------------------");
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println("求平均工资方式：" + average);
        System.out.println("---------------------------------------------");
        // 求最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compareTo));
        System.out.println("求最高工资方式1：" + max.get());
        max = personList.stream().map(Person::getSalary).max(Integer::compareTo);
        System.out.println("求最高工资方式2：" + max.get());
        System.out.println("---------------------------------------------");
        // 求工资之和
        int sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        System.out.println("求工资之和方式1：" + sum);
        sum = personList.stream().mapToInt(Person::getSalary).sum();
        System.out.println("求工资之和方式2：" + sum);
        System.out.println("---------------------------------------------");
        // 一次性统计所有信息
        DoubleSummaryStatistics statistics = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("员工工资所有统计信息：" + statistics);
    }

    /**
     * 分组(partitioningBy/groupingBy)
     * 分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
     * 分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/2/22 4:05 PM
     */
    private static void partitioningByGroupingBy() {
        // 案例：将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组
        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(person -> person.getSalary() > 8000));
        System.out.println("员工按薪资是否高于8000分组：" + part);
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        System.out.println("员工按性别分组：" + group);
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> groupGroup = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工先按性别分组，再按地区分组：" + groupGroup);
    }

    /**
     * 接合(joining)
     * joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/2/22 5:01 PM
     */
    private static void joining() {
        String names = personList.stream().map(Person::getName).collect(Collectors.joining(",", "<", ">"));
        System.out.println("所有员工的姓名：" + names);
        String string = Stream.of("A", "B", "C", "D").collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
        string = Stream.of(1, 2, 3, 4, 5).map(String::valueOf).collect(Collectors.joining("--"));
        System.out.println("数字拼接后的字符串：" + string);
    }

    /**
     * 归约(reducing)
     * Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/2/22 5:30 PM
     */
    private static void reducing() {
        // 员工工资总和
        int sumVal = personList.stream().mapToInt(Person::getSalary).sum();
        System.out.println("员工工资总和：" + sumVal);
        // 求员工工资减去税收起征点后的总和
        Optional<Integer> sum = personList.stream().map(person -> person.getSalary() - point).collect(Collectors.reducing(Integer::sum));
        System.out.println("求员工工资减去税收起征点后的总和方式1：" + sum.get());
        sum = personList.stream().map(person -> person.getSalary() - point).reduce(Integer::sum);
        System.out.println("求员工工资减去税收起征点后的总和方式2：" + sum.get());
        sumVal = personList.stream().mapToInt(person -> person.getSalary() - point).sum();
        System.out.println("求员工工资减去税收起征点后的总和方式3：" + sumVal);
        sumVal = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (x, y) -> x + y - point));
        System.out.println("求员工工资减去税收起征点后的总和方式4：" + sumVal);
    }

    /**
     * 排序(sorted)
     * sorted，中间操作。有两种排序：
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：Comparator排序器自定义排序
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/3/22 11:31 AM
     */
    private static void sorted() {
        List<Integer> numList = Arrays.asList(4, 6, 7, 0, 2, 3, 1, 8, 9, 5);
        List<Integer> sortedNumList = numList.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println("升序排序方式1：" + sortedNumList);
        sortedNumList = numList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println("升序排序方式2：" + sortedNumList);
        sortedNumList = numList.stream().sorted(Comparator.comparing(num -> num)).collect(Collectors.toList());
        System.out.println("升序排序方式3：" + sortedNumList);
        System.out.println("---------------------------------------------");
        sortedNumList = numList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("降序排序方式1：" + sortedNumList);
        sortedNumList = numList.stream().sorted(Comparator.comparing(num -> (int) num).reversed()).collect(Collectors.toList());
        System.out.println("降序排序方式2：" + sortedNumList);
        System.out.println("---------------------------------------------");
        // 案例：将员工按工资由高到低（工资一样则按年龄由大到小）排序
        // 按工资升序排序（自然排序）
        List<Person> sortedPersonList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).collect(Collectors.toList());
        System.out.println("按工资升序排序方式1：" + sortedPersonList);
        sortedPersonList = personList.stream().sorted(Comparator.comparing(Person::getSalary, Comparator.naturalOrder())).collect(Collectors.toList());
        System.out.println("按工资升序排序方式2：" + sortedPersonList);
        System.out.println("---------------------------------------------");
        // 按工资降序排序
        sortedPersonList = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).collect(Collectors.toList());
        System.out.println("按工资降序排序方式1：" + sortedPersonList);
        sortedPersonList = personList.stream().sorted(Comparator.comparing(Person::getSalary, Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println("按工资降序排序方式2：" + sortedPersonList);
        System.out.println("---------------------------------------------");
        // 先按工资再按年龄升序排序
        sortedPersonList = personList.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).collect(Collectors.toList());
        System.out.println("先按工资再按年龄升序排序：" + sortedPersonList);
        System.out.println("---------------------------------------------");
        // 先按年龄再按工资升序排序
        sortedPersonList = personList.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getSalary)).collect(Collectors.toList());
        System.out.println("先按年龄再按工资升序排序：" + sortedPersonList);
        System.out.println("---------------------------------------------");
        // 先按工资再按年龄自定义排序（降序）
        sortedPersonList = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            }
            return p2.getSalary() - p1.getSalary();
        }).collect(Collectors.toList());
        System.out.println("先按工资再按年龄自定义排序（降序）：" + sortedPersonList);
    }

    /**
     * 提取/组合(concat/distinct/limit/skip)
     * 流也可以进行合并、去重、限制、跳过等操作。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/3/22 3:42 PM
     */
    private static void concatDistinctLimitSkip() {
        Stream<String> stream1 = Stream.of("a", "b", "c", "d");
        Stream<String> stream2 = Stream.of("d", "e", "f", "g");
        // concat合并两个stream，再distinct去重
        List<String> stringList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        System.out.println("concat合并流，再distinct去重：" + stringList);
        // limit限制从流中获得前n个数据
        List<Integer> integerList = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        System.out.println("limit限制从流中获得前n个数据：" + integerList);
        // 先skip跳过前n个数据，再limit获得前n个数据
        integerList = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());
        System.out.println("先skip跳过前n个数据，再limit获得前n个数据：" + integerList);
        // 先limit获得前n个数据，再skip跳过前n个数据
        integerList = Stream.iterate(1, x -> x + 2).limit(6).skip(1).collect(Collectors.toList());
        System.out.println("先limit获得前n个数据，再skip跳过前n个数据：" + integerList);
        // skip跳过第1个员工，再limit取3个员工
        List<Person> newPersonList = personList.stream().skip(1).limit(3).collect(Collectors.toList());
        System.out.println("skip跳过第1个员工，再limit取3个员工：" + newPersonList);
    }

    /**
     * 删除/保留(dropWhile/takeWhile)
     * dropWhile：从原始流起始位置开始删除满足给定条件的元素，直到遇到第一个不满足给定条件的元素。
     * takeWhile：从原始流起始位置开始保留满足给定条件的元素，直到遇到第一个不满足给定条件的元素。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/3/22 5:02 PM
     */
    private static void dropWhileTakeWhile() {
        List<Integer> list = Arrays.asList(3, 3, 6, 9, 12, 5, 7, 9, 11);
        List<Integer> newList = list.stream().dropWhile(x -> x % 3 == 0).collect(Collectors.toList());
        System.out.println("从流的起始位置删除为3的倍数的数，直到遇到第一个不是3的倍数的数：" + newList);
        newList = list.stream().takeWhile(x -> x % 3 == 0).collect(Collectors.toList());
        System.out.println("从流的起始位置保留为3的倍数的数，直到遇到第一个不是3的倍数的数：" + newList);
    }

    /**
     * 窥视/读取(peek)
     * peek操作接收的是一个Consumer<T>函数，peek操作会按照Consumer<T>函数提供的逻辑去消费流中的每一个元素，同时有可能改变元素内部的一些属性。
     * peek操作一般用于不想改变流中元素本身的类型或者只想改变元素的内部状态时；
     * 而map则用于改变流中元素本身类型，即从元素中派生出另一种类型的操作，这是他们之间的最大区别。
     * 那么peek操作实际开发中我们会用于哪些场景呢？
     * 比如对Collection<T>中的T的某些属性进行批处理的时候用peek操作就比较合适。
     * 如果我们要从Collection<T>中获取T的某个属性的集合时用map也就最好不过了。
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 3/4/22 10:08 AM
     */
    private static void peek() {
        // 打印每个员工的姓名并获得姓名长度最长的员工
        Optional<Person> max = personList.stream().peek(person -> System.out.print(person.getName() + "--")).max(Comparator.comparing(Person::getName, Comparator.comparingInt(String::length)));
        System.out.println("姓名长度最长的员工：" + max.get());
        System.out.println("---------------------------------------------");
        // 打印每个员工加薪1000前后的姓名和工资，并收集加薪1000后的员工列表
        List newPersonList = personList.stream()
                .peek(person -> System.out.print(person.getName() + "加薪前：" + person.getSalary()))
                .peek(person -> person.setSalary(person.getSalary() + 1000))
                .peek(person -> System.out.println("，" + person.getName() + "加薪后：" + person.getSalary()))
                .collect(Collectors.toList());
        System.out.println("---------------------------------------------");
        System.out.println("加薪1000前的员工列表：" + personList);
        System.out.println("加薪1000后的员工列表：" + newPersonList);
    }
}