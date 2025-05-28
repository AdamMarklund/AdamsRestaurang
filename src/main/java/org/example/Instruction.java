package org.example;

/**
 * Represents an abstract task that a waiter can perform at a table.
 * <p>
 * Each task is associated with a table. Subclasses implement specific tasks.
 */
public abstract class Instruction {
    protected Table table;

    /**
     * Creates an instruction for a specific table.
     *
     * @param table the table associated with the task
     */
    Instruction(Table table) {
        this.table = table;
    }

    int getTableNumber() {
        return table.getTableNumber();
    }

    Table getTable() {
        return table;
    }

    /**
     * Gets the order associated with the table.
     *
     * @return the order object
     */
    public Order getOrder() {
        return table.getOrder();
    }

    /**
     * Executes the task. Must be implemented by subclasses.
     */
    abstract void executeTask();

    /**
     * Checks if the task requires the waiter to go to the kitchen.
     * <p>
     * Default is false. Subclasses override this behavior.
     *
     * @return true if the task requires going to the kitchen, false otherwise
     */
    boolean forceGoToKitchen() {
        return false;
    }

    abstract String getTaskString();

    abstract void setForceGoToKitchen(boolean forceGoToKitchen);
}
