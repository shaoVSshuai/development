<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="#" method="post">
		
		账号：<input type="text" /><span style="color:#f00">用户手动填入</span><br />
		密码：<input type="password" />
		确认密码：<input type="password" />
		真实姓名：<input type="text" />
		身份证号：<input type="text" />
		电话：<input type="text" />
		邮箱：<input type="text" /><br />
		家庭住址：<input type="text" />
		注册店铺：<input type="text" /><span style="color:#f00">这里需要判断，如果user表中的store_info_id字段为空则显示下拉框让用户选择店铺，如果不为空则直接显示店铺名称</span>		
		<input type="submit" value="提交">
		
	</form>
</body>
</html>