package com.liuh.reflectionlearn.field;

/**
 * Created by huan on 2018/4/22.
 */

public class Son extends Farther {

    int c;

    private String d;

    protected float e;

    private Son() {
        super();
    }

    public Son(int c, String d) {
        super();
        this.c = c;
        this.d = d;
    }
}
