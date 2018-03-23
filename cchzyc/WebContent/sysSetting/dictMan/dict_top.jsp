<%@page import="com.hzyc.website.beans.Dictionary"%>
<%@page import="com.hzyc.website.system.Dict"%>
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
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
<link type="text/css"  rel="stylesheet"  href="<%=basePath%>plugins/layui/css/layui.css" />
<link type="text/css"  rel="stylesheet"  href="<%=basePath%>css/common.css" />
<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>

<script>
	 $(document).ready(function(){
		$("#add").click(function(){
			var iWidth=620;                          //弹出窗口的宽度; 
       		var iHeight=420; 
            var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
            //获得窗口的水平位置 
            var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
            window.open("dict_add.jsp", "_blank", 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft +
            ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no'); 	
		});
	}); 


</script>
<style>
	div#add-div{
		display:none;
		width:500px;
		height:400px;
		position:absolute;
		top:50%;
		left:50%;
	}
	*{
		margin:0;padding:0;
	}
</style>
</head>
<body>
        
	<div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;">
		当前位置 >> 试听申请管理 >> 试听学员查询
	</div>
	<div class="wrapper" style="">
		<form name="myForm" action="<%=basePath%>sysCon/selDict.hzyc" target="bottom" method="post">
		<table border="0" cellpadding="0" cellspacing="20px">
	        <tr style="height:60px;">
	          <td>分类名称</td>
	          <td >
				<select name="type" class="layui-input" style="height:30px; margin-left:10px;" >
					<%
					Dictionary[] array = Dict.getAllType();
					if(array != null && array.length > 0){
						for(Dictionary i :  array){
					%>	
						<option value="<%=i.getDictType()%>"><%=i.getTypeName()%></option>	
					<%
						}
					}else{
						
					%>	
					<%	
					}
					%>	
					
				</select>
			  </td>
	          
	          <td rowspan="2" style="vertical-align: middle" >
	          		<input type="submit" name="button"  value="查询"  style="margin-left:20px;">
	          		<input type="reset" onclick="return confirm('重置所有选项吗?');" name="button"   value="重置" style="margin-left:10px;">
	      			<input type="button" name="button" id="add" value="添加"  style="margin-left:10px;" >
	          </td>
	        </tr>
	        
	   </table>
	   
	   </form>
   </div>
   
   <!-- <div id="add-div">
   		类别<input type="text"/>   
   		类型代码<input type="text"/>  
   		字典代码<input type="text"/>  
   
   </div> -->
</body>
</html>