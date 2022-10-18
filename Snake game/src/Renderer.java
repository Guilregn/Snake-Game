import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Renderer extends Frame {
	Frame f = new Frame();
	Button b = new Button("Start Game");
	Button b2 = new Button("Exit");
	JLabel l = new JLabel("Snake Game");
	int width;
	int height;
	
	public Renderer(){
		= -1width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
		f.setSize((int)(3.0/4.0 * width), (int) (3.0/4.0 * height));
		f.setLocation((int)(1.0/8.0 * width), (int) (1.0/8.0 * height));
		f.setTitle("Snake Game");
		
		b.setBounds((int)(9.0/32.0 * width), (int) (35.0/80.0 * height),(int)(3.0/16.0 * width), (int) (3.0/40.0 * height));
		b2.setBounds((int)(9.0/32.0 * width), (int) (45.0/80.0 * height),(int)(3.0/16.0 * width), (int) (3.0/40.0 * height));
		
		int fontSizeToUse = (int) (5.0/40.0 * height);
		l.setBounds((int)(5.0/32.0 * width), (int) (5.0/80.0 * height), width, (int) (5.0/40.0 * height));
		l.setFont(new Font(l.getName(), Font.PLAIN, fontSizeToUse));
		
		f.add(b);
		f.add(b2);
		f.add(l);
		
		b.addActionListener(new jouer());
		b2.addActionListener(new Sortir());
		
		f.setLayout(null);
		f.setVisible(true);
		
	}
	
	public class jouer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.dispose();
		}
	}

	class Sortir implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
}
