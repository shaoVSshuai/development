<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>系统配置</h1>
		<form action="privilege/setPrivilege" method="post">
		<input type="checkbox" name="func" value="1">会员功能
		<hr />
			卡号生成规则：<select name="vip_code_type">
				<option value="51">随机码</option>
				<option value="52">手机号</option>
				<option value="53">年-月-日-时-分-秒</option>
				</select>
	会员短信：	<input type="radio" name="vip_message" value="1" checked="checked">	开启
				<input type="radio" name="vip_message" value="0">	关闭<br />
	会员设置：	<input type="radio" name="vip_type" value="41" checked="checked">打折
				<input type="radio" name="vip_type" value="42" >	储值&打折 <br />
	会员积分： 	<input type="radio" name="vip_integral" value="1" checked="checked">	开启
				<input type="radio" name="vip_integral" value="0">	关闭<br />
		<hr />
		<input type="checkbox" name="func" value="2">折扣功能
		<hr />
		<input type="checkbox" name="func" value="3">订单管理
		<hr />
		<input type="checkbox" name="func" value="4">商品管理
		<hr />
		<input type="checkbox" name="func" value="5">外送管理
		<hr />
		<input type="checkbox" name="func" value="6">员工管理
		<hr />
		<input type="checkbox" name="func" value="7">库存管理
		<hr />
		<input type="checkbox" name="func" value="8">打印功能
		<hr />
		<input type="submit" value="提交">
	</form>
</body>
</html>