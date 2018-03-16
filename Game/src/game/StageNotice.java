package game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

public class StageNotice extends JComponent{

	private ImageIcon stageNotice_img;
	
	public StageNotice(int stage){
		stageNotice_img = new ImageIcon("src/source/DoorOpenAni_"+ stage + "S.gif");
	}

	public void paintComponent(Graphics g){
		Graphics gc = g.create();
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		stageNotice_img.paintIcon(this, g2d, 0, 0);
	}
	
	public void openFlush()
	{
		stageNotice_img.getImage().flush();
	}
	
}
