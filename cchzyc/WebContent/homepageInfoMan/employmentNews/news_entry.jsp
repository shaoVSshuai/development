<%@page import="com.hzyc.website.beans.Company"%>
<%@page import="java.util.List"%>
<%@page import="com.hzyc.website.system.CompanyForInit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/tags/dict.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘信息录入</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String fCode = request.getParameter("flag");
%>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>css/common.css" >
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js" charset=”utf-8″></script>
<script  type="text/javascript" src="<%=basePath%>plugins/layer/layer.js" charset=”utf-8″></script>
<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery1.4.2.js" charset=”utf-8″></script>

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
<script type="text/javascript">

layui.use('layer', function(){
	  var layer = layui.layer;
	  var code = <%=fCode%> ;
	  if(code != null && code == 1){
		  layer.msg('操作成功 ！！');
	  }
	  if(code != null && code == 2){
		  layer.msg('操作失败 ！！');
	  }
}); 

</script>
</head>
<body >
	<!-- action="?%=basePath%?empCon/selEmp.hzyc" -->
	<form action="*"  method="post" target="result">
		  <table >
		  	<tr>
		  	<td><label  >学员姓名</label></td> 
		 		<td><input type="text"  class="hzyc-select" name="stuName" /></td>
		  		<td><label  >职位</label></td>
		  		<td><input type="text"  class="hzyc-select" name="position" /></td>
		  	
		 		<td colspan="2" style="text-align: center">
		 			<input type="submit" value="查询" />
		 			<input type="reset"  onclick="javascript:if(confirm('重置所有选项吗?')) return; return false;" value="重置" />
		 			<input type="button" id="add" value="录入" />	
		 		</td>
		  	</tr>
		  </table>
	</form>
	
	<iframe src="<%=basePath%>/homepageInfoMan/employmentNews/news_maintenance.jsp" name="result" style="width:1130px;height:450px;margin-top:20px;">
	
	</iframe>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#add").click(function(){
			 layer.open({
			      type: 2,
			      title: '招聘信息录入',
			      maxmin: true,
			      name:'add',
			      shadeClose: false, //点击遮罩关闭层
			      area : ['900px' , '520px'],
			      content: '<%=basePath%>homepageInfoMan/employmentNews/news_add.jsp'//,
			      /*success:function($el,index){
			    	 $el.find(".layui-layer-max").unbind("click.aa").bind("click.aa",function(){
			    	        var momBool=$(this).hasClass("layui-layer-maxmin");//true时当前是正常窗口正要执行最大化，false反之
			    	    	console.log(momBool);
			    	    	var divObj=$el.find("iframe").contents().find("#add_content");
							if(momBool){
								divObj.css("margin-left","150px");
								console.log("放大");
							}else{
								divObj.css("margin-left","10px");
								console.log("缩小");
							}
							
			    	    	//console.log(divObj);
			    	// })
			   	} */
			});
		});
		
	});
	</script>
</body>
</html>