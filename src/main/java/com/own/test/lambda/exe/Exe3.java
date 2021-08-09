package com.own.test.lambda.exe;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * @author : xy
 * @date : 2021/8/2 17:11
 */
public class Exe3 {
    public static void main(String[] args) {
        /**
         * Lambda 表达式中的闭包问题
         * 这个问题我们在匿名内部类中也会存在，如果我们把注释放开会报错，告诉我 num 值是 final 不能被改变。
         * 这里我们虽然没有标识 num 类型为 final，但是在编译期间虚拟机会帮我们加上 final 修饰关键字。
         */
        int num = 10;
        Consumer<String> consumer = ele -> {
            System.out.println(num);
        };
        // num =num+2;
        consumer.accept("hello");
    }

    /**
     * 我们以往都是通过创建 Thread 对象，然后通过匿名内部类重写 run() 方法，
     * 一提到匿名内部类我们就应该想到可以使用 lambda 表达式来简化线程的创建过程。
     */
    @Test
    public void createThread() {
        //lambda表达式创建线程
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(2 + ":" + i);
            }
        });
        t.start();
    }

    /**
     * 我们可以调用集合的 public void forEach(Consumer<? super E> action) 方法，
     * 通过 lambda 表达式的方式遍历集合中的元素。以下是 Consumer 接口的方法以及遍历集合的操作。
     * Consumer 接口是 jdk 为我们提供的一个函数式接口。
     */
    @Test
    public void listTest() {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5);

        //lambda表达式 方法引用
        list.forEach(System.out::println);
        System.out.println("===================");
        list.forEach(element -> {
            if (element % 2 == 0) {
                System.out.println(element);
            }
        });
    }

    /**
     * 我们通过public boolean removeIf(Predicate<? super E> filter)方法来删除集合中的某个元素，
     * Predicate 也是 jdk 为我们提供的一个函数式接口，可以简化程序的编写。
     */
    @Test
    public void listDemo() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(11, "小牙刷", 12.05));
        items.add(new Item(5, "日本马桶盖", 999.05));
        items.add(new Item(7, "格力空调", 888.88));
        items.add(new Item(17, "肥皂", 2.00));
        items.add(new Item(9, "冰箱", 4200.00));

        items.removeIf(ele -> ele.getId() == 7);

        //通过 foreach 遍历，查看是否已经删除
        items.forEach(System.out::println);
    }

    /**
     * 集合内元素的排序
     * 在以前我们若要为集合内的元素排序，就必须调用 sort 方法，
     * 传入比较器匿名内部类重写 compare 方法，我们现在可以使用 lambda 表达式来简化代码。
     */
    @Test
    public void listDemo2() {
        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item(13, "背心", 7.80));
        list.add(new Item(11, "半袖", 37.80));
        list.add(new Item(14, "风衣", 139.80));
        list.add(new Item(12, "秋裤", 55.33));
        /*
        list.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getId() - o2.getId();
            }
        });
        */
        list.sort(((o1, o2) -> o1.getId() - o2.getId()));
    }

}
