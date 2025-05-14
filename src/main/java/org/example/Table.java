package org.example;

import java.util.ArrayList;

public class Table {
    private int x;
    private int y;
    private int diameter;
    private int seats;
    private int tableNumber;
    private boolean hasOrdered = false;
    private boolean hasMenus = false;
    private boolean visibleMenus = false;

    private int elapsedTime = 0;

    static ArrayList<WaiterListener> listeningWaiters = new ArrayList<WaiterListener>();


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

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean areMenusVisible() {
        return visibleMenus;
    }

    public void addListeningWaiter(WaiterListener waiter) {
        listeningWaiters.add(waiter);
    }



    public void notifyListeners() {
        for (WaiterListener listeningWaiter : listeningWaiters) {
            listeningWaiter.receiveNotification(new MenuInstruction(this.tableNumber));
            hasMenus = true;
            visibleMenus = true;


        }
        // send in tagblnumber
    }

    public void update() {
        elapsedTime += 33;
        // What happens when a table hasn't ordered for 5 seconds
        if (!hasMenus && elapsedTime > 5000) {
            System.out.println("hi");
            notifyListeners();
        }

        // They should order after and additional 5 seconds
    }

}
