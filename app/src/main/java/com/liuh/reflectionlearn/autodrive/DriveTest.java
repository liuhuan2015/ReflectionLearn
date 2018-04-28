package com.liuh.reflectionlearn.autodrive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by huan on 2018/4/28.
 * 使用普通调用方式和反射调用方式模拟自动驾驶
 */

public class DriveTest {

    public static void main(String[] args) {
        //normalUse();

        reflectionUse();
    }

    /**
     * 使用反射的方式
     */
    private static void reflectionUse() {
        Class clazz = AutoDrive.class;
        try {
            AutoDrive autoDrive = (AutoDrive) clazz.getConstructor(String.class, AutoDrive.Color.class).newInstance("Changcheng", AutoDrive.Color.RED);

            Method method = clazz.getDeclaredMethod("drive", null);
            method.invoke(autoDrive, null);

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

    /**
     * 普通调用方式
     */
    private static void normalUse() {
        AutoDrive autoDrive = new AutoDrive();
        autoDrive.drive();
    }
}
