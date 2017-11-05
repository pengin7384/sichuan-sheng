package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.Timer;

public class GameSoundTimer implements ActionListener {

	//private GameSound gameSound;
	private Hashtable<String, GameSound> gamesound_table;
	private String keyV;
	private int time;
	private int delay;
	Timer t;
	GameSoundTimer(Hashtable<String, GameSound> gamesound_table, String keyV){
		this.gamesound_table = gamesound_table;
		this.keyV = keyV;
		if(keyV == "BGM")
			delay = 1300;
		else if(keyV == "ReadyGo")
			delay = 3000;
		t = new Timer(delay, this);
		t.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(keyV);
		if(keyV=="BGM")
			gamesound_table.get(keyV).Play(true); // 메인 BGM은 반복 재생
		else
			gamesound_table.get(keyV).Play();
		t.stop();
		
	}

}
