package org.example;

import java.awt.event.WindowStateListener;

public abstract class Task {



    private int tableNumber;

    // tableobjektet istället för tablenumber
    Task(int tableNumber) {
        this.tableNumber = tableNumber;

    }

    int getTableNumber() {
        return tableNumber;
    }

    abstract void executeTask();
}