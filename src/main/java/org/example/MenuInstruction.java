package org.example;

/**
 * Represents a task for handing out menus to a table.
 */
public class MenuInstruction extends Instruction {

    /**
     * Constructs a MenuInstruction associated with a specific table.
     *
     * @param table the table to receive the menu
     */
    MenuInstruction(Table table) {
        super(table);
    }

    /**
     * Executes the menu instruction by handing out menus upon the waiter's arrival to the assigned tble.
     */
    @Override
    void executeTask() {
        // Hands out menus
        handOutMenus();
    }

    /**
     * Sets whether the task should force the water to the kitchen.
     * Not needed for MenuInstruction, so left empty.
     *
     * @param forceGoToKitchen whether to force going to the kitchen
     */
    @Override
    void setForceGoToKitchen(boolean forceGoToKitchen) {
    }

    @Override
    public String getTaskString() {
        return "Handing out menus";
    }

    /**
     * Makes menus visible at the table and resets the table's elapsed time in order to track the time for how long they've had the menus.  .
     */
    public void handOutMenus() {
        getTable().setMenusVisible(true);
        getTable().resetElapsedTime();
    }
}
