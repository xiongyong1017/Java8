package com.own.test.stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author : xy
 * @date : 2021/6/24 17:07
 */
public class StreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        stream1();//stream() 和 parallelStream() 方法
        stream2();//Arrays 中的 stream() 方法，将数组转成流
        stream3();//Stream中的静态方法：of()、iterate()、generate()
        stream4();//BufferedReader.lines() 方法，将每行内容转成流
        stream5();//Pattern.splitAsStream() 方法，将字符串分隔成流
    }

    /**
     * 1.5 使用 Pattern.splitAsStream() 方法，将字符串分隔成流
     */
    private static void stream5() {
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
    }

    /**
     * 1.4 使用 BufferedReader.lines() 方法，将每行内容转成流
     */
    private static void stream4() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\code\\StreamTest\\testStream.txt"));
        Stream<String> linesStream = reader.lines();
        linesStream.forEach(System.out::println);
        System.out.println("==========");
    }

    /**
     * 1.3 使用Stream中的静态方法：of()、iterate()、generate()
     */
    private static void stream3() {
        Stream<Integer> streamMethod = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> streamMethod2 = Stream.iterate(0, (x) -> x + 2).limit(6);
        streamMethod2.forEach(System.out::println);
        System.out.println("==========");
        Stream<Double> streamMethod3 = Stream.generate(Math::random).limit(2);
        streamMethod3.forEach(System.out::println);
        System.out.println("==========");
    }

    /**
     * 1.2 使用Arrays 中的 stream() 方法，将数组转成流
     */
    private static void stream2() {
        Integer[] nums = new Integer[10];
        Stream<Integer> streamInteger = Arrays.stream(nums);
        System.out.println(streamInteger);
    }

    /**
     * 1.1使用Collection下的 stream() 和 parallelStream() 方法
     */
    private static void stream1() {
        //创建一个集合
        List<String> list = new ArrayList<>();
        //获取一个顺序流
        Stream<String> stream = list.stream();
        //获取一个并行流
        Stream<String> parallelStream = list.parallelStream();
    }
}
