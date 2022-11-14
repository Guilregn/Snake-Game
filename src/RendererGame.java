import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import javax.imageio.ImageIO;


public class RendererGame extends JPanel implements ActionListener,KeyListener{
	
	public static  void RendertheGame() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
		
		RendererGame Rg = new RendererGame();
        Rg.addKeyListener(Rg);       
        Rg.setFocusable(true);
        
		int width = (int)size.getWidth(); 
		int height = (int)size.getHeight(); 
		int window_w = (width*75)/100;
		int window_h = (height*75)/100;
		
		JFrame f = new JFrame();
		
		f.setTitle("Snake Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(Rg);
		f.pack();
		f.setSize(window_w, window_h);
		f.setVisible(true);
    }
	

		int board_size = 16;	
	    snake s = new snake();
		apple a = new apple();
		boolean appleMatch = false;
		int[] dir = {1,0};
		
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			g.fillRect(100, 50, 40*board_size, 40*board_size);
		 
			g.setColor(Color.BLACK);
			for (int i=0; i< board_size+1; i++) {
				g.drawLine(100, i*40+50, 40*board_size+100, i*40+50); 
				g.drawLine(i*40+100, 50, i*40+100, 40*board_size+50);
			}
	 
			g.setColor(Color.GREEN);
			g.fillRect(100 + 40 * (s.shead[0]-1), 60 + 40 * (s.shead[1]-1), 20, 20);		 
			g.fillPolygon(new int[] {120 + 40 * (s.shead[0]-1), 120 + 40 * (s.shead[0]-1), 140 + 40 * (s.shead[0]-1)}, new int[] {50 + 40 * (s.shead[1]-1),90 + 40 * (s.shead[1]-1),70 + 40 * (s.shead[1]-1)}, 3);
			
			for(int i = 0; i < s.snakeLength - 1; i++) {
				g.fillOval(100 + 40*(s.stail[i][0]-1), 50 + (s.stail[i][1]-1)*40, 40, 40);	
			}
			
			g.setColor(Color.RED);
			g.fillOval(100 + 40*(a.pos[0]-1), 50 + (a.pos[1]-1)*40, 40, 40);
			
			if (s.alive == 0) {
					
			}
			 
		 }
	 
		private int pressed;
	   
	    public RendererGame() {
			s.Init();
			a.Init(s);
            this.repaint();
			appleMatch = false;
			System.out.println(dir[0]);
		    Timer timer = new Timer(500, this);
	        timer.start();
			}
	    	
	    @Override
        public void actionPerformed(ActionEvent e) {
             	if(s.alive == 1) {
             	             	
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
             		
             		s.checkDeadlyMove(dir);	
                     	
                 	if(s.alive != 0) {

            	
             			if (s.shead[0] + dir[0] == a.pos[0] && s.shead[1] + dir[1] == a.pos[1]) {
             				appleMatch = true;
             				a = new apple();
             				a.Init(s);
             			}
    	    	
             			s.move(dir, appleMatch);
             			System.out.println(s.stail[0][0]);
             			appleMatch = false;
             			this.repaint();
             		} 
                 	else {
                 		JButton GO = new JButton("Game Over");
                 		GO.setSize(600, 400);
                 		GO.setFont(new Font("Arial", Font.PLAIN, 100));
                 		
                 		this.add(GO);
                 		GO.setLocation(500,200);
                 		this.repaint();
                 	}
             	}
             	
             	if(s.alive == 2) { 
             		JButton GO = new JButton("It's won !");
             		GO.setSize(600, 400);
             		GO.setFont(new Font("Arial", Font.PLAIN, 100));
             		
             		this.add(GO);
             		GO.setLocation(500,200);
             		this.repaint();	
             	}
	        
	        }
	    
		public void keyTyped(KeyEvent e) {
	        return;
	    }

	    public void keyPressed(KeyEvent e) {
	        pressed = e.getKeyCode();
	    }

	    public void keyReleased(KeyEvent e) {
	        return;
	    }
		 
	
}
