package game;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.jar.Attributes.Name;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class Register extends JFrame {
	JPanel contentPane;
	JTextField txt_id, txt_pw;
	JLabel lb1, lb2;
	
	
	public Register() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lb1 = new JLabel("ID : ");
		lb1.setBounds(50, 50, 100, 30);
		contentPane.add(lb1);
		
		lb2 = new JLabel("PW : ");
		lb2.setBounds(50, 85, 100, 30);
		contentPane.add(lb2);

		txt_id = new JTextField();
		txt_id.setEditable(true);
		txt_id.setBounds(100, 50, 150, 30);
		contentPane.add(txt_id);
		
		txt_pw = new JTextField();
		txt_pw.setEditable(true);;
		txt_pw.setBounds(100, 85, 150, 30);
		contentPane.add(txt_pw);
		
		JButton btnRegister = new JButton();
		btnRegister.setText("회원가입");
		btnRegister.setBounds(260, 50, 100, 65);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txt_id.getText();
				String pw = txt_pw.getText();
				
				if(id.isEmpty() || pw.isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 정확히 입력해주세요");
					return;
				}
				
				String postURL = "http://serverip:8080/SachunServer/register.jsp"; // jsp 주소
				
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
		                if (result[1].equals("overlap")) {    //중복
		                	JOptionPane.showMessageDialog(null, "중복된 아이디입니다");
		                } else if (result[1].equals("success")) { // 가입완료
		                	JOptionPane.showMessageDialog(null, "가입완료");
		                }
		            }
		        }catch(IOException e1){
		        	JOptionPane.showMessageDialog(null, "서버 접속 오류");
		            e1.printStackTrace();
		        }
		    }
		});			
		
		
		contentPane.add(btnRegister);
		setVisible(true);
	}

}
