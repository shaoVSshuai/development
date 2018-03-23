<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="com.hzyc.website.beans.Employee"%>
<%@page import="com.hzyc.website.beans.DeptJob"%>
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
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
<script src="<%=basePath %>plugins/layui/layui.js"></script>
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
<script type="text/javascript">
function divide(point){
	var code = $(point).text();
	var request = new XMLHttpRequest();
	//2. 设置监听
	request.onreadystatechange = function (){
		
		//判断状态（整个异步传输是否完成）
		if(request.readyState == 4){
			//接收返回字符串		
		}
		
	};
	//3. 打开通道
    request.open("post", "<%=basePath%>sysCon/selJobByCode.hzyc", true);
	//4.设置请求头信息
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//5. 发送请求
	request.send("jobCode="+code);	
}
function tijiao(i){
	
	$("input[name^='code']").attr('name','code1');
	var code = document.getElementById(i);
	code.name = "code";
}
</script>
<style>
    	*{
    		margin:0;
    		padding:0;
    	}
    .wrapper{
   			 width: 466px;
   			 height: 500px;
    	}
   	.wrapper1{
  			 width: 466px; 
  			 float: left;
   	   }
    	ul{
    		list-style:none;
    	}
    	ul li{
    		display: inline;
    	}
    	.layui-input{
    		width:150px;
    		height:30px;
    		display: inline;	
    	}
    	.layui-form-label {
		    float: left;
		    display: block;
		    padding: 9px 15px;
		    font-weight: 400;
		    width:auto;
		    text-align: right;
		}
		.file {
		    position: relative;
		    display: inline-block;
		    background: #D0EEFF;
		    border: 1px solid #99D3F5;
		    border-radius: 4px;
		    padding: 4px 12px;
		    overflow: hidden;
		    color: #1E88C7;
		    text-decoration: none;
		    text-indent: 0;
		    line-height: 20px;
		}
		.file input {
		    position: absolute;
		    font-size: 100px;
		    right: 0;
		    top: 0;
		    opacity: 0;
		}
		.file:hover {
		    background: #AADFFD;
		    border-color: #78C3F3;
		    color: #004974;
		    text-decoration: none;
		}
    
</style>
</head>
<body>
	  <div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;box-sizing: border-box;">
		当前位置 >> 系统设置>> 角色查询
	  </div>
	<div class="wapper">
	  <div class="wrapper1">
	   <form action="<%=basePath%>empCon/selEmpJob.hzyc" style="width:466px;height:78px;" method="post" target="rightFrame">
	    <table class="layui-table" id="test">
		        <thead>
		            <tr>
		                <th lay-data="{field:'id', width:40, sort: true}">姓名</th>
		                <th lay-data="{field:'code', width:80}">工号</th>
		                <th lay-data="{field:'wealth', width:135, sort: true}">操作</th>
		            </tr>
		        </thead>
		   <% List<Employee> eList = (List)request.getAttribute("list");
	            if(eList !=null && eList.size()!=0){
				for(int i=0;i<eList.size();i++){ 
		   %>
		        <tbody>
		        <tr>
		           <td><%=eList.get(i).getName() %></td>
		           <td onclick="divide(this)"><%=eList.get(i).getCode()%>
		           <input type="hidden" name="code1" id="<%=i %>" value="<%=eList.get(i).getCode()%>" />
		           </td>
		           <td rowspan="2" style="text-align:center;vertical-align:middle;">
		 			<button class="layui-btn" type="button">详情</button>  
		 			<button class="layui-btn" type="submit" onclick="tijiao(<%=i %>)">分配</button>  
		 		   </td>
		        </tr>
		        </tbody>
		          <%
        }
        }
        
        else{
        
        %>
        	<td colspan="3" style="font-size:20px;text-align:center">暂无此人信息</td>
        	<%
       		 }
        %>
        
		 </table>
	  </form>
	</div>
	
	
	</div>
		    
	<script>
        layui.use('table', function() {
            var table = layui.table;
        });
    </script>
</body>
</html>