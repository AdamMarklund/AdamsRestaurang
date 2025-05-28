package org.example;


/**
 * Represents a menu item in the restaurant.
 */
public class MenuItem implements Cloneable {

    private String dishName; // The name of the dish
    private double price; // The price of the dish
    private Chef assignedChef; // The chef responsible for preparing this dish
    private int requiredIngredients; // The number of ingredients required to prepare this dish
    private boolean isFoodCooked; // Whether the dish has been cooked

    /**
     * Constructs a MenuItem with a name, price, assigned chef, and required ingredients.
     *
     * @param dishName the name of the dish
     * @param price the price of the dish
     * @param assignedChef the chef responsible for preparing this dish
     * @param requiredIngredients the number of ingredients needed
     */
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

    /**
     * Creates a shallow copy of this MenuItem object.
     *
     * @return the cloned MenuItem
     */
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
