package org.example;

import java.awt.*;

public class Chef {
    private int x;
    private int y;
    protected Color color;
    protected int diameter;

    public Chef(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getDiameter() {
        return diameter;
    }

}
