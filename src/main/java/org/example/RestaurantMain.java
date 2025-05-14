package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RestaurantMain extends JPanel {

    static Waiter waiter = new Waiter(500,320 - 25);
    static HeadChef headChef = new HeadChef(200,295);

    static ArrayList<Waiter> waiters = new ArrayList<Waiter>();
    static ArrayList<Table> tables = new ArrayList<Table>();
    static ArrayList<Guest> guest = new ArrayList<Guest>();

    // In here all objects that are needed for operating the restaurant should be created.
    // This is initialisation and determines the initial state of the program.
    static void setupRestaurant(){
        waiters.add(waiter);

        for (int i = 0; i < 3; i++) {
            tables.add(new Table(580 + 170 * i, 100, 1 + i));
            tables.get(i*2).addListeningWaiter(waiter);

            tables.add(new Table(580 + 170 * i, 450, 4 + i));
            tables.get(i*2+1).addListeningWaiter(waiter);

        }

        for (int i = 0; i < 6; i++) {
            // Temporary fix
            waiter.tablesWaiter.add(tables.get(i));
        }

        for (Table table : waiter.tablesWaiter) {
            System.out.println(table.getTableNumber());
        }
    }



    // Contains the simulation logic, should probably be broken into smaller pieces as the program expands
    static void update() {

        // what should happen with the waiter each time the simulation loops
        for (Waiter waiter : waiters) {

            waiter.update();
        }

        // What should happen with the table each time the simulation loops
        for (Table table : tables) {

            table.update();
        }


        // ... similar updates for all other agents in the simulation.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(new Color(255, 245, 158, 184)); //  // Set the background color to light yellow

        g.setColor(Color.DARK_GRAY); // Set the color for the border lines
        g.drawRect(500, 0, 600, getHeight() - 5);
        //g.drawRect(800, 0, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        g.drawRect(500, 0, 695, getHeight() - 5);

        // Draw kitchen door
        g.setColor(Color.DARK_GRAY);
        g.fillRect(490, 270, 20, 100);
        g.fillRect(1090, 270, 20, 100);

        // Draw tables
        drawTables(g);
        // During executeTask if isMenuVisible i tables, draw menus

        // Draw the waiters
        drawWaiters(g);

        // Draw the head chef
        drawHeadChef(g);

        // MORE CODE HERE
    }

    static void drawTables(Graphics g) {
        for (Table table : tables) {
            g.setColor(Color.RED);
            g.fillOval(table.getX(), table.getY(), table.getDiameter(), table.getDiameter()); // Draw circle with diameter of 50 pixels
            g.setColor(Color.WHITE);
            g.fillOval(table.getX() + 3, table.getY() + 3, table.getDiameter() - 6, table.getDiameter() - 6);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(table.getTableNumber()), table.getX() + 30, table.getY() + 35);
            if (table.hasMenusVisible()) {
                g.setColor(Color.decode("#CD7F32"));
                g.fillRect(table.getX(), table.getY(), 20, 30);
            }
        }
        g.drawString("Hello", 580,100);
    }

    // Add drawmenus

    static void drawWaiters(Graphics g){
        for (Waiter waiter : waiters) {
            g.setColor(Color.BLACK);
            g.fillOval(waiter.getX(), waiter.getY(), waiter.getDiameter(), waiter.getDiameter()); // Draw circle with diameter of 50 pixels
            g.setColor(Color.WHITE);
            g.fillOval(waiter.getX()+7, waiter.getY()+7, waiter.getDiameter()-14, waiter.getDiameter()-14); // Draw circle with diameter of 50 pixels
        }
    }

    static void drawHeadChef(Graphics g) {
        // Draw master head office
        g.setColor(new Color(93, 191, 73, 255));
        g.fillRect(410, 200, 60, 200);

        // Draw the head chef
        g.setColor(Color.BLACK);
        g.fillOval(headChef.getX(), headChef.getY(), headChef.getDiameter(), headChef.getDiameter());
        // Draws head chef's inner circle/layer
        g.setColor(Color.PINK);
        g.fillOval(headChef.getX()+5, headChef.getY()+5, headChef.getDiameter()-10, headChef.getDiameter()-10);
    }


    // Draws souschef, prepchef, and manangartenåt
    static void drawChefs(Graphics g) {

    }

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Restuarant Simulation");
        frame.setSize(1200, 640); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Add the custom panel (with circles) to the frame
        RestaurantMain panel = new RestaurantMain();
        frame.add(panel);

        // Display the window
        frame.setVisible(true);

        // Setup for the restaurant
        setupRestaurant();

        while (true) {
            try {
                Thread.sleep(33); // With the goal of having about 30 fps.
            } catch (Exception threadException) {
                System.out.println("Sleep exception: " + threadException.getMessage());
            }
            update();

            panel.repaint();
        }
    }
}