package com.liuh.reflectionlearn;

/**
 * Created by huan on 2018/4/22.
 * 因为是内部类，所以通过 getName() 方法获取到的是二进制形式的全限定类名，并且类名前面还有个 $ 符号。
 * getSimpleName() 则直接返回了 Inner，去掉了包名限定。
 * <p>
 * Inner Class name:com.liuh.reflectionlearn.Outter$Inner
 * Inner Class simple name:Inner
 * <p>
 * 需要注意的是，当获取一个数组的 Class 中的 simplename 时，不同于 getName() 方法，simplename 不是在前面加 [，而是在后面添加对应数量的 []
 * <p>
 * clz1 Inner Class name:[[[Lcom.liuh.reflectionlearn.Outter$Inner;
 * clz1 Inner Class simple name:Inner[][][]
 * <p>
 * 还需要注意的是，对于匿名内部类，getSimpleName() 返回的是一个空的字符串
 * <p>
 * run Inner Class name:com.liuh.reflectionlearn.TestGetSimpleName$1
 * run Inner Class simple name:
 */

public class TestGetSimpleName {

    public static void main(String[] args) {

        Class clz = Outter.Inner.class;

        System.out.println("Inner Class name:" + clz.getName());
        System.out.println("Inner Class simple name:" + clz.getSimpleName());

        Class clz1 = new Outter.Inner[][][]{}.getClass();

        System.out.println("clz1 Inner Class name:" + clz1.getName());
        System.out.println("clz1 Inner Class simple name:" + clz1.getSimpleName());

        Runnable run = new Runnable() {
            @Override
            public void run() {

            }
        };
        System.out.println("run Inner Class name:" + run.getClass().getName());
        System.out.println("run Inner Class simple name:" + run.getClass().getSimpleName());

    }

}
