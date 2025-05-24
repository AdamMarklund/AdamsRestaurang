package org.example;

import java.util.ArrayList;

public class MenuItem {
    private String dishName;
    private double price;
    private String assignedChef;
    private int requiredIngredients;
    private int amountOfIngredients;


    public MenuItem(String dishName, double price, String dishType, Chef assignedChef, int requiredIngredients) {
        this.dishName = dishName;
        this.price = price;
        this.amountOfIngredients = 0;
    }

    public String getDishName() {
        return dishName;
    }

    public double getPrice() {
        return price;
    }


}
