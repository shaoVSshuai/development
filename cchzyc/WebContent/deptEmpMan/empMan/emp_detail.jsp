<%@page import="com.hzyc.website.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/dict.tld"%>
<%@ taglib prefix="cs" uri="../../WEB-INF/tags/codeswitch.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath %>css/common.css">
<script src="<%=basePath %>plugins/layui/layui.js"></script>
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
<script src="<%=basePath %>js/loadDept.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>

/**
	3.图片上传预览
	
*/
function previewFile1() {
	
	var preview = document.querySelector('img');
	var file  = document.getElementById("imgUpload1").files[0];
	var reader = new FileReader();
	
	reader.onloadend = function () {
		preview.src = reader.result;
	};
	if (file) {
		reader.readAsDataURL(file);
	} 
}
function previewFile2() {
	
	var preview = document.getElementById("img2");
	var file  = document.getElementById("imgUpload2").files[0];
	var reader = new FileReader();
	
	reader.onloadend = function () {
		preview.src = reader.result;
	};
	if (file) {
		reader.readAsDataURL(file);
	} 
}
	<%-- //监听省份下拉框
	function loadCity(){
		var selectObj = document.getElementById("province");
		var index = selectObj.selectedIndex ;
		var provinceCode  = selectObj.options[index].value ;
	
		xhr=$.ajax({
			url:"<%=basePath%>AddressUtil/getAddress.hzyc",
			async:false,
			data:{
				targetCode:provinceCode
			}
		});
		//取值
		var info = eval("("+xhr.responseText+")") ;
		var infoObj = document.getElementById("city");
		//重置节点
		var cLength = infoObj.childNodes.length;
		for(var i=0,len=cLength;i<len;i++){
			infoObj.removeChild(infoObj.lastChild);
		}
		alert(info.length);
		//追加子节点
		for(var i = 0 ; i < info.length ; i++){
			//var option = document.createElement("option");
			//option.innerText = info[i].value;
			alert(info[i]);
			//infoObj.appendChild(option);
		}
		
	}; --%>

</script>
</head>
<body  >
	<%
		Employee emp = (Employee)request.getAttribute("employee");
	%>
	<table class="layui-table">
		<tr>
			<td>姓名</td>
			<td><%=emp.getName() %></td>
			<td>性别</td>
			<td>
				<cs:CodeSwitch classname=""  code="<%=emp.getSex()%>"/>
			</td>
			<td rowspan="5" >
				二寸照片
				<br />
				身份证照
			</td>
			<td rowspan="5" colspan="2">
				<div style="position: relative;bottom:10px;">
					<div class="layui-input-block" style="margin:0 auto;">
						<div>
							<img src="<%=basePath%>empImg/<%=emp.getEmpImgName()%>"
							style="width: 100px; height: 126px; border:2px solid #ccc" title="学员二寸照片"
							id="stuImg" alt="暂无上传照片" />
							<img src="<%=basePath%>empImg/<%=emp.getCardImgName() %>"s
							style="width: 100px; height: 126px; border:2px solid #ccc" title="学员二寸照片"
							id="img2" alt="暂无上传照片" />
						</div>
						
					</div>
					
					</div>
			</td>
		</tr>
		<tr>
			<td>电话</td>
			<td colspan="3"><%=emp.getTel() %></td>
		</tr>
		<tr>
			<td>身份证号</td>
			<td colspan="3"><%=emp.getIdcard() %></td>
		</tr>
		<tr>
			<td>出生日期</td>
			<td  ><%=emp.getBirthday() %></td>
			<td>入职时间</td>
			<td  ><%=emp.getTime() %></td>
		</tr>
		<tr>
			<td>省 </td>
			<td>
				<%=emp.getProvince() %>
			</td>
			<td>市</td>
			<td>
				 <%=emp.getCity() %>
			</td>
		</tr>
		<tr>
			<td>一级部门 </td>
			<td><%=emp.getDeptOne() %></td>
			<td>二级部门</td>
			<td><%=emp.getDeptTwo() %></td>
			<td>职位 </td>
			<td><%=emp.getJobCode() %></td>
		</tr>
		<tr>
			<td>工号</td>
			<td><%=emp.getCode() %></td>
			<td> </td>
			<td></td>
			<td> 备注</td>
			<td><%=emp.getRemark() %></td>
		</tr>
	
	
	</table>
	<div style="width:300px;height:auto;margin:0 auto;margin-top:30px;">
		<ul>
			<li><input type="submit" value="保存"/></li>
			<li><input type="button" value="取消" /></li>
			<li><input type="reset" value="重置"/></li>
		</ul>
	</div>
	<script>
		layui.use('form', function(){
		  var form = layui.form;
		  
		  //各种基于事件的操作，下面会有进一步介绍
		});
	   layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#test1'

		  });
		//执行一个laydate实例
		  laydate.render({
		    elem: '#test2'

		  });
	});
	
	</script>
	
	
</body>
</html>