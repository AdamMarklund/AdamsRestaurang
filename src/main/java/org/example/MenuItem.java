package org.example;

public class MenuItem {
    private String dishName;
    private double price;
    private String dishType;

    public MenuItem(String dishName, double price, String dishType) {
        this.dishName = dishName;
        this.price = price;
    }

    public String getDishName() {
        return dishName;
    }

    public double getPrice() {
        return price;
    }

    public String getDishType() {
        return dishType;
    }
}
