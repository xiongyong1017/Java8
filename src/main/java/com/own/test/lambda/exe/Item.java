package com.own.test.lambda.exe;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : xy
 * @date : 2021/8/2 17:04
 */
@Setter
@Getter
public class Item {
    private Integer id;
    private String name;
    private double price;

    public Item(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
