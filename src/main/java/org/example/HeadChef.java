package org.example;

import java.util.ArrayList;

public class HeadChef {
    private int x;
    private int y;
    private int diameter = 65;

    private Waiter waiter;
    private ArrayList<MenuItem> order = new ArrayList<>();

    HeadChef(int x, int y, Waiter waiter) {
        this.x = x;
        this.y = y;
        this.waiter = waiter;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getDiameter() { return diameter; }

    public void checkForWaiter() {
        if (waiter.queue.isEmpty()) {
            return; 
        }
        if (waiter.isAtKitchen()){
            order = waiter.queue.get(0).getOrder();
        }
    }

    public void update() {
        checkForWaiter();
    }
}
