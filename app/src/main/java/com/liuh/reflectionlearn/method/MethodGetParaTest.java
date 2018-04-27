package com.liuh.reflectionlearn.method;

import com.liuh.reflectionlearn.Car;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Date: 2018/4/27 09:44
 * Description:Method类的获取方法参数(参数名称,参数类型)
 * method.getParameters()需要Api 26
 */

public class MethodGetParaTest {

    public static void main(String[] args) {

        Class clazz = Car.class;

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println("method name:" + method.getName());

            Parameter[] paras = method.getParameters();//需要Api level 26,即8.0

            for (Parameter p : paras) {
                System.out.println("Parameter name:" + p.getName() + "Parameter type:" + p.getType());
            }


            Class[] pTypes = method.getParameterTypes();//获取所有的参数类型
            System.out.println("---------------method para types:");
            for (Class type : pTypes) {
                System.out.println("type : " + type.getName());
            }
            System.out.println("---------------method para types.");


//            Type[] pTypesGeneric = method.getGenericParameterTypes();//获取所有的参数类型,包括泛型
//            System.out.println("---------------method para generic types:");
//            for (Type typeG : pTypesGeneric) {
//                System.out.println("type : " + typeG.getTypeName());//这个方法好像找不到
//            }
//            System.out.println("---------------method para generic types.");


        }

    }
}
