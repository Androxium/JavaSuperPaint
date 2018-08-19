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
import javax.swing.JTextField;
import javax.swing.JOptionPane;

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
    private JLabel lineSizeLabel;
    private JLabel dashLineLengthLabel;
    private JButton undoButton;
    private JButton redoButton;
    private JButton clearButton;
    private JButton colourButton;
    private JButton secondGradButton;
    private JButton confirmLineSizeButton;
    private JColorChooser colourChooser;
    private JColorChooser secondGradColour;
    private JComboBox<String> shapeChooser;
    private JComboBox<String> lineStyleChooser;
    private JTextField lineSizeTextField;
    private JTextField dashLineLengthTextField;
    private String[] shapeName = { "Line", "Rectangle", "Oval" };
    private String[] lineStyleName = {"Solid", "Dashed"};
    private JCheckBox fillCheckBox;
    private JCheckBox gradientCheckBox;
    private Color colourOne;
    private Color colourTwo;
    
    /* This is the constructor method for this class. This method accepts and returns no values.
     */
    public DrawFrame() {
        super( "SuperPaint Application!" );
        setLayout( new BorderLayout() );
        setBackground( Color.WHITE );
        colourOne = Color.BLACK;
        colourTwo = Color.BLACK;
        
        // creating the event listeners for the checkbox, combo box, and buttons
        ItemListener itemEventListener = new ItemEventListener();
        ActionListener eventListener = new ButtonEventListener();
        
        // creating the options panel
        optionsPanel = new JPanel();
        optionsPanel.setLayout( new GridLayout( 1, 14, 5, 5 ) );
        
        // creating the coordinate label, and PaintPanel object
        statusLabel = new JLabel(); 
        paintPanel = new PaintPanel( statusLabel );
        
        paintPanel.setBackground( Color.WHITE );
        paintPanel.setColourOne( colourOne );
        paintPanel.setColourTwo( colourTwo );
        
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
        colourButton = new JButton( "Colour One" );
        colourButton.addActionListener( eventListener );
        optionsPanel.add( colourButton );
        colourChooser = new JColorChooser();
        
        // adding the secondary gradient colour
        secondGradButton = new JButton("Colour Two");
        secondGradButton.addActionListener( eventListener );
        optionsPanel.add( secondGradButton );
        secondGradColour = new JColorChooser();
        
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
        
        // gradient checkbox
        gradientCheckBox = new JCheckBox( "Gradient" );
        gradientCheckBox.addItemListener( itemEventListener );
        optionsPanel.add( gradientCheckBox );
        
        // line style drop down menu
        lineStyleChooser = new JComboBox<String>( lineStyleName );
        lineStyleChooser.setMaximumRowCount( 2 );
        lineStyleChooser.addItemListener( itemEventListener );
        optionsPanel.add( lineStyleChooser );
        
        // textfield for the user to enter the specified line length
        dashLineLengthTextField = new JTextField( 3 );
        dashLineLengthTextField.setEnabled( false );
        dashLineLengthLabel = new JLabel( "Dash Line Length", JLabel.RIGHT );
        optionsPanel.add( dashLineLengthLabel );
        optionsPanel.add( dashLineLengthTextField );
        
        // line width text field with label
        lineSizeLabel = new JLabel( "Line Size", JLabel.RIGHT );
        lineSizeTextField = new JTextField( "3", 4 );
        paintPanel.setLineSize( Double.parseDouble(lineSizeTextField.getText()) );
        
        // confirm button for the textfields
        confirmLineSizeButton = new JButton( "Confirm" );
        confirmLineSizeButton.addActionListener( eventListener );
        
        optionsPanel.add( lineSizeLabel );
        optionsPanel.add( lineSizeTextField );
        optionsPanel.add( confirmLineSizeButton );
        
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
            // if the colour button was pressed, display the colour pallete
            if ( event.getSource() == colourButton ){
                colourOne = colourChooser.showDialog( null, "Pick a Colour", colourOne );
                paintPanel.setColourOne( colourOne );
            }
            // if the second gradient button is clicked
            else if ( event.getSource() == secondGradButton ) {
                colourTwo = colourChooser.showDialog( null, "Pick a Colour", colourTwo );
                paintPanel.setColourTwo( colourTwo );
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
            
            if (event.getSource() == confirmLineSizeButton ){
                try{
                    double temp = Double.parseDouble( lineSizeTextField.getText() );
                    if (temp < 0){
                        JOptionPane.showMessageDialog(null, "Invalid input for line size. Setting the line size to 3.");
                        lineSizeTextField.setText("3");
                        temp = 3.0;
                    }
                    paintPanel.setLineSize( temp );
                }
                catch(NumberFormatException numberFormatExpection){
                    JOptionPane.showMessageDialog(null, "Invalid input for line size. Setting the line size to 3.");
                    lineSizeTextField.setText("3");
                    paintPanel.setLineSize( 3.0 );
                }
                
                try{
                    double temp = Double.parseDouble( dashLineLengthTextField.getText() );
                    if (temp < 0){
                        JOptionPane.showMessageDialog(null, "Invalid input for line size. Setting the line size to 3.");
                        lineSizeTextField.setText("3");
                        temp = 3.0;
                    }
                    paintPanel.setDashLineLength( temp );
                }
                catch(NumberFormatException numberFormatExpection){
                    JOptionPane.showMessageDialog(null, "Invalid input for line size. Setting the line size to 3.");
                    dashLineLengthTextField.setText("3");
                    paintPanel.setDashLineLength( 3.0 );
                }
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
            // if the line style combo box was selected
            if ( event.getSource() == lineStyleChooser ){
                if (lineStyleChooser.getSelectedIndex() == 0){
                    dashLineLengthTextField.setEnabled( false );
                }
                else {
                    dashLineLengthTextField.setEnabled( true );
                    
                }
            }
            // checking to see if the user selected the fill box
            if ( event.getSource() == fillCheckBox ) {
                paintPanel.setFill( fillCheckBox.isSelected() );
            }
            // checking to see if the gradient checkbox is selected
            if (event.getSource() == gradientCheckBox) {
                paintPanel.setIsGradient( gradientCheckBox.isSelected() );
            }
        }   
    }
}