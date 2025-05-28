package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the Head Waiter who manages guests (guestgroups) and seating.
 */
public class HeadWaiter {

    private int x;
    private int y;
    private int diameter = 50;

    private Color color = Color.ORANGE;

    private ArrayList<Table> tables = new ArrayList<>(); // List of all tables in the restaurant
    private ArrayList<ArrayList<Guest>> guestGroups = new ArrayList<>(); // Groups of guests waiting to be seated
    private ArrayList<ArrayList<Guest>> seatedGroups = new ArrayList<>(); // Groups of guests already seated
    private Table availableTable; // The next available table to seat guests

    private Random rand = new Random(); // Random generator for group sizes
    private int guestSpacing = 30; // Space between each guest vertically
    private int groupSpacing = 5; // Space between groups vertically
    private int startingY = 310; // Y-position for the first guest drawn

    /**
     * Constructs a Head Waiter at a specific position.
     *
     * @param x the x-coordinate of the Head Waiter
     * @param y the y-coordinate of the Head Waiter
     */
    HeadWaiter(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getDiameter() { return diameter; }

    public Color getColor() { return color; }

    /**
     * Adds a table to the list of tables managed by the Head Waiter.
     *
     * @param table the Table to add
     */
    public void addTable(Table table) {
        this.tables.add(table);
    }

    public ArrayList<ArrayList<Guest>> getGuestGroups() {
        return guestGroups;
    }

    public ArrayList<ArrayList<Guest>> getSeatedGroups() {
        return seatedGroups;
    }

    /**
     * Generates new guest groups up to a maximum of 6 groups.
     * Each group has between 2 and 4 guests.
     */
    public void generateGuestGroups() {
        if (guestGroups.size() < 6) {
            ArrayList<Guest> group = new ArrayList<>();
            int groupSize = rand.nextInt(3) + 2; // 2-4 people per group

            //
            int currentY = calculateNextGroupStartY();

            for (int i = 0; i < groupSize; i++) {
                group.add(new Guest(1120, currentY + (i * guestSpacing)));
            }

            guestGroups.add(group);
        }
    }

    /**
     * Calculates the starting Y-position for the next guest group based on existing groups.
     *
     * @return the Y-position for the new group
     */
    private int calculateNextGroupStartY() {
        if (guestGroups.isEmpty()) {
            return startingY;
        }

        ArrayList<Guest> lastGroup = guestGroups.get(guestGroups.size() - 1);
        int lowestY = startingY;

        for (Guest guest : lastGroup) {
            if (guest.getY() > lowestY) {
                lowestY = guest.getY();
            }
        }

        return lowestY + this.diameter + groupSpacing;
    }

    /**
     * Moves guests from waiting groups to an available table if any is free.
     */
    public void moveGuestsToTable() {
        if (!guestGroups.isEmpty()) {
            availableTable = findAvailableTable();

            if (availableTable != null) {
                ArrayList<Guest> groupToSeat = guestGroups.get(0);

                for (Guest currentGuest : groupToSeat) {
                    moveToTargetTable(currentGuest, availableTable);
                }
            }
        }
    }

    /**
     * Calculates the X-position of a table based on its table number.
     *
     * @param tableNumber the table number
     * @return the X-position of the table
     */
    public int calculateTablePosX(int tableNumber) {
        if (tableNumber < 4) {
            return 580 + 170 * (tableNumber - 1);
        } else {
            return calculateTablePosX(tableNumber - 3);
        }
    }

    /**
     * Calculates the Y-position of a table based on its table number.
     *
     * @param tableNumber the table number
     * @return the Y-position of the table
     */
    public int calulateTablePosY(int tableNumber) {
        if (tableNumber < 4) {
            return 100;
        } else {
            return 450;
        }
    }

    /**
     * Moves a guest toward the target table by adjusting their X and Y coordinates.
     * When the guest reaches the table, updates seating groups and table availability.
     *
     * @param currentGuest the Guest to move
     * @param targetTable  the Table to move the guest to
     */
    private void moveToTargetTable(Guest currentGuest, Table targetTable) {
        int tableNumber = targetTable.getTableNumber();
        int currentTablePosX = calculateTablePosX(tableNumber);
        int currentTablePosY = calulateTablePosY(tableNumber);

        // Same logic as the waiter moveToTable. Look there to find explanation
        if (currentGuest.getY() + this.getDiameter() / 2 > 320 && currentGuest.getX() + this.getDiameter() / 2 != currentTablePosX + 45) {
            currentGuest.moveY(-currentGuest.getSpeed());
        } else if (currentGuest.getY() + this.getDiameter() / 2 < 320 && currentGuest.getX() + this.getDiameter() / 2 != currentTablePosX + 45) {
            currentGuest.moveY(currentGuest.getSpeed());
        } else if (currentGuest.getX() + this.getDiameter() / 2 < currentTablePosX + 45) {
            currentGuest.moveX(currentGuest.getSpeed());
        } else if (currentGuest.getX() + this.getDiameter() / 2 > currentTablePosX + 45) {
            currentGuest.moveX(-currentGuest.getSpeed());
        } else if (currentGuest.getX() + this.getDiameter() / 2 == currentTablePosX + 45 && currentGuest.getY() > currentTablePosY + 90) {
            currentGuest.moveY(-currentGuest.getSpeed());
        } else if (currentGuest.getX() + this.getDiameter() / 2 == currentTablePosX + 45 && currentGuest.getY() + this.getDiameter() < currentTablePosY) {
            currentGuest.moveY(currentGuest.getSpeed());
        } else {
            System.out.println("Arrived");
            seatedGroups.add(guestGroups.get(0));
            guestGroups.remove(0);
            availableTable.setisFree(false);
        }
    }

    /**
     * Finds the first available free table.
     *
     * @return a free Table if any, otherwise null
     */
    private Table findAvailableTable() {
        for (Table table : tables) {
            if (table.isFree()) {
                return table;
            }
        }
        return null;
    }

    /**
     * Updates the Head Waiter state: generates guest groups and moves guests to tables.
     */
    public void update() {
        generateGuestGroups();
        moveGuestsToTable();
    }
}
