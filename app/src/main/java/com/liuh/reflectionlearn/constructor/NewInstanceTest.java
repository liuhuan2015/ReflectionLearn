package com.liuh.reflectionlearn.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Date: 2018/4/27 14:21
 * Description:在反射中获取一个类的实例化对象的两种方式
 * Class.newInstance()和Constructor.newInstance(),官方文档建议开发者使用后面这种方式
 * <p>
 * Class.newInstance() 只能调用无参的构造方法，而 Constructor.newInstance() 则可以调用任意的构造方法。
 * Class.newInstance() 通过构造方法直接抛出异常，而 Constructor.newInstance() 会把抛出来的异常包装到 InvocationTargetException 里面去，这个和 Method 行为一致。
 * Class.newInstance() 要求构造方法能够被访问，而 Constructor.newInstance() 却能够访问 private 修饰的构造器。
 */

public class NewInstanceTest {

    public static void main(String[] args) {
        Class clazz = ConstructorTestModel.class;

        try {
            System.out.println(clazz.newInstance().toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Constructor constructor = clazz.getConstructor(String.class);
            ConstructorTestModel model = (ConstructorTestModel) constructor.newInstance("我是小明,找我干啥?");
            System.out.println(model.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
