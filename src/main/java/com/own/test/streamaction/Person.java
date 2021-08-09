package com.own.test.streamaction;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : xy
 * @date : 2021/7/28 16:04
 */
@Setter
@Getter
public class Person {
    private String name;//姓名
    private int salary;//薪资
    private int age;
    private String sex;//
    private String area;

    public Person() {
    }

    public Person(String name, int salary, int age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}





