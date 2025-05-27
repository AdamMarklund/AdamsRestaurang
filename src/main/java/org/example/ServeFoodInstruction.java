package org.example;

/**
 * Task representing the instruction for serving food to a table.
 * Handles whether the waiter needs to go to the kitchen for this task.
 */
public class ServeFoodInstruction extends Task {

    private boolean goTokitchen = true; // Flag indicating if the waiter should go to the kitchen to get the food

    /**
     * Constructs a ServeFoodInstruction for a given order.
     *
     * @param order the order associated with this serving task
     */
    ServeFoodInstruction(Order order) {
        super(order.getTable());
    }

    /**
     * Executes the serving task.
     * Intended to handle drawing plates and leave a 5 second pause after receiving food.
     */
    @Override
    public void executeTask() {
        // TODO: Draw plates
        // TODO: Leave 5 seconds delay after receiving food
    }

    /**
     * Checks whether the waiter is forced to go to the kitchen for this task.
     *
     * @return true if the waiter must go to kitchen, false otherwise
     */
    @Override
    boolean forceGoToKitchen() {
        return goTokitchen;
    }

    /**
     * Sets the flag forcing the waiter to go to the kitchen.
     *
     * @param forceGoToKitchen true to force going to kitchen, false otherwise
     */
    @Override
    void setForceGoToKitchen(boolean forceGoToKitchen) {
        this.goTokitchen = forceGoToKitchen;
    }
}
