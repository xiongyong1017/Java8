package com.own.test.test;

import java.util.regex.Pattern;

/**
 * 快捷键的测试
 *
 * @author : xy
 * @date : 2021/8/31 15:48
 */
public class RegexpExample {
    /**
     * 使用alt + enter可以进行正则表达式的测试
     */
    Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{3,4}");
}
