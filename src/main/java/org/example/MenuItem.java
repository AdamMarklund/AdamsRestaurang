package org.example;

import java.util.ArrayList;

public class MenuItem {
    private String dishName;
    private double price;
    private Chef assignedChef;
    private int requiredIngredients;



    public MenuItem(String dishName, double price, Chef assignedChef, int requiredIngredients) {
        this.dishName = dishName;
        this.price = price;
        this.assignedChef = assignedChef;
        this.requiredIngredients = requiredIngredients;
    }

    public String getDishName() {
        return dishName;
    }

    public double getPrice() {
        return price;
    }

    public Chef getAssignedChef() {
        return assignedChef;
    }

    public int getRequiredIngredients() {
        return requiredIngredients;
    }
}
