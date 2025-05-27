package org.example;

import java.awt.*;

/**
 * Represents a Garde Manger Chef responsible for Appetizers.
 * <p>
 * Extends the Chef class and defines specific properties.
 */
public class GardeManger extends Chef {

    /**
     * Constructs a Garde Manger chef with specified position and reference to PrepChef.
     *
     * @param x the x-coordinate of the chef
     * @param y the y-coordinate of the chef
     * @param prepChef the PrepChef that supplies ingredients
     */
    public GardeManger(int x, int y, PrepChef prepChef) {
        super(x, y, prepChef);
        diameter = 40; // Diameter of the Garde Manger's illustration
        color = Color.GREEN; // Color representing the Garde Manger
    }
}
