package com.own.test;

/**
 * @author : xy
 * @date : 2021/7/1 14:30
 */
public class ExceptionTest {
    public static void main(String[] args) {
        String[] array = new String[10];
        System.out.println("length=" + array.length);
        try {
            String s = array[10];
        } catch (ArrayIndexOutOfBoundsException ex1) {
            System.out.println("ArrayIndexOutOfBoundsException");
        } catch (Exception ex2) {
            System.out.println("Exception");
        }finally {
            System.out.println("finally");

        }
    }
}
