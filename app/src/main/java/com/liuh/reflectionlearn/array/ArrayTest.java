package com.liuh.reflectionlearn.array;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Date: 2018/4/27 15:08
 * Description:反射中的数组
 * 数组本质上是一个Class,在 Class 中存在一个方法用来识别它是否为一个数组:isArray
 * <p>
 * Array的读取和赋值
 * 首先，对于 Array 整体的读取与赋值，把它作为一个普通的 Field，根据 Class 中相应获取和设置就好了。
 * 调用的是 Field 中对应的方法。
 * <p>
 * 还需要处理的情况是对于数组中指定位置的元素进行读取与赋值，这要涉及到 Array 提供的一系列 setXXX() 和 getXXX() 方法。
 * 和之前 Field 相应的 set 、get 方法类似
 */

public class ArrayTest {

    public static void main(String[] args) {

        Class clz = Shuzu.class;

        Field[] fields = clz.getDeclaredFields();

        isArrayTest(fields);

        arrayGetSetTest(clz);

    }

    /**
     * 反射中数组对象的创建，赋值给某一个实例对象的属性
     *
     * @param clz
     */
    private static void arrayGetSetTest(Class clz) {

        try {
            Shuzu shuzu = (Shuzu) clz.newInstance();

            //获取array属性
            Field arrayF = clz.getDeclaredField("array");
            arrayF.setAccessible(true);

            //创建一个数组对象，数组长度为3,并赋值
            Object o = Array.newInstance(int.class, 3);
            Array.set(o, 0, 1);
            Array.set(o, 1, 3);
            Array.set(o, 2, 5);

            //对对象中的属性赋值
            arrayF.set(shuzu, o);

            //打印
            int[] array = shuzu.getArray();
            for (int i = 0; i < array.length; i++) {
                System.out.println("array index : " + i + " , value : " + array[i]);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否是数组类型，如果是则打印其类型名称和数组中元素的类型名称
     *
     * @param fields
     */
    private static void isArrayTest(Field[] fields) {
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
