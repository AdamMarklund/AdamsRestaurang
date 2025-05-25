package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HeadChef {
    private int x;
    private int y;
    private int diameter = 65;

    private Waiter waiter;
    private ArrayList<Order> ordersPreparing = new ArrayList<>();

    HeadChef(int x, int y, Waiter waiter, GardeManger gardeManger, SousChef sousChef, Patissier patissier) {
        this.x = x;
        this.y = y;
        this.waiter = waiter;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getDiameter() { return diameter; }

    public void checkForWaiter() {
        // return if queue is empty (No more orders)
        if (waiter.queue.isEmpty()) {
            return;
        }

        // receive order when the waiter arrives to the kitchen
        if (waiter.isAtKitchen()){
           addOrder();
            //System.out.println("atkitchen");

        }
    }

    public void addOrder() {
        // The tables order
        //this.orderList.add(waiter.queue.get(0).getOrder());
        //if (!this.orderList.isEmpty()) {
            for (MenuItem item: waiter.queue.get(0).getOrder().getOrderItems()) {
                item.getAssignedChef().addDish(item);
            }
            ordersPreparing.add(waiter.queue.get(0).getOrder());
        waiter.queue.remove(0);
        //}

    }


    public void update() {

        checkForWaiter();
        for (Order order: ordersPreparing) {
            System.out.println(order.isOrderReady());

        }

    }
}
