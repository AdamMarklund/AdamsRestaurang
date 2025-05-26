package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Guest {
    private int x;
    private int y;
    private int diameter = 20;
    private Color randomColor;
    private Random rand = new Random();
    private int speed = 5;


    Guest(int x, int y) {
        this.x = x;
        this.y = y;
        randomColor = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));



    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void moveX(int x) {
        this.x += x;
    }

    public void moveY(int y) {
        this.y += y;
    }

    public int getDiameter() {
        return diameter;
    }

    public Color getRandomColor() {
        return randomColor;
    }

}
