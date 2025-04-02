package org.example;

import java.util.ArrayList;

public class Table {
    private int x;
    private int y;
    private int diameter;
    private int seats;
    private int tableNumber;
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

    public void notifyWaiters(){

        // send in tagblnumber
    }






}
