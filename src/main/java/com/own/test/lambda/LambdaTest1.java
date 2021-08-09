package com.own.test.lambda;

/**
 * @author : xy
 * @date : 2021/8/2 9:06
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        //无参无返回
        LambdaInterface.NoReturnNoParam noReturnNoParam = () -> {
            System.out.println("noReturnMultiParam");
        };
        noReturnNoParam.method();

        //一个参数无返回值
        LambdaInterface.NoReturnOneParam noReturnOneParam = (int a) -> {
            System.out.println("noReturnOneParam");
        };
        noReturnOneParam.method(5);

        //多个参数无返回
        LambdaInterface.NoReturnMultiParam noReturnMultiParam = (int a, int b) -> {
            System.out.println("noReturnMultiParam");
        };
        noReturnMultiParam.method(6, 8);

        //无参有返回值
        LambdaInterface.ReturnNoParam returnNoParam = () -> {
            System.out.println("returnNoParam");
            return 1;
        };
        int res = returnNoParam.method();
        System.out.println("return:" + res);

        //一个参数有返回值
        LambdaInterface.ReturnOneParam returnOneParam = (int a) -> {
            System.out.println("returnOneParam:" + a);
            return 1;
        };
        int res2 = returnOneParam.method(6);
        System.out.println("retuen:" + res2);

        //多个参数有返回值
        LambdaInterface.ReturnMultiParam returnMultiParam = (int a, int b) -> {
            System.out.println("returnMultiParam" + "{" + a + "," + b + "}");
            return 1;
        };
        int res3 = returnMultiParam.method(6, 8);
        System.out.println("return:" + res3);

    }
}
