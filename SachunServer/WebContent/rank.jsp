<%@page import="org.json.simple.JSONObject"%>
<%@page import="jdk.nashorn.internal.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
	<%
	request.setCharacterEncoding("utf-8");

	
	Connection conn = null;
	Statement stmt = null;

	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost/sachun_db", "root", "apmsetup");
	
	    if (conn == null)
	        throw new Exception("데이터베이스에 연결할 수 없습니다.");

	    stmt = conn.createStatement();	   
	    String command = String.format("Select * FROM user_tb WHERE record > 0 ORDER BY record DESC LIMIT 0, 10;");
     	ResultSet rs = stmt.executeQuery(command);    
     	JSONObject j = new JSONObject();
		StringBuilder str = new StringBuilder(":"); 
		
	    while(rs.next()) {  	
	    	str.append(rs.getString("id"));
	    	str.append(":");
	    	str.append(rs.getString("record"));
	    	str.append(":");	    		
	    }   
    	str.append("finish");
    	str.append(":");	 
    	
    	
	  	out.println(str);
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