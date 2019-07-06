package com.rect3d.rect3d;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;

public class Draw3DRectanglesExample extends Applet {
    public static void main(String[] args) {
        Frame Draw3DRect = new Frame("Draw 3D Rectangles Example");
        Draw3DRect.setSize(350, 250);
        Applet Draw3DRectanglesExample = new Draw3DRectanglesExample();
        Draw3DRect.add(Draw3DRectanglesExample);
        Draw3DRect.setVisible(true);
        Draw3DRect.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {

        g.setColor(Color.blue);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Draw 3D Rectangles Example", 50, 40);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("http://ecomputernotes.com", 200, 205);

        //set color to Green
        g.setColor(Color.green);

        //this will draw a 3-D rectangle of width 50 & height 70 at (70,50)
        //The Syntax for draw3DRect(int x_coordinates,int y_coordinates, int width, int height, boolean raised)
        g.draw3DRect(50, 50, 50, 70, true);

        g.setColor(Color.orange);
        //this will Fill Rectangle of width 50 & height 70 at (150,50)
        //The Syntax for fill3DRect(int x_coordinates,int y_coordinates, int width, int height, boolean raised)
        g.fill3DRect(130, 50, 50, 70, true);
        /*
         * If we speficy same width and height of the draw3DRect method then we will draw a 3-D square.
         */
        //we will draw a 3-D square
        g.draw3DRect(210, 50, 50, 50, true);
        /*
         * If we speficy same width and height of the fill3DRect method then we will draw a filled 3-D square.
         */

        //we will draw a filled 3-D square
        g.fill3DRect(280, 50, 50, 50, true);
    }
}