package com.liuh.reflectionlearn.field;

import java.lang.reflect.Field;

/**
 * Date: 2018/4/24 09:53
 * Description:Field类型获取
 * <p>
 * Field Name:cars
 * Field type:interface java.util.List
 * Field generic type:java.util.List<com.liuh.reflectionlearn.Car>
 * ------------------------
 * Field Name:map
 * Field type:class java.util.HashMap
 * Field generic type:java.util.HashMap<java.lang.Integer, java.lang.String>
 * ------------------------
 * Field Name:a
 * Field type:int
 * Field generic type:int
 * ------------------------
 * <p>
 * 从测试结果可以看出:getGenericType()比getType()获取到的类型描述更加详细,能够获取到泛型类型
 */

public class FieldGetTypeTest {

    public static void main(String[] args) {
        Class clazz = Son.class;

        Field[] fields = clazz.getFields();

        for (Field field : fields) {

            System.out.println("Field Name:" + field.getName());
            System.out.println("Field type:" + field.getType());
            System.out.println("Field generic type:" + field.getGenericType());
            System.out.println("------------------------");
        }
    }
}
