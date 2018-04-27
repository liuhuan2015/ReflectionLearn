package com.liuh.reflectionlearn;

import com.liuh.reflectionlearn.field.Son;

import java.util.HashMap;
import java.util.List;

/**
 * Created by huan on 2018/4/22.
 */

public class Car {

    private String mBand;

    private Color mColor;

    public enum Color {
        RED,
        WHITE,
        BLACK,
        BLUE,
        YELLOW
    }

    public Car() {
    }

    public Car(String mBand) {
        this.mBand = mBand;
    }

    public void drive() {
        System.out.println("di di di ,开车了");
    }

    @Override
    public String toString() {
        return "Car{" +
                "mBand='" + mBand + '\'' +
                ", mColor=" + mColor +
                '}';
    }

    public void test(String[] paraa, List<String> b, HashMap<Integer, Son> maps) {
    }

}
