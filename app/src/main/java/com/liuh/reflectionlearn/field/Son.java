package com.liuh.reflectionlearn.field;

import com.liuh.reflectionlearn.Car;

import java.util.HashMap;
import java.util.List;

/**
 * Created by huan on 2018/4/22.
 */

public class Son extends Farther {

    int c;

    private String d;

    protected float e;

    public List<Car> cars;

    public HashMap<Integer, String> map;


    private Son() {
        super();
    }

    public Son(int c, String d) {
        super();
        this.c = c;
        this.d = d;
    }
}
