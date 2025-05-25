package org.example;

import java.util.ArrayList;

public class MenuItem implements Cloneable{
    private String dishName;
    private double price;
    private Chef assignedChef;
    private int requiredIngredients;
    private boolean isFoodCooked;



    public MenuItem(String dishName, double price, Chef assignedChef, int requiredIngredients) {
        this.dishName = dishName;
        this.price = price;
        this.assignedChef = assignedChef;
        this.requiredIngredients = requiredIngredients;
        this.isFoodCooked = false;

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

    public boolean getIsFoodCooked() {
        return isFoodCooked;
    }

    public void setIsFoodCooked(boolean foodCooked) {
        isFoodCooked = foodCooked;
    }


    protected Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
