package org.example;

public class MenuInstruction extends Task {


    MenuInstruction(Table table) {
        super(table);
    }

    @Override
    void executeTask() {
        //Hands out menus
        handOutMenus();
    }

    @Override
    void setForceGoToKitchen(boolean forceGoToKitchen) {

    }

    public void handOutMenus() {
        getTable().setMenusVisible(true);
        getTable().resetElapsedTime();
        //tablesWaiter.get(queue.get(0).getTableNumber()-1).setMenusVisible(true);

    }
    // gettable()

}
