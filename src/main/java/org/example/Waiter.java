package org.example;

import java.util.ArrayList;

public class Waiter implements Listener {
    private int x;
    private int y;
    private int diameter = 50;
    private int speed = 5;


    ArrayList<Integer> orderingQueue= new ArrayList<Integer>();
    ArrayList<Integer> menuQueue= new ArrayList<Integer>();


    Waiter(int x, int y) {
        this.x = x;
        this.y = y;

        menuQueue.add(2);
        menuQueue.add(1);
        menuQueue.add(6);
        menuQueue.add(3);
        menuQueue.add(4);
        menuQueue.add(5);



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


    // Moves Waiter, isAtTable, whenAtTable
    public void update() {
        handOutMenus();
    }


    public void move() {
        System.out.println(this.x - getDiameter()/2);
        if (this.x - this.getDiameter()/2 < 570) {
            this.x += speed;
        }
        else if (this.x - this.getDiameter()/2 >= 570) {

        }

    }


    // The waiter is a subscriber to the publisher Table, getOrder = notifyListeners
    @Override
    public void getOrders(int tableNumber) {
        this.orderingQueue.add(tableNumber);
    }


    // Turn into listener as well? And change into movetotable()

    // make into one queue with objects.
    public void handOutMenus() {
        if (menuQueue.isEmpty()) return;

        int tableNumber = menuQueue.get(0);
        int currentTablePosX = calculateTablePosX(tableNumber);
        int currentTablePosY = calulateTablePosY(tableNumber);

        System.out.println(currentTablePosX);



        if (this.x + this.getDiameter()/2 < currentTablePosX + 45) {
            this.x += 5; // Move right
        }
        else if (this.x + this.getDiameter() / 2 > currentTablePosX + 45) {
            this.x -= 5; // Move left
        }

        // If the table is beneath the waiter
        else if (this.y + this.getDiameter() < currentTablePosY) {
            this.y += speed; // Move down
         }
        // if the table is above the waiter
        else if (this.y > currentTablePosY + 90) {
            this.y -= speed; // Move up

        } else {
            // object.run
            menuQueue.remove(0);

        }
    }

    // Calculates the x position of a table based on its tablenumber
    public int calculateTablePosX(int tableNumber) {
        if (tableNumber < 4) {
            return 580 + 170 * (tableNumber-1);
        }
        else { return calculateTablePosX(tableNumber-3);
        }
    }

    // Decides the x position of a table based on its tablenumber
    public int calulateTablePosY(int tableNumber) {

        // Take the diameter into account
        if (tableNumber < 4)
            return 100;
        else
            return 450;
    }

}


