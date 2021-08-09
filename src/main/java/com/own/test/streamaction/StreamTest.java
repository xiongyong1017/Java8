package com.own.test.streamaction;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : xy
 * @date : 2021/7/28 16:12
 */
public class StreamTest {
    public static void main(String[] args) {


    }

    /**
     * Stream也是支持类似集合的遍历和匹配元素的，只是Stream中的元素是以Optional类型存在的。
     * Stream的遍历、匹配非常简单。
     */
    @Test
    public void bianlihepipei() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        //遍历输出符合条件的元素(大于6的)
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        //匹配第一个符合条件的元素
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        //匹配任意（适用于并行流）
        Optional<Integer> findAny = list.stream().filter(x -> x > 6).findAny();
        //是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x > 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
    }


    /**
     * 筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作
     */
    @Test
    public void shaixuan() {
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        list.stream().filter(x -> x > 7).forEach(System.out::println);
        // Arrays.asList(6, 7, 3, 8, 1, 2, 9).stream().filter(x -> x > 7).forEach(System.out::println);
    }

    /**
     * 筛选员工中工资高于8000的人，并形成新的集合
     */
    @Test
    public void test1() {
        List<Person> personList = getPersonList();
        List<String> filterList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println("高于8000的员工姓名" + filterList);
    }

    /**
     * max、min、count这些字眼你一定不陌生，没错，在mysql中我们常用它们进行数据统计。
     * Java stream中也引入了这些概念和用法，极大地方便了我们对集合、数组的数据统计工作。
     */
    @Test
    public void test2() {
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
    }

    /**
     * 获取Integer集合中的最大值。
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);
        //    自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        //    自定义排序
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("自然排序的最大值：" + max.get());
        System.out.println("自定义排序的最大值：" + max2.get());
    }

    /**
     * 获取员工工资最高的人。
     */
    @Test
    public void test4() {
        List<Person> personList = getPersonList();
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("员工工资的最大值为：" + max.get().getSalary());
    }


    /**
     * 计算Integer集合中大于6的元素的个数。
     */
    @Test
    public void test5() {
        List<Integer> list = Arrays.asList(7, 6, 4, 8, 2, 11, 9);
        long count = list.stream().filter(x -> x > 6).count();
        System.out.println("list中大于6的元素的个数：" + count);
    }

    /**
     * 映射，可以将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap：
     * <p>
     * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */


    /**
     * 英文字符串数组的元素全部改为大写。整数数组每个元素+3。
     */
    @Test
    public void map1() {
        String[] strarr = {"abcd", "bcdd", "defde", "ftr"};
        List<String> strList = Arrays.stream(strarr).map(String::toUpperCase).collect(Collectors.toList());

        List<Integer> intlist = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intlistnew = intlist.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素+3：" + intlistnew);
    }

    /**
     * 将员工的薪资全部增加1000
     */
    @Test
    public void map2() {
        List<Person> personList = getPersonList();
        //不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 1000);
            return personNew;
        }).collect(Collectors.toList());

        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println("================================");
        //改变原来员工集合的方式
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前：" + personList.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew.get(0).getSalary());
    }

    /**
     * 将两个字符数组合并成一个新的字符数组。
     */
    @Test
    public void map3() {
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            //将每个元素转换成stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());
        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
    }

    /**
     * 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作
     */
    /**
     * 求Integer集合的元素之和、乘积和最大值
     */
    @Test
    public void reduce1() {
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        //求和
        Optional<Integer> sum1 = list.stream().reduce((x, y) -> x + y);
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        //求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        //求最大值
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        Integer max2 = list.stream().reduce(0, Integer::max);

        System.out.println("list求和：" + sum1.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);

    }

    /**
     * 求所有员工的工资之和和最高工资
     */
    @Test
    public void reduce2() {
        List<Person> personList = getPersonList();
        //求工资之和
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        Integer sumSalary2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), (sum1, sum2) -> sum1 + sum2);
        Integer sumSalary3 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);
        //求工资最高
        Integer maxSalary1 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), Integer::max);
        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), (max1, max2) -> max1 > max2 ? max1 : max2);
        System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资：" + maxSalary1 + "," + maxSalary2);
    }
    /**
     * collect，收集，可以说是内容最繁多、功能最丰富的部分了。从字面上去理解，就是把一个流收集起来，最终可以是收集成一个值也可以收集成一个新的集合。
     * collect主要依赖java.util.stream.Collectors类内置的静态方法
     */
    /**
     * 因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据重新归集到新的集合里。
     * toList、toSet和toMap比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法。
     */
    @Test
    public void collect1() {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> newList = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

        List<Person> personList = getPersonList();
        Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000).collect(Collectors.toMap(Person::getName, p -> p));

        System.out.println("toList:" + newList);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);
    }

    /**
     * 统计(count/averaging)
     * Collectors提供了一系列用于数据统计的静态方法：
     * <p>
     * 计数：count
     * 平均值：averagingInt、averagingLong、averagingDouble
     * 最值：maxBy、minBy
     * 求和：summingInt、summingLong、summingDouble
     * 统计以上所有：summarizingInt、summarizingLong、summarizingDouble
     */
    @Test
    public void collect2() {
        List<Person> personList = getPersonList();
        //求员工总数
        Long count = personList.stream().collect(Collectors.counting());
        //求平均工资
        Double avgSal = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        //求最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        //求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        //一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + avgSal);
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);
    }

    /**
     * 分组(partitioningBy/groupingBy)
     * 分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
     * 分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
     * 将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组
     */
    @Test
    public void collect3() {
        List<Person> personList = getPersonList();
        //将员工薪资是否大于8000进行分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        //将员工按照性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        //将员工先按性别分组， 再按地区进行分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：");
        for (Map.Entry<Boolean, List<Person>> aBoolean : part.entrySet()) {
            System.out.println("==========" + aBoolean.getKey() + "==========");
            List<Person> value = aBoolean.getValue();
            for (Person person : value) {
                System.out.println(person.toString());
            }
        }
        System.out.println("员工按性别分组情况：");
        for (Map.Entry<String, List<Person>> stringListEntry : group.entrySet()) {
            System.out.println("==========" + stringListEntry.getKey() + "==========");
            List<Person> value = stringListEntry.getValue();
            for (Person person : value) {
                System.out.println(person.toString());
            }
        }
        System.out.println("员工按性别、地区：");
        for (Map.Entry<String, Map<String, List<Person>>> stringMapEntry : group2.entrySet()) {
            System.out.println("==========" + stringMapEntry.getKey() + "==========");
            Map<String, List<Person>> value = stringMapEntry.getValue();
            for (Map.Entry<String, List<Person>> stringListEntry : value.entrySet()) {
                System.out.println("==========" + stringListEntry.getKey() + "==========");
                List<Person> value1 = stringListEntry.getValue();
                for (Person person : value1) {
                    System.out.println(person.toString());
                }
            }
        }
    }

    /**
     * joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串
     */
    @Test
    public void collect4() {
        List<Person> personList = getPersonList();
        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    /**
     * Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
     */
    @Test
    public void collect5() {
        List<Person> personList = getPersonList();
        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 2000)));
        System.out.println("员工税后薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }

    /**
     * sorted，中间操作。有两种排序：
     *
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：Comparator排序器自定义排序
     */
    /**
     * 将员工按工资由高到低（工资一样则按年龄由大到小）排序
     */
    @Test
    public void sorted1() {
        List<Person> personList = getPersonList();
        //按工资升序排序（自然排序）
        List<String> newList1 = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        //按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).map(Person::getName).collect(Collectors.toList());
        //先按工资再按年龄升序排序
        List<String> newList3 = personList.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getSex)).map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资升序排序：" + newList1);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }

    /**
     * <p>limit(n)：获取n个元素</p>
     * <p>skip(n)：跳过n元素，配合limit(n)可实现分页</p>
     * <p>distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素</p>
     */
    @Test
    public void testOther() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);

    }

    private List<Person> getPersonList() {
        List<Person> personList = new ArrayList<Person>();
        initList(personList);
        return personList;
    }

    private void initList(List<Person> personList) {
        personList.add(new Person("张三", 8900, 23, "男", "中国台湾"));
        personList.add(new Person("李四", 7000, 25, "男", "中国内地"));
        personList.add(new Person("王五", 7800, 21, "女", "中国内地"));
        personList.add(new Person("赵六", 8200, 24, "女", "中国台湾"));
        personList.add(new Person("头七", 9500, 25, "男", "中国台湾"));
        personList.add(new Person("阿八", 7900, 26, "女", "中国台湾"));
    }
}
