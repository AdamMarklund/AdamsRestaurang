package org.example;

/**
 * Represents the instruction/task for taking orders from a table.
 */
public class TakeOrdersInstruction extends Instruction {

    private boolean goTokitchen = false;  // Flag to indicate if the waiter should go to the kitchen after taking the order

    /**
     * Constructor that initializes the task with the given table.
     *
     * @param table the table from which to take the order
     */
    TakeOrdersInstruction(Table table) {
        super(table);
    }

    /**
     * Executes the task of taking the order from the table.
     * This includes placing the order and hiding the menus.
     */
    @Override
    void executeTask() {
        table.placeOrder();
        goTokitchen = true;
        table.setMenusVisible(false);
    }

    @Override
    boolean forceGoToKitchen() {
        return goTokitchen;
    }

    @Override
    public String getTaskString() {
        return "Taking orders";
    }

    @Override
    void setForceGoToKitchen(boolean forceGoToKitchen) {
        // No implementation needed currently
    }
}
