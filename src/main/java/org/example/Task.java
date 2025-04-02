package org.example;

import java.awt.event.WindowStateListener;

public class Task {

    enum Instruction {
        MENU,
        RECEIVEORDER
    }

    private int tableNumber;
    private Instruction instruction;

    Task(int tableNumber, Instruction instruction) {
        this.tableNumber = tableNumber;
        this.instruction = instruction;
    }

    int getTableNumber() {
        return tableNumber;
    }

    Instruction getTask() {
        return instruction;
    }
}