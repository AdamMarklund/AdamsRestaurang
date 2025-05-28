package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    static Menu menu;
    static MenuItem pizza;
    static MenuItem patatasBravas;
    static MenuItem bananaSplit;

    @BeforeEach
    void setup() {
        menu = new Menu();

        pizza = new MenuItem("Pizza", 200, null, 1);
        patatasBravas = new MenuItem("Patatas Bravas", 100, null, 1);
        bananaSplit = new MenuItem("Banana Split", 150, null, 1);
    }

    @Test
    public void testAddItem() {
        menu.addItem(pizza);
        menu.addItem(patatasBravas);
        menu.addItem(bananaSplit);

        assertTrue(menu.getItems().contains(pizza));
        assertTrue(menu.getItems().contains(patatasBravas));
        assertTrue(menu.getItems().contains(bananaSplit));
    }

    @Test
    public void testGetItemByDishName() {
        menu.addItem(pizza);
        menu.addItem(patatasBravas);
        menu.addItem(bananaSplit);

        MenuItem retrievedPizza = menu.getItemByDishName("Pizza");
        assertNotNull(retrievedPizza);
        assertEquals("Pizza", retrievedPizza.getDishName());

        MenuItem retrievedPatatas = menu.getItemByDishName("Patatas Bravas");
        assertNotNull(retrievedPatatas);
        assertEquals("Patatas Bravas", retrievedPatatas.getDishName());

        MenuItem retrievedBananaSplit = menu.getItemByDishName("Banana Split");
        assertNotNull(retrievedBananaSplit);
        assertEquals("Banana Split", retrievedBananaSplit.getDishName());
    }

    @Test
    public void testGetFaultyItemByDishName() {
        assertNull(menu.getItemByDishName("Sushi")); // Non-existent dish should return null

    }
}
