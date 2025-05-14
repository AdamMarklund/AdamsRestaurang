package org.example;

public class TakeOrdersInstruction extends Task {
    int tableNumber;

    TakeOrdersInstruction(int tableNumber) {
        super(tableNumber);
    }

    @Override
    void executeTask() {
        System.out.println("Orders");
    }
}
