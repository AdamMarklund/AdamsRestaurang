package org.example;

import java.util.ArrayList;

/**
 * Represents an order placed by a table in the restaurant.
 */
public class Order {

    private ArrayList<MenuItem> orderItems; // The list of menu items in the order
    private Table table; // The table that placed the order
    private boolean isNotified = false; // Whether the order is notified to the waiter

    /**
     * Constructs an Order with a list of menu items and the table that placed the order.
     *
     * @param orderItems the list of menu items in the order
     * @param table the table that placed the order
     */
    public Order(ArrayList<MenuItem> orderItems, Table table) {
        this.orderItems = orderItems;
        this.table = table;
    }

    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }

    public Table getTable() {
        return table;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean notified) {
        isNotified = notified;
    }

    /**
     * Adds a menu item to the order.
     *
     * @param item the menu item to add
     */
    public void addItem(MenuItem item) {
        this.orderItems.add(item);
    }

    /**
     * Checks if the entire order is ready (all dishes are cooked).
     *
     * @return true if all dishes are cooked, false otherwise
     */
    public boolean isOrderReady() {
        for (MenuItem item : orderItems) {
            if (!item.getIsFoodCooked()) {
                return false;
            }
        }
        return true;
    }
}
