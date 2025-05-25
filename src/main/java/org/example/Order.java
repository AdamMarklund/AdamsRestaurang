package org.example;

import java.util.ArrayList;

public class Order {

    private ArrayList<MenuItem> orderItems;
    private Table table;
    private boolean isNotified = false;

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

    public void addItem(MenuItem item) {
        this.orderItems.add(item);
    }



    public boolean isOrderReady() {
        for (MenuItem item : orderItems) {
            if (!item.getIsFoodCooked()) {
                return false;
            }
        }
        return true;
    }






}
