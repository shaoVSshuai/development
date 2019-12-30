<%@page import="com.hzyc.website.system.CompanyForInit"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="ex" uri="../../WEB-INF/dict.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>招聘公司维护</title>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>css/common.css" >
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js" charset=”utf-8″></script>
<script  type="text/javascript" src="<%=basePath%>plugins/layer/layer.js" charset=”utf-8″></script>

<script src="<%=basePath %>js/area.js" charset="UTF-8"></script>

<style type="text/css">
	.layui-input{
    		width:200px;
    		height:30px;
    		display: inline;	
    }
    .layui-form-label{
		width:auto;
	}
	*{
		font-size:12px;
	}
	table td{
		padding:8px 10px;
	}
</style>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		$("#add").click(function(){
			 layer.open({
			      type: 2,
			      title: '添加公司',
			      maxmin: true,
			      shadeClose: false, //点击遮罩关闭层
			      area : ['900px' , '520px'],
			      content: 'companyMan_add.jsp'
			});
		});
	});
</script>
<body>
	<form action="*"  method="post" target="result">
		 <table >
		  	<tr>
		  		<!-- 
		  			下拉框样式
		  			<td><select id="dept1" onchange="initDept2(this)" class="hzyc-select" name="deptOne" > </select></td>
		  		 	文本框样式
		  		 	<td><input type="text" class="layui-input" name="compName"/></td>
		  		 -->
		  	
		  		<td><label  >公司名</label></td>
		  		<!-- 下拉框 -->
		  		<td><select id="comp" onchange="initCompany(this)" class="hzyc-select" name="compName" >
		  			<%
						for(int i = 0;	i < CompanyForInit.getList().size();i++){
							
							%>
							<option><%=CompanyForInit.getList().get(i).getName() %></option>
							<% 
						}
		  			%>
		  		 </select></td>
		  		<!-- <td><input type="text" class="layui-input" name="compName"/></td> -->
		  		<td><label  >公司规模</label> </td>
		 		<td>
		 			<ex:dict classname="hzyc-select" type="company_scale"/>
		 		</td>
		 		
		  	</tr>
		  	<tr>
		  	<td><label  >省份</label></td> 
		 		<td><select id="s_province" class="hzyc-select" onchange="initEduBak()" name="eduBg" ></select></td>
		  		<td><label  >市区</label></td>
		  		<td><select id="s_city" class="hzyc-select" onchange="initPosition()" name="position" ></select></td>
		 		<td colspan="2" style="text-align: center">
		 			<input type="submit" value="查询" />
		 			<input type="reset"  onclick="javascript:if(confirm('重置所有选项吗?')) return; return false;" value="重置" />
		 			<input type="button" id="add" value="添加" />	
		 		</td>
		  	</tr>
		  </table>
	</form>
	
	<iframe src="companyMan_result.jsp" name="result" style="width:1130px;height:450px;margin-top:20px">
		
	</iframe>
	<script type="text/javascript">
		//省市二级联动函数
		_init_area();
</script>
</body>
</html>