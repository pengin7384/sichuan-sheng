<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="org.json.simple.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String stage = request.getParameter("stage");

	if(id==null || id=="")
		return;
	
	Connection conn = null;
	Statement stmt = null;	
	

	
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost/sachun_db", "root", "apmsetup");
	
	    if (conn == null)
	        throw new Exception("데이터베이스에 연결할 수 없습니다.");
	    stmt = conn.createStatement();
	
	    String command = String.format("Select stage as stage from user_tb where id=\""+id+"\";");
	    ResultSet rs = stmt.executeQuery(command);       
	    
	    rs.next();
	    int prev_stage = rs.getInt("stage");
	
	    if(prev_stage < Integer.parseInt(stage)) {
	    	String command2 = String.format("update user_tb set stage=\"" + stage + "\" where id=\""+id+"\";");
		    stmt.executeUpdate(command2);
		    System.out.println("[Update] 사용자 " + id + "님 새로운 스테이지 클리어 (스테이지:" + stage + ")");
	    	return;
	    }	   	
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