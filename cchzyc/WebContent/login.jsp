<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>登录</title>
		<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=basePath%>css/login.css" />
	</head>

	<body class="beg-login-bg">
		<div class="beg-login-box">
			<header>
				<h1>合众易成后台管理</h1>
			</header>
			<div class="beg-login-main">
				<form action="<%=basePath%>empCon/login.hzyc" class="layui-form" method="post" id="formId"><input name="__RequestVerificationToken" type="hidden" value="fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81" />
					<div class="layui-form-item">
						<label class="beg-login-icon">
                        <i class="layui-icon">&#xe612;</i>
                    </label>
						<input type="text" name="code" lay-verify="required" value="13244236072" autocomplete="off" placeholder="工号 / 手机号" class="layui-input">
					</div>
					<div class="layui-form-item">
						<label class="beg-login-icon">
                        <i class="layui-icon">&#xe642;</i>
                    </label>
						<input type="password" name="password" lay-verify="required" value="111111" autocomplete="off" placeholder="这里输入密码" class="layui-input">
					</div>
					<div class="layui-form-item">
						<div class="beg-pull-left beg-login-remember">
							<label>记住帐号？</label>
							<input type="checkbox" name="rememberMe" value="true" lay-skin="switch"  title="记住帐号">
						</div>
						<div class="beg-pull-right">
							<button class="layui-btn layui-btn-primary" lay-submit lay-filter="login">
                            <i class="layui-icon">&#xe650;</i> 登录
                        </button>
						</div>
						<div class="beg-clear"></div>
					</div>
				</form>
			</div>
			<footer>
				<p>Beginner © www.zhengjinfan.cn</p>
			</footer>
		</div>
		<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
		<script>
			layui.use(['layer', 'form'], function() {
				var layer = layui.layer,
					$ = layui.jquery,
					form = layui.form();
					
				form.on('submit(login)',function(data){
					
					//location.href='index.html';
					return false;
				});
			});
			document.getElementById("formId").submit();
		</script>
		<%
			String flag = request.getParameter("flag");
			if(flag != null && flag.equals("102")){
		%>
			<script>
				alert("用户名或密码错误!");
				window.location.href = "login.jsp";
			</script>
		<%
			}
		%>
	</body>

</html>