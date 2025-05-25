package org.example;

public class ServeFoodInstruction extends Task{

    private boolean goTokitchen = true;

    ServeFoodInstruction(Order order) {
        super(order.getTable());
    }

    @Override
    public void executeTask() {

// Draw plates
        // Leave 5 s after receiving food

    }

    @Override
    boolean forceGoToKitchen() {
        return goTokitchen;
    }

    @Override
    void setForceGoToKitchen(boolean forceGoToKitchen) {
        this.goTokitchen = forceGoToKitchen;

    }


}
