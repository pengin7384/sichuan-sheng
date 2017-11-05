<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
	<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");

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
	    int stage = rs.getInt("stage");
	
	    
	    out.println(":" + stage + ":");	    
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