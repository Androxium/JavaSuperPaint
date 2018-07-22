/*
 * Name: Thomas Cheng
 * 
 * Date: May 2, 2018
 * 
 * Dsscription: This is the draw frame that will create the options bar and a PaintPanel object.
 */

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class DrawFrame extends JFrame{
    // instance variables used throughout the class
    private PaintPanel paintPanel;
    private JPanel optionsPanel;
    private JLabel statusLabel;
    private JButton undoButton;
    private JButton redoButton;
    private JButton clearButton;
    private JButton colorButton;
    private JColorChooser colorChooser;
    private JComboBox<String> shapeChooser;
    private String[] shapeName = { "Line", "Rectangle", "Oval" };
    private JCheckBox fillCheckBox;
    private Color currentColor;
    
    /* This is the constructor method for this class. This method accepts and returns no values.
     */
    public DrawFrame() {
        super( "SuperPaint Application!" );
        setLayout( new BorderLayout() );
        setBackground( Color.WHITE );
        currentColor = Color.BLACK;
        
        // creating the event listeners for the checkbox, combo box, and buttons
        ItemListener itemEventListener = new ItemEventListener();
        ActionListener eventListener = new ButtonEventListener();
        
        // creating the options panel
        optionsPanel = new JPanel();
        optionsPanel.setLayout( new GridLayout( 1, 6, 5, 5 ) );
        
        // creating the buttons, associating them with eventListener, and adding them to optionsPanel
        // undo button
        undoButton = new JButton( "Undo" );
        undoButton.addActionListener( eventListener );
        optionsPanel.add( undoButton );
        // redo button
        redoButton = new JButton( "Redo" );
        redoButton.addActionListener( eventListener );
        optionsPanel.add( redoButton );
        // clear button
        clearButton = new JButton( "Clear" );
        clearButton.addActionListener( eventListener );
        optionsPanel.add( clearButton );
        // the button that will bring up a colour palette, and creating a JColorChooser object
        colorButton = new JButton( "Colours" );
        colorButton.addActionListener( eventListener );
        optionsPanel.add( colorButton );
        colorChooser = new JColorChooser();
        // creating the combo box for choosing which shape to draw, and adding it to optionsPanel
        shapeChooser = new JComboBox<String>( shapeName );
        shapeChooser.setMaximumRowCount( 3 );
        shapeChooser.addItemListener( itemEventListener );
        optionsPanel.add( shapeChooser );
        
        // creating the fill checkbox and adding it to optionsPanel
        fillCheckBox = new JCheckBox( "Filled" );
        fillCheckBox.setEnabled( false );
        fillCheckBox.addItemListener( itemEventListener );
        optionsPanel.add( fillCheckBox );
        
        // creating the coordinate label, and PaintPanel object
        statusLabel = new JLabel(); 
        paintPanel = new PaintPanel( statusLabel );
        paintPanel.setBackground( Color.WHITE );
        paintPanel.setShapeColor( currentColor );
        
        // add the different objects various parts of the frame  
        add( optionsPanel, BorderLayout.NORTH );
        add( paintPanel, BorderLayout.CENTER );
        add( statusLabel, BorderLayout.SOUTH );
    }
    
    /* This inner class implements ActionListener, and creates a custom actionPerformed method.
     */
    class ButtonEventListener implements ActionListener {
        /* This overriden method will run depending on which button the user has pressed. This method accepts 1 
         * parameter: a reference to an ActionEvent object. This method returns no values.
         */
        @Override
        public void actionPerformed( ActionEvent event ){
            // if the colour button was pressed, display the color pallete
            if ( event.getSource() == colorButton ){
                currentColor = colorChooser.showDialog( null, "Pick a Colour", currentColor );
                paintPanel.setShapeColor( currentColor );
            }
            // if the undo, redo, or clear button was pressed, call paintPanel's respective methods
            if ( event.getSource() == undoButton ) {
                paintPanel.undo();
            }
            else if ( event.getSource() == redoButton ) {
                paintPanel.redo();
            }
            else if ( event.getSource() == clearButton ){
                paintPanel.clear();
            }
        }
    }
    
    /* This inner class implements ItemListener, and is for the shape type combo box, and the check box.
     */
    class ItemEventListener implements ItemListener {
        /* This overriden method is the listener for the combo box and check box. This method accepts 1 parameter:
         * a reference to an ItemEvent object. This method returns no values.
         */
        @Override
        public void itemStateChanged( ItemEvent event ){
            // if the shape combo box was selected
            if ( event.getSource() == shapeChooser ){
                // determining if the shape selected was a line, rectangle, or oval
                if ( shapeChooser.getSelectedIndex() == 0 ){
                    fillCheckBox.setEnabled( false );
                }
                else {
                    fillCheckBox.setEnabled( true );
                }
                // set the shape type to the selected index
                paintPanel.setShapeType( shapeChooser.getSelectedIndex() );
            }
            // checking to see if the user selected the fill box
            if ( event.getSource() == fillCheckBox ) {
                paintPanel.setFill( fillCheckBox.isSelected() );
            }
        }   
    }
}