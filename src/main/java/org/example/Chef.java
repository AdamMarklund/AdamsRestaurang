package org.example;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a chef who prepares dishes in the kitchen.
 * <p>
 * The chef manages a queue of dishes to cook and uses a PrepChef for ingredient supply.
 */
public class Chef {

    private int x;
    private int y;
    protected Color color;
    protected int diameter;

    protected ArrayList<MenuItem> dishesToMake = new ArrayList<>(); // The queue of dishes the Chef has to prepare
    private PrepChef prepChef; // The PrepChef that supplies ingredients
    private int elapsedTime; // The time passed since the Chef started cooking the current dish
    private boolean waitForCooking = false; // Whether the Chef is waiting for the current dish to finish cooking

    /**
     * Constructs a chef at the specified position with access to the PrepChef.
     *
     * @param x the x-coordinate of the chef
     * @param y the y-coordinate of the chef
     * @param prepChef the PrepChef that supplies ingredients
     */
    public Chef(int x, int y, PrepChef prepChef) {
        this.x = x;
        this.y = y;
        this.prepChef = prepChef;
        this.elapsedTime = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getDiameter() {
        return diameter;
    }

    /**
     * Adds a dish to the chef's queue to prepare.
     *
     * @param item the menu item to be prepared
     */
    public void addDish(MenuItem item) {
        dishesToMake.add(item);
    }

    /**
     * Prepares the current dish if ingredients are available.
     * <p>
     * The chef requests ingredients from the PrepChef and simulates cooking time.
     * Dishes are removed from the queue when finished.
     */
    private void prepareDish() {
        if (!dishesToMake.isEmpty()) {
            MenuItem currentFood = dishesToMake.get(0);
            // cooking the food
            if (waitForCooking) {
                elapsedTime += 33;
                if (elapsedTime >= 1000 * currentFood.getRequiredIngredients()) {
                    elapsedTime = 0;
                    waitForCooking = false;

                    // Dish is ready
                    currentFood.setIsFoodCooked(true);
                    dishesToMake.remove(0); // Start making next dish instead.
                }
            }
            // Get the required ingredients and start cooking
            else if (prepChef.getIngredientsCount() >= dishesToMake.get(0).getRequiredIngredients()) {
                // Take the amount of ingredients needed to make the dish from the prepChef.
                prepChef.supplyIngredients(dishesToMake.get(0).getRequiredIngredients());
                waitForCooking = true;
            }
        }
    }

    public ArrayList<MenuItem> getDishesToMake() {
        return dishesToMake;
    }


    /**
     * Updates the chef's state by preparing dishes.
     */
    public void update() {
        prepareDish();
    }
}
