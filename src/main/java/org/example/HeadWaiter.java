package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;



public class HeadWaiter {
    private int x;
    private int y;
    private int diameter = 50;
    private ArrayList<Table> tables = new ArrayList<>();
    private Color color = Color.ORANGE;
    private ArrayList<ArrayList<Guest>> guestGroups = new ArrayList<>();
    private ArrayList<ArrayList<Guest>> seatedGroups = new ArrayList<>();
    private ArrayList<Table> assignedTables = new ArrayList<>();
    private Table availableTable;

    private Random rand = new Random();
    private int guestSpacing = 30; // Space between each guest
    private int groupSpacing = 5; // Space between groups
    private int startingY = 310; // Y-position for the first guest drawn

    HeadWaiter(int x, int y) {
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

    public Color getColor() {
        return color;
    }

    public void addTable(Table table) {
        this.tables.add(table);
    }

    public ArrayList<ArrayList<Guest>> getGuestGroups() {
        return guestGroups;
    }

    public ArrayList<ArrayList<Guest>> getSeatedGroups() {
        return seatedGroups;
    }

    public void generateGuestGroups() {
        if (guestGroups.size() < 6) {
            ArrayList<Guest> group = new ArrayList<>();
            int groupSize = rand.nextInt(3) + 2; // 2-4 people per group

            // Calculate starting Y position for this group
            int currentY = calculateNextGroupStartY();

            for (int i = 0; i < groupSize; i++) {
                group.add(new Guest(1120, currentY + (i * guestSpacing)));
            }

            guestGroups.add(group);
            System.out.println("Group " + guestGroups.size() + " created with " + groupSize + " guests");
        }
    }

    // Calculate where the next group should start based on existing groups
    private int calculateNextGroupStartY() {
        if (guestGroups.isEmpty()) {
            return startingY;
        }

        // Find the lowest Y position of the last group
        ArrayList<Guest> lastGroup = guestGroups.get(guestGroups.size() - 1);
        int lowestY = startingY;

        for (Guest guest : lastGroup) {
            if (guest.getY() > lowestY) {
                lowestY = guest.getY();
            }
        }

        // Return the next available position (lowest Y + guest diameter + group spacing)
        return lowestY + diameter + groupSpacing;
    }

    public void moveGuestsToTable() {
        if (!guestGroups.isEmpty()){
            availableTable = findAvailableTable();

            // if there exists a free table or more
            if (availableTable != null) {

                // First group in line gets the first table to seat at
                ArrayList<Guest> groupToSeat = guestGroups.get(0);

                for (int i = 0; i < groupToSeat.size(); i++) {
                    Guest currentGuest = groupToSeat.get(i);

                    moveToTargetTable(currentGuest, availableTable);
                }
            }
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
    private void moveToTargetTable(Guest currentGuest, Table targetTable) {
        int tableNumber = targetTable.getTableNumber();
        int currentTablePosX = calculateTablePosX(tableNumber);
        int currentTablePosY = calulateTablePosY(tableNumber);


        // if the waiter is beneath the center of the screen and the waiter has not arrived at the tables x position
        if (currentGuest.getY() + this.getDiameter()/2 > 320 && currentGuest.getX() + this.getDiameter()/2 != currentTablePosX + 45) {
            currentGuest.moveY(-currentGuest.getSpeed());


        }
        // if the waiter is Above the center of the screen and the waiter has not arrived at the tables x position
        else if (currentGuest.getY() + this.getDiameter()/2 < 320 && currentGuest.getX() + this.getDiameter()/2 != currentTablePosX + 45 ) {
            currentGuest.moveY(currentGuest.getSpeed());

        }
        // Then it should move to its x position
        else if (currentGuest.getX() + this.getDiameter()/2 < currentTablePosX + 45) {
            currentGuest.moveX(currentGuest.getSpeed()); // Move right
        }
        else if (currentGuest.getX() + this.getDiameter()/2 > currentTablePosX + 45) {
            currentGuest.moveX(-currentGuest.getSpeed()); // Move left
        }
        // Go to tables y position
        else if (currentGuest.getX() + this.getDiameter()/2 == currentTablePosX + 45 && currentGuest.getY() > currentTablePosY + 90) {
            currentGuest.moveY(-currentGuest.getSpeed());
        }
        else if (currentGuest.getX() + this.getDiameter()/2 == currentTablePosX + 45 && currentGuest.getY() + this.getDiameter() < currentTablePosY) {
            currentGuest.moveY(currentGuest.getSpeed());
        }
        else {
            System.out.println("Arrived");
            seatedGroups.add(guestGroups.get(0));
            guestGroups.remove(0);
            availableTable.setisFree(false);

        }
    }

    private Table findAvailableTable() {
        for (Table table : tables) {
            if (table.isFree()) {
                return table;
            }
        }
        return null; // No available tables
    }



    public void update() {
        generateGuestGroups();
        moveGuestsToTable();
    }
}
