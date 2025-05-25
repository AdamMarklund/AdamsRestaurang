package org.example;

public class HeadWaiter implements HeadWaiterListener{
    private int x;
    private int y;
    private int diameter = 50;

    HeadWaiter(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public void receiveNotification() {

    }

    public void update() {

    }
}

