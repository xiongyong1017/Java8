package com.own.test.lambda;


import lombok.*;

/**
 * @author : xy
 * @date : 2021/6/25 17:24
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Employee {
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 员工年龄
     */
    private Integer age;
    /**
     * 基本薪水
     */
    private double basicSalary;
    /**
     * 订单成交总额
     */
    private double dealTotalPrice;

    public Employee(String name, Integer age, double basicSalary, double dealTotalPrice) {
        this.name = name;
        this.age = age;
        this.basicSalary = basicSalary;
        this.dealTotalPrice = dealTotalPrice;

    }

    /**
     * 员工总薪资
     *
     * @return Double
     */
    public Double getTotalSalary() {
        return this.basicSalary + this.dealTotalPrice * 0.04;
    }
}

