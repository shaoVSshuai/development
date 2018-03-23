<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<frameset rows="30px,*" frameborder="0">
	<frame src="top.jsp" />
	<frameset  cols="30%,*" frameborder="1" border="1" >
		<frame src="<%=basePath%>deptEmpCon/selAllDept.hzyc" noresize="noresize"/>
		<frame src="right.jsp" name="rightFrame"/>
	</frameset>
</frameset> 
</html>