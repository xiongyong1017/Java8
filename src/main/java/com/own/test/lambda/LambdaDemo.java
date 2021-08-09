package com.own.test.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author : xy
 * @date : 2021/6/25 17:22
 */
public class LambdaDemo {
    public static void main(String[] args) {
        Employee qingLong = new Employee("青龙", 25, 5500, 7500);
        Employee baiHu = new Employee("白虎", 27, 5000, 9000);
        Employee zhuQue = new Employee("朱雀", 22, 3800, 4500);
        Employee xuanWu = new Employee("玄武", 24, 3300, 3300);
        List<Employee> employees = Arrays.asList(qingLong, baiHu, zhuQue, xuanWu);
        //现在有个统计的需求是，按员工年龄从小到大排列，并获取员工姓名列表
        //java8之前通常的做法
        //员工先进行排序
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                Integer age1 = o1.getAge();
                Integer age2 = o2.getAge();
                return age1.compareTo(age2);
            }
        });
        //遍历排序后的列表并输出员工的姓名
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }
        //使用lambda的做法
        employees.stream().sorted((o1, o2) -> o1.getAge().compareTo(o2.getAge())).forEach(o -> System.out.println(o.getName()));
    }
}

