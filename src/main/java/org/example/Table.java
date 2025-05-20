package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Table {

    // variables regarding position
    private int x;
    private int y;
    private int diameter;

    private int seats;
    private int tableNumber;

    // Variables regarding the menus

    private boolean hasMenus = false;
    private boolean visibleMenus = false;

    // Orders
    private Menu menu;
    private boolean hasOrdered = false;
    private ArrayList<MenuItem> order = new ArrayList<MenuItem>();

    // Time since last course of action
    private int elapsedTime = 0;

    static ArrayList<WaiterListener> listeningWaiters = new ArrayList<WaiterListener>();


    Table(int x, int y, int tableNumber, Menu menu) {
        this.x = x;
        this.y = y;
        this.diameter = 90;
        this.seats = 6;
        this.tableNumber = tableNumber;
        this.menu = menu;


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

    public boolean hasMenusVisible() {
        return visibleMenus;
    }

    public void setMenusVisible(boolean visibleMenus) {
        this.visibleMenus = visibleMenus;

    }
    public void addListeningWaiter(WaiterListener waiter) {
        listeningWaiters.add(waiter);
    }



    public void notifyListeners(Task instruction) {
        for (WaiterListener listeningWaiter : listeningWaiters) {
            listeningWaiter.receiveNotification(instruction);
            hasMenus = true;

        }
        // send in tagblnumber
    }

    public void update() {
        elapsedTime += 33;

        // What happens when a table hasn't ordered for 5 seconds
        if (!hasMenus && elapsedTime > 2000) {
            System.out.println("hi");
            notifyListeners(new MenuInstruction(this));
            elapsedTime = 0;
        }
        // the tables want to order

        else if (!hasOrdered && elapsedTime > 2000) {
            System.out.println(hasOrdered);
            notifyListeners(new TakeOrdersInstruction(this));
            hasOrdered = true;

        }



        // They should order after and additional 5 seconds
    }

    public void placeOrder() {
        MenuItem item = menu.getItemByDishName("Pizza");
        order.add(item);
        hasOrdered = true;
    }

    public ArrayList<MenuItem> getOrder() {
        return order;
    }
}
