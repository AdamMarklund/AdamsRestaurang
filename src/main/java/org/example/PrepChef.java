package org.example;

import java.awt.*;

/**
 * The PrepChef handles the preparation and supply of ingredients for other chefs.
 */
public class PrepChef {

    private int x; // The x-position of the PrepChef
    private int y; // The y-position of the PrepChef
    private Color color; // The color used to visually represent the PrepChef
    private int diameter;  // The diameter used to visually represent the PrepChef


    private final int MAX_INGREDIENTS_COUNT; // The maximum number of ingredients the PrepChef can store
    private int ingredientsCount; // The current number of ingredients available
    private int elapsedTime;  // Elapsed time tracker for ingredient preparation (in milliseconds)

    /**
     * Constructs a PrepChef at a specific position.
     *
     * @param x the x-position of the PrepChef
     * @param y the y-position of the PrepChef
     */
    public PrepChef(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.MAGENTA;
        this.diameter = 50;
        MAX_INGREDIENTS_COUNT = 10;
        ingredientsCount = 0;
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

    public int getIngredientsCount() {
        return ingredientsCount;
    }

    /**
     * Increases ingredients over time up to the maximum limit.
     * Each ingredient is prepared after 1 second (1000ms).
     */
    private void prepareIngredients() {
        this.elapsedTime += 33;
        if (this.elapsedTime >= 1000 && ingredientsCount < MAX_INGREDIENTS_COUNT) {
            ingredientsCount += 1;
            elapsedTime = 0;
        }
    }

    /**
     * Called periodically to update the ingredient preparation status.
     */
    public void update() {
        prepareIngredients();
    }

    /**
     * Supplies a specific number of ingredients to a chef, reducing the PrepChef's stock.
     *
     * @param ingredientsNeeded the number of ingredients required
     */
    public void supplyIngredients(int ingredientsNeeded) {
        ingredientsCount -= ingredientsNeeded;
    }
}
