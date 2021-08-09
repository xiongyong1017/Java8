package com.own.test.lambda.exe;

import com.own.test.lambda.LambdaInterface;

/**
 * lambda 表达式引用方法
 * 有时候我们不是必须要自己重写某个匿名内部类的方法，我们可以可以利用 lambda表达式的接口快速指向一个已经被实现的方法。
 * <p>
 * 语法
 * <p>
 * 方法归属者::方法名 静态方法的归属者为类名，普通方法归属者为对象
 */
public class Exe1 {
    public static void main(String[] args) {
        LambdaInterface.ReturnOneParam lambda1 = a -> doubleNum(a);
        System.out.println(lambda1.method(3));

        //lambda2引用已经实现的doubleNum方法
        LambdaInterface.ReturnOneParam lambda2 = Exe1::doubleNum;
        System.out.println(lambda2.method(3));

        Exe1 exe1 = new Exe1();
        //lambda4引用了已经实现的addTwo方法
        LambdaInterface.ReturnOneParam lambda4 = exe1::addTwo;
        System.out.println(lambda4.method(4));
    }

    /**
     * 要求
     * 1.参数数量和类型要与接口中定义的一致
     * 2.返回值类型要与接口中定义的一致
     */
    public static int doubleNum(int a) {
        return a * 2;
    }

    public int addTwo(int a) {
        return a + 2;
    }
}
