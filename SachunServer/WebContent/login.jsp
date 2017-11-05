<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
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
	
	    String command = String.format("Select count(*) as count from user_tb where id=\""+id+"\" and pw=\""+pw+"\";");
	    ResultSet rs = stmt.executeQuery(command);       
	    
	    rs.next();
	    int check_count = rs.getInt("count");
	
	    if(check_count > 0) {		// 아이디, 비밀번호에 해당하는 정보가 있을경우 로그인 성공
	    	out.println(":success:");
	        System.out.println("[login]id:"+id+" pw:"+pw+"(로그인 성공)");
	    	return;
	    }
	
	    out.println(":fail:");
	    System.out.println("[login]id:"+id+" pw:"+pw+"(로그인 실패)");
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