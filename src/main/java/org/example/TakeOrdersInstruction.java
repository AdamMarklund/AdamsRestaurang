package org.example;

public class TakeOrdersInstruction extends Task {


    TakeOrdersInstruction(Table table) {
        super(table);
    }

    @Override
    void executeTask() {
        System.out.println("Orders");
    }
}
