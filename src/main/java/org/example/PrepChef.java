package org.example;

import java.awt.*;

public class PrepChef {

    // Regarding illustration of the prepChef
    private int x;
    private int y;
    private Color color;
    private int diameter;

    // Regarding the preparation of ingredients
    private final int MAX_INGREDIENTS_COUNT;
    private int ingredientsCount;
    private int elapsedTime;

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

    private void prepareIngredients() {
        this.elapsedTime += 33;
        if (this.elapsedTime >= 2000 && ingredientsCount < MAX_INGREDIENTS_COUNT) {
            ingredientsCount += 1;
            elapsedTime = 0;

        }

    }
    public void update() {
        prepareIngredients();

    }

    public void supplyIngredients(int ingredientsNeeded) {
        ingredientsCount -= ingredientsNeeded;
    }
}
