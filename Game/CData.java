package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

//import ��õ��.CData.ComboTimerTask;

public class CData {
	private final int MAX_X;   	// �� ũ��
	private final int MAX_Y;   	// �� ũ��
	Random rand;
	Map map;						// ���� �ε�� ��
	private int combo;			// �޺�
	Timer comboTimer;			// �޺� Ÿ�̸�
	private String id;


	CData() {   
		MAX_X = 22;
		MAX_Y = 12;
		combo = 0;
		rand = new Random();
		
		id = "test10";
	}

	//////////////////////////////////////////////////////// �޺� ����
	public void addCombo(int tic) { // 1129 ���� ����
		ComboTimerTask comboTimerTask = new ComboTimerTask();
		if(combo>0) {
			comboTimer.cancel();
		}
		if(combo == 10) // ���� �߰�
			combo = 0;

		comboTimer = new Timer();
		comboTimer.schedule(comboTimerTask, tic); // �����κ�

		combo++;
		System.out.println("�޺�:" + combo);
	}

	public class ComboTimerTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("�޺� ��");
			combo = 0;
		}
	}

	////////////////////////////////////////////////////////



	public int[][] loadStage(int stage) {


		if(stage == 1) {		// Map(���ѽð�, ī������, ���, ��ź����, ������1, ������2, ������3)
			map = new Map(60, 8, 1, 1, 2, 2, 1); 
			int[][] data = {				   
					{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100},
					{100, 100, 100, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 100, 100, 100, 100},
					{100, 100, 100, 999, 100, 100, 100, 100, 100, 100, 151, 101, 108, 108, 107, 107, 170, 999, 100, 100, 100, 100},
					{100, 100, 100, 999, 100, 100, 100, 100, 100, 100, 151, 101, 102, 102, 103, 103, 106, 999, 100, 100, 100, 100},
					{100, 100, 100, 999, 150, 101, 100, 102, 100, 107, 151, 151, 151, 151, 151, 151, 151, 999, 100, 100, 100, 100},
					{100, 100, 100, 999, 102, 100, 103, 100, 102, 100, 103, 100, 108, 100, 106, 100, 102, 999, 100, 100, 100, 100},
					{100, 100, 100, 999, 100, 101, 100, 105, 100, 105, 100, 106, 100, 107, 100, 108, 100, 999, 100, 100, 100, 100},
					{100, 100, 100, 999, 151, 151, 151, 151, 151, 151, 151, 100, 100, 100, 100, 100, 150, 999, 100, 100, 100, 100},
					{100, 100, 100, 999, 101, 101, 102, 102, 103, 103, 151, 100, 100, 100, 100, 100, 100, 999, 100, 100, 100, 100},
					{100, 100, 100, 999, 170, 106, 105, 105, 104, 104, 151, 100, 100, 100, 100, 100, 100, 999, 100, 100, 100, 100},
					{100, 100, 100, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 100, 100, 100, 100},
					{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100}	   
			};

			map.editData(data);  
		}
		else if(stage == 2) {	// ���� ��

			map = new Map(65, 8, 1, 1, 2, 2, 1); 
			int[][] data = {				   
					{999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
					{999, 100, 100, 100, 100, 151, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 160, 103, 100, 151, 100, 100, 100, 150, 101, 102, 107, 101, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 101, 101, 100, 100, 151, 100, 100, 100, 105, 101, 103, 102, 103, 103, 100, 100, 100, 100, 999},
					{999, 100, 100, 103, 105, 107, 100, 100, 151, 100, 100, 100, 103, 102, 103, 104, 105, 106, 100, 100, 100, 999},
					{999, 100, 100, 107, 108, 108, 107, 100, 100, 151, 100, 100, 100, 104, 107, 108, 101, 102, 103, 100, 100, 999},
					{999, 100, 100, 102, 103, 104, 105, 106, 100, 100, 151, 100, 100, 100, 105, 101, 102, 103, 103, 100, 100, 999},
					{999, 100, 100, 101, 102, 103, 104, 105, 106, 100, 100, 161, 100, 100, 100, 106, 104, 102, 105, 100, 100, 999},
					{999, 100, 100, 100, 107, 108, 101, 102, 102, 104, 100, 100, 161, 100, 100, 100, 107, 101, 104, 100, 100, 999},
					{999, 100, 100, 100, 100, 106, 106, 105, 104, 103, 102, 100, 100, 161, 100, 100, 100, 108, 101, 100, 100, 999},
					{999, 100, 100, 100, 100, 100, 107, 108, 101, 102, 103, 160, 100, 100, 161, 100, 100, 100, 150, 100, 100, 999},
					{999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999}	   
			};
			map.editData(data);
		}
		else if(stage == 3) {

			map = new Map(65, 8, 1, 1, 2, 2, 1); 
			int[][] data = {				   
					{100, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
					{100, 999, 100, 100, 100, 100, 101, 108, 102, 101, 106, 107, 108, 102, 101, 105, 100, 100, 100, 100, 100, 999},
					{100, 999, 100, 100, 100, 100, 102, 999, 102, 103, 100, 104, 105, 100, 104, 106, 999, 104, 100, 100, 100, 999},
					{100, 999, 100, 100, 100, 103, 999, 170, 101, 103, 100, 105, 104, 100, 105, 107, 101, 999, 103, 100, 100, 999},
					{100, 999, 100, 100, 101, 999, 999, 101, 102, 100, 100, 100, 100, 100, 106, 108, 105, 999, 999, 104, 100, 999},
					{100, 999, 100, 100, 102, 999, 999, 999, 999, 999, 999, 100, 100, 100, 999, 999, 999, 999, 999, 105, 100, 999},
					{100, 999, 100, 100, 103, 999, 999, 999, 999, 999, 999, 100, 100, 100, 999, 999, 999, 999, 999, 104, 100, 999},
					{100, 999, 100, 100, 104, 999, 999, 101, 105, 101, 100, 100, 100, 100, 100, 101, 102, 999, 999, 103, 100, 999},
					{100, 999, 100, 100, 100, 101, 999, 102, 102, 100, 100, 100, 100, 100, 104, 102, 170, 999, 102, 100, 100, 999},
					{100, 999, 100, 100, 100, 100, 103, 100, 103, 103, 104, 100, 100, 100, 105, 101, 999, 101, 100, 100, 100, 999},
					{100, 999, 100, 100, 100, 100, 100, 105, 106, 107, 107, 108, 101, 102, 103, 105, 104, 100, 100, 100, 100, 999},
					{100, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999}	
			};
			map.editData(data);
		}
		else if(stage == 4) {
			map = new Map(65, 10, 1, 1, 2, 2, 1); 
			int[][] data = {				   
					{100, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 100, 100, 100, 100},
					{100, 999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 108, 101, 100, 999, 100, 100, 100, 100},
					{100, 999, 100, 100, 100, 151, 151, 151, 151, 151, 151, 151, 151, 151, 102, 151, 100, 999, 100, 100, 100, 100},
					{100, 999, 100, 100, 100, 151, 100, 100, 100, 100, 100, 100, 100, 103, 100, 151, 100, 999, 100, 100, 100, 100},
					{100, 999, 100, 100, 100, 100, 151, 100, 100, 100, 100, 100, 104, 100, 151, 100, 100, 999, 100, 100, 100, 100},
					{100, 999, 100, 100, 100, 100, 151, 151, 105, 104, 106, 107, 108, 103, 151, 100, 100, 999, 100, 100, 100, 100},
					{100, 999, 100, 100, 100, 100, 100, 151, 110, 105, 104, 110, 103, 151, 100, 100, 100, 999, 100, 100, 100, 100},
					{100, 999, 100, 100, 100, 150, 100, 151, 104, 106, 108, 110, 108, 151, 100, 150, 100, 999, 999, 999, 100, 100},
					{100, 999, 100, 100, 100, 105, 100, 110, 151, 151, 151, 151, 151, 108, 100, 103, 100, 100, 100, 999, 100, 100},
					{100, 999, 100, 104, 106, 106, 105, 103, 105, 106, 105, 107, 108, 101, 102, 106, 104, 103, 100, 999, 100, 100},
					{100, 999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999, 100, 100},
					{100, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 100, 100},
					
			};
			map.editData(data);
		}
		else if(stage == 5) {
			map = new Map(50, 10, 1, 1, 2, 2, 1); 
			int[][] data = {				   
					{999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 100},
					{999, 100, 100, 100, 150, 107, 105, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999, 100},
					{999, 100, 100, 100, 103, 104, 105, 107, 108, 109, 104, 103, 105, 102, 102, 106, 100, 100, 100, 100, 999, 100},
					{999, 100, 100, 100, 100, 100, 100, 108, 110, 109, 108, 101, 102, 103, 105, 106, 100, 100, 100, 100, 999, 100},
					{999, 100, 100, 100, 100, 100, 101, 100, 102, 103, 102, 104, 103, 108, 160, 102, 107, 100, 100, 100, 999, 100},
					{999, 100, 100, 100, 100, 100, 100, 101, 100, 100, 110, 109, 108, 100, 100, 110, 109, 100, 100, 100, 999, 100},
					{999, 100, 100, 100, 151, 151, 151, 100, 105, 106, 107, 107, 107, 105, 161, 161, 161, 100, 100, 100, 999, 100},
					{999, 100, 100, 151, 102, 104, 104, 151, 100, 100, 106, 107, 108, 161, 110, 106, 106, 161, 100, 100, 999, 100},
					{999, 100, 151, 101, 101, 170, 102, 103, 150, 100, 100, 105, 160, 104, 105, 170, 102, 106, 161, 100, 999, 100},
					{999, 100, 100, 151, 101, 104, 102, 151, 100, 100, 110, 110, 100, 161, 104, 106, 107, 161, 100, 100, 999, 100},
					{999, 100, 100, 100, 151, 151, 151, 100, 100, 100, 100, 100, 100, 100, 161, 161, 161, 100, 100, 100, 999, 100},
					{999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 100},
					
			};
			map.editData(data);
		}
		else if(stage == 6) {
			map = new Map(10, 10, 0, 1, 2, 2, 1); 
			/*int[][] data = {				   
					{999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999},
					{999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 100, 105, 105, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 999},
					{999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999, 999}		   
			};*/

			//map.editData(data);  
		}



		return map.getData();	   
	}



	public boolean isCorrect(int x, int y, int x2, int y2) {
		int backup = map.getPosData(x, y);
		int backup2 = map.getPosData(x2, y2);	   

		if(backup != map.getPosData(x2, y2)) // ī�� ������ �ٸ���
			return false;
		else if(x==x2 && y==y2)	// ī�� ��ġ�� ����
			return false;

		map.setPossible(false);
		map.setTarget(x, y);
		map.setTarget_x(x2);
		map.setTarget_y(y2);
		map.setPathCount(0);
		map.setPosData(x, y, 100);
		map.setPosData(x2, y2, 100);
		map.dfs(x, y, 0, -1);

		map.setPath(map.getPathMax()-1, x2, y2, 8);
		map.setPath(0, x, y, 7);
		map.setPosData(x, y, backup);
		map.setPosData(x2, y2, backup2);

		if(map.isPossible()) {
			map.addDirection();		   
		}

		return map.isPossible();
	}



	public int getCombo(){
		return combo;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}
	



	///////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Map { // �� Ŭ���� �Ŀ� Map.java�� �� ����
		private int time;       	// ���ѽð�
		private int kind;       	// �� ����
		private int mode;       	// ��� (0:�⺻��� 1:�������� �ε� 2:��Ƽ��)
		private int bomb;       	// ��ź ����
		private int[][] data;  		// ��
		private int item;      		// ������1
		private int item2;       	// ������2
		private int item3;       	// ������3
		private boolean item3En;	// ������3 Ȱ��ȭ ����
		Random rand = new Random();

		////////////////////////////////////////// �޼ҵ��
		private boolean possible;
		private int target;
		private int target_x, target_y;	// dfs�� ��������
		////////////////////////////////////////// ������ ��
		private int path_count;
		private int path_max;
		Path []path = new Path [100];

		Path [][]path2 = new Path [100][2];
		private int path2_count;
		private boolean possible2;
		private int original;
		private int dest_x2, dest_y2;
		//////////////////////////////////////////
		private int score;
		//////////////////////////////////////////

		Map(int time, int kind, int mode, int bomb, int item, int item2, int item3) {
			this.time = time;
			this.kind = kind;
			this.mode = mode;
			this.bomb = bomb;
			this.item = item;
			this.item2 = item2;
			this.item3 = item3;         
			this.score = 0;
			data = new int [MAX_Y][MAX_X];
			setPossible(false);
			
			path_max=1; // �������۸��� �¸�ī�� Ŭ���� ���׹���

			if(mode == 0) {	// �⺻ ����ϰ�� �� �ڵ�����
				newData();
			mixData();            
			}
			else if(mode == 1) { // ���� ����̹Ƿ� �������� �߰����ּ���.

			}        

			for(int i=0; i<100; i++) {
				path[i] = new Path();
			}

			for(int i=0; i<100; i++) {
				for(int j=0; j<2; j++) {
					path2[i][j] = new Path();
				}
			}
		}
		
		public void addScore(int score) {
			this.score += score;
		}
		
		public int getScore() {
			return score;
		}
		
		public void setScore(int score) {
			this.score = score;
		}

		void editData(int[][] data) {         // ���� �������� �߰����ִ� �޼ҵ�
			for(int i=0; i<MAX_Y; i++) {
				for(int j=0; j<MAX_X; j++) {
					this.data[i][j] = data[i][j];   
				}
			}
		}

		public void newData() {   // ���� ����
			for(int i=0; i<(MAX_X-2)*(MAX_Y-2); i+=2) {
				int zero = rand.nextInt(1)+1;
				if(zero != 0) {
					int num = rand.nextInt(kind)+1;
					data[i/(MAX_X-2)+1][i%(MAX_X-2)+1] = 100+num;
					data[i/(MAX_X-2)+1][i%(MAX_X-2)+2] = 100+num;   
				}
			}

			for(int i=0; i<MAX_X; i++) {
				data[0][i] = 999;
				data[MAX_Y-1][i] = 999;
			}

			for(int i=0; i<MAX_Y; i++) {
				data[i][0] = 999;
				data[i][MAX_X-1] = 999;
			}
		}

		public void mixData() {   // ���� �����ִ� �޼ҵ�
			int pos_x=1, pos_y=1, pos_x2=1, pos_y2=1, temp;

			for(int i=0; i<10000; i++) {      
				pos_x = rand.nextInt(MAX_X-2)+1;
				pos_y = rand.nextInt(MAX_Y-2)+1;               
				pos_x2 = rand.nextInt(MAX_X-2)+1;
				pos_y2 = rand.nextInt(MAX_Y-2)+1;   

			//if(data[pos_y][pos_x]==100 || data[pos_y2][pos_x2]==100) // ����ִ� ������ �ٲ��� �ʴ´�.
			//		continue;
				if((data[pos_y][pos_x]>100 && data[pos_y][pos_x]<150) && (data[pos_y2][pos_x2]>100 && data[pos_y2][pos_x2]<150))
				{
					temp = data[pos_y][pos_x];
					data[pos_y][pos_x] = data[pos_y2][pos_x2];
					data[pos_y2][pos_x2] = temp;           
				}
			}         
		}


		public void dfs(int x, int y, int direction, int corner_count) {
			int backup = data[y][x];

			if(corner_count > 2 || possible) {
				if(possible)
					path_count --;
				return;
			}

			if(x==target_x && y==target_y) {
				possible = true;
				path_count ++;
				path_max = path_count;
				return;
			}

			data[y][x] = 999;

			int temp_x = 0, temp_y = 0, temp_count = 0;

			for(int i=1; i<=4; i++) {
				temp_count = 0;
				if(i==1) 			{	temp_x = 0;		temp_y = -1;	}
				else if(i==2) 	{	temp_x = 1;		temp_y = 0;		}
				else if(i==3) 	{	temp_x = 0;		temp_y = 1;		}
				else if(i==4) 	{	temp_x = -1;	temp_y = 0;		}

				if(x+temp_x < MAX_X && x+temp_x >= 0 && y+temp_y < MAX_Y && y+temp_y >= 0)
				{
					if(direction != i)
						temp_count = 1;

					if(data[y + temp_y][x + temp_x] == 100) {
						path[path_count++].set(x, y);
						dfs(x + temp_x, y + temp_y, i, corner_count + temp_count);
						if(!possible) {
							path_count--;
						}
					}								
				}	
			}
			data[y][x] = backup; // ��Ʈ��ŷ�� ���󺹱�
		}


		public void addDirection() {

			for(int i=0; i<path_max-2; i++) {
				int x = path[i].getX(), y = path[i].getY();
				int x2 = path[i+1].getX(), y2 = path[i+1].getY();
				int x3 = path[i+2].getX(), y3 = path[i+2].getY();

				path[i+1].setDirection(0);

				if(x-1==x2 && x2-1==x3 && y==y2 && y2==y3) { 			// �� �����ʿ��� ����
					path[i+1].setDirection(1);
				} 
				else if(x+1==x2 && x2+1==x3 && y==y2 && y2==y3) { 		// �� ���ʿ��� ������
					path[i+1].setDirection(1);
				}

				else if(x==x2 && x2==x3 && y+1==y2 && y2+1==y3) { 		// �� ������ �Ʒ�
					path[i+1].setDirection(2);
				}
				else if(x==x2 && x2==x3 && y-1==y2 && y2-1==y3) { 		// �� �Ʒ����� ��
					path[i+1].setDirection(2);
				}

				else if(x==x2 && x2+1==x3 && y+1==y2 && y2==y3) { 		// �� ������ �Ʒ�
					path[i+1].setDirection(3);
				}
				else if(x-1==x2 && x2==x3 && y==y2 && y2-1==y3) { 		// �� �Ʒ����� ��
					path[i+1].setDirection(3);
				}

				else if(x==x2 && x2+1==x3 && y-1==y2 && y2==y3) { 		// �� �Ʒ����� ������
					path[i+1].setDirection(4);
				}
				else if(x-1==x2 && x2==x3 && y==y2 && y2+1==y3) { 		// �� �����ʿ��� �Ʒ�
					path[i+1].setDirection(4);
				}

				else if(x==x2 && x2-1==x3 && y-1==y2 && y2==y3) { 		// �� �Ʒ����� ����
					path[i+1].setDirection(5);
				}
				else if(x+1==x2 && x2==x3 && y==y2 && y2+1==y3) { 		// �� ���ʿ��� �Ʒ�
					path[i+1].setDirection(5);
				}

				else if(x==x2 && x2-1==x3 && y+1==y2 && y2==y3) { 		// �� ������ ��������
					path[i+1].setDirection(6);
				}
				else if(x+1==x2 && x2==x3 && y==y2 && y2-1==y3) { 		// �� ���ʿ��� ����
					path[i+1].setDirection(6);
				}
				else
				{
					System.out.println("��ΰ����� x1(" + path[i].getX() + "," + path[i].getY() + ")" + 
							"x2(" + path[i+1].getX() + "," + path[i+1].getY() + ")" +
							"x3(" + path[i+2].getX() + "," + path[i+2].getY() + ")");
				}
			}
		}

		public int totalCard() {
			int tot=0;
			for(int y=1; y<=MAX_Y-2; y++) {
				for(int x=1; x<=MAX_X-2; x++) {
					if(data[y][x] > 100 && data[y][x] < 200 && data[y][x] != 151  && data[y][x] != 161) {
						tot++;
					}
				}
			}
			return tot;
		}


		public int totalPossible() {
			path2_count = 0;
			
			for(int y=1; y<=MAX_Y-2; y++) {
				for(int x=1; x<=MAX_X-2; x++) {
					
					if(data[y][x] > 100 && data[y][x] < 200 && data[y][x] != 151  && data[y][x] != 161) {
						original = data[y][x];
						data[y][x] = 100;
						possible2 = false;
						dfs2(x, y, 0, -1);
						data[y][x] = original;
						
						
						if(possible2 == true) {
							
							path2[path2_count][0].set(x, y);
							path2[path2_count][1].set(dest_x2, dest_y2);
							data[y][x] += 100;
							data[dest_y2][dest_x2] += 100;
							path2_count++;	
						}
					}				
				}
			}		


			for(int y=1; y<=MAX_Y-2; y++) {
				for(int x=1; x<=MAX_X-2; x++) {
					if(data[y][x] > 200 && data[y][x] < 300) {
						data[y][x] -= 100;
					}
				}
			}
			
			return path2_count;
		}


		public void dfs2(int x, int y, int direction, int corner_count) {
			int backup = data[y][x];

			if(corner_count > 2 || possible2) {
				return;
			}

			if(data[y][x] == original) {
				dest_x2 = x;
				dest_y2 = y;

				possible2 = true;			
				return;
			}

			data[y][x] = 999;
			int temp_x = 0, temp_y = 0, temp_count = 0;

			for(int i=1; i<=4; i++) {
				temp_count = 0;
				if(i==1) 		{	temp_x = 0;		temp_y = -1;	}
				else if(i==2) 	{	temp_x = 1;		temp_y = 0;		}
				else if(i==3) 	{	temp_x = 0;		temp_y = 1;		}
				else if(i==4) 	{	temp_x = -1;	temp_y = 0;		}

				if(x+temp_x < MAX_X && x+temp_x >= 0 && y+temp_y < MAX_Y && y+temp_y >= 0)
				{
					if(direction != i)
						temp_count = 1;				
					if(data[y + temp_y][x + temp_x] == 100 || data[y + temp_y][x + temp_x] == original) {
						dfs2(x + temp_x, y + temp_y, i, corner_count + temp_count);
					}				
				}	
			}
			data[y][x] = backup;		
		}

		public Path[] getPathData() { // ��θ� �Ѱ��� 0������ ���
			return path;
		}


		public Path[] hint() {
			return path2[rand.nextInt(path2_count)];		
		}

		public Path deleteCardLikeThis(int _x, int _y)
		{
			Path temp = null;
			int value = data[_y][_x];

			for(int y=1; y<=MAX_Y-2; y++) {
				for(int x=1; x<=MAX_X-2; x++) {
					if(!(x==_x && y==_y)) {
						if(value == data[y][x]) {
							temp = new Path();
							temp.set(x, y);
							x=999;
							y=999;
						}
					}
				}
			}		

			return temp;
		}
		/*
		public void destroyRed() {
			for(int y=1; y<=MAX_Y-2; y++) {
				for(int x=1; x<=MAX_X-2; x++) {
					if(data[y][x] == 151) {
						data[y][x] = 100;
					}
				}
			}			
		}*/

		///////////////////////////////////////////////////////////////////////////////////////////////////////


		public int[][] getData() 				{ return data; 			}
		public int getPosData(int x, int y) 	{ return data[y][x]; 	}
		public void setPosData(int x, int y, int value) 	{ data[y][x] = value; 	}

		public void addPathCount(int value) 	{ path_count += value; 	}
		public void setPathCount(int value) 	{ path_count = value; 	}
		public int getPathCount()				{ return path_count; 	}
		public void setPathMax(int value) 	{ path_max = value; 	}
		public int getPathMax()				{ return path_max; 	}

		public Path getPath(int index)		{ return path[index];	}
		public void setPath(int index, int x, int y, int direction) { path[index].set(x, y); path[index].setDirection(direction); }


		public int  getTime() 			{ 	return time;  			}
		public void setTime(int time) 	{	this.time = time;     	}
		public void decTime()			{ 	time--;					}
		public int  getKind() 			{ 	return kind;  			}
		public void setKind(int kind) 	{	this.kind = kind;     	}
		public int  getMode() 			{ 	return mode;  			}
		public void setMode(int mode) 	{	this.mode = mode;     	}
		public int  getBomb() 			{ 	return bomb;  			}
		public void setBomb(int bomb) 	{	this.bomb = bomb;     	}
		public int  getItem() 			{ 	return item;  			}
		public void setItem(int item) 	{	this.item = item;     	}
		public int  getItem2() 			{ 	return item2;  			}
		public void setItem2(int item2) 	{	this.item2 = item2;     }      
		public int  getItem3() 			{ 	return item3;  			}
		public void setItem3(int item3) 	{	this.item3 = item3;     }

		public boolean checkItem3En() {
			return item3En;
		}

		public void setItem3En(boolean en) {
			item3En = en;
		}

		public boolean isPossible() {
			return possible;
		}

		public void setPossible(boolean possible) {
			this.possible = possible;
		}

		public int getTarget() {
			return target;
		}

		public void setTarget(int x, int y) {
			target = data[y][x];
		}

		public int getTarget_x() {
			return target_x;
		}

		public void setTarget_x(int target_x) {
			this.target_x = target_x;
		}

		public int getTarget_y() {
			return target_y;
		}

		public void setTarget_y(int target_y) {
			this.target_y = target_y;
		}




	}   


}










