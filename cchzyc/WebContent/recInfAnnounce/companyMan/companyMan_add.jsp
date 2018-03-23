<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="ex" uri="../../WEB-INF/tags/dict.tld"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath %>css/common.css">
<script src="<%=basePath %>plugins/layui/layui.js"></script>
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
<script src="<%=basePath %>js/area.js" charset="UTF-8"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公司信息添加</title>
</head>
<body>
	
	<form action=""  method="post" enctype="multipart/form-data" >
		
		<table class="layui-table">
		
				<tr>
					<td>公司名称</td>
					<td colspan="3"><input type="text" name="name" class="hzyc-input" style="width:400px"/></td>
					
					<td>公司性质</td>
					<td><ex:dict classname="hzyc-select" type="company_property" name="characters"/></td>
				
				</tr>
				
				<tr>
					<td>所属行业</td>
					<td><ex:dict classname="hzyc-select" type="company_type" name="profession"/></td>
					
					<td>公司规模</td>
					<td><ex:dict classname="hzyc-select" type="company_scale" name="scale"/></td>
					
					<td>公司网址</td>
					<td><input type="text" class="hzyc-input" id="companyUrl" name="netUrl"/></td>
					
				</tr>
				<tr>
					<td>省</td>
					<td><select class="hzyc-select"  id="s_province" name="province">
					</select></td>
					
					<td>市</td>
					<td><select class="hzyc-select" id="s_city" name="city">
					</select></td>
				</tr>
				
				<tr>
					<td>详细地址</td>
					<td colspan="5"><input type="text" class="hzyc-input" style="width:515px" id="detailAdress" name="detailAddress"/></td>
				</tr>
				
				<tr>
					<td>公司简介</td>
					<td colspan="5"><textarea  cols="80" rows ="10" placeholder="字数限制在250左右" style="resize:none;" id="companyProfile" name="companyInfo"></textarea></td>
				</tr>
				
				
		</table>
	</form>
	<script type="text/javascript">
		//省市二级联动函数
		_init_area();
	</script>
</body>
</html>