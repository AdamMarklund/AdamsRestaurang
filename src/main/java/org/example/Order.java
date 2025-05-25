package org.example;

import java.util.ArrayList;

public class Order {

    private ArrayList<MenuItem> order;
    private Table table;

    public Order(ArrayList<MenuItem> order, Table table) {
        this.order = order;
        this.table = table;
    }

    public ArrayList<MenuItem> getOrder() {
        return order;
    }

    public Table getTable() {
        return table;
    }

    public void addItem(MenuItem item) {
        this.order.add(item);
    }



    public boolean isOrderReady() {
        for (MenuItem item : order) {
            if (!item.getIsFoodCooked()) {
                return false;
            }
        }
        return true;
    }






}
