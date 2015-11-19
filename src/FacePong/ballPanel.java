package FacePong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ballPanel extends JPanel implements ActionListener, KeyListener
{
    //Game states will determine what screen
    // the game is currently on
    // ------------------------
    
    boolean onHomeScreen = true;
    boolean curPlaying = false;
    boolean curWaiting = false;
    boolean endScreen = false;
    
    //Paddle controls
    // Varaibles for keyboard listener for left
    // an right paddle
    //-------------------------------------
    
    private boolean upPressed = false;
    private boolean downPressed = false;
    
    private boolean wPressed = false;
    private boolean sPressed = false;
    
    // Paddle color flicker variable
    // Variable inside of switch statement
    // that gives paddles the rainbow effect
    //------------------------------------
    
    private int paddleColor = 1;
    
    // Player one stats
    // Positioning height and width of
    // Player one paddle
    //-----------------------------
    private int playerOneX = 25;
    private int playerOneY = 250;
    private int playerOneWidth = 20;
    private int playerOneHeight = 60;
    
    // Player two stats
    // Positioning height and width of
    // Player one paddle
    //-----------------------------
    
    private int playerTwoX = 955;
    private int playerTwoY = 250;
    private int playerTwoWidth  =20;
    private int playerTwoHeight = 60;
    
    // Paddle Speed
    //------------------------
    private int paddleSpeed = 5;
    
   
    
    //Ball Stats
    // ball positioning, diameter, and speed
    //-----------------------------
    private int ballDiameter = 60;

    private int ballX = 220;
    private int ballY = 220;
    private int xSpeed = 8;
    private int ySpeed=4;
    
    // Game Score
    //--------------------
    
    int playerOneScore = 0;
    int playerTwoScore = 0;
   
    
    // Variables to Import face image
    //-------------------------------------------
    
    // Imports unscaled image
    ImageIcon icon = new ImageIcon("images/ankit_face_scaled.png");
    Image unscaledImage = icon.getImage();
    
    // Scales image to be same size as ball diameter
    //----------------------------------------------
    
    Image scaledImage = unscaledImage.getScaledInstance(ballDiameter, ballDiameter, Image.SCALE_SMOOTH);
    
    
    
    public ballPanel( )
        {
            setBackground(Color.BLACK);
            
            // Add key listener and makes the panel
            // focusable so that it actually 
            // picks up keys 
            //-----------------------------------
            setFocusable(true);
            addKeyListener(this);
            
            // Adds timer to panel which will call the 
            // move ball function and animate the game
            // the *this keyword makes the ballPanel it's
            // own action listener in order to call the 
            // moveBall() function
            // ------------------------------------------
            Timer timer = new Timer(1000/45, this);
            timer.start();
        }

   @Override
    public void actionPerformed(ActionEvent e) 
    {
        // if on the homescreen do not animate
        // -------------------------------------
        if(onHomeScreen)
        {
            
        }
        
        if(curPlaying)
        {
        // Animates the game, see function definition below
        //------------------------------------------------
        moveBall();
        }
    }

    
    // ANY CHANGES MADE HERE WILL DIRECTLY AFFECT
    // GAME ANIMATION!
    //----------------------------------------------
    
    public void moveBall() 
    {
        
        
        //Player one and two paddle positions
    
        int playerOneRight = playerOneX + playerOneWidth;
        int playerOneTop = playerOneY;
        int playerOneBottom = playerOneY + playerOneHeight;
    
        int playerTwoLeft = playerTwoX;
        int playerTwoTop = playerTwoY;
        int playerTwoBottom = playerTwoY + playerTwoHeight;
        
        
        
        //Move paddles
        //-----------------------------
        
        if (wPressed) {
            if (playerOneY-paddleSpeed > 0) {
                playerOneY -= paddleSpeed;
            }
        }
        if (sPressed) {
            if (playerOneY + paddleSpeed + playerOneHeight < getHeight()) {
                playerOneY += paddleSpeed;
            }
        } 
        
        if(upPressed )
        {
            if(playerTwoY - paddleSpeed > 0)
            {
                playerTwoY -= paddleSpeed;
            }
        }
        if(downPressed)
        {
            if(playerTwoY + playerTwoHeight + paddleSpeed < getHeight() )
            {
                playerTwoY += paddleSpeed;
            }
        }
        
    // Bounderies of the ball
    int ballNextLeft = ballX + xSpeed;
    int ballNextRight = ballX + ballDiameter + xSpeed;
    int ballNextTop = ballY + ySpeed;
    int ballNextBottom = ballY + ballDiameter + ySpeed;
    
    
// if ball bounces off the top and bottom of screen
    //---------------------------------------------------------
  
    if ( ballNextTop < 0 || ballNextBottom > getHeight() )
    {
        ySpeed *= -1;
    }
    
    
// BALL-PADDLE COLLISION DETECTION
    //--------------------------------------------------
    
    //Player One
    
    if(ballNextLeft <= playerOneRight)
    {
            if(ballNextTop > playerOneBottom || ballNextBottom < playerOneTop)
            {
                playerTwoScore++;
                xSpeed *= -1;
                ySpeed *= -1;
                
                if(playerTwoScore >= 10)
                {
                    endScreen = true;
                }
                
                curWaiting = true;
            }
            else
            {
                xSpeed *= -1;
                 // Remove after test
                if(xSpeed > 0)
                {
                    xSpeed++;
                    
                    if(xSpeed > 16)
                    {
                        xSpeed = 8; // Default speed
                    }
                }
                
                if(xSpeed < 0)
                {
                    xSpeed--;
                    
                    if(xSpeed < -16)
                    {
                        xSpeed = -8;
                    }
                }
            }
    }
                
             
    
    
    // Player Two
    
    if(ballNextRight  >= playerTwoLeft)
    {
         if(ballNextTop > playerTwoBottom || ballNextBottom < playerTwoTop)
         {
             playerOneScore++;
             xSpeed *= -1;
             ySpeed *= -1;
             
             if(playerOneScore >= 10)
                {
                    endScreen = true;
                }
             
             curWaiting = true;
         }
         else
          {
                xSpeed *= -1;
                 // Remove after test
                if(xSpeed > 0)
                {
                    xSpeed++;
                    
                    if(xSpeed > 16)
                    {
                        xSpeed = 8; // Default speed
                    }
                }
                
                if(xSpeed < 0)
                {
                    xSpeed--;
                    
                    if(xSpeed < -16)
                    {
                        xSpeed = -8;
                    }
                }
                
                
          
         }
    }
    
    // Move the ball
    //------------------------
    if(curPlaying && curWaiting == false )
    {
    ballX += xSpeed;
    ballY += ySpeed;
    repaint();
    }
    // Repaint the panel
    if(curWaiting == true)
    {
         ballX = 490; // Width / 2 - radius = half of screen
         ballY=240;
         repaint();
    }
    
    }
    
    
    
         @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            
            
            if(onHomeScreen)
            {
                 g.setColor(Color.WHITE);
                 g.setFont(new Font(Font.DIALOG, Font.BOLD, 64));
                 g.drawString("Pong", 434, 200);
                 g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
                 g.drawString("Press P to play!", 482, 250);
            }
            
            if(endScreen)
            {
                if(playerOneScore > playerTwoScore)
                {
                    g.setColor(Color.CYAN);
                    g.drawString("Player One Wins!", 200, 250);
                }
                else
                {
                    g.setColor(Color.YELLOW);
                    g.drawString("Player Two Wins!", 700, 250);
                }
            }
            
            if(curPlaying)
            {
            
           g.drawImage(scaledImage, ballX,ballY, this);
            
            
            
            
            
            //Draw Scoreboard
            //---------------------------
            //draw the scores
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
            g.drawString(String.valueOf(playerOneScore), 200, 100);
            g.drawString(String.valueOf(playerTwoScore), 700, 100);

            
            switch(paddleColor)
            {
                case 1:
                    g.setColor(Color.WHITE);
                 
                 // Player One Paddle
                 //-------------------------
                 g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
                 //Player Two Paddle
                 //--------------------------
                 g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
                 
                    paddleColor++;
                    break;
                    
                case 2:
                    g.setColor(Color.CYAN);
                 
                 // Player One Paddle
                 //-------------------------
                 g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
                 //Player Two Paddle
                 //--------------------------
                 
                 g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
                    paddleColor++;
                    break;
                    
                case 3:
                  
                    g.setColor(Color.YELLOW);
                 
                 // Player One Paddle
                 //-------------------------
                 g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
                 //Player Two Paddle
                 //--------------------------
                 g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
                    
                    paddleColor++;
                    break;
                    
                case 4:
                
                 g.setColor(Color.MAGENTA);
                
                 // Player One Paddle
                 //-------------------------
                 g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
                 //Player Two Paddle
                 //--------------------------
                 
                 g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
                    
                    paddleColor++;
                    break;
                
                case 5:
                 
                 g.setColor(Color.GREEN);
                    
                     // Player One Paddle
                 //-------------------------
                 g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
                 //Player Two Paddle
                 //--------------------------
                 g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
                    
                    
                    paddleColor=1;
                    break;
            }
                 
        
        }

        }
        
        // Override keyListener functions
        
        
         public void keyTyped(KeyEvent e) {}



         public void keyPressed(KeyEvent e) 
         {
             
             if(onHomeScreen)
             {
                 if(e.getKeyCode() == KeyEvent.VK_P)
                 {
                     onHomeScreen = false;
                     curPlaying = true;
                 }
             }
             
             if(curWaiting)
             {
                 if(e.getKeyCode() == KeyEvent.VK_SPACE)
                 {
                     curWaiting = false;
                 }
             }
             
             if(curPlaying)
                
            {
              
                if (e.getKeyCode() == KeyEvent.VK_UP) 
                {
                    upPressed = true;
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
                {
                    downPressed = true;
                }
            
                if(e.getKeyCode() == KeyEvent.VK_W)
                {
                    wPressed = true;
                }
                else if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    sPressed = true;
                }
        
            }
         
         }

    
    public void keyReleased(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_UP) 
        {
            upPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
        {
            downPressed = false;
        }
    
         if(e.getKeyCode() == KeyEvent.VK_W)
            {
                wPressed = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_S)
            {
                sPressed = false;
            }
    
    
    
    }

}