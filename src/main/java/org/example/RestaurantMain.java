package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Main JPanel class representing the restaurant simulation.
 * Handles initialization, update logic, and rendering of all restaurant components.
 */
public class RestaurantMain extends JPanel {


    static Menu menu; // The restaurant's menu

    static Waiter waiter = new Waiter(500, 320 - 25); //  The waiter
    static HeadWaiter headWaiter = new HeadWaiter(1090 - 60, 320 - 25);
    static PrepChef prepChef = new PrepChef(70, 70);

    static GardeManger gardeManger = new GardeManger(300, 95, prepChef);
    static SousChef sousChef = new SousChef(135, 375, prepChef);
    static Patissier patissier = new Patissier(380, 480, prepChef);

    static HeadChef headChef = new HeadChef(200, 295, waiter);

    static ArrayList<Waiter> waiters = new ArrayList<>();


    static ArrayList<Table> tables = new ArrayList<>();

    static ArrayList<Chef> chefs = new ArrayList<>();

    /**
     * Sets up the initial restaurant state:
     * creates menu items, tables, assigns waiters to tables and chefs.
     */
    static void setupRestaurant() {
        waiters.add(waiter);
        chefs.add(sousChef);
        chefs.add(patissier);
        chefs.add(gardeManger);

        // Initialize menu with dishes and responsible chefs
        menu = new Menu();
        menu.addItem(new MenuItem("Pizza", 200, sousChef, 1));
        menu.addItem(new MenuItem("Patatas Bravas", 100, gardeManger, 1));
        menu.addItem(new MenuItem("Banana Split", 150, patissier, 1));

        // Create tables in two rows, assign menu and listening waiter
        for (int i = 0; i < 3; i++) {
            tables.add(new Table(580 + 170 * i, 100, 1 + i, menu));
            tables.get(i * 2).addListeningWaiter(waiter);

            tables.add(new Table(580 + 170 * i, 450, 4 + i, menu));
            tables.get(i * 2 + 1).addListeningWaiter(waiter);
        }

        // Waiter listens to HeadChef's notifications
        headChef.addListeningWaiter(waiter);

        // HeadWaiter gains acess to all tables
        for (int i = 0; i < 6; i++) {
            headWaiter.addTable(tables.get(i));
        }
    }

    /**
     * Updates all dynamic components in the restaurant simulation.
     * This includes waiters, tables, chefs, and head waiter logic.
     */
    static void update() {
        for (Waiter waiter : waiters) {
            waiter.update();
        }
        for (Table table : tables) {
            table.update();
        }
        headChef.update();
        prepChef.update();
        gardeManger.update();
        sousChef.update();
        patissier.update();
        headWaiter.update();
    }

    /**
     * Paints all visual elements of the restaurant.
     * @param g the Graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(new Color(255, 245, 158, 184)); // Light yellow background

        // Draw walls
        g.setColor(Color.DARK_GRAY);
        g.drawRect(500, 0, 600, getHeight() - 5);
        g.setColor(Color.BLACK);
        g.drawRect(500, 0, 695, getHeight() - 5);

        // Draw kitchen doors
        g.setColor(Color.DARK_GRAY);
        g.fillRect(490, 270, 20, 100);
        g.fillRect(1090, 270, 20, 100);

        drawTables(g);
        drawWaiters(g);
        drawHeadChef(g);
        drawKitchen(g);
        drawChefs(g);
        drawHeadWaiter(g);
        drawGuests(g);
    }

    /**
     * Draws all the chef's kitchenstations
     * @param g the Graphics object used for drawing
     */
    static void drawKitchen(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 50, 150); // Prep chef area
        g.fillRect(50, 0, 100, 50);
        g.fillRect(350, 50, 100, 100); // Garde Manger area
        g.fillRect(450, 445, 50, 150); // Patissier area
        g.fillRect(50, 300, 75, 200); // Sous Chef area

        // Draws prep chef's name and ingredientscount
        g.setColor(Color.BLACK);
        g.drawString("PrepKitchen", 150, 10);
        g.drawString("Ingredients: " + prepChef.getIngredientsCount(), 50, 25);

        // Garde manger's dishes to make
        g.drawString("Garde Manger", 350, 50);
        int i = 0;
        for (MenuItem item : gardeManger.getDishesToMake()) {
            g.drawString(item.getDishName(), 350, 60 + i * 10);
            i++;
        }

        // Illustrates the sous chef's dishes to make
        g.drawString("Sous chef", 50, 300);
        i = 0;
        for (MenuItem item : sousChef.getDishesToMake()) {
            g.drawString(item.getDishName(), 50, 310 + i * 10);
            i++;
        }

        // Illustartes the patissier's dishes to make
        g.drawString("Patissier", 450, 445);
        i = 0;
        for (MenuItem item : patissier.getDishesToMake()) {
            g.drawString(item.getDishName(), 450, 455 + i * 10);
            i++;
        }
    }

    /**
     * Draws all tables.
     * @param g the Graphics object used for drawing
     */
    static void drawTables(Graphics g) {
        for (Table table : tables) {
            g.setColor(Color.RED);
            g.fillOval(table.getX(), table.getY(), table.getDiameter(), table.getDiameter());
            g.setColor(Color.WHITE);
            g.fillOval(table.getX() + 3, table.getY() + 3, table.getDiameter() - 6, table.getDiameter() - 6);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(table.getTableNumber()), table.getX() + 30, table.getY() + 35);
            if (table.hasMenusVisible()) {
                g.setColor(Color.decode("#CD7F32"));
                g.fillRect(table.getX(), table.getY(), 20, 30);
            }
        }
        g.drawString("Hello", 580, 100);
    }

    /**
     * Draws all waiters.
     * @param g the Graphics object used for drawing
     */
    static void drawWaiters(Graphics g) {
        for (Waiter waiter : waiters) {
            g.setColor(Color.BLACK);
            g.fillOval(waiter.getX(), waiter.getY(), waiter.getDiameter(), waiter.getDiameter());
            g.setColor(Color.WHITE);
            g.fillOval(waiter.getX() + 7, waiter.getY() + 7, waiter.getDiameter() - 14, waiter.getDiameter() - 14);
        }
    }

    /**
     * Draws the head chef and their current order statuses.
     * @param g the Graphics object used for drawing
     */
    static void drawHeadChef(Graphics g) {
        g.setColor(new Color(93, 191, 73, 255));
        g.fillRect(410, 200, 60, 200);

        g.setColor(Color.BLACK);
        g.fillOval(headChef.getX(), headChef.getY(), headChef.getDiameter(), headChef.getDiameter());
        g.setColor(Color.PINK);
        g.fillOval(headChef.getX() + 5, headChef.getY() + 5, headChef.getDiameter() - 10, headChef.getDiameter() - 10);

        g.setColor(Color.BLACK);
        int i = 0;
        for (Order order : headChef.getOrdersPreparing()) {
            g.drawString("Order " + order.getTable().getTableNumber() + ": " + (order.isOrderReady() ? "done" : "preparing"),
                    headChef.getX() + headChef.getDiameter(), headChef.getY() + i * 10);
            i++;
        }
    }

    /**
     * Draws all chefs (SousChef, Patissier, GardeManger) and PrepChef.
     * @param g the Graphics object used for drawing
     */
    static void drawChefs(Graphics g) {
        for (Chef chef : chefs) {
            g.setColor(chef.getColor());
            g.fillOval(chef.getX(), chef.getY(), chef.getDiameter(), chef.getDiameter());
            g.setColor(Color.WHITE);
            g.fillOval(chef.getX() + 3, chef.getY() + 3, chef.getDiameter() - 6, chef.getDiameter() - 6);
        }

        g.setColor(prepChef.getColor());
        g.fillOval(prepChef.getX(), prepChef.getY(), prepChef.getDiameter(), prepChef.getDiameter());
        g.setColor(Color.WHITE);
        g.fillOval(prepChef.getX() + 3, prepChef.getY() + 3, prepChef.getDiameter() - 6, prepChef.getDiameter() - 6);
    }

    /**
     * Draws the head waiter.
     * @param g the Graphics object used for drawing
     */
    static void drawHeadWaiter(Graphics g) {
        g.setColor(headWaiter.getColor());
        g.fillOval(headWaiter.getX(), headWaiter.getY(), headWaiter.getDiameter(), headWaiter.getDiameter());
        g.setColor(Color.WHITE);
        g.fillOval(headWaiter.getX() + 3, headWaiter.getY() + 3, headWaiter.getDiameter() - 6, headWaiter.getDiameter() - 6);
    }

    /**
     * Draws all guests including those waiting and seated.
     * @param g the Graphics object used for drawing
     */
    static void drawGuests(Graphics g) {
        for (int i = 0; i < headWaiter.getGuestGroups().size(); i++) {
            for (Guest guest : headWaiter.getGuestGroups().get(i)) {
                g.setColor(guest.getRandomColor());
                g.fillOval(guest.getX(), guest.getY(), guest.getDiameter(), guest.getDiameter());
                g.setColor(Color.WHITE);
                g.fillOval(guest.getX() + 3, guest.getY() + 3, guest.getDiameter() - 6, guest.getDiameter() - 6);
            }

            if (!headWaiter.getSeatedGroups().isEmpty()) {
                for (int j = 0; j < headWaiter.getSeatedGroups().size(); j++) {
                    for (Guest guest : headWaiter.getSeatedGroups().get(j)) {
                        g.setColor(guest.getRandomColor());
                        g.fillOval(guest.getX(), guest.getY(), guest.getDiameter(), guest.getDiameter());
                        g.setColor(Color.WHITE);
                        g.fillOval(guest.getX() + 3, guest.getY() + 3, guest.getDiameter() - 6, guest.getDiameter() - 6);
                    }
                }
            }
        }
    }

    /**
     * Main program entry point.
     * Initializes the JFrame, sets up the restaurant, and runs the simulation loop.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurant Simulation");
        frame.setSize(1200, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        RestaurantMain panel = new RestaurantMain();
        frame.add(panel);

        frame.setVisible(true);
        setupRestaurant();

        // updates and repaints the simulation about 30 times per second
        while (true) {
            try {
                Thread.sleep(33);
            } catch (Exception threadException) {
                System.out.println("Sleep exception: " + threadException.getMessage());
            }
            update();
            panel.repaint();
        }
    }
}
