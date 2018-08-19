/*
 * Name: Thomas Cheng
 * 
 * Date: May 2, 2018
 * 
 * Description: This is the test class for the SuperPaint program
 */

import javax.swing.JFrame;

class SuperPaint {
    public static void main(String[] args) {
        DrawFrame application = new DrawFrame();
        
        application.setSize( 1500, 700 );
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.setVisible( true );
    }
}