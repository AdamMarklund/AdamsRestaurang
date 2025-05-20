package org.example;

public class MenuItem {
    private String dishName;
    private double price;

    public MenuItem(String dishName, double price) {
        this.dishName = dishName;
        this.price = price;
    }

    public String getDishName() {
        return dishName;
    }

    public double getPrice() {
        return price;
    }
}
