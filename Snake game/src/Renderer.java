import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Renderer extends Frame {
	Frame f = new Frame();
	Button b = new Button("Start Game");
	Button b2 = new Button("Exit");
	JLabel l = new JLabel("Snake Game");
	
	public Renderer(){
		b.setBounds(400,450,200,100);
		b2.setBounds(400,600,200,100);
		l.setBounds(200,100,600,300);
		l.setFont(new Font(l.getName(), 65, 100));
		
		f.add(b);
		f.add(b2);
		f.add(l);
		
		b.addActionListener(new jouer());
		b2.addActionListener(new Sortir());
		
		f.setSize(1000,3000);
		f.setTitle("Snake Game");
		
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
