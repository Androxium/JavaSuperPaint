/*
 * Name: Thomas Cheng
 * 
 * Date: May 2, 2018
 * 
 * Dsscription: This is the Rectangle class that extends the FillableShape class
 */

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends FillableShape{
    
    /* This is the constructor method for the Rectangle class. This method accepts 6 parameters. x1, y1, x2, y2,
     * representing the starting and ending coordinates of the line respectively, a reference to a Color object, and
     * a boolean value representing whether the oval is filled or not.
     */
    public Rectangle (int x1, int y1, int x2, int y2, Color color, boolean fill){
        super(x1, y1, x2, y2, color, fill);
    }
    
    /* This method sets the colour of the rectangle, and draws it onto the panel. This method accepts 1 parameters: a
     * reference to a Graphics object. This method returns no values.
     */
    public void draw(Graphics g){
        // draws either an empty oval or a filled oval
        g.setColor(getColor());
        if (getFilling())
            g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        else
            g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }
}