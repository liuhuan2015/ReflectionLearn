package com.liuh.reflectionlearn.enumTest;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by huan on 2018/4/28.
 * 反射中枚举的使用
 * <p>
 * 在 Java 反射中，可以把枚举看成一般的 Class，但是反射机制也提供了 3 个特别的的 API 用于操控枚举
 * <p>
 * Class.isEnum() 用来判定 Class 对象是不是枚举类型
 * <p>
 * Class.getEnumConstants() 获取所有的枚举常量
 * <p>
 * java.lang.reflect.Field.isEnumConstant() 判断一个 Field 是不是枚举常量
 */

public class EnumTest {

    public enum State {
        IDLE,
        DRIVING,
        STOPPING,

        test();

        int test1() {
            return 0;
        }
    }

    public State state = State.DRIVING;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static void main(String[] args) {

        Class clz = State.class;
        if (clz.isEnum()) {
            System.out.println(clz.getName() + " is enum");

            //打印枚举中所有的枚举常量
            System.out.println(Arrays.asList(clz.getEnumConstants()));
            //获取枚举中的所有属性，并作判断（判断其是不是枚举常量）
            Field[] fs = clz.getDeclaredFields();

            for (Field f : fs) {
                if (f.isEnumConstant()) {
                    System.out.println(f.getName() + " is EnumConstant");
                } else {
                    System.out.println(f.getName() + " is not EnumConstant");
                }
            }

            //对一个类中的枚举属性进行值的获取和赋值
            Class cEnumTest = EnumTest.class;
            EnumTest enumTest = new EnumTest();

            try {
                Field f = cEnumTest.getDeclaredField("state");
                f.setAccessible(true);

                State state = (State) f.get(enumTest);

                System.out.println("State Current is " + state);

                f.set(enumTest, State.STOPPING);

                System.out.println("State Current is " + enumTest.getState());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }


    }

}
