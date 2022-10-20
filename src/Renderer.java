import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Renderer extends Frame {
	
    // getScreenSize() returns the size of the screen in pixels 
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
    private int width = (int)size.getWidth(); 
    private int height = (int)size.getHeight(); 
    
    private int window_w = (width*75)/100;
    private int window_h = (height*75)/100;
    
    private int buttonSize_w = 100;
    private int buttonSize_h = 50;
    private int buttonPosition_w = (window_w/2)-(buttonSize_w/2);
    private int buttonPosition_h = window_h/2;

    private int gamenameSize_w = 600;
    private int gamenameSize_h = 200;
    private int gamenamePosition_w = (window_w/2)-(gamenameSize_w/2);
    private int gamenamePosition_h = (window_h/2)-gamenameSize_h;
  
    
	Frame window = new Frame();
	Button button_1 = new Button("Start Game");
	Button button_2 = new Button("Exit");
	JLabel gameName = new JLabel("Snake Game");
	
	
	public Renderer(){
		button_1.setBounds(buttonPosition_w, buttonPosition_h, buttonSize_w, buttonSize_h);
		button_2.setBounds(buttonPosition_w, buttonPosition_h+buttonSize_h+25, buttonSize_w, buttonSize_h);
		gameName.setBounds(gamenamePosition_w, gamenamePosition_h, gamenameSize_w, gamenameSize_h);
		gameName.setFont(new Font(gameName.getName(), 65, 100));
		
		window.add(button_1);
		window.add(button_2);
		window.add(gameName);
		
		button_1.addActionListener(new jouer());
		button_2.addActionListener(new Sortir());
		
		window.setSize(window_w, window_h);
		window.setTitle("Snake Game");
		
		window.setLayout(null);
		window.setVisible(true);
		
	}
	
	public class jouer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			window.dispose();
		}
	}

	class Sortir implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
}
