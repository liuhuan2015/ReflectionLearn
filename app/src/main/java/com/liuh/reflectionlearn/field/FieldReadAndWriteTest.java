package com.liuh.reflectionlearn.field;

import java.lang.reflect.Field;

/**
 * Date: 2018/4/26 16:30
 * Description:Field(属性)的读取和赋值
 */

public class FieldReadAndWriteTest {

    public static void main(String[] args) {

//        test_public_a();

        test_private_b();

    }

    private static void test_private_b() {
        A testb = new A();
        testb.setB(3);

        System.out.println("testb.b=" + testb.getB());

        Class c = A.class;

        try {
            Field fieldB = c.getDeclaredField("b");

            fieldB.setAccessible(true);

            int rb = fieldB.getInt(testb);

            System.out.println("reflection testb.b=" + rb);

            fieldB.setInt(testb, 11);

            System.out.println("testb.b=" + testb.getB());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void test_public_a() {
        A testa = new A();
        testa.a = 10;

        System.out.println("testa.a=" + testa.a);

        Class c = A.class;

        try {
            Field fieldA = c.getField("a");

            int ra = fieldA.getInt(testa);

            System.out.println("reflection testa.a=" + ra);

            fieldA.setInt(testa, 15);

            System.out.println("testa.a=" + testa.a);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
