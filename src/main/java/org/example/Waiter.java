/**
 * Represents a waiter that moves between tables and the kitchen to handle tasks.
 * <p>
 * The waiter listens for task notifications and adds them to a queue. It moves
 * toward the assigned location (table or kitchen) and executes tasks when it arrives.
 */
package org.example;

import java.util.ArrayList;

public class Waiter implements TableListener, HeadChefListener {

    private int x; // X-coordinate of the waiter
    private int y; // Y-coordinate of the waiter
    private int diameter = 50; // Diameter for display purposes
    private int speed = 10; // Speed of movement

    ArrayList<Task> queue = new ArrayList<>(); // Queue of tasks
    private boolean isAtKitchen; // Flag indicating if the waiter is at the kitchen

    /**
     * Creates a waiter at the given coordinates.
     *
     * @param x initial X position
     * @param y initial Y position
     */
    Waiter(int x, int y) {
        this.x = x;
        this.y = y;
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

    /**
     * Updates the waiter’s behavior.
     * <p>
     * Calls the work method to process the next task.
     */
    public void update() {
        work();
    }

    /**
     * Moves the waiter to the current table in the queue.
     * <p>
     * Adjusts the X and Y positions step-by-step until reaching the table.
     * Executes the task when the waiter arrives.
     */
    public void moveToTable() {
        isAtKitchen = false;
        int tableNumber = queue.get(0).getTableNumber();
        int currentTablePosX = calculateTablePosX(tableNumber);
        int currentTablePosY = calulateTablePosY(tableNumber);

        if (this.y + this.getDiameter() / 2 > 320 && this.x + this.getDiameter() / 2 != currentTablePosX + 45) {
            this.y -= speed;
        } else if (this.y + this.getDiameter() / 2 < 320 && this.x + this.getDiameter() / 2 != currentTablePosX + 45) {
            this.y += speed;
        } else if (this.x + this.getDiameter() / 2 < currentTablePosX + 45) {
            this.x += speed;
        } else if (this.x + this.getDiameter() / 2 > currentTablePosX + 45) {
            this.x -= speed;
        } else if (this.x + this.getDiameter() / 2 == currentTablePosX + 45 && this.y > currentTablePosY + 90) {
            this.y -= speed;
        } else if (this.x + this.getDiameter() / 2 == currentTablePosX + 45 && this.y + this.getDiameter() < currentTablePosY) {
            this.y += speed;
        } else {
            queue.get(0).executeTask();
            if (!queue.get(0).forceGoToKitchen())
                queue.remove(0);
        }
    }

    public boolean isAtKitchen() {
        return isAtKitchen;
    }

    /**
     * Moves the waiter to the kitchen at fixed coordinates.
     * <p>
     * Adjusts the X and Y positions step-by-step until reaching the kitchen.
     * Sets isAtKitchen to true when arrived.
     */
    public void moveToKitchen() {
        int targetX = 500;
        int targetY = 320;

        if (this.y + this.getDiameter() / 2 > targetY) {
            this.y -= speed;
        } else if (this.y + this.getDiameter() / 2 < targetY) {
            this.y += speed;
        } else if (this.x + this.getDiameter() / 2 > targetX) {
            this.x -= speed;
        } else {
            isAtKitchen = true;
        }
    }

    /**
     * Processes the next task in the queue.
     * <p>
     * If the task requires going to the kitchen, calls moveToKitchen.
     * Otherwise, calls moveToTable.
     */
    public void work() {
        if (queue.isEmpty()) return;

        if (queue.get(0).forceGoToKitchen()) {
            moveToKitchen();
        } else {
            moveToTable();
        }
    }

    /**
     * Calculates the X position of a table based on its table number.
     *
     * @param tableNumber the table number (1-6)
     * @return X position of the table
     */
    public int calculateTablePosX(int tableNumber) {
        if (tableNumber < 4) {
            return 580 + 170 * (tableNumber - 1);
        } else {
            return calculateTablePosX(tableNumber - 3);
        }
    }

    /**
     * Calculates the Y position of a table based on its table number.
     *
     * @param tableNumber the table number (1-6)
     * @return Y position of the table
     */
    public int calulateTablePosY(int tableNumber) {
        return (tableNumber < 4) ? 100 : 450;
    }

    /**
     * Receives a task notification and adds it to the queue.
     *
     * @param instruction the task to be added
     */
    @Override
    public void receiveNotification(Task instruction) {
        queue.add(instruction);
    }
}
