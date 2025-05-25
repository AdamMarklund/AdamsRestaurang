package org.example;

import java.util.ArrayList;

public class Order {

    private ArrayList<MenuItem> orderItems;
    private Table table;

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
