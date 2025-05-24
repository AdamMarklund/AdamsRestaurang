package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Chef {

    // Variables regarding the illustration of the chef
    private int x;
    private int y;
    protected Color color;
    protected int diameter;

    protected ArrayList<MenuItem> dishesToMake = new ArrayList<>();
    private PrepChef prepChef;
    private int elapsedTime;
    private boolean waitForCooking = false;


// MIGHT screw it up that both the dhildren and parent have reference to prepChef. the children might not need it.

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

    public void addDish(MenuItem item) {
        dishesToMake.add(item);

    }

    private void prepareDish() {
        if (!dishesToMake.isEmpty()) {

            // cooking the food
            if (waitForCooking) {
                elapsedTime += 33;
                if (elapsedTime >= 3000) {
                    elapsedTime = 0;
                    waitForCooking = false;
                    dishesToMake.remove(0);

                }

            }
            else if (prepChef.getIngredientsCount() >= dishesToMake.get(0).getRequiredIngredients()) {
                // Take the amount of ingredients needed to make the dish from the prepChef.
                prepChef.supplyIngredients(dishesToMake.get(0).getRequiredIngredients());
                waitForCooking = true;

            }

            // If has enough ingredients and elapsed time > 2000 the dish is finished
        }

        // dont forget to remove from dishes to make when done
    }




    public void update() {
        prepareDish();
    }
}
