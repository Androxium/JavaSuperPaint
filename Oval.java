/*
 * Name: Thomas Cheng
 * 
 * Date: May 2, 2018
 * 
 * Dsscription: This is the Oval class that extends the FillableShape class
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Oval extends FillableShape{
    /* This is the constructor method for the Oval class. This method accepts 6 parameters. x1, y1, x2, y2,
     * representing the starting and ending coordinates of the line respectively, a reference to a Color object, and
     * a boolean value representing whether the oval is filled or not.
     */
    public Oval (int x1, int y1, int x2, int y2, Color colourOne, Color colourTwo, boolean isGradient, 
                 boolean isFilled, double lineSize){
        super(x1, y1, x2, y2, colourOne, colourTwo, isGradient, isFilled, lineSize);
    }
    
    /* This method sets the colour of the oval, and draws it onto the panel. This method accepts 1 parameters: a
     * reference to a Graphics object. This method returns no values.
     */
    public void draw( Graphics g ) {
        Graphics2D g2d = convertGraphics(g);
        if (getFilling())
            g2d.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        else
            g2d.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }
}