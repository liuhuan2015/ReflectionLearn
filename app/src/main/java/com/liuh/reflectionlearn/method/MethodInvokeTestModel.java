package com.liuh.reflectionlearn.method;

/**
 * Date: 2018/4/27 11:02
 * Description:测试Method的invoke方法的被测试类
 */

public class MethodInvokeTestModel {

    public static void testStatic() {
        System.out.println(" test static 我是一个静态方法");
    }

    private int add(int a, int b) {
        return a + b;
    }

    public void testException() throws IllegalAccessException {
        throw new IllegalAccessException("You have some problem");
    }

}
