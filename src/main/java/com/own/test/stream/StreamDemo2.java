package com.own.test.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 流的中间操作
 *
 * @author : xy
 * @date : 2021/6/25 16:50
 */
public class StreamDemo2 {
    public static void main(String[] args) {

    }

    /**
     * <p>filter：过滤流中的某些元素</p>
     * <p>limit(n)：获取n个元素</p>
     * <p>skip(n)：跳过n元素，配合limit(n)可实现分页</p>
     * <p>distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素</p>
     */
    @Test
    public void stream1() {
        Stream<Integer> stream1 = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
        Stream<Integer> stream2 = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
        Stream<Integer> stream3 = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
        Stream<Integer> stream4 = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
        Stream<Integer> newStream = stream1.filter(s -> s > 5);
        Stream<Integer> distinct = stream2.distinct();
        Stream<Integer> skip = stream3.skip(2);
        Stream<Integer> limit = stream4.limit(2);
        System.out.println("过滤：");
        newStream.forEach(System.out::println);

        System.out.println("去重：");
        distinct.forEach(System.out::println);

        System.out.println("跳过：");
        skip.forEach(System.out::println);

        System.out.println("分页（取n）：");
        limit.forEach(System.out::println);
    }

    /**
     * <p>map:接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素</p>
     * <p>flatMap:接收一个函数作为参数，将流中的每个值都换成另一个流，然后再把所有的流连接成一个流</p>
     */
    @Test
    public void stream2() {
        List<String> list = Arrays.asList("a,b,c,", "1,2,3");
        //将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",", ""));
        s1.forEach(System.out::println);
    }

}
