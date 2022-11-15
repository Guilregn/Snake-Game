import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.time.*;
import javax.imageio.ImageIO;

// Class that starts / process the game with keyboard inputs and action listener on a Panel
public class RendererGame extends JPanel implements ActionListener,KeyListener{
 	
 	// "Main" method : Initiate Frame and Panel
	public static  void RendertheGame() {
		// Get dimension of the screen of the computer to create a Frame with right width and length
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
		
		// this
		RendererGame Rg = new RendererGame();
        Rg.addKeyListener(Rg); // KeyListener included in class
        Rg.setFocusable(true);
        
		int width = (int)size.getWidth(); 
		int height = (int)size.getHeight(); 
		int window_w = (width*75)/100;
		int window_h = (height*75)/100;
		
		JFrame f = new JFrame();
		
		f.setTitle("Snake Game");
		
		// Make the exit button work
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(Rg);
		f.pack();
		f.setSize(window_w, window_h);
		f.setVisible(true);

    }
	
		
		int board_size = 16; // Size of the board	
	    snake s = new snake(); 
		apple a = new apple();
		boolean appleMatch = false;
		int[] dir = {1,0}; // Direction of the head of the snake
		
		
		@Override // Create the graphics of the board, snake, apple...
		public void paintComponent(Graphics g) {
			
			// White board (rectangle)
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			g.fillRect(100, 50, 40*board_size, 40*board_size);
			
			// Black grid (Lines) 
			g.setColor(Color.BLACK);
			for (int i=0; i< board_size+1; i++) {
				g.drawLine(100, i*40+50, 40*board_size+100, i*40+50); 
				g.drawLine(i*40+100, 50, i*40+100, 40*board_size+50);
			}
	 
			// Green snake
			g.setColor(Color.GREEN);
			// Head of the snake (rectangle + triangle) with its position in a cell depending on its leaning (direction)
			if(dir[0] == -1) {
			g.fillRect(120 + 40 * (s.shead[0]-1), 60 + 40 * (s.shead[1]-1), 20, 20);		 
			g.fillPolygon(new int[] {120 + 40 * (s.shead[0]-1), 120 + 40 * (s.shead[0]-1), 100 + 40 * (s.shead[0]-1)}, new int[] {50 + 40 * (s.shead[1]-1),90 + 40 * (s.shead[1]-1),70 + 40 * (s.shead[1]-1)}, 3);
			}
			
			else if(dir[0] == 1) {
				g.fillRect(100 + 40 * (s.shead[0]-1), 60 + 40 * (s.shead[1]-1), 20, 20);		 
				g.fillPolygon(new int[] {120 + 40 * (s.shead[0]-1), 120 + 40 * (s.shead[0]-1), 140 + 40 * (s.shead[0]-1)}, new int[] {50 + 40 * (s.shead[1]-1),90 + 40 * (s.shead[1]-1),70 + 40 * (s.shead[1]-1)}, 3);
			}
			
			else if(dir[1] == 1) {
				g.fillRect(110 + 40 * (s.shead[0]-1), 50 + 40 * (s.shead[1]-1), 20, 20);		 
				g.fillPolygon(new int[] {100 + 40 * (s.shead[0]-1), 140 + 40 * (s.shead[0]-1), 120 + 40 * (s.shead[0]-1)}, new int[] {70 + 40 * (s.shead[1]-1),70 + 40 * (s.shead[1]-1),90 + 40 * (s.shead[1]-1)}, 3);
			}
			
			else {
				g.fillRect(110 + 40 * (s.shead[0]-1), 70 + 40 * (s.shead[1]-1), 20, 20);		 
				g.fillPolygon(new int[] {100 + 40 * (s.shead[0]-1), 140 + 40 * (s.shead[0]-1), 120 + 40 * (s.shead[0]-1)}, new int[] {70 + 40 * (s.shead[1]-1),70 + 40 * (s.shead[1]-1),50 + 40 * (s.shead[1]-1)}, 3);
			}
			
			// Tail of the snake (multiple circles)
			for(int i = 0; i < s.snakeLength - 1; i++) {
				g.fillOval(100 + 40*(s.stail[i][0]-1), 50 + (s.stail[i][1]-1)*40, 40, 40);	
			}
			
			// Red Apple (circle)
			g.setColor(Color.RED);
			g.fillOval(100 + 40*(a.pos[0]-1), 50 + (a.pos[1]-1)*40, 40, 40);
			
			if (s.alive == 0) {
					
			}
			 
		 }
	    
		// pressed is the key code of the last pressed arrow key which is a legal move
		private int pressed;
	   
		// Initialization of Panel, apple, snake...
	    public RendererGame() {
			s.Init();
			a.Init(s);
            this.repaint();
			appleMatch = false;
			
			// Initialization of a Timer : each 500 ms, the function actionPerformed will play
			// Action performed will move the snake accordingly to KeyListener and update the graphics of the panel
		    Timer timer = new Timer(500, this);
	        timer.start();
			}
	    	
	    @Override // Move the snake accordingly to actionListener
        public void actionPerformed(ActionEvent e) {
	    	    // If the snake is not alive, he doesn't move
             	if(s.alive == 1) {
             	    
             		// Change the direction according to last arrow key pressed (doesn't change anything is last pressed direction is the opposite of current dirction)
             		if(pressed == KeyEvent.VK_UP && dir[1] == 0) {
             			dir[0] = 0;
             			dir[1] = -1;
             		}
             
             		else if(pressed == KeyEvent.VK_DOWN && dir[1] == 0) {
             			dir[0] = 0;
             			dir[1] = 1;
             		}
             
             		else if(pressed == KeyEvent.VK_LEFT && dir[0] == 0) {
             			dir[0] = -1;
             			dir[1] = 0;
             		}
             
             		else if(pressed == KeyEvent.VK_RIGHT && dir[0] == 0) {
             			dir[0] = 1;
             			dir[1] = 0;
             		}
             		
             		// Check if according to the new direction, the snake will be dead
             		s.checkDeadlyMove(dir);	
                     	
                 	if(s.alive != 0) {
                 		// If the head of the snake is going to an apple...
             			if (s.shead[0] + dir[0] == a.pos[0] && s.shead[1] + dir[1] == a.pos[1]) {
             				// Snake will eat apple (grows in size)
             				appleMatch = true;
             			}
    	    	        
             			// Make the snake move towards dir
             			s.move(dir, appleMatch);
             			
             			if (appleMatch && s.alive != 2) {
             				// If snake ate apple and do not fill all cells, create new apple
             				a = new apple();
             				a.Init(s);
             			}
             			
             			//Reset apple position
             			appleMatch = false;
             			
             			// Create new graphics of the new situation (new Frame of the game, snake moved)
             			this.repaint();
             		} 
                 	
                 	else { // If alive == 0 (Dead)
                 		
                 		// Game Over button in the center of the Frame
                 		JButton GO = new JButton("Game Over");
                 		GO.setSize(600, 400);
                 		GO.setFont(new Font("Arial", Font.PLAIN, 100));
                 		
                 		this.add(GO);
                 		GO.setLocation(500,200);
                 		this.repaint();
                 	}
             	}
             	
             	if(s.alive == 2) { // If alive == 2 (Win)
             	    // Win screen in the center of the Frame
             		JButton GO = new JButton("It's won !");
             		GO.setSize(600, 400);
             		GO.setFont(new Font("Arial", Font.PLAIN, 100));
             		
             		this.add(GO);
             		GO.setLocation(500,200);
             		this.repaint();	
             	}
	        
	        }
	    
	    // Key listener functions : only use the case when a key is pressed
		public void keyTyped(KeyEvent e) {
	        return;
	    }

		// When a Key is pressed, give pressed the key code of it
	    public void keyPressed(KeyEvent e) {
	        pressed = e.getKeyCode();
	    }

	    public void keyReleased(KeyEvent e) {
	        return;
	    }
		 
	
}
