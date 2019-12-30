<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/dict.tld"%>
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
    <title>table模块快速使用</title>
    <link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
    <script src="<%=basePath %>plugins/layui/layui.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/common.css" >
    <style>
    	ul{
    		list-style:none;
    	}
    	ul li{
    		display: inline;
    	}
    	.layui-input{
    		width:200px;
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
		    background: #1E88C7;
		    border:0;
		    border-radius: 0px;
		    padding: 4px 12px;
		    overflow: hidden;
		    color: #fff;
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
		    cursor:pointer;
		    text-decoration: none;
		}
		#fileForm{
			float:right;
			height:78px;
			line-height:30px;
		}
    </style>
    <script>
    	function importFile(){
    		alert("您将要导入excel文件,导入学员信息前请下载并阅读导入模版，格式不符将会导致导入失败.");
    	}
    	function exportFile(){
    		var name = document.getElementById("name").value;
    		var tel = document.getElementById("tel").value;
    		var idcard = document.getElementById("idcard").value;
    		var classes = document.getElementById("classes").value;
    		var info = "";
    		if(name.trim() != '' ){
    			info += "姓名:" + name +",";
    		}
    		if(tel.trim() != ''){
    			info += "电话:" + tel +",";
    		}
			if(idcard.trim() != ''){
    			info += "身份证号:" + idcard +",";
    		}
			if(classes.trim() != '' && classes.trim() != '全部' && classes.trim() != '0'){
				info += "班级:" + classes +"";
    		}
    		if(confirm('导出 '+ info + ' 全部学员?')){
    			document.getElementById("form").action="<%=basePath%>stuCon/exportExcel.hzyc";
    			document.getElementById("form").submit();
    		}
    	}
    	function validFile(){
    		var f_content = document.getElementById("file").value;  
   		    var fileext=f_content.substring(f_content.lastIndexOf("."),f_content.length);
   		    var filename =  f_content.substring(f_content.lastIndexOf("\\")+1,f_content.length);
   		    fileext=fileext.toLowerCase();
   		    if (fileext!='.xls' ){  
   		        alert("对不起，导入数据格式必须是xls格式文件哦，请您调整格式后重新上传，谢谢 ！");
   		        
   		    }else{
   		    	if(confirm('您将要导入 ['+filename+"] 文件?")){
   		    		//开始导入
   		    		alert("开始提交");
   		    		document.getElementById("fileForm").submit();
   		    	}
   		    }  
   		 
    	}
    
    </script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js" charset=”utf-8″></script>
</head>

<body>
	 <div id="position" style="height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;">
		当前位置 >> 学员信息管理 >> 学员信息查询    &nbsp;&nbsp;<span style="color:#d00;font-size:13px;"> 提示 : 导入excel前请下载模版校对格式,未选择任何导出条件将导出全部学员</span>
	</div>
	<div style="width:850px;height:78px;;display: inline-block;">
		<form action="<%=basePath%>stuCon/selectiveByPage.hzyc" id="form" style="width:850px;height:78px;" method="post" target="result">
		  <table>
		  	<tr>
		  		<td><label class="layui-form-label">姓名</label></td>
		  		<td><input type="text" id="name" class="layui-input" name="name"/></td>
		  		<td><label class="layui-form-label">电话</label></td>
		  		<td><input type="text" id="tel" class="layui-input" name="tel"/></td>
		  		<td></td>
		  		<td></td>
		  	</tr>
		  	<tr>
		  		<td><label class="layui-form-label">班级</label></td>
		  		<td>
		  			<ex:dict name="classes" id="classes" type="classes" classname="hzyc-select" defaultvalue="全部" />
		  		</td>
		 		<td><label class="layui-form-label">身份证号</label></td>
		 		<td><input type="text" id="idcard" class="layui-input" name="idcard"/></td>
		 		<td rowspan="2" style="text-align:center;vertical-align:middle;" >
		 			<input type="submit" value="查询" />
		 			<input type="reset"  onclick="javascript:if(confirm('重置所有选项吗?')) return; return false;" value="重置" />
		 		</td>
		  	</tr>
		  </table>
			
		</form>
	</div>
	
		
		<form action="<%=basePath%>stuCon/importExcel.hzyc" enctype="multipart/form-data" method="post" id="fileForm">
			<a href="<%=basePath%>/stuCon/downloadDemo.hzyc" style="color: #83AFE2;text-decoration:underline;">模版下载</a> 	
			<input type="file" name="file" onclick="javascript:importFile()"  id="file" style="vertical-align:middle" >
			<!-- 哈哈哈哈 -->
			<br />
			<input  type="button" onclick="validFile()"  style="color: #fff;" value="导入" />
			<input type="button" value="导出" onclick="exportFile()" />
		</form>	
	<div style="width:100%;height:auto;margin-top:10px;">
		<iframe style="height:400px;border:0" src="stu_sel_result.jsp" id="result" name="result" >
		   
		</iframe>
    </div>
    <script>
	  //默认iframe大小
		document.getElementById("result").style.width = document.body.clientWidth +"px";
		document.getElementById("position").style.width = document.body.clientWidth +"px";
		//默认提交一次form表单
		document.getElementById("form").submit();
		function formSubmit(){
			document.getElementById("form").submit();
		}
    </script>
    
</body>

</html>