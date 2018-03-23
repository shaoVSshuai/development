<%@page import="com.hzyc.website.beans.StudentInfo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/tags/dict.tld"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学员信息添加</title>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">

<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/common.css" media="all">
<script type="text/javascript" src="<%=basePath%>js/loadCity.js" charset=”utf-8″></script>
<style>
.layui-table td, .layui-table th {
    position: relative;
    padding: 6px 15px;
    min-height: 20px;
    line-height: 20px;
    font-size: 14px;
}
</style>
<script type="text/javascript">
		
	function getBirthday(){
		
		var idcard = document.getElementsByName("idcard");
		if( idcard[0].value.length == 0){
			alert("请先输入身份证号码~");
		}else{
			var birthday = idcard[0].value.substring(6,14);
			document.getElementsByName("birthday")[0].value = birthday ;
		}
	}
	
	/**
		图片预览
	
	*/
	function previewFile() {
		
		var preview = document.querySelector('img');
		var file  = document.querySelector('input[type=file]').files[0];
		alert(file);
		var reader = new FileReader();
		
		reader.onloadend = function () {
			preview.src = reader.result;
		};
		if (file) {
			reader.readAsDataURL(file);
			/* var imgPath = document.getElementById("imgPath");
			imgPath.innerHTML = preview.src;	 */
		} 
		
	}
	
	//建立一個可存取到該file的url
	function getObjectURL(file) {
	var url = null ;
	if (window.createObjectURL!=undefined) { // basic
	url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
	url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
	url = window.webkitURL.createObjectURL(file) ;
	}
	var imgPath = document.getElementById("imgPath");
	imgPath.innerHTML = url;
	}

	
	/*
		动态使用input标签
	*/
	function showInfo(){
		
		var selectObj = document.getElementsByName("university")[0];
		//alert(selectObj.selectedIndex);
		var index = selectObj.selectedIndex ;
		//alert(selectObj.options[index].value);
		if(selectObj.options[index].value == "n"){
			var input = document.getElementsByName("school")[0];
			input.removeAttribute("disabled");
			input.value="请填写123";
		}
	}
	
</script>
<% 
	StudentInfo student = (StudentInfo)request.getAttribute("student");
%>
<style> 
</style>
</head>
<body >
	<form action="<%=basePath%>stuCon/updStudentInfo.hzyc" method="post" enctype="multipart/form-data">
		<input type="hidden" value="${student.id}" name="id"/>
		<table  border="1px" class="layui-table" cellpadding="20px" cellspacing="10px" >
			<colgroup>
				<col width="11%" />
				<col width="15%" />
				<col width="11%" />
				<col width="15%" />
				<col width="11%" />
				<col width="37%" />
			</colgroup>
			<tbody>
				<tr>
					<th>姓名:</th>
					<td  >
					  	<input type="text" value="${student.name}" class="hzyc-input"/>
					</td>
				    <td>
					          性别：
					 </td>
					 <td>
					 	<!-- type代表加载sex的字典项 name用来参数绑定  defaultValue是下拉框的默认值  classname是css类样式 -->
					 	<ex:dict type="sex" name="sex" defaultvalue="${student.sex}" classname="hzyc-select" />
				    </td>
						
				   <th rowspan="5" valign="top">学员照片:<br />
					<td rowspan="5" valign="top">
						<!-- 用来显示图片的div -->
						<div id="img-wrapper">
							<img src="<%=basePath%>stuImg/${student.imgName}"
								style="width: 130px; height: 156px;" title="学员二寸照片"
								id="stuImg" alt="暂无照片" />
							<br />
							<input class="hzyc-input" type="file" value="文件上传" id="imgUpload"   class="layui-btn" name="img1" onchange="previewFile()" />
						</div>
						</td>
				</tr>
				<tr>
					<th>身份证号:</th>
					<td  ><input class="hzyc-input" type="text" value="${student.idcard}" />
					</td>
					 <td>
					         学号：
				    </td>
				    <td>
				    	<input class="hzyc-input" type="text" value="${student.code }" />
				    </td>
				</tr>
				<tr>
					<th>出生日期:</th>
					<td colspan="3">
						<input class="hzyc-input" type="text" value="${student.birthday}" />
					</td>
				</tr>
				<tr>
					<th>籍贯:</th>
					<td colspan="3">
							${student.province}
							${student.city}
							${student.birthday}   
					</td>
				</tr>
				<tr>
					<th>学校:</th>
					<td colspan="3">
						<ex:dict type="university" defaultvalue="${student.school}" name="school" classname="hzyc-select" />
					</td>
				</tr>
				<tr>
					<th>专业:</th>
					<td colspan="3">
						<ex:dict type="major" defaultvalue="${student.major}" name="major" classname="hzyc-select" />
					</td>
					<th>家庭住址</th>
					<td>
						<input class="hzyc-input" type="text" value="${student.address}" />
					</td>
				</tr>
				<tr>
					<th>学制:</th>
					<td colspan="3">
						<ex:dict type="s_length" defaultvalue="${student.eLength}" name="eLength" classname="hzyc-select" />
						
						&nbsp;年制&nbsp;&nbsp;&nbsp; 
						<ex:dict type="period"  defaultvalue="${student.period}" name="period" classname="hzyc-select" />
						
						&nbsp;年入学</td>
					<th>手机:</th>
					<td>
						<input class="hzyc-input" name="tel" value="${student.tel}" class="" />
					</td>
				</tr>
				<tr>
					<th>QQ 帐号:</th>
					<td colspan="3">
						<input class="hzyc-input" name="qq" value="${student.qq}" />
					<th>微信:</th>
					<td>
						<input class="hzyc-input" name="wx" value="${student.wx }" />
					</td>
				</tr>
				<tr>
					<th>合众班号:</th>
					<td colspan="3">
						<ex:dict defaultvalue="${student.classes}" type="classes" classname="hzyc-select"/>
					</td>
					<th>班级类别:</th>
					<td>
						没写<input class="hzyc-input" type="text" value="${student.classes}" />
					</td>
				</tr>
				<tr>
					<th>学员状态:</th>
					<td colspan="3">
						<ex:dict type="state" defaultvalue="${student.state }" name="state" classname="hzyc-select"/>
					</td>	
					<th>规划师:</th>
					<td>
						<input class="hzyc-input" type="text" value="${student.planner }" />
					</td>
				</tr>
				<tr>
					<th>在校职务:</th>
					<td>
						没写<input class="hzyc-input" type="text" value="${student.planner }" />
					</td>
					<th>学校班级:</th>
					<td>
						没写<input class="hzyc-input" type="text" value="${student.planner }" />
					</td>
					<th>操作:</th>
					<td> <button onclick="upd('${student.id}')" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon"></i>   修改</button> 
				</tr>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
            _init_area();
   </script>
	<script>
	layui.use('form', function(){
	  var form = layui.form;
	  //各种基于事件的操作，下面会有进一步介绍
	});
	</script>
</body>
</html>