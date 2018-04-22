package com.liuh.reflectionlearn;

/**
 * Created by huan on 2018/4/22.
 */

public class Test {

    public static void main(String[] args) {

        try {
            Class clz = Class.forName("com.liuh.reflectionlearn.Car");

            Class clz1 = float.class;

            Class clz2 = Void.class;

            Class clz3 = new int[]{}.getClass();

            Class clz4 = new Car[]{
            }.getClass();

            System.out.println(clz.getName());
            System.out.println(clz1.getName());
            System.out.println(clz2.getName());
            System.out.println(clz3.getName());
            System.out.println(clz4.getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
