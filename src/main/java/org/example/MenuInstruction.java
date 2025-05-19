package org.example;

public class MenuInstruction extends Task {
    int tableNumber;


    MenuInstruction(int tableNumber) {
        super(tableNumber);
    }

    @Override
    void executeTask() {
        //Hands out menus
        System.out.println("Menu");

    }
}
