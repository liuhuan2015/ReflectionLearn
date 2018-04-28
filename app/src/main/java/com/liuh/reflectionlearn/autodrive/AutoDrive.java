package com.liuh.reflectionlearn.autodrive;

/**
 * Created by huan on 2018/4/28.
 * 用代码模拟汽车开动的步骤
 */

public class AutoDrive {

    public enum Color {
        WHITE,
        RED,
        BLUE
    }

    private String vendor;

    private Color color;

    public AutoDrive(String vendor, Color color) {
        this.vendor = vendor;
        this.color = color;
    }

    public AutoDrive() {
        vendor = "Hongqi";
        color = Color.BLUE;
    }

    public void drive() {

        boot();

        turnOnLeftLight();

        cailiheguayidang();

        songshousha();

        tips();
    }

    private void boot() {
        System.out.println("空挡发动汽车");
    }

    private void turnOnLeftLight() {
        System.out.println("打左向灯");
    }

    private void cailiheguayidang() {
        System.out.println("踩离合，挂一档");
    }

    private void songshousha() {
        System.out.println("松手刹");
    }

    private void tips() {
        System.out.println("您正在驾驶 " + color + " " + vendor + " 汽车，请小心驾驶。");
    }


}
