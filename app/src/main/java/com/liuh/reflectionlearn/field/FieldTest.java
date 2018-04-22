package com.liuh.reflectionlearn.field;

import java.lang.reflect.Field;

/**
 * Created by huan on 2018/4/22.
 * <p>
 * public Field getDeclaredField(String name) 可获取到private,protected,public,default属性，但是不能获取从父类继承下来的属性
 * <p>
 * public Field getField(String name) 可以获取到public属性，当前Class没有时会向祖先类获取
 * <p>
 * public Field[] getDeclaredFields() 获取所有的属性，但不能获取从父类继承下来的属性
 * <p>
 * public Field[] getFields() 获取public属性，当前Class没有时会向祖先类获取
 */

public class FieldTest {

    public static void main(String[] args) {

        Class cls = Son.class;

        try {
            Field field = cls.getDeclaredField("c");
            System.out.println(field.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
//            System.out.println("getDeclaredField " + e.getMessage());
        }


        try {
            Field field = cls.getField("a");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
//            System.out.println("getField " + e.getMessage());
        }

        System.out.println("------------------------");
        Field[] fields1 = cls.getDeclaredFields();

        for (Field field : fields1) {
            System.out.println("DeclaredField:" + field.getName());
        }
        System.out.println("------------------------");
        Field[] fields2 = cls.getFields();
        for (Field field : fields2) {
            System.out.println("Field:" + field.getName());
        }


    }


}
