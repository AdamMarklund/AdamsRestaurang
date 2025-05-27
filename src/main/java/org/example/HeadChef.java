package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents the Head Chef who manages orders, coordinates with the Waiter, and notifies listeners when food is ready.
 */
public class HeadChef {

    private int x;
    private int y;
    private int diameter = 65;

    private Waiter waiter; // Reference to the Waiter to interact with
    private ArrayList<Order> ordersPreparing = new ArrayList<>(); // List of orders currently being prepared

    ArrayList<TableListener> listeningWaiters = new ArrayList<TableListener>(); // List of Waiters (listeners) subscribed to notifications

    /**
     * Constructs the Head Chef with position, a Waiter reference, and other chef roles.
     *
     * @param x the x-coordinate of the Head Chef
     * @param y the y-coordinate of the Head Chef
     * @param waiter the Waiter that interacts with the Head Chef
     */
    HeadChef(int x, int y, Waiter waiter) {
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

    /**
     * Checks if the waiter has arrived at the kitchen and processes orders accordingly.
     * <p>
     * If the waiter's queue is empty, no action is taken.
     * If the waiter is at the kitchen, orders are added and food handed over.
     */
    public void checkForWaiter() {
        if (waiter.queue.isEmpty()) {
            return;
        }

        if (waiter.isAtKitchen()) {
            addOrder();
            handOverFood();
        }
    }

    /**
     * Adds the current order from the waiter's queue to the chefs' task lists if it is not ready.
     */
    public void addOrder() {
        if (waiter.queue.size() > 0 && !waiter.queue.get(0).getOrder().isOrderReady()) {
            for (MenuItem item: waiter.queue.get(0).getOrder().getOrderItems()) {
                item.getAssignedChef().addDish(item);
            }
            ordersPreparing.add(waiter.queue.get(0).getOrder());
            waiter.queue.remove(0);
        }
    }

    /**
     * Hands over food to the waiter when an order is ready.
     * <p>
     * Removes the order from the preparing list and updates the waiter's instructions.
     */
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

    /**
     * Notifies all listening waiters that orders are ready to be served.
     */
    public void notifyListeners() {
        for (TableListener listeningWaiter : listeningWaiters) {
            for (Order order: ordersPreparing) {
                if (order.isOrderReady() && !order.isNotified()) {
                    listeningWaiter.receiveNotification(new ServeFoodInstruction(order));
                    order.setNotified(true);
                }
            }
        }
    }

    /**
     * Updates the HeadChef by checking for waiters, managing orders, and notifying listeners.
     */
    public void update() {
        checkForWaiter();
        notifyListeners();
    }
}
