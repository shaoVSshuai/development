<%@page import="com.hzyc.website.beans.Dictionary"%>
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
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>css/common.css" />
    <script src="<%=basePath %>plugins/layui/layui.js"></script>
     <script src="<%=basePath %>js/jquery1.4.2.js"></script>
     <style>
     	tr{
     		height:40px;
     	}
     </style>
     <script>
     	function valid(){
     		$("#form").submit();
     	}
     </script>
</head>
<body>
	<% 
		Dictionary dict = (Dictionary)request.getAttribute("dict");
	%>
	<div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;">
		当前位置 >> 数据字典管理 >> 修改字典项
	</div>
	
	<form action="<%=basePath %>sysCon/updDict.hzyc" method="post" id="form"  >
		<table border="0" cellpadding="10" cellspacing="10" align="center" style="margin-top:20px;">
			<tr>
				<td> ID</td>
				<td> <input type="text" value="<%=dict.getId()%>" readonly="readonly" class="hzyc-input" name="id"></td>
			</tr>
			<tr>
				<td>字典类别代码</td>
				<td> <input type="text" value="<%=dict.getDictType()%>" readonly="readonly" class="hzyc-input"></td>
			</tr>
			<tr>
				<td>字典代码</td>
				<td> <input type="text" value="<%=dict.getDictCode()%>" readonly="readonly" class="hzyc-input"></td>
			</tr>
			<tr>
				<td> 字典名称 </td>
				<td><input type="text" value="<%=dict.getDictName()%>" class="hzyc-input" name="dictName"></td>
			</tr>
			<tr>
				<td>   排序</td>
				<td><input type="text" value="<%=dict.getSort()%>" class="hzyc-input" name="sort">
					<span style="color:#d00">*注:数字越小越优先显示,最小为1</span>
				</td>
			</tr>
			<tr>
				<td> 是否可用</td>
				<td>
					<select class="hzyc-select" name="enabled">
						<option value="<%=dict.getEnabled()%>"><%=dict.getEnabled().equals("1")? "启用": "禁用" %></option>
						<option value="0"><%="禁用"%></option>
						<option value="1"><%="启用"%></option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					 <input type="button" onclick="valid()" value="保存" style="width:120px;border-radius:5px;height:38px;background-color: #009688; margin-top:20px;color:#fff"/>
			         <input type="reset" value="重置 " style="width:120px;border-radius:5px;height:38px;background-color: #FF5722;; margin-top:20px;margin-left:10px;color:#fff;"/>
				</td>
				
			</tr>
	  </table>
  </form>
</body>
</html>