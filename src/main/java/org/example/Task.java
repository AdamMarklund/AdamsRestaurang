package org.example;

import java.awt.event.WindowStateListener;

public abstract class Task {
    protected Table table;



    // tableobjektet istället för tablenumber
    Task(Table table) {
        this.table = table;

    }

    int getTableNumber() {
        return table.getTableNumber();
    }

    Table getTable() {
        return table;
    }

    abstract void executeTask();

    boolean forceGoToKitchen() {
        return false;
    }
}