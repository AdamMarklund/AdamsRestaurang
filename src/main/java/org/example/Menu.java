package org.example;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> items;

    public Menu() {
        this.items = new ArrayList<>();

    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem menuItem) {
        items.add(menuItem);
    }
    public MenuItem getItemByDishName(String dishName) {
        for (MenuItem item : items) {
            if (item.getDishName().equalsIgnoreCase(dishName)) {
                return item;
            }
        }
        return null;
    }
}
