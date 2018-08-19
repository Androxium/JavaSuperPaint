/*
 * Name: Thomas Cheng
 * 
 * Date: May 2, 2018
 * 
 * Dsscription: This is the abstract FillableShape class that extends the Shape class.
 */

import java.awt.Color;

abstract public class FillableShape extends Shape{
    private boolean filled;
    
    /* This is the constructor method for the FillableShape class. This method accepts 6 parameters. x1, y1, x2, y2,
     * representing the starting and ending coordinates of the line respectively, a reference to a Color object, and
     * a boolean value representing whether the oval is filled or not.
     */
    public FillableShape(int x1, int y1, int x2, int y2, Color colourOne, Color colourTwo, boolean isGradient, 
                         boolean isFilled, double lineSize){
        super(x1, y1, x2, y2, colourOne, colourTwo, isGradient, lineSize);
        this.filled = isFilled;
    }
    
    /* This is the accessor method for "filled" . This method returns the boolean value of "filled". This method
     * accepts no values.
     */
    public boolean getFilling(){
        return filled;
    }
    
    /* This is the mutator method that changes the value of "filled". This method accepts 1 parameter: a boolean value
     * representing whether the shape is filled of not. This method returns no values.
     */
    public void setFilling( boolean newFill){
        filled = newFill;
    }
  
    /* This method will return the upper left x value of the rectangle. This method returns 1 value: the min int value
     * between x1 and x2.
     */
    public int getUpperLeftX(){
        return Math.min(getX1(), getX2());
    }
    
    /* This method will return the upper-left y-value of the rectangle. This method returns 1 value: the min int value
     * between y1 and y2.
     */
    // 
    public int getUpperLeftY(){
        return Math.min(getY1(), getY2());
    }

    // Accessor method that returns the length of the rectangle
    public int getWidth(){
        return Math.abs(getX2() - getX1());
    }
    
    // Accessor method that returns the height of the rectangle
    public int getHeight(){
        return Math.abs(getY2() - getY1());
    }
}