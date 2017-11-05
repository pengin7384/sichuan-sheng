package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Intro extends JPanel{
	
	private ImageIcon intro_img = new ImageIcon("src/source/Intro4.png");
	
	public Intro() {	
	}

	public void paintComponent(Graphics g){
		g.drawImage(intro_img.getImage(), 0, 0, intro_img.getIconWidth()+30, intro_img.getIconHeight()+30, null);
	}
}
