<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="com.hzyc.website.beans.Employee"%>
<%@page import="com.hzyc.website.beans.DeptJob"%>
<%@page import="com.hzyc.website.beans.Job"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>授权的右部分</title>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
<script src="<%=basePath %>plugins/layui/layui.js"></script>
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
<script type="text/javascript">
function defaultChecked(point){
	var jobName = $(point).text();//获取职位名称
	var data = request.responseText;
	alert("后台传入的数据为："+data);
	
	//1. 创建XMLHttpRequest
	var request = new XMLHttpRequest();
	//2. 设置监听
	request.onreadystatechange = function (){
		
		//判断状态（整个异步传输是否完成）
		if(request.readyState == 4){
			//接收返回字符串
			
			alert("后台传入的数据为："+data);
				
		}
		
	};
	//3. 打开通道
    request.open("post", "<%=basePath%>sysCon/selJobByCode.hzyc", true);
	//4.设置请求头信息
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//5. 发送请求
	request.send("jobName="+jobName);	
	
}

</script>
<style>
    	*{
    		margin:0;
    		padding:0;
    	}
    .wrapper{
   			 width: 700px;
   			 height: 500px;
    	}
   	.wrapper1{
  			 width: 400px; 
  			 float: left;
   	}
</style>
</head>
<body>
 <div style="float: left;margin-top: 20px;margin-left: 50px">
	<table class="layui-table" id="test">
	  <thead>
	     <tr>
	        <td></td>
	        <td>职位名称</td>
	        <td>二级部门名称</td>
	        <td>一级部门名称</td>
	     </tr>
	  </thead>
	    <% 
	    int flag = 0;
	    List<DeptJob> list = (List<DeptJob>)request.getAttribute("deptList");
	    if(list != null &&  list.size() > 0){
		   		flag =1;
				for(int i=0;i<list.size();i++){ 
					List<Job> jlist = (List<Job>)request.getAttribute("jobList");
					for(int j=0;j<jlist.size();j++){
		   %>  
		        <tbody>
		        <tr> 
		           <td><input type="checkbox" value="checkbox" name="checkbox"></td>
		           <td onclick="defaultChecked(this)"><%=list.get(i).getJobList().get(j).getJobName() %></td>
		           <td><%=list.get(i).getDeptList().get(j).getDept2Name() %></td>
		           <td><%=list.get(i).getDeptList().get(j).getDept1Name() %></td> 
		         </tr>
		        </tbody>
		          <%
        }}}
        	
         %> 
	</table>
	<div style="margin-left:30px;margin-top:50xp;">
		<input type="button" value="确定" style="margin-left:300px;margin-top:100px;" class="layui-btn"/> 
	</div>
	</div>
	<script>
        layui.use('table', function() {
            var table = layui.table;
        });
        //默认加载下所有角色职位
        if(<%=flag%> == 0){
        	window.location.href="<%=basePath%>sysCon/selRightRole.hzyc";
        }
        
    </script>
</body>
</html>