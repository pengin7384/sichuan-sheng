package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BaseDraw extends JComponent{
	
	private ImageIcon time_gauge = new ImageIcon("src/source/time_gauge.png");
	private ImageIcon background = new ImageIcon("src/source/wide_game_bg.png");
	private Font hudFont = new Font("±¼¸²", Font.BOLD, 20);
	private int time;
	private int removable;
	private int totalcard;
	private int hint;
	private int shuffle;
	private int hammer;
	private int barWidth = 185;
	
	JLabel removable_hud = new JLabel();
	JLabel total_hud = new JLabel();
	JLabel time_hud = new JLabel();
	JLabel hint_hud = new JLabel();
	JLabel shuffle_hud = new JLabel();
	JLabel hammer_hud = new JLabel();
	
	BaseDraw (int time, int removable, int totalcard, int hint, int shuffle, int hammer){
		
		this.time = time;
		this.removable = removable;
		this.totalcard = totalcard;
		this.hint = hint;
		this.shuffle = shuffle;
		this.hammer = hammer;
		
		removable_hud.setText(String.valueOf(removable));
		removable_hud.setLayout(null);
		removable_hud.setBounds(310, 18, 40, 40);
		
		total_hud.setText(String.valueOf(totalcard));
		total_hud.setLayout(null);
		total_hud.setBounds(130,18,40,40);
		
		time_hud.setText(String.valueOf(time));
		time_hud.setLayout(null);
		time_hud.setBounds(410, 18, 40, 40);
		
		hint_hud.setText(String.valueOf(hint));
		hint_hud.setLayout(null);
		hint_hud.setBounds(785, 32, 40, 40);
		
		shuffle_hud.setText(String.valueOf(shuffle));
		shuffle_hud.setLayout(null);
		shuffle_hud.setBounds(934, 32, 40, 40);
		
		hammer_hud.setText(String.valueOf(hammer));
		hammer_hud.setLayout(null);
		hammer_hud.setBounds(1075, 32, 40, 40);
		
		removable_hud.setFont(hudFont);
		removable_hud.setForeground(new Color(197, 205, 246));
		
		total_hud.setFont(hudFont);
		total_hud.setForeground(new Color(197, 205, 246));
		
		time_hud.setFont(hudFont);
		time_hud.setForeground(new Color(197, 205, 246));
		
		hint_hud.setFont(hudFont);
		hint_hud.setForeground(new Color(197, 205, 246));
		
		shuffle_hud.setFont(hudFont);
		shuffle_hud.setForeground(new Color(197, 205, 246));
		
		hammer_hud.setFont(hudFont);
		hammer_hud.setForeground(new Color(197, 205, 246));
		
		this.add(removable_hud);
		this.add(total_hud);
		this.add(time_hud);
		this.add(hint_hud);
		this.add(shuffle_hud);
		this.add(hammer_hud);
	}
	
	public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			removable_hud.setText(String.valueOf(removable));
			total_hud.setText(String.valueOf(totalcard));
			time_hud.setText(String.valueOf(time));
			
			hint_hud.setText(String.valueOf(hint));
			shuffle_hud.setText(String.valueOf(shuffle));
			hammer_hud.setText(String.valueOf(hammer));
						
			g.drawImage(background.getImage(), 0, 0, background.getIconWidth()+30, background.getIconHeight()+30, null);
			g.drawImage(time_gauge.getImage(), 485, 23, barWidth, 25, null);
	}
	
	public void setCardHud(int removable, int totalcard)
	{
		this.removable = removable;
		this.totalcard = totalcard;
	}
	
	public void setItemHud(int hint, int shuffle, int hammer)
	{
		this.hint = hint;
		this.shuffle = shuffle;
		this.hammer = hammer;
	}
	
	public void setRemainTime(int time)
	{
		this.time = time;
		try{
			barWidth = barWidth - (barWidth/this.time);
		}
		catch(Exception e){
			barWidth = 0;
		}
		repaint();
	}

	

}