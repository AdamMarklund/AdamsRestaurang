package org.example;

import java.awt.*;

/**
 * The Patissier is a type of Chef specialized in making desserts.
 */
public class Patissier extends Chef {

    /**
     * Constructs a Patissier with a position and reference to the PrepChef.
     *
     * @param x the x-position of the Patissier
     * @param y the y-position of the Patissier
     * @param prepChef reference to the PrepChef
     */
    public Patissier(int x, int y, PrepChef prepChef) {
        super(x, y, prepChef);
        diameter = 60; // The visual size of the Patissier
        color = Color.BLUE; // The color of the Patissier
    }
}
