package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HeadChef {
    private int x;
    private int y;
    private int diameter = 65;

    private Waiter waiter;
    private ArrayList<Order> ordersPreparing = new ArrayList<>();

    // Waiters listening to the HeadChef
    ArrayList<TableListener> listeningWaiters = new ArrayList<TableListener>();

    HeadChef(int x, int y, Waiter waiter, GardeManger gardeManger, SousChef sousChef, Patissier patissier) {
        this.x = x;
        this.y = y;
        this.waiter = waiter;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getDiameter() { return diameter; }

    public ArrayList<Order> getOrdersPreparing() {
        return ordersPreparing;
    }

    public void checkForWaiter() {
        // return if queue is empty (No more orders)
        if (waiter.queue.isEmpty()) {
            return;
        }

        // receive order when the waiter arrives to the kitchen
        if (waiter.isAtKitchen()){
           addOrder();
           handOverFood();

            //System.out.println("atkitchen");

        }
    }

    public void addOrder() {
        // The tables order
        //this.orderList.add(waiter.queue.get(0).getOrder());
        //if (!this.orderList.isEmpty()) {
        if (waiter.queue.size() > 0 && !waiter.queue.get(0).getOrder().isOrderReady()) {
            for (MenuItem item: waiter.queue.get(0).getOrder().getOrderItems()) {
                item.getAssignedChef().addDish(item);
            }
            ordersPreparing.add(waiter.queue.get(0).getOrder());
        waiter.queue.remove(0);
        }

    }

    public void handOverFood() {
        for (Order order: ordersPreparing) {
            if (order.isOrderReady()) {
               if (waiter.queue.get(0).getOrder() == order) {
                   waiter.queue.get(0).setForceGoToKitchen(false);
                   ordersPreparing.remove(order);
                   break;
               }
            }
        }
    }

    public void addListeningWaiter(TableListener waiter) {
        listeningWaiters.add(waiter);
    }
    public void notifyListeners() {
        for (TableListener listeningWaiter : listeningWaiters) { // Loop needs to be remade if I add more waiters.
            for (Order order: ordersPreparing) {
                if (order.isOrderReady() && !order.isNotified()) {
                    listeningWaiter.receiveNotification(new ServeFoodInstruction(order));
                    order.setNotified(true);
                }
            }

        }

    }


    public void update() {

        checkForWaiter();
        for (Order order: ordersPreparing) {
           // System.out.println(order.isOrderReady());

        }

        notifyListeners();
    }
}
