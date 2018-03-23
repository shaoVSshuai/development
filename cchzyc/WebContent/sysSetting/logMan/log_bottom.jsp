<%@page import="com.hzyc.website.beans.Log"%>
<%@page import="com.hzyc.website.beans.Dictionary"%>
<%@page import="com.hzyc.website.beans.StudentInfo"%>
<%@page import="java.util.List"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
    <script src="<%=basePath %>plugins/layui/layui.js"></script>
     <script src="<%=basePath %>js/jquery1.4.2.js"></script>
    <script>
    
	    $(document).ready(function(){
	    		$("#allSelectd").click( 
	    				  function(){ 
	    				    if(this.checked){ 
	    				        $("input[name='checkname']").attr('checked', true)
	    				    }else{ 
	    				        $("input[name='checkname']").attr('checked', false)
	    				    } 
	    				  } 
	    				);
		});
	    /*
	    * 删除
	    */
    	function del(id,name){
    		if(confirm("您确认删除该学员吗?ID："+ id +",姓名:"+ name)){
    			//删除
    			htmlobj=$.ajax({url:"<%=basePath%>stuCon/delStudent.hzyc?id="+id,async:false});
    			if(htmlobj.responseText == 1){
    				alert("删除成功!");
    			}else{
    				alert("删除失败!");
    			}
    		}
    	}
    	/**
    	* 修改
    	*/
    	function upd(id){
    		window.open("<%=basePath%>stuCon/selOneStudent.hzyc?id="+id,"main");
    	}
    	/*
    	* 详细
    	*/
		function detail(id){
			window.open("<%=basePath%>stuCon/selStudentDetail.hzyc?id="+id,"main");
    	}    
    
    </script>
    <style>
    	*{
    		font-size:12px;
    	}
    	
    </style>
</head>
<body>
	 <%
	 	List<Log> logs = (List<Log>)request.getAttribute("logList");
	 %>
	 
	 <table class="layui-table" id="test">
		        <thead>
		            <tr>
		            


		            	<!-- <th > <input type="checkbox" id="allSelectd">全选</th> -->
		            	<th  >ID</th>
		            	<th lay-data="{field:'id', width:40, sort: true}">执行人</th>
		                <th lay-data="{field:'id', width:20, sort: true}">执行人IP</th>
		                <th lay-data="{field:'username', width:40}">访问路径</th>
		                <th lay-data="{field:'username', width:50}">执行模块</th>
		                <th lay-data="{field:'username', width:50}">执行方法</th>
		                <th lay-data="{field:'sex', width:60, sort: true}">执行状态</th>
		                <th lay-data="{field:'sex', width:60, sort: true}">状态代码</th>
		                <th lay-data="{field:'sex', width:60, sort: true}">执行时间</th>
		                <th lay-data="{field:'sex', width:60, sort: true}">响应时间</th>
		                <th lay-data="{field:'sex', width:60, sort: true}">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<%
		        		if(logs != null && logs.size() > 0 ){
			        		for(Log log : logs){
			        %>
			        		<tr>
			        			<%-- <td style="font-size:12px"><input type="checkbox" name="checkname" value="<%=log.getId() %>" /></td> --%>
			        			<td style="font-size:12px"><%=log.getId() %></td>
			        			<td style="font-size:12px"><%=log.getLoginaccount()%></td>
				                <td style="font-size:12px"><%=log.getLoginip()%></td>
				                <td style="font-size:12px"><%=log.getActionurl()%></td>
				                <td style="font-size:12px"><%=log.getModule()%></td>
				                <td style="font-size:12px"><%=log.getMethod() %> </td>
				                <td style="font-size:12px"><%=log.getDescription() %> </td>
				                <td style="font-size:12px"><%=log.getState()%> </td>
				                <td style="font-size:12px"><%=log.getGmtcreate()%> </td>
				                <td style="font-size:12px"><%=log.getActiontime()%> ms </td>
				                <td style="font-size:12px"><button >删除</button></td>
			        		</tr>
			        <%
			        		}
		        		}else{
		        	%>
		        			<tr>
			        			<td colspan="11" align="center">暂无数据</td>
			        		</tr>
		        	<%
		        		}
		        	%>
		        	
		        	
		        
		        </tbody>
		    </table>
	<script>
        layui.use('table', function() {
            var table = layui.table;
        });
    </script>
</body>
</html>