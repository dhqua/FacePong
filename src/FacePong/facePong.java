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

        JFrame window = new JFrame("Face Pong");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        
        ballPanel panel = new ballPanel();
        
        
        
        window.add(panel, BorderLayout.CENTER);
        window.setSize(1000,500);
        
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        System.out.println(panel.getWidth());
        

    }
    
}
