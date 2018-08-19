/*
 * Name: Thomas Cheng
 * 
 * Date: May 2, 2018
 * 
 * Dsscription: This is the Line class that extends the Shape class.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Shape{
    /* This is the constructor method for the Line class. This method accepts 5 parameters. x1, y1, x2, y2,
     * representing the starting and ending coordinates of the line respectively, and a reference to a Color object.
     */
    public Line (int x1, int y1, int x2, int y2, Color colourOne, Color colourTwo, boolean isGradient, double lineSize){
        super(x1, y1, x2, y2, colourOne, colourTwo, isGradient, lineSize);
    }
    
    /* This method sets the colour of the line, and draws it onto the panel. This method accepts 1 parameters: a
     * reference to a Graphics object. This method returns no values.
     */
    public void draw( Graphics g ){
        Graphics2D g2d = convertGraphics(g);
        g2d.drawLine(getX1(), getY1(), getX2(), getY2());
    }
}