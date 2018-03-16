package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

public class ComboEffect extends JComponent implements ActionListener{

	private ImageIcon comboImg;
	private int combo;
	private Timer t;
	private int opacity;
	private float alpha = 1f;
	
	private BufferedImage comboImgB;
	
	ComboEffect(int combo){
		this.combo = combo;
		opacity = 100;
		try {
			comboImgB = ImageIO.read(new File("src/combo/¿¬¼Ó" + combo + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		t = new Timer(100,this);
		t.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
        int x = (getWidth() - comboImgB.getWidth()) / 2;
        int y = (getHeight() - comboImgB.getHeight()) / 2;
        g2d.drawImage(comboImgB, x, y, this);
        g2d.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		opacity-=20;
		alpha -= 0.2;
		repaint();
		if (opacity == 0){
			t.stop();
		}
		
	}

}

