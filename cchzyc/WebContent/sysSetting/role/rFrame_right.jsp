<%@page import="org.apache.catalina.connector.OutputBuffer"%>
<%@page import="com.hzyc.website.beans.EmployeeJob"%>
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
			String fCode = request.getParameter("flag");
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
layui.use('layer', function(){
	  var layer = layui.layer;
	  var code = <%=fCode%> ;
	  if(code != null && code == 1){
		  layer.msg('授权成功了！！');
	  }
	  if(code != null && code == 2){
		  layer.msg('授权失败了 ！！');
	  }
}); 

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
<body onload="getData()">
	 <div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;box-sizing: border-box;">
		当前位置 >> 系统设置>> 角色分配管理
	  </div>
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
<%-- 	    <% 
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
		          <td><input type="checkbox" value="<%=jlist.get(j).getJobCode() %>" name="checkbox"></td>
       	           <td onclick="defaultChecked(this)"><%=list.get(i).getJobList().get(j).getJobName() %></td>
		           <td><%=list.get(i).getDeptList().get(j).getDept2Name() %></td>
		           <td><%=list.get(i).getDeptList().get(j).getDept1Name() %></td> 
		         </tr>
		        </tbody>
		          <%
        }}}
        	
         %>  --%>
         <tbody id="tbody">
         	
         </tbody>
	</table>
	<div style="margin-left:30px;margin-top:50xp;">
		<form id="formId" action="<%=basePath%>sysCon/addDeptJob.hzyc" method="post" target="rightFrame">
			<input type="hidden" id="eid" name="employeeCode" value="${EmployeeCode}" />
			<input type="hidden" id="param" name="param" value="" />
			<input type="button" value="确定" type="submit" style="margin-left:300px;margin-top:100px;" class="layui-btn" onclick="sub()"/> 
		</form>
	</div>
	</div>
	<script>
        layui.use('table', function() {
            var table = layui.table;
        });
        function getData(){
    		//执行在页面加载完毕时，获取到所有的省份信息，添加到select标签上
    		//吸血鬼数字-->分治 
    		//分治：分开，治理，有一个N个复杂度的问题，拆分成X个独立的且逻辑相同的问题
    		//二分法，快速排序，冒泡法，插入排序，递归算法
    		//alert("获取省份信息");
    		var xmlhttp = new XMLHttpRequest();
    		xmlhttp.onreadystatechange = function(){
    			if(xmlhttp.readyState == 4){ 
    				var data = xmlhttp.responseText;
    				var dataArray = eval("(("+data+"))");
    				for(var i = 0;i < dataArray.length;i++){
    					for(var j = 0;j<dataArray[0].deptList.length;j++){
    						//alert(dataArray[i].deptList[j].jobName);
        					//alert(dataArray[i].deptList[j].dept2Name);
        					//alert(dataArray[i].deptList[j].dept1Name);
        					var tr ="<tr><td><input type='checkbox' name='checkbox1' value='"+dataArray[i].deptList[j].jobCode+"'></td><td>"+dataArray[i].deptList[j].jobName+"</td><td>"+dataArray[i].deptList[j].dept2Name+"</td><td>"+dataArray[i].deptList[j].dept1Name+"</td></tr>";
        					$("#test tbody").append(tr);
    					}
    				}
    				/*
    				{"jobName":"人力总监","dept2Name":"人力资源总部","dept2Code":"A11","dept1Name":"人力资源部","dept1Code":"A1"}
    				{"jobName":"行政专员","dept2Name":"人力资源吉林分公司","dept2Code":"A12","dept1Name":"人力资源部","dept1Code":"A1"}
    				*/
    			}
    			onload1();
    		};
    		xmlhttp.open("post", "<%=basePath%>sysCon/selRightRole1.hzyc", true);
    		xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    		xmlhttp.send(null);
    	}
    	function onload1(){
    		//如果复选框里面的值后ejList里面存的jobCode相同，则打对号
        	var boxObj = $("input:checkbox[name='checkbox1']"); //获取所有的复选框值  
            var expresslist = '${supplierExpressids}'; //用el表达式获取在控制层存放的复选框的值为字符串类型  
            var express = expresslist.split(',');  
            $.each(express, function(index, expressId){  
               boxObj.each(function () {  
                    if($(this).val() == expressId) {  
                       $(this).attr("checked",true);  
                    }  
                });  
            });  
    	}
    	function sub(){
    		//如果复选框里面的值后ejList里面存的jobCode相同，则打对号
        	var boxObj = $("input:checkbox[name='checkbox1'][checked]"); //获取所有的复选框值  
        	var str=""; 
        	boxObj.each(function(){ 
       		 	str+=$(this).val()+","; 
        	});
        	str += $("#eid").val();
        	//把数据传到后台
        	document.getElementById("param").value = str;
        	
        	document.getElementById("formId").submit();
    	}
    	</script>
</body>
</html>