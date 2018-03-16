package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import game.CController.GameTimerTask;

//import 사천성.CData.Path;

public class CView{
	private final int MAX_X = 22;
	private final int MAX_Y = 12;
	private final ArrayList<JButton> list = new ArrayList<JButton>();	
	private final int panel_X = 50;
	private final int panel_Y = 100;
	
	CController cController;
	BaseDraw base;
	JFrame frame;
	Intro intro;
	SelectMapPage smp;
	StageNotice sn;
	FailedNotice fn;
	ClearNotice cn;
	ComboEffect ce;
	MenuDraw md;
	
	GameSound gs;
	GameSoundTimer gst;
	
	/////////////////// 로그인관련
	JTextField txt_id;
	JTextField txt_pw;
	///////////////////
	
	/////////////////// 콤보 이미지를 위한 마우스 좌표
	private int mouseX;
	private int mouseY;
	/////////////////
	private ImageIcon backCardImg = new ImageIcon("src/source/card_back.png");
	private Hashtable<String, GameSound> gamesound_table;
	private String[] gamesoundlist = { "BGM", "Select", "Delete1", "Delete2", "ReadyGo",
										"Combo3", "Combo4", "Combo5", "Combo8", "Combo9", "Combo10",
										"Hint", "Shuffle", "Hammer", "Clear", "TimeOver", "Warning","FailBgm", "Error"};
	private int deleteSoundCnt;
	JLayeredPane lpane = new JLayeredPane();
	JPanel panel = new JPanel(new GridLayout(MAX_Y,MAX_X));

	CView(CController cController) {
		this.cController = cController;
	}


	public void disableButton() {
		for(int y=0; y<MAX_Y-2; y++) {
			for(int x=0; x<MAX_X-2; x++) {
				int index = y * MAX_X + x;
				list.get(index).setEnabled(false);
			}
			
		}
	}
	
	public void enableButton() {
		for(int y=0; y<MAX_Y-2; y++) {
			for(int x=0; x<MAX_X-2; x++) {
				int index = y * MAX_X + x;
				list.get(index).setEnabled(true);
			}			
		}
	}
	
	public void clearButton(int x, int y) {
		 int index = y * MAX_X + x;
	      list.get(index).setVisible(false);
	}
	
	public void comboEffect(int combo){ // 1129 세윤 추가
		ce = new ComboEffect(combo);
		ce.setBounds(30, 30, 200, 200);
		lpane.add(ce, new Integer(110));
	}
	
	public void requestFoc() {		
		frame.requestFocus();
	}
	
	public void SoundLoad(){
		gamesound_table = new Hashtable<String, GameSound>();
		for(String loadingListItem : gamesoundlist){
			gs = new GameSound("src/source/Sound/" + loadingListItem + ".wav");
			gamesound_table.put(loadingListItem, gs);
		}
	}
	
	public void warngingControl(boolean mode){
		if(mode)
			gamesound_table.get("Warning").Play();
		else
			gamesound_table.get("Warning").Stop();
	}
	
	public void timeoverPlay(){
		gamesound_table.get("TimeOver").Play();
	}
	
	public void failBgmPlay(){
		gamesound_table.get("FailBgm").Play();
	}
	
	public void clearPlay(){
		gamesound_table.get("Clear").Play();
	}
	
	public void hintPlay(){
		gamesound_table.get("Hint").Play();
	}
	
	public void shufflePlay(){
		gamesound_table.get("Shuffle").Play();
	}
	
	public void hammerPlay(){
		gamesound_table.get("Hammer").Play();
	}
	
	public void errorPlay(){
		gamesound_table.get("Error").Play();
	}
	
	
	
	public void setRemainCardHud()
	{
		base.setCardHud(cController.getRemovableCard(), cController.getTotalCard());
	}
	
	public void setRemainItemHud()
	{
		base.setItemHud(cController.getHintItemCnt(), cController.getShuffleItemCnt(), cController.getHammerItemCnt());
	}
	
	public void setRemainTimeHud(int time)
	{
		base.setRemainTime(time);
	}
	
	public void frameSetting(){
		frame = new JFrame("사천성"){
        	public Dimension getPreferredSize(){
        		Dimension size = super.getPreferredSize();
        		size.width = 1150;
        		size.height = 770;
				return size;
        	}
        };
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setStageSelectPage(){ // 1127 세윤	
		
		
		smp = new SelectMapPage();
		smp.setBounds(0, 0, 1150, 770);
		lpane.add(smp, new Integer(10));
		 MouseAdapter mouseListener = new MouseAdapter() {
	        	public void mouseClicked(MouseEvent e){
	        		super.mouseClicked(e);
	        	}
			};	    
			
			frame.setLayout(new BorderLayout());
			frame.add(lpane, BorderLayout.CENTER);
			lpane.setBounds(0, 0, 1130, 750);
			
			JButton[] btnStage = new JButton [6];	
			JButton btnLogout = new JButton();
			
			btnLogout.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Logout");
					lpane.remove(smp);
					setIntro();
				}
			});
			
			for(int i=0; i<6; i++) {
				final int temp = i;
				btnStage[i] = new JButton();
				btnStage[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("View action");
						frame.remove(intro);
						panel.removeAll();
						lpane.removeAll();
						cController.startStage(temp+1);
						if(cController.getOriginalRun() == false)
						{
							sn.openFlush();
						}
						openDraw();
						cController.setOriginalRun(false);
					}
				});			
				//btnStage[i].setBounds(100+i*50, 500, 50, 50);
				btnStage[i].setText(String.valueOf(i));
				//smp.add(btnStage[i]);
			}
			
			btnStage[0].setBounds(70, 150, 250, 110);
			btnStage[1].setBounds(250, 390, 250, 110);
			btnStage[2].setBounds(100, 560, 250, 110);
			btnStage[3].setBounds(770, 160, 250, 110);
			btnStage[4].setBounds(680, 340, 250, 110);
			btnStage[5].setBounds(630, 500, 250, 110);
			
			///////////////// 스테이지 적용
			
			String id = cController.cData.getID();
			
			if(id.equals("test")) { // 서버없이 테스트용 계정
	            for(int i=0; i<5; i++) {
	            	btnStage[i].setEnabled(true);
	            }		
			} else {
		
				String postURL = "http://serverip:8080/SachunServer/getStage.jsp"; // jsp 주소
				
				try{
		            HttpClient client = new DefaultHttpClient();
		            HttpPost post = new HttpPost(postURL);
		            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	
		            params.add(new BasicNameValuePair("id",id));
	
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
		            String recv = sb.toString();
		            String[] result = recv.split(":");  // jsp로부터 들어온 응답 데이터를 ':' 단위로 자른다.
	
		            int clearStage = Integer.parseInt(result[1]);
		            for(int i=0; i<5; i++) {
		            	btnStage[i].setEnabled(false);
		            	if(i<=clearStage)
		            		btnStage[i].setEnabled(true);
		            }
		            
		        }catch(IOException e1){
		        	JOptionPane.showMessageDialog(null, "서버 접속 오류");
		            e1.printStackTrace();
		        }
			}
			
			//////////////////////////
			
			btnLogout.setBounds(980, 10, 105, 40);
			ImageIcon logoutImg = new ImageIcon("src/source/책덮기.png");
			btnLogout.setIcon(new ImageIcon(logoutImg.getImage().getScaledInstance(105, 40, Image.SCALE_SMOOTH)));			
			
			for(int i=0; i<6; i++){
				ImageIcon stageIcon;
				stageIcon = new ImageIcon("src/source/"+ (i+1) + "단원.png");
				btnStage[i].setIcon(new ImageIcon(stageIcon.getImage().getScaledInstance(250, 110, Image.SCALE_SMOOTH)));
				btnStage[i].setContentAreaFilled(false);
				btnStage[i].setBorderPainted(false);
				smp.add(btnStage[i]);
			}
			smp.add(btnLogout);
			
			

			
			
			frame.pack();
			frame.setVisible(true);
	}
	
	public void setIntro(){ // 1128 세윤 수정
        	
        intro = new Intro();
        Font introFont = new Font("굴림", Font.BOLD, 20);
        MouseAdapter mouseListener = new MouseAdapter() {
        	public void mouseClicked(MouseEvent e){
        		super.mouseClicked(e);
        		System.out.println("in View Listener");
        	}
		};
		
		JButton btnLogin = new JButton();
		JButton start_btn = new JButton();
		JButton btnRegister = new JButton();
		JLabel label_id = new JLabel();
		JLabel label_pw = new JLabel();
		
		label_id.setText("ID");
		label_id.setForeground(Color.white);
		label_id.setFont(introFont);
		label_id.setBounds(400, 560, 30, 30);
		label_id.setVisible(false);
		
		label_pw.setText("PW");
		label_pw.setForeground(Color.white);
		label_pw.setFont(introFont);
		label_pw.setBounds(390, 600, 40, 30);
		label_pw.setVisible(false);
		
		intro.add(label_id);
		intro.add(label_pw);
		
		txt_id = new JTextField();
		txt_id.setEditable(true);
		txt_id.setFont(introFont);
		txt_id.setBounds(430, 560, 150, 35);
		intro.add(txt_id);
		
		txt_pw = new JTextField();
		txt_pw.setEditable(true);
		txt_pw.setFont(introFont);
		txt_pw.setBounds(430, 600, 150, 35);
		intro.add(txt_pw);
		
		ImageIcon enterbtn_img = new ImageIcon("src/source/Enter.png");
		btnLogin.setBounds(600, 555, 105, 40);
		btnLogin.setIcon(new ImageIcon(enterbtn_img.getImage().getScaledInstance(105, 40, Image.SCALE_SMOOTH)));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txt_id.getText();
				String pw = txt_pw.getText();

				if(id.isEmpty() || pw.isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 정확히 입력해주세요");
					return;
				}
				
				if(id.equals("test") && pw.equals("test")) {
					JOptionPane.showMessageDialog(null, "테스트 모드");
                	cController.cData.setID(id);
                	frame.remove(intro);
                	setStageSelectPage();								
				} else {
				
					String postURL = "http://serverip:8080/SachunServer/login.jsp"; // jsp 주소
					
					try{
			            HttpClient client = new DefaultHttpClient();
			            HttpPost post = new HttpPost(postURL);
			            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	
			            params.add(new BasicNameValuePair("id",id));
			            params.add(new BasicNameValuePair("pw",pw));
	
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
			            String recv = sb.toString();
			            String[] result = recv.split(":");  // jsp로부터 들어온 응답 데이터를 ':' 단위로 자른다.
	
			            if(result.length>1) {
			                if (result[1].equals("fail")) {    //중복
			                	JOptionPane.showMessageDialog(null, "로그인 실패");
			                } else if (result[1].equals("success")) { // 가입완료
			                	JOptionPane.showMessageDialog(null, "로그인 완료");
			                	cController.cData.setID(id);
			                	frame.remove(intro);
			                	setStageSelectPage();
			                }
			            }
			        }catch(IOException e1){
			        	JOptionPane.showMessageDialog(null, "서버 접속 오류");
			            e1.printStackTrace();
			        }
				}
		    }
		});			
		intro.add(btnLogin);
		
		ImageIcon registerbtn_img = new ImageIcon("src/source/Register.png");
		btnRegister.setBounds(600, 600, 105, 40);
		btnRegister.setIcon(new ImageIcon(registerbtn_img.getImage().getScaledInstance(105, 40, Image.SCALE_SMOOTH)));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Register action");
				new Register();				
			}
		});			
		intro.add(btnRegister);
		
		JButton btnScore = new JButton();
		ImageIcon btnScoreImg = new ImageIcon("src/source/순위보기.png");
		btnScore.setBounds(590, 460, 200, 70);
		btnScore.setIcon(new ImageIcon(btnScoreImg.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH)));
		//btnScore.setText("점수판");
		btnScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Score();
			}
		});		
		intro.add(btnScore);
		
		//////////////////////////////////////////////
		
		
		
		intro.setLayout(null);
		ImageIcon start_btn_img = new ImageIcon("src/source/start_btn_img.png");
		start_btn.setBounds(340, 460, 200, 70);
		start_btn.setIcon(new ImageIcon(start_btn_img.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH)));
		intro.add(start_btn);
		
		btnLogin.setVisible(false);
		btnRegister.setVisible(false);
		txt_id.setVisible(false);
		txt_pw.setVisible(false);
		
		start_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("View action");
				//frame.remove(intro);
				btnLogin.setVisible(true);
				btnRegister.setVisible(true);
				txt_id.setVisible(true);
				txt_pw.setVisible(true);
				label_id.setVisible(true);
				label_pw.setVisible(true);
				//setStageSelectPage();
				//JOptionPane.showMessageDialog(null, "아이디" + cController.cData.getID());
				//cController.startStage(stage);
				/*if(cController.getOriginalRun() == false)
				{
					sn.openFlush();
				}
				openDraw();*/
			}
		});
		
		frame.add(intro);
		
		
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setMenu(){
		md = new MenuDraw();
		md.setBounds(430, 250, 268, 282);
		
		
		JButton btnReturn = new JButton();
		JButton btnStage = new JButton();
		JButton btnExit = new JButton();
		
		ImageIcon btnReturnImg = new ImageIcon("src/source/돌아가기.png");
		ImageIcon btnStageImg = new ImageIcon("src/source/단원선택.png");
		ImageIcon btnExitImg = new ImageIcon("src/source/책던지기.png");
		
		btnReturn.setBounds(34, 30, 200, 60);
		btnStage.setBounds(34, 110, 200, 60);
		btnExit.setBounds(34, 190, 200, 60);
		
		btnReturn.setIcon(new ImageIcon(btnReturnImg.getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH)));
		btnStage.setIcon(new ImageIcon(btnStageImg.getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH)));
		btnExit.setIcon(new ImageIcon(btnExitImg.getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH)));
		
		md.add(btnReturn);
		md.add(btnStage);
		md.add(btnExit);
				
		lpane.add(md, new Integer(120));
		
		btnReturn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				//cController.setKeyPrevention(true);
				cController.timerResume();
				lpane.remove(md);
			}
		});
		
		btnStage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamesound_table.get("BGM").Stop();
				viewClear();
				frame.removeKeyListener(cController);
				setStageSelectPage();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void selectEffect(int x, int y, int map[][]){  // sy - 패 선택시 이미지 변경 
		System.out.println("select");
		int index = y * MAX_X + x;
		ImageIcon sic;
		gamesound_table.get("Select").Play();
		sic = new ImageIcon("src/source/selected_Card/" + map[y][x] + "_selected.png");
		list.get(index).setIcon(new ImageIcon(sic.getImage().getScaledInstance(45, 50, Image.SCALE_SMOOTH)));
	}
	
	public void deselectEffect(int x, int y, int map[][]){
		int index = y * MAX_X + x;
		ImageIcon dic;
		dic = new ImageIcon("src/source/Card/" + map[y][x] + "_card.png");
		list.get(index).setIcon(new ImageIcon(dic.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH)));	
	}
	
	public void hintEffect(int x, int y, int map[][]){
		int index = y * MAX_X + x;
		ImageIcon hic;
		hic = new ImageIcon("src/source/selected_Card/" + map[y][x] + "_selected.png");
		list.get(index).setIcon(new ImageIcon(hic.getImage().getScaledInstance(45, 50, Image.SCALE_SMOOTH)));
	}
	
	public void pathEffect(Path[] pathData, int pathMax) {
	
		int index;
		System.out.println(pathMax);
		ImageIcon pathImg;
		PathEffectTimer pet;
		if(pathMax>2)
		{
			for(int i=1; i<pathMax-1; i++){
				index = pathData[i].getY() * MAX_X + pathData[i].getX();
				pet = new PathEffectTimer(list.get(index), pathData[i].getDirection(), panel);
				pet.setPathImage();
			}
		}
	}
	
	public void openDraw(){
		frame.remove(intro);
		sn = new StageNotice(cController.getCurrentStage());
		sn.setBounds(0, 0, 1150, 770);
        lpane.add(sn, new Integer(50)); // 대망의 패널 겹치기
        frame.pack();
		frame.setVisible(true);
		gst = new GameSoundTimer(gamesound_table, "ReadyGo");
		gst = new GameSoundTimer(gamesound_table, "BGM");
	}
	
	public void comboSound(int combo)  //1109 세윤추가
	{
		
		if(combo<3 || (combo>5 && combo<8)){
			deleteSoundCnt++;
			gamesound_table.get("Delete" + deleteSoundCnt).Play();
			if(deleteSoundCnt == 2)
				deleteSoundCnt = 0;
		}
		else if((combo>=3 && combo<=5) || (combo>=8 && combo<=10))
			gamesound_table.get("Combo" + combo).Play();
	}
	
	public void failedDraw()  //1109 세윤추가
	{
		gamesound_table.get("BGM").Stop();
		fn = new FailedNotice();
		fn.setBounds(230, 200, 800, 600);
		lpane.add(fn, new Integer(60));
	
		
		JButton back_btn = new JButton();
		
        MouseAdapter mouseListener = new MouseAdapter() {
        	public void mouseClicked(MouseEvent e){
        		super.mouseClicked(e);
        	}
		};
		
		back_btn.addActionListener(new ActionListener() { // 1129 세윤 수정
			@Override
			public void actionPerformed(ActionEvent e) {
				//initialize
				viewClear();
				frame.removeKeyListener(cController);
				//cController.setOriginalRun(false); // 최초 실행 여부 false
				setStageSelectPage();
				//setIntro();
			}
		});
		fn.setLayout(null);
		ImageIcon back_btn_img = new ImageIcon("src/source/back_btn_img.png");
		back_btn.setBounds(200, 380, 200, 70);
		back_btn.setIcon(new ImageIcon(back_btn_img.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH)));
		fn.add(back_btn);
		frame.pack();
		frame.setVisible(true);

	}
	
	public void clearDraw()
	{
		gamesound_table.get("BGM").Stop();
		cn = new ClearNotice();
		cn.setBounds(200, 200, 800, 600);
		lpane.add(cn, new Integer(70));
		
		JButton back_btn = new JButton();
		
        MouseAdapter mouseListener = new MouseAdapter() {
        	public void mouseClicked(MouseEvent e){
        		super.mouseClicked(e);
        	}
		};
		
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//initialize
				viewClear();
				frame.removeKeyListener(cController);
				//cController.setOriginalRun(false); // 최초 실행 여부 false
				//setIntro();
				setStageSelectPage();
			}
		});
		cn.setLayout(null); //형 파란카드가 사라져요 큰일
		ImageIcon back_btn_img = new ImageIcon("src/source/back_btn_img.png");
		back_btn.setBounds(200, 380, 200, 70);
		back_btn.setIcon(new ImageIcon(back_btn_img.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH)));
		cn.add(back_btn);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void viewClear()
	{
		list.clear();
		panel.removeAll();
		lpane.removeAll();
		//cController.initialize();	
	}
	
	public void mapChange(int map[][]) {
		shufflePlay();
	    panel.removeAll();
	    list.clear();
	    ImageIcon bi; 
	      
	    for(int i=0; i<MAX_Y; i++)
	    {
	    	for(int j=0; j<MAX_X; j++)
	        {
	    		JButton gb = createGridButton(j, i);
	                
	            bi = new ImageIcon("src/source/Card/"+ map[i][j] + "_card.png"); // 맵 뿌릴때 카드 이미지 결정
	            gb.setIcon(new ImageIcon(bi.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH)));
	           
	            list.add(gb);
	            panel.add(gb);
	            gb.addKeyListener(cController); // 키인식
	            
                if(map[i][j] == 151){
                	bi = new ImageIcon("src/source/card_keyhole_r.png");
                	gb.setIcon(new ImageIcon(bi.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH)));
                }
              
                else if(map[i][j] == 161){
                	bi = new ImageIcon("src/source/card_keyhole_b.png");
                	gb.setIcon(new ImageIcon(bi.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH)));
                }
                
                else if(map[i][j] == 999){
                	bi = new ImageIcon("src/source/block_card.png");
                	gb.setIcon(new ImageIcon(bi.getImage().getScaledInstance(47, 50, Image.SCALE_SMOOTH)));
                }
                
                if(map[i][j] == 100 )
                	gb.setVisible(false);
	               
	        }           
	    }      
	}
	
	public void cardTurnBack(){
		int index;
		for(int x=0; x<MAX_X; x++){
			for(int y=0; y<MAX_Y; y++){
				index = y * MAX_X + x;
				if(list.get(index).isVisible())
					list.get(index).setIcon(new ImageIcon(backCardImg.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH)));
			}
		}
		
	}
	
	public void viewMap(int map[][]) {

		frame.remove(intro);
		lpane.remove(smp);
		ImageIcon bi;
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		base = new BaseDraw(cController.getRemainTime(), cController.getRemovableCard(), cController.getTotalCard(), 
				cController.getHintItemCnt(), cController.getShuffleItemCnt(), cController.getHammerItemCnt()); 

		//frame.setLayout(new BorderLayout());
		//frame.add(lpane, BorderLayout.CENTER);
		//lpane.setBounds(0, 0, 1130, 750);
        
        for(int i=0; i<MAX_Y; i++)
        {
        	for(int j=0; j<MAX_X; j++)
        	{
                JButton gb = createGridButton(j, i);
                
                bi = new ImageIcon("src/source/Card/"+ map[i][j] + "_card.png"); // 맵 뿌릴때 카드 이미지 결정
            	gb.setIcon(new ImageIcon(bi.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH)));
           
                list.add(gb);
                panel.add(gb);
                gb.addKeyListener(cController); // 키인식
                
                if(map[i][j] == 151){
                	bi = new ImageIcon("src/source/card_keyhole_r.png");
                	gb.setIcon(new ImageIcon(bi.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH)));
                }
                
                else if(map[i][j] == 161){
                	bi = new ImageIcon("src/source/card_keyhole_b.png");
                	gb.setIcon(new ImageIcon(bi.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH)));
                }
                
                else if(map[i][j] == 999){
                	bi = new ImageIcon("src/source/block_card.png");
                	gb.setIcon(new ImageIcon(bi.getImage().getScaledInstance(47, 50, Image.SCALE_SMOOTH)));
                }
                
                if(map[i][j] == 100 )
                	gb.setVisible(false);
                               
        	}        	
        }				
        panel.setBackground(new Color(0, 0, 0, 1));
        
        base.setBounds(0, 0, 1150, 770);
        lpane.add(base, new Integer(15));

        panel.setBounds(panel_X, panel_Y, 1000,600);
        lpane.add(panel, new Integer(30));
   
        cController.timeControl();
		frame.pack();
		frame.setVisible(true);	
		
		///////////////////////////////////////////////////////
		frame.addKeyListener(cController); // 키인식
		frame.requestFocus();
		////////////////////////////////////////////////////////
		
	}
	
	public void redraw() 
	{
		panel.setBounds(0,0,1000,600);
		panel.setBounds(panel_X,panel_Y,1000,600);
		panel.repaint();
	}
	
	private static class RoundedBorder implements Border {

        private int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
	
	private JButton createGridButton(final int row, final int col) {
	    final JButton b = new JButton();
	    	b.setBorder(new RoundedBorder(10));
	    	
	    	b.setName(row + "," + col + ",");
	        b.setPreferredSize(new Dimension(50,60));
	        b.addActionListener(cController);
	        
	        return b;
	  }
}
