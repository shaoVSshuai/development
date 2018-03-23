<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
</head>
<body>
	
	<%
		//添加成功flag
		String flag = request.getParameter("flag");
		if(flag != null && flag.equals("101")){
	%>
		<script>
			alert("添加成功!点击确定返回...");
			window.close();
		</script>
	<%				
		}else if(flag != null && flag.equals("102")){
	%>
		<script>
		alert("添加失败!未知错误...");
		</script>
	
	<%
		}
	%>
	<form action="<%=basePath%>sysCon/addRole.hzyc" method="post" >
		职位编码<input type="text" name="jobCode" /><br />
		职位名称<input type="text" name="jobName" /><br />
		职位简介<textarea name="jobRemark"></textarea><br />
		<input type="submit" value="提交">
	</form>
	<script type="text/javascript">
		
	
	
	</script>
</body>
</html>