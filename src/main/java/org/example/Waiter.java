package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class Waiter implements TableListener, HeadChefListener { // also implement HeadChefListener

    private int x;
    private int y;
    private int diameter = 50;
    private int speed = 10;


    ArrayList<Task> queue= new ArrayList<Task>();
    private boolean isAtKitchen;


    Waiter(int x, int y) {
        this.x = x;
        this.y = y;
        /*

        queue.add((new MenuInstruction(2)));
        queue.add((new TakeOrdersInstruction(1)));
        queue.add((new MenuInstruction(3)));
        queue.add((new MenuInstruction(4)));
        queue.add((new MenuInstruction(5)));
        queue.add((new MenuInstruction(6)));

         */



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
        work();
    }






    // The waiter is a subscriber to the publisher Table, getOrder = notifyListeners



    // Turn into listener as well? And change into movetotable()

    public void moveToTable() {
        isAtKitchen = false;
        int tableNumber = queue.get(0).getTableNumber();
        int currentTablePosX = calculateTablePosX(tableNumber);
        int currentTablePosY = calulateTablePosY(tableNumber);


        // if the waiter is beneath the center of the screen and the waiter has not arrived at the tables x position
        if (this.y + this.getDiameter()/2 > 320 && this.x + this.getDiameter()/2 != currentTablePosX + 45) {
            this.y -= speed;

        }
        // if the waiter is Above the center of the screen and the waiter has not arrived at the tables x position
        else if (this.y + this.getDiameter()/2 < 320 && this.x + this.getDiameter()/2 != currentTablePosX + 45 ) {
            this.y += speed;

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
        }
        else if (this.x + this.getDiameter()/2 == currentTablePosX + 45 && this.y + this.getDiameter() < currentTablePosY) {
            this.y += speed;
        }
        else {
            //tablesWaiter.sort(Comparator.comparingInt(Table::getTableNumber));

            queue.get(0).executeTask();
            // If the instruction is to hand out menus
            if (!queue.get(0).forceGoToKitchen())
                queue.remove(0);

        }
    }

    public boolean isAtKitchen() {
        return isAtKitchen;
    }

    public void moveToKitchen() {

        int targetX = 500;
        int targetY = 320;

        // move to the middle of y-axis
        if (this.y + this.getDiameter()/2 > targetY) {
            this.y -= speed;
        } else if (this.y + this.getDiameter()/2 < targetY) {
            this.y += speed;
        }
        // once in the middle of y-axis
        else if (this.x + this.getDiameter()/2 > targetX) {
            this.x -= speed;
        } else {
            isAtKitchen = true;
            //queue.remove(0);
        }


    }

    // make into one queue with objects.
    public void work() {
        if (queue.isEmpty()) return;






        // use direction and set it at the beginning.
        if (queue.get(0).forceGoToKitchen()) {
            //System.out.println(queue.size());
            moveToKitchen();


        } else
            moveToTable();



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



    @Override
    public void receiveNotification(Task instruction) {
        queue.add(instruction);
    }

}


