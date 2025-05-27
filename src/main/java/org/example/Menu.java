package org.example;

import java.util.ArrayList;

/**
 * Represents a Menu containing a list of MenuItem objects.
 */
public class Menu {

    private ArrayList<MenuItem> items; // The list of menu items available in the menu

    /**
     * Constructs an empty Menu.
     */
    public Menu() {
        this.items = new ArrayList<>();
    }

    /**
     * Returns the list of menu items.
     *
     * @return the list of MenuItem objects in the menu
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Adds a new item to the menu.
     *
     * @param menuItem the MenuItem to add
     */
    public void addItem(MenuItem menuItem) {
        items.add(menuItem);
    }

    /**
     * Searches for a MenuItem in the menu by its dish name
     *
     * @param dishName the name of the dish to search for
     * @return the MenuItem if found, or null if not found
     */
    public MenuItem getItemByDishName(String dishName) {
        for (MenuItem item : items) {
            if (item.getDishName().equalsIgnoreCase(dishName)) { // compares strings ignoring case sensitivity
                return item;
            }
        }
        return null;
    }
}
