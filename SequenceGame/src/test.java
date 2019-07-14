import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.*;

public class test extends JFrame {

	public test() {
		
		Container contentPane = getContentPane();
		
		JPanel p = new JPanel();
		p.setLayout(new OverlayLayout(p));
		JButton b = new JButton(new ImageIcon("normalCards/7H.png"));
		JButton t = new JButton();
		ImageIcon i = new ImageIcon("tokens/redToken.png");
		
		Image img = i.getImage();
		Image newImg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);
		
		t.setIcon(newIcon);
		//t.setOpaque(false);
		t.setBorder(null);
		t.setContentAreaFilled(false);
		t.setAlignmentX(CENTER_ALIGNMENT);
		t.setAlignmentY(CENTER_ALIGNMENT);

		b.setOpaque(false);
		b.setAlignmentX(CENTER_ALIGNMENT);
		b.setAlignmentY(CENTER_ALIGNMENT);

		p.add(t);
		p.add(b);
		contentPane.add(p);
		
		setVisible(true);
		setSize(500, 500);
	}
	
	public static void main(String[] args) {
		test t = new test();

	}

}
