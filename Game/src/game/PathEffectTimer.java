package game;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PathEffectTimer implements ActionListener {

	private ImageIcon pathImg;
	private int pathDirection;
	private JButton card;
	private Timer t;
	private JPanel panel;
	
	PathEffectTimer(JButton card, int pathDirection, JPanel panel){
		this.card = card;
		this.pathDirection = pathDirection;
		this.panel = panel;
	}
	
	public void setPathImage()
	{
		pathImg = new ImageIcon("src/direction/clearLine_" + pathDirection + ".png");
		card.setVisible(true);
		card.setEnabled(false);
		card.setContentAreaFilled(false);
		card.setDisabledIcon(new ImageIcon(pathImg.getImage().getScaledInstance(50,60,Image.SCALE_SMOOTH)));
		
		card.setBorderPainted(false);
		card.setIcon(new ImageIcon(pathImg.getImage().getScaledInstance(50,60,Image.SCALE_SMOOTH)));
		t = new Timer(100, this);
		t.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		t.stop();
		card.setEnabled(true);
		card.setVisible(false);
		panel.setBounds(0,0,1000,600);
		panel.setBounds(50,100,1000,600);
		panel.repaint();
	}

}
