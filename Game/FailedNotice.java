package game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class FailedNotice extends JComponent {

	private ImageIcon failed_img = new ImageIcon("src/source/FailedAni.gif");
	
	FailedNotice(){
		
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics gc = g.create();
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		failed_img.paintIcon(this, g2d, 0, 0);
	}
	
}
