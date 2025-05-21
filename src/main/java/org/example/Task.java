package org.example;

import java.awt.event.WindowStateListener;
import java.util.ArrayList;

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

    public ArrayList<MenuItem> getOrder() {
        return table.getOrder();
    }

    abstract void executeTask();

    boolean forceGoToKitchen() {
        return false;
    }
}