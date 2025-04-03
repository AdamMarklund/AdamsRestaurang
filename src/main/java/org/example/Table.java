package org.example;

import java.util.ArrayList;
import java.util.List;

public class Table implements Listener {
    private int x;
    private int y;
    private int diameter;
    private int seats;
    private int tableNumber;
    private boolean hasOrdered = false;

    private int elapsedTime = 0;

    static ArrayList<Waiter> listeningWaiters = new ArrayList<Waiter>();


    Table(int x, int y, int tableNumber) {
        this.x = x;
        this.y = y;
        this.diameter = 90;
        this.seats = 6;
        this.tableNumber = tableNumber;

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

    public int getTableNumber() { return tableNumber;}

    public void addListeningWaiter(Waiter waiter) {
        listeningWaiters.add(waiter);
    }

    @Override
    public void notifyWaiters(){
        elapsedTime += 0.33;
        if (!hasOrdered && elapsedTime > 5) {
            for (Waiter listeingWaiter: listeningWaiters) {
               // listeningWaiters.addToQueue(MenuTask(1));

            }
        }
        // send in tagblnumber
    }






}
