package FacePong;

import java.awt.BorderLayout;
import javax.swing.*; 
import java.awt.*;
/**
 *
 * @author Qua
 */
public class facePong 
{

    public static void main(String[] args) 
    {
        // Creates the window for the game
        //--------------------------------
        JFrame window = new JFrame("Face Pong");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        
        //Adds panel to the window
        //----------------------------
        ballPanel panel = new ballPanel();
        
        // Makes panel the main focus of the screen
        // and sets the the size to 1000x500
        //---------------------------------------
        window.add(panel, BorderLayout.CENTER);
        window.setSize(1000,500);
        
        //Centers the windown in the screen and makes it
        // unresizable 
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        
        

    }
    
}
