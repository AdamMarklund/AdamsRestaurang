package org.example;

import java.awt.*;

public class Patissier extends Chef {

    public Patissier(int x, int y, PrepChef prepChef) {
        super(x,y, prepChef);
        diameter = 60;
        color = Color.BLUE;
    }
}
