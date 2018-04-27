package com.liuh.reflectionlearn.constructor;

/**
 * Date: 2018/4/27 14:17
 * Description:
 */

public class ConstructorTestModel {

    private String self;

    public ConstructorTestModel() {
        this.self = "小红 xiao hong";
    }

    public ConstructorTestModel(String self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "ConstructorTestModel{" +
                "self='" + self + '\'' +
                '}';
    }
}
