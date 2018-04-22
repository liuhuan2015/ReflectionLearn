package com.liuh.reflectionlearn.field;

import java.lang.reflect.Constructor;

/**
 * Created by huan on 2018/4/22.
 * <p>
 * 同Field一样
 * getConstructors：获取当前类中的所有public构造方法，如果当前类没有构造方法，则有一个默认的构造方法
 * <p>
 * getDeclaredConstructors：获取当前类中所有的构造方法
 * <p>
 * getConstructors:public com.liuh.reflectionlearn.field.Son(int,java.lang.String)
 * getDeclaredConstructors:private com.liuh.reflectionlearn.field.Son()
 * getDeclaredConstructors:public com.liuh.reflectionlearn.field.Son(int,java.lang.String)
 * <p>
 * Constructor 不能从父类继承，所以没有办法通过 getConstructor() 获取到父类的 Constructor
 */

public class ConstructorTest {

    public static void main(String[] args) {

        Class clz = Son.class;

        Constructor[] constructors = clz.getConstructors();

        for (Constructor c : constructors) {
            System.out.println("getConstructors:" + c.toString());
        }

        constructors = clz.getDeclaredConstructors();

        for (Constructor c : constructors) {
            System.out.println("getDeclaredConstructors:" + c.toString());
        }
    }

}
