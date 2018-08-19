/*
 * Name: Thomas Cheng
 * 
 * Date: July 22, 2018
 * 
 * Dsscription: This is the PaintPanel class that will draw all the shapes.
 */

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintPanel extends JPanel {
    // instance variables
    private LinkedList<Shape> shapeList;
    private DynamicStack<Shape> redoStack;
    private JLabel statusLabel;
    private Shape currentShape = null;
    private int shapeType;
    private Color colourOne;
    private Color colourTwo;
    private boolean isFilled;
    private boolean isGradient;
    private double lineSize;
    private boolean isDashLine;
    private double dashLineLength;
    
    /* This is the constructor method for the class. This method accepts 1 parameter: a reference to a JLabel object.
     */
    public PaintPanel( JLabel statusLabel ) {
        super();
        setLayout( new BorderLayout() );
        shapeList = new LinkedList<Shape>();
        redoStack = new DynamicStack<Shape>();
        this.statusLabel = statusLabel;
        
        // Create and register listener for mouse and mouse motion events
        MouseEventListener drawFrameListener = new MouseEventListener(); 
        addMouseListener( drawFrameListener ); 
        addMouseMotionListener( drawFrameListener ); 
    }
    
    /* This inner class handles mouse events.
     */
    class MouseEventListener extends MouseAdapter {
        /* This method checks for a mouse press to indicate a new shape has been started. This method accepts 1
         * parameter: a reference to a MouseEvent object. This method returns no values.
         */
        @Override
        public void mousePressed( MouseEvent event ) {
            // draws the specificed shape
            // draw a line
            if (currentShape == null){
                if ( shapeType == 0 ){
                    currentShape = new Line( event.getX(), event.getY(), event.getX(), event.getY(), colourOne, 
                                            colourTwo, isGradient, lineSize );
                }
                // draw a rectangle
                else if ( shapeType == 1 ){
                    currentShape = new Rectangle( event.getX(), event.getY(), event.getX(), event.getY(), 
                                                 colourOne, colourTwo, isGradient, isFilled, lineSize );
                }
                // draw an oval
                else if ( shapeType == 2 ){
                    currentShape = new Oval( event.getX(), event.getY(), event.getX(), event.getY(), 
                                            colourOne, colourTwo, isGradient, isFilled, lineSize);
                }
                // Tell JVM to call paintComponent( g )
                repaint();
            }
        } 
        
        /* This method checks for a mouse release to indicate the shape has been finished. This method accepts 1
         * parameter: a reference to a MouseEvent object. This method returns no values.
         */
        @Override
        public void mouseReleased( MouseEvent event ) {
            // Update ending coordinates
            if (currentShape != null){
                currentShape.setX2( event.getX() );
                currentShape.setY2( event.getY() );
                currentShape.setColourOne( colourOne );
                
                // add the new shape to the END of the LinkedList
                shapeList.addLast( currentShape );
                
                // Get ready for the next line to be drawn
                currentShape = null;
                repaint();
                
                // clear the redo stack
                redoStack.clear();
            }
        } 
        
        /* This method updates the ending coordinates of currentShape and statusLabel as the mouse is dragged. 
         * This method accepts 1 parameter: a reference to a MouseEvent object. This method returns no values.
         */
        @Override
        public void mouseDragged( MouseEvent event ) {
            if (currentShape != null){
                currentShape.setX2( event.getX() );
                currentShape.setY2( event.getY() );
                statusLabel.setText( String.format( "(%d, %d)", event.getX(), event.getY() ) );
                repaint();
            }
        } 
        
        /* This method updates the statusLabel as the mouse is moved. This method accepts 1 parameter: a reference to 
         * a MouseEvent object. This method returns no values.
         */
        @Override
        public void mouseMoved( MouseEvent event ) {
            statusLabel.setText( String.format( "(%d, %d)", event.getX(), event.getY() ) );
        } 
    } 
    
    /* This method is called automatically by the JVM when the window needs to be (re)drawn. This methid accepts 1
     * parameter: a reference to a Graphics object. This method returns no values.
     */
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        removeAll();
        // Call the draw() method for each Line object in the array
        for ( int index = 0; index < shapeList.size(); index++ ){
            Shape shape = shapeList.removeFirst();
            shape.draw( g );
            shapeList.addLast( shape );
        }
        
        // If a line is in progress, draw it on top of all others
        if ( currentShape != null )
            currentShape.draw( g );
    }
    
    /* This method will remove the last element in shapeList if it isn't empty, push it to redoStack,
     * and call repaint(). This method accepts and returns no values.
     */
    public void undo() {
        if ( !( shapeList.isEmpty() ) ){
            redoStack.push( shapeList.removeLast() );
            repaint();
        }
    }
    
    /* This method will pop the first element in redoStack if it isn't empty, add it to the end of shapeList,
     * and call repaint(). This method accepts and returns no values.
     */
    public void redo() {
        if ( !( redoStack.isEmpty() ) ){
            shapeList.addLast( redoStack.pop() );
            repaint();
        }
    }
    
    /* This method will call the clear method for both shapeList and redoStack, and call repaint(). 
     * This method accepts and returns no values.
     */
    public void clear() {
        shapeList.clear();
        redoStack.clear();
        repaint();
    }
    
    /* This is the mutator method for shapeType. This method accepts 1 parameter: an integer value representing the
     * type of shape the user wants to draw. This method returns no values.
     */
    public void setShapeType( int shapeType ) {
        this.shapeType = shapeType;
    }
    
    /* This is the mutator method for currentColor. This method accepts 1 parameter: a reference to a Color object.
     * This method returns no values.
     */
    public void setColourOne( Color colour ) {
        colourOne = colour;
    }
    
    public void setColourTwo( Color colour ) {
        colourTwo = colour;
    }
    
    /* This is the mutator method for isFilled. This method accepts 1 parameter: an boolean value representing whether
     * the shape is filled or not. This method returns no values.
     */
    public void setFill( boolean isFilled ) {
        this.isFilled = isFilled;
    }
    
    public void setIsGradient( boolean isGradient ) {
        this.isGradient = isGradient;
    }
    
    public void setLineSize( double lineSize ){
        this.lineSize = lineSize;
    }
    
    public void setLineStyle(  ) {
    }
    
    public void setDashLineLength( double dashLineLength ) {
        this.dashLineLength = dashLineLength;
    }
}