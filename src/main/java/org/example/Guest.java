package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a guest in the restaurant with random color and simple movement behavior.
 */
public class Guest {

    private int x; // The x-position of the guest
    private int y; // The y-position of the guest
    private int diameter = 20; // The size of the guest
    private Color randomColor; // The randomly generated color of the guest
    private Random rand = new Random(); // Random generator for the color
    private int speed = 5; // The movement speed of the guest

    /**
     * Constructs a guest at the specified position with a random color.
     *
     * @param x the x-coordinate of the guest
     * @param y the y-coordinate of the guest
     */
    Guest(int x, int y) {
        this.x = x;
        this.y = y;
        randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)); // Assign a random color
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
