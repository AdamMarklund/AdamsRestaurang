package org.example;

public class MenuInstruction extends Task {
    int tableNumber;


    MenuInstruction(int tableNumber) {
        super(tableNumber);
    }

    @Override
    void executeTask() {
        System.out.println("Menu");

    }
}
