package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import game.CData.ComboTimerTask;

//import 사천성.CData.Path;

public class CController implements ActionListener, KeyListener {
	CData cData;
	CView cView;
	Intro intro;
	GameTimerTask gameTimerTask;
	Timer timeCounting;

	private boolean keyPrevention = true;
	
	// private int limited_time = 5;
	private boolean press = false;
	private int start_x, start_y;
	private int dest_x, dest_y;
	
	// /////////////////////1128 황세윤//////////////////////////////
	private int currentStage; 
	private boolean usingHammer;
	// /////////////////////// /////////////////////
	private boolean originalRun; // 최초 실행인지 아닌지 구분


	CController() {
		cData = new CData();
		cView = new CView(this);
		originalRun = true; // 최초실행 여부 : true
		intro();
	}

	public void timeControl() { // 세윤 추가, 게임 시간 재기
		gameTimerTask = new GameTimerTask();
		timeCounting = new Timer();
		timeCounting.schedule(gameTimerTask, 4500, 1000);
	}

	public class GameTimerTask extends TimerTask {
		@Override
		public void run() {
			cData.map.decTime();
			cView.setRemainTimeHud(cData.map.getTime());
			if (cData.map.getTime() == 7)
				cView.warngingControl(true);
			else if (cData.map.getTime() <= 0) {
				if(cData.map.getMode() == 0) { // 타임어택 모드
					//점수디스플레이 추가
					//디비 score 쏴주기
					cView.cardTurnBack();
					String id = cData.getID();	
					
					if(id.equals("test")) {
						
					} else {
						
						String postURL = "http://serverip:8080/SachunServer/update.jsp"; // jsp 주소
						
						try{
				            HttpClient client = new DefaultHttpClient();
				            HttpPost post = new HttpPost(postURL);
				            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	
				            params.add(new BasicNameValuePair("id",id));
				            params.add(new BasicNameValuePair("record",String.valueOf(cData.map.getScore())));
	
				            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				            post.setEntity(ent);
	
				            HttpResponse responsePOST = client.execute(post);
				            HttpEntity resEntity = responsePOST.getEntity();
				            InputStream is = resEntity.getContent();
				            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
	
				            StringBuilder sb = new StringBuilder();
				            String line = null;
	
				            reader.readLine();
				            while ((line = reader.readLine()) != null) {
				                sb.append(line).append("\n");
				            }
				            is.close();
	
				        }catch(IOException e1){
				        	JOptionPane.showMessageDialog(null, "서버 접속 오류");
				            e1.printStackTrace();
				        }
					}
			    }
					
				else {
					cView.disableButton(); // 클릭 불가능하게
					cView.warngingControl(false);
					cView.failBgmPlay();
					cView.timeoverPlay();
					cView.failedDraw();
					cView.cardTurnBack();
					timeCounting.cancel();
				}
			}
		}
	}

	public void timerResume(){
		//System.out.println("remain time : " + cData.map.getTime());
		gameTimerTask = new GameTimerTask();
		timeCounting = new Timer();
		timeCounting.schedule(gameTimerTask, 0, 1000);
	}
	
	public int getRemainTime() {
		return cData.map.getTime();
	}

	// public void initialize() {
	// limited_time = 30; // 스테이지 정보에 있는 시간으로 초기화 해줄 것, 추후 수정 필요
	// }

	public boolean getOriginalRun() {
		return originalRun;
	}

	public void setOriginalRun(boolean originalRun) {
		this.originalRun = originalRun;
	}

	// ///////////////////////////////////////////////////
	/*
	 * 1120 최상희 public void start() { cView.viewMap(cData.getMap()); }
	 */

	public void startStage(int stage) {
		//cView.enableButton();
		currentStage = stage;
		cView.viewMap(cData.loadStage(stage));
	}

	// ///////////////////////////////////////////////////////
	
	// /////////////////////1128 황세윤//////////////////////////////
	public int getCurrentStage()
	{
		return currentStage;
	}
	// ///////////////////////////////////////////////////
	public void intro() {
		cView.frameSetting();
		cView.setIntro();
		cView.SoundLoad();
	}

	public int getRemovableCard() {
		return cData.map.totalPossible();
	}

	public int getTotalCard() {
		return cData.map.totalCard();
	}
	
	public int getHintItemCnt(){
		return cData.map.getItem();
	}
	
	public int getShuffleItemCnt(){
		return cData.map.getItem2();
	}

	public int getHammerItemCnt(){
		return cData.map.getItem3();
	}

	public Path[] getPathData() {
		return cData.map.getPathData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String[] temp = btn.getName().split(",");
		


		if (cData.map.checkItem3En()) {
			int temp_x = Integer.parseInt(temp[0]);
			int temp_y = Integer.parseInt(temp[1]);

			
			if (cData.map.getPosData(temp_x, temp_y) == 151
					|| cData.map.getPosData(temp_x, temp_y) == 161
					|| cData.map.getPosData(temp_x, temp_y) == 999
					|| cData.map.getPosData(temp_x, temp_y) == 100
					|| cData.map.getPosData(temp_x, temp_y) == 170
					|| cData.map.getPosData(temp_x, temp_y) == 150
					|| cData.map.getPosData(temp_x, temp_y) == 160)
				return;
			
			Path temp2 = cData.map.deleteCardLikeThis(temp_x, temp_y);

			
			if (temp2 != null) {
				int temp_x2 = temp2.getX();
				int temp_y2 = temp2.getY();

				clearCard(temp_x, temp_y, temp_x2, temp_y2);
				


				cData.map.setItem3En(false);
			}

			return;
		}

		if (press == false) {
			start_x = Integer.parseInt(temp[0]);
			start_y = Integer.parseInt(temp[1]);			
			
			if (cData.map.getPosData(start_x, start_y) == 151
					|| cData.map.getPosData(start_x, start_y) == 161
					|| cData.map.getPosData(start_x, start_y) == 999
					|| cData.map.getPosData(start_x, start_y) == 100)
				return;
			/*
			if (cData.map.getPosData(start_x, start_y) == 151
					|| cData.map.getPosData(start_x, start_y) == 161
					|| cData.map.getPosData(start_x, start_y) == 999
					|| cData.map.getPosData(start_x, start_y) == 100)
				return;
*/
			press = true;
			cView.selectEffect(start_x, start_y, cData.map.getData());
		} else {
			dest_x = Integer.parseInt(temp[0]);
			dest_y = Integer.parseInt(temp[1]);

			if (cData.isCorrect(start_x, start_y, dest_x, dest_y)) {

				int backup = cData.map.getPosData(start_x, start_y);
				clearCard(start_x, start_y, dest_x, dest_y);
				cView.pathEffect(cData.map.getPathData(), cData.map.getPathMax());
				
				if (cData.map.totalCard() == 0 || (cData.map.getMode()==1 && backup == 170) ) {
					cView.cardTurnBack();
					cView.disableButton(); // 클릭 불가능하게
					cView.clearPlay();
					cView.clearDraw();
					timeCounting.cancel();
					stageClear();
				}
				
				else if(backup == 150) { // 레드 열쇠 제거시
					for(int y=1; y<=10; y++) {	// 수정 필요
						for(int x=1; x<=20; x++) {
							if(cData.map.getPosData(x, y) == 151) {
								cData.map.setPosData(x, y, 100);
								cView.clearButton(x, y);
							}
						}
					}							
					cView.redraw();
				}
				else if(backup == 160) { // 파란색 열쇠 제거시
					for(int y=1; y<=10; y++) {	// 수정 필요
						for(int x=1; x<=20; x++) {
							if(cData.map.getPosData(x, y) == 161) {
								cData.map.setPosData(x, y, 100);
								cView.clearButton(x, y);
							}
						}
					}							
					cView.redraw();
				}
				
			} else {
				cView.deselectEffect(start_x, start_y, cData.map.getData());
				cView.errorPlay(); // 1129 세윤
			}

			press = false;
		}

	}

	public void clearCard(int x, int y, int x2, int y2) { // 1129세윤 수정
		cData.map.addScore(101);
		
		cData.map.setPosData(x, y, 100);
		cData.map.setPosData(x2, y2, 100);
		cView.clearButton(x, y);
		cView.clearButton(x2, y2);

		cView.setRemainCardHud();
		cView.redraw();
		
		if(usingHammer == false){ // 1129세윤 추가
			if(cData.getCombo() == 4 || cData.getCombo() == 9)
				cData.addCombo(5000);
			else
				cData.addCombo(2000);
			cView.comboEffect(cData.getCombo());
			cView.comboSound(cData.getCombo());
				
		}
		
		if(usingHammer == true){ // 1129세윤 추가
			cView.comboSound(1);
			//cData.reSchedule(cData.getCombo());
			usingHammer = false;
		}
		/* 5개 연속삭제시 자동 아이템 활성화기능 끔
		else if((cData.getCombo() == 5 || cData.getCombo() == 10) && usingHammer == false) // 1129세윤 추가
		{
			cData.map.setItem3En(true);
			usingHammer = true;
			cView.hammerPlay();
			if(cData.map.totalCard() == 0) {
				stageClear();
			}
		}*/
	
		cView.requestFoc();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 49 && cData.map.getItem() > 0) {
			if(cData.map.getPathCount() == 0)
				return;
			
			cData.map.setItem(cData.map.getItem()-1);
			System.out.println("1번 아이템");
			cView.hintPlay();
			int [][] tempMap = cData.map.getData();
			Path[] temp = cData.map.hint();
			System.out.println("힌트:(" + temp[0].getX() + "," + temp[0].getY()
					+ ") (" + temp[1].getX() + "," + temp[1].getY() + ")");
			cView.hintEffect(temp[0].getX(), temp[0].getY(), tempMap);
			cView.hintEffect(temp[1].getX(), temp[1].getY(), tempMap);
			cView.setRemainItemHud();

		} else if (e.getKeyCode() == 50 && cData.map.getItem2() > 0) {
			cData.map.setItem2(cData.map.getItem2()-1);
			System.out.println("2번 아이템");
			cView.shufflePlay();
			cData.map.mixData();
			cView.mapChange(cData.map.getData());
			cView.setRemainItemHud();
			cData.map.totalPossible();
			
			cView.setRemainCardHud();
			cView.redraw();
			
		} else if (e.getKeyCode() == 51 && cData.map.getItem3() > 0) {
			cData.map.setItem3(cData.map.getItem3()-1);
			System.out.println("3번 아이템");
			cView.hammerPlay();
			cData.map.setItem3En(true);
			cView.setRemainItemHud();
			
			if(cData.map.totalCard() == 0) {
				stageClear();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("Show Menu");
			cView.setMenu();
			timeCounting.cancel();
			//keyPrevention = false;
		}
		
	}
	
	private void stageClear() {
		// TODO Auto-generated method stub
		System.out.println(currentStage + "클리어");
		
		String id = cData.getID();		
		
		if(id.equals("test")) {
			
		} else {
			
			String postURL = "http://serverip:8080/SachunServer/updateStage.jsp"; // jsp 주소
			
			try{
	            HttpClient client = new DefaultHttpClient();
	            HttpPost post = new HttpPost(postURL);
	            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	
	            params.add(new BasicNameValuePair("id",id));
	            params.add(new BasicNameValuePair("stage",String.valueOf(currentStage)));
	
	            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
	            post.setEntity(ent);
	
	            HttpResponse responsePOST = client.execute(post);
	            HttpEntity resEntity = responsePOST.getEntity();
	            InputStream is = resEntity.getContent();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
	
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	
	            reader.readLine();
	            while ((line = reader.readLine()) != null) {
	                sb.append(line).append("\n");
	            }
	            is.close();
	
	        }catch(IOException e1){
	        	JOptionPane.showMessageDialog(null, "서버 접속 오류");
	            e1.printStackTrace();
	        }
		}
	}

	public void setKeyPrevention(boolean kp)
	{
		this.keyPrevention = kp;
		System.out.println(keyPrevention);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
