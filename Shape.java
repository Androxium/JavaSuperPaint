/*
 * Name: Thomas Cheng
 * 
 * Date: May 2, 2018
 * 
 * Dsscription: This is the abstract Shape class.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.BasicStroke;

abstract public class Shape{
    // creating the private variables for this class
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color colourOne;
    private Color colourTwo;
    private boolean isGradient;
    private double lineSize;
    
    /* This is the constructor method for the Shape class. This method accepts 5 parameters. x1, y1, x2, y2,
     * representing the starting and ending coordinates of the shape respectively, and a reference to a Color object.
     */
    public Shape( int x1, int y1, int x2, int y2, Color colourOne, Color colourTwo, Boolean isGradient, double lineSize){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.colourOne = colourOne;
        this.colourTwo = colourTwo;
        this.isGradient = isGradient;
        this.lineSize = lineSize;
    }
    
    /* This is the accessor method that returns the x1 value of the shape. This method accepts no values.
     */
    public int getX1(){
        return x1;
    }
    
    /* This is the accessor method that returns the x2 value of the shape. This method accepts no values.
     */
    public int getX2(){
        return x2;
    }
    
    /* This is the accessor method that returns the y1 value of the shape. This method accepts no values.
     */
    public int getY1(){
        return y1;
    }
    
    /* This is the accessor method that returns the y2 value of the shape. This method accepts no values.
     */
    public int getY2(){
        return y2;
    }

    /* This is the mutator method that changes the x1 value. This method accepts 1 parameter: an int value representing
     * the object's x1 value. This method returns no values.
     */
    public void setX1 (int xValue){
        x1 = xValue;
    }
    
    /* This is the mutator method that changes the y1 value. This method accepts 1 parameter: an int value representing
     * the object's y1 value. This method returns no values.
     */ 
    public void setY1 (int yValue){
        y1 = yValue;
    }
    
    /* This is the mutator method that changes the x2 value. This method accepts 1 parameter: an int value representing
     * the object's x2 value. This method returns no values.
     */
    public void setX2 (int xValue){
        x2 = xValue;
    }

    /* This is the mutator method that changes the y2 value. This method accepts 1 parameter: an int value representing
     * the object's y2 value. This method returns no values.
     */
    public void setY2 (int yValue){
        y2 = yValue;
    }
    
    /* This is the mutator method that changes the shape's colour. This method accepts 1 parameter: a reference to a
     * Color object. This method returns no values.
     */
    public void setColourOne( Color colourOne ){
        this.colourOne = colourOne;
    }
    
     /* This is the accessor method that returns the colour of the shape. This method accepts no values.
     */
    public Color getColourOne(){
        return colourOne;
    }
    
    public void setColourTwo( Color colourTwo ){
        this.colourTwo = colourTwo;
    }
    
    public Color getColourTwo( Color colourTwo ){
        return colourTwo;
    }
    
    public void setIsGradient ( boolean isGradient ) {
        this.isGradient = isGradient;
    }
    
    public boolean getIsGradient(){
        return isGradient;
    }
    
    public Graphics2D convertGraphics(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke( new BasicStroke( (float)lineSize ) );
        if ( !isGradient )
            colourTwo = colourOne;
        GradientPaint gradient = new GradientPaint(x1, y1, colourOne, x2, y2, colourTwo);
        g2d.setPaint(gradient);
        return g2d;
    }
    
    
    /* This is the abstract draw method. This method accepts 1 parameter: a reference to a Graphics object. This method
     * returns no values.
     */
    abstract public void draw( Graphics g );
}