package com.own.test.lambda.exe;

/**
 * @author : xy
 * @date : 2021/8/2 16:05
 */
public class Exe2 {
    /**
     * 一般我们需要声明接口，该接口作为对象的生成器，
     * 通过 类名::new 的方式来实例化对象，然后调用方法返回对象。
     */
    public static void main(String[] args) {
        ItemCreatorBlankConstruct creator = () -> new Item();
        Item item = creator.getItem();

        ItemCreatorBlankConstruct creator2 = Item::new;
        Item item2 = creator2.getItem();

        ItemCreatorParamContruct creator3 = Item::new;
        Item item3 = creator3.getItem(112, "鼠标", 135.99);

        System.out.println(item);
        System.out.println(item2);
        System.out.println(item3);
    }

    interface ItemCreatorBlankConstruct {
        Item getItem();
    }

    interface ItemCreatorParamContruct {
        Item getItem(int id, String name, double price);
    }

}
