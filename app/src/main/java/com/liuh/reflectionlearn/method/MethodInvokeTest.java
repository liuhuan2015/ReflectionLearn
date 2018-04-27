package com.liuh.reflectionlearn.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Date: 2018/4/27 11:07
 * Description:Method的invoke方法的使用
 * <p>
 * public Object invoke(Object obj,Object... args)
 * <p>
 * 注意点:
 * 1.invoke() 方法中第一个参数 Object 实质上是 Method 所依附的 Class 对应的类的实例，
 * 如果这个方法是一个静态方法，那么 ojb 为 null，后面的可变参数 Object 对应的自然就是参数
 * <p>
 * 2.invoke() 返回的对象是 Object，所以使用的时候一般要进行强制转换
 * <p>
 * 3.在对Method 调用 invoke() 的时候，如果方法本身会抛出异常，那么这个异常会被包装，
 * 由Method 统一抛出 InvocationTargetException。而通过 InvocationTargetException.getCause() 可以获取真正的异常
 */

public class MethodInvokeTest {

    public static void main(String[] args) {

        Class clazz = MethodInvokeTestModel.class;
        //静态方法的调用
        try {
            Method method1_static = clazz.getMethod("testStatic", null);
            method1_static.invoke(null, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //非静态方法的调用
        MethodInvokeTestModel model = new MethodInvokeTestModel();

        try {
            Method method2_nonstatic = clazz.getDeclaredMethod("add", int.class, int.class);
            method2_nonstatic.setAccessible(true);
            int result = (int) method2_nonstatic.invoke(model, 5, 11);
            System.out.println("result : " + result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //当方法抛出异常时
        try {
            Method testExcep = clazz.getMethod("testException", null);
            testExcep.invoke(model, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
//            e.printStackTrace();
            System.out.println("some error occur ,error type is : " + e.getCause().getClass().getName());
            System.out.println("error message is : " + e.getCause().getMessage());
        }

    }
}
