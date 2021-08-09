package com.own.test.lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * @author : xy
 * @date : 2021/6/28 10:43
 */
public class FourFunctionsTest {
    /**
     * 消费式接口
     */
    @Test
    public void testConsumer() {
        Consumer<Integer> consumer = x -> System.out.println(x);
        consumer.accept(2);
    }

    /**
     * 供给式接口
     */
    @Test
    public void testSupplier() {
        Supplier<String> supplier = () -> {
            StringBuffer sb = new StringBuffer();
            return sb.append("我").append(520).append("you").toString();
        };
        System.out.println(supplier.get());
    }

    /**
     * 断言式接口
     */
    @Test
    public void testPredicate() {
        /*Predicate<Long> predicate = x -> x == 1L;
        System.out.println(predicate.test(2L));*/
        Predicate<String> predicate = x -> x == "我稀饭你";
        System.out.println(predicate.test("我稀饭你"));
    }

    /**
     * 函数式接口
     */
    @Test
    public void testFunction() {
        Function<Integer, Integer> function = x -> x - 3;
        System.out.println(function.apply(4));
    }

    public static void main(String[] args) {
        testFunction1(5);
    }

    public static Integer testFunction1(Integer a) {
        Function<Integer, Integer> function = x -> x * 6;
        return function.apply(a);
    }

    /**
     * 方法引用
     * 使用操作符::将方法名和对象或者类的名字分隔开，组合有以下三种。
     *
     * 对象::实例方法名
     * 类::静态方法
     * 类::实例方法
     * 常见的x-> System.out.println() 等同于System.out::println。
     * 注意:
     *1. Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口抽象方法的该函数列表和返回值类型保持一致。
     *2. 若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时。可以使用 ClassName :: method
     */

    /**
     * 对象：：实例方法
     */
    @Test
    public void testConsumer1() {
        Employee emp = new Employee();
        //函数式接口supplier是空参，返回的是integer类型的接口
        //而对应emp的的实例方法getAge（）刚刚好是空参切返回Integer类型的数据
        Supplier<Integer> supplier = () -> emp.getAge();
        Supplier<Double> sup2 = emp::getBasicSalary;
        System.out.println(supplier.get());
    }

    /**
     * 类：：方法名
     */
    @Test
    public void testSupplier1() {
        //要求参数第一个值作为方法体的被调用者，第二个参数值作为方法体的被调用者
        Comparator<String> comparator = (x, y) -> x.compareTo(y);
        Comparator<String> compString = String::compareTo;
        System.out.println(comparator.compare("2", "3"));

        Comparator<Integer> com=Integer::compare;
        System.out.println(com.compare(1, 2));

        BiPredicate<String,String>predicate =String::equals;
        System.out.println(predicate.test("we", "eq"));
    }


}
