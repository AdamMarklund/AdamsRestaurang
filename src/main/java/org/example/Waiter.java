package org.example;

import java.util.ArrayList;

public class Waiter implements Listener {
    private int x;
    private int y;
    private int diameter = 50;
    private int speed = 5;


    ArrayList<Task> queue= new ArrayList<Task>();


    Waiter(int x, int y) {
        this.x = x;
        this.y = y;

        queue.add((new Task(2, Task.Instruction.MENU)));
        queue.add((new Task(1, Task.Instruction.MENU)));
        queue.add((new Task(6, Task.Instruction.MENU)));
        queue.add((new Task(3, Task.Instruction.MENU)));
        queue.add((new Task(4, Task.Instruction.MENU)));
        queue.add((new Task(5, Task.Instruction.MENU)));



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
        executeTasks();
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

       // this.orderingQueue.add(tableNumber);
    }


    // Turn into listener as well? And change into movetotable()

    // make into one queue with objects.
    public void executeTasks() {
        if (queue.isEmpty()) return;

        int tableNumber = queue.get(0).getTableNumber();
        int currentTablePosX = calculateTablePosX(tableNumber);
        int currentTablePosY = calulateTablePosY(tableNumber);

        //int directionX = this.x + this.getDiameter()/2 < ternary operator

        // instead of long lines such as this.y + this.getDiameter()/2 > 320 && this.x
        // use direction and set it at the beginning.

        // if the waiter is beneath the center of the screen and the waiter has not arrived at the tables x position
        if (this.y + this.getDiameter()/2 > 320 && this.x + this.getDiameter()/2 != currentTablePosX + 45) {
                this.y -= speed;
            System.out.println(1);
        }
        // if the waiter is Above the center of the screen and the waiter has not arrived at the tables x position
        else if (this.y + this.getDiameter()/2 < 320 && this.x + this.getDiameter()/2 != currentTablePosX + 45 ) {
            this.y += speed;
            System.out.println(this.y + this.getDiameter()/2);
        }
        // Then it should move to its x position
        else if (this.x + this.getDiameter()/2 < currentTablePosX + 45) {
            this.x += speed; // Move right
        }
        else if (this.x + this.getDiameter()/2 > currentTablePosX + 45) {
            this.x -= speed; // Move left
        }
        // Go to tables y position
        else if (this.x + this.getDiameter()/2 == currentTablePosX + 45 && this.y  > currentTablePosY + 90) {
            this.y -= speed;
            System.out.println("please go up");
        }
        else if (this.x + this.getDiameter()/2 == currentTablePosX + 45 && this.y + this.getDiameter() < currentTablePosY) {
            this.y += speed;
        }
        else {
            queue.remove(0);
        }





/*
        // If the table is beneath the waiter
        if (this.y + this.getDiameter() < currentTablePosY) {
            this.y += speed; // Move down
         }
        // if the table is above the waiter
        else if (this.y > currentTablePosY + 90) {
            this.y -= speed; // Move up

        } else {
            // object.run
            queue.remove(0);

        }

 */
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


