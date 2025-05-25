package org.example;

import java.util.ArrayList;

public class TakeOrdersInstruction extends Task {
    private boolean goTokitchen = false;

    TakeOrdersInstruction(Table table) {
        super(table);
    }


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
    void setForceGoToKitchen(boolean forceGoToKitchen) {

    }
}
