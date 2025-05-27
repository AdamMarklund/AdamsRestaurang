package org.example;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a table in the restaurant.
 * Manages table properties like position, seating, menu visibility, orders,
 * and notifies waiters of tasks related to the table.
 */
public class Table {

    private int x;
    private int y;
    private int diameter;

    private int tableNumber;          // Identifier for the table

    private boolean hasMenus = false;     // Indicates if menus have been handed out
    private boolean visibleMenus = false; // Indicates if menus are currently visible

    private Menu menu;                // The menu available for this table
    private boolean hasOrdered = false;    // Indicates if the table has placed an order
    private Order order = new Order(new ArrayList<MenuItem>(), this); // Current order for this table

    private int elapsedTime = 0;      // Time elapsed since last table action (in ms)

    ArrayList<TableListener> listeningWaiters = new ArrayList<TableListener>();  // Waiters listening for table events
    HeadWaiterListener listeningHeadWaiter;    // Head waiter listener for the table (should probabl be used instead of listeningWaiters in one case)

    private boolean isFree = true;   // Indicates if the table is free (no guests)

    /**
     * Constructs a Table at given position with a table number and menu.
     *
     * @param x           X position
     * @param y           Y position
     * @param tableNumber Identifier for the table
     * @param menu        Menu associated with the table
     */
    Table(int x, int y, int tableNumber, Menu menu) {
        this.x = x;
        this.y = y;
        this.diameter = 90;
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

    public void resetElapsedTime() {
        elapsedTime = 0;
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

    public boolean isFree() {
        return this.isFree;
    }

    public void setisFree(boolean isFree) {
        this.isFree = isFree;
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


    /**
     * Updates the state of the table on each simulation tick.
     * Handles logic for handing out menus and taking orders based on elapsed time.
     */
    public void update() {
        if (!isFree)
            elapsedTime += 33;

        // If table is occupied, hasn't got menus, and 2 seconds elapsed, hand out menus
        if (!this.hasMenus && elapsedTime > 2000 && !isFree) {
            notifyListeners(new MenuInstruction(this));
        }
        // If menus are visible, table hasn't ordered, and 2 seconds elapsed, take order
        else if (!hasOrdered && elapsedTime > 2000 && !isFree && hasMenusVisible()) {
            notifyListeners(new TakeOrdersInstruction(this));
            hasOrdered = true;
        }

        // Additional order timing logic can be added here
    }

    /**
     * Places an order by adding some cloned MenuItems to the order.
     * Currentl adds Pizza, Patatas Bravas, and Banana Split. Would be randomized if I continued working on this project.
     */
    public void placeOrder() {
        MenuItem item = menu.getItemByDishName("Pizza");
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
