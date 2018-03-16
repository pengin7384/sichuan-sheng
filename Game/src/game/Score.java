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
import java.sql.ResultSet;
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

public class Score extends JFrame {
	JPanel contentPane;
	JTextField txt_id, txt_pw;
	JLabel lb1, lb2;
	
	
	public Score() {
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		String postURL = "http://serverip:8080/SachunServer/rank.jsp"; // jsp 주소
		
		try{
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(postURL);
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            
           

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
            
            //System.out.println(recv);
            
            
            //ResultSet recv2 = recv;
            
            //System.out.println("...................");
            String[] result = recv.split(":");  // jsp로부터 들어온 응답 데이터를 ':' 단위로 자른다.
            
            
            for(int i=1; i<=20; i+=2) { 
            	if(result[i].equals("finish")) {
            		break;
            	}
            	//System.out.println(i + "!" + result[i]);
            	JLabel lb1 = new JLabel(""+((i/2)+1));
            	JLabel lb2 = new JLabel(result[i]);
            	JLabel lb3 = new JLabel(result[i+1]);
            	
            	lb1.setBounds(50, i*30, 100, 30);
            	lb2.setBounds(150, i*30, 100, 30);
            	lb3.setBounds(250, i*30, 100, 30);
            	
            	contentPane.add(lb1);
            	contentPane.add(lb2);
            	contentPane.add(lb3);
            	
            	
            }// 됐어유 
            //System.out.println(recv);
        }catch(IOException e1){
        	JOptionPane.showMessageDialog(null, "서버 접속 오류");
            e1.printStackTrace();
        }
		
    
		
		setVisible(true);
	}

}
