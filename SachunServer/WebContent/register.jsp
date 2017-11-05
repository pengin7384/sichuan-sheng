<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="org.json.simple.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");

	if(id==null || id=="" || pw=="" || id=="")
		return;
	
	Connection conn = null;
	Statement stmt = null;	
	
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost/sachun_db", "root", "apmsetup");
	
	    if (conn == null)
	        throw new Exception("데이터베이스에 연결할 수 없습니다.");
	    stmt = conn.createStatement();
	
	    String command = String.format("Select count(*) as count from user_tb where id=\""+id+"\";");
	    ResultSet rs = stmt.executeQuery(command);       
	    
	    rs.next();
	    int check_count = rs.getInt("count");
	
	    if(check_count > 0) {
	    	out.println(":overlap:");
	        System.out.println("[register]id:"+id+" pw:"+pw+ " 회원가입 거부(아이디 중복)");	
	    	return;
	    }
	
	    String command2 = String.format("insert into user_tb " + 
	                                   "(id, pw, stage, record, total, win, lose) values ('%s', '%s', '%d', '%d', '%d', '%d', '%d');", id, pw, 0, 0, 0, 0, 0);
	    
	    int rowNum = stmt.executeUpdate(command2);
	    if (rowNum < 1)
	        throw new Exception("데이터를 DB에 입력할 수 없습니다.");
	    out.println(":success:");
	    System.out.println("[register]id:"+id+" pw:"+pw+" 회원가입 완료");
	}
	finally{
		
	    try{
	        stmt.close();
	    }
	    catch(Exception ignored){
	    }
	    try{
	        conn.close();
	    }
	    catch(Exception ignored){
	    }
	}

%>