package org.example;

import java.util.ArrayList;

public class TakeOrdersInstruction extends Task {
    private boolean goTokitchen = false;

    TakeOrdersInstruction(Table table) {
        super(table);
    }

    public ArrayList<MenuItem> getOrder() {
        return table.getOrder();
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
}
