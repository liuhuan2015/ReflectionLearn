package com.liuh.reflectionlearn.array;

import java.lang.reflect.Field;

/**
 * Date: 2018/4/27 15:08
 * Description:反射中的数组
 * 数组本质上是一个Class,在 Class 中存在一个方法用来识别它是否为一个数组:isArray
 */

public class ArrayTest {

    public static void main(String[] args) {

        Class clz = Shuzu.class;

        Field[] fields = clz.getDeclaredFields();

        for (Field f : fields) {
            //获取Field的类型
            Class c = f.getType();
            //判断这个类型是否是数组类型
            if (c.isArray()) {
                System.out.println("Type is " + c.getName());
                System.out.println("ComponentType type is " + c.getComponentType());//获取数组的里面的元素的类型
            }
        }
    }
}
