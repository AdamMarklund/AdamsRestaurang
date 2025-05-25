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
    private Order order = new Order(new ArrayList<MenuItem>(),this);

    // Time since last course of action
    private int elapsedTime = 0;

    ArrayList<TableListener> listeningWaiters = new ArrayList<TableListener>();
    HeadWaiterListener listeningHeadWaiter;

    // Guests
    private boolean isFree = false;


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
    public void addListeningWaiter(TableListener waiter) {
        listeningWaiters.add(waiter);
    }



    public void notifyListeners(Task instruction) {
        for (TableListener listeningWaiter : listeningWaiters) {
            listeningWaiter.receiveNotification(instruction);
            hasMenus = true;

        }
    }


    int i = 0;
    public void update() {
        elapsedTime += 33;

        // What happens when a table hasn't ordered for 5 seconds
        if (!hasMenus && elapsedTime > 2000 ) {
            notifyListeners(new MenuInstruction(this));
            elapsedTime = 0;
        }
        // the tables want to order

        else if (!hasOrdered && elapsedTime > 2000) {
            notifyListeners(new TakeOrdersInstruction(this));
            hasOrdered = true;

        }



        // They should order after and additional 5 seconds
    }

    public void placeOrder() {

        // Loop trhough amount of guests and randomize orders.
        MenuItem item = menu.getItemByDishName("Pizza");
        //System.out.println(item);
        MenuItem item2 = menu.getItemByDishName("Patatas Bravas");
        order.addItem((MenuItem)item.clone());
        order.addItem((MenuItem)item2.clone());

        MenuItem item3 = menu.getItemByDishName("Banana Split");
        order.addItem((MenuItem)item3.clone());
        hasOrdered = true;
    }

    public Order getOrder() {
        return order;
    }
}
