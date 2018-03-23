<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/tags/dict.tld"%>
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

<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js" charset=”utf-8″></script>
<script  type="text/javascript" src="<%=basePath%>plugins/layer/layer.js" charset=”utf-8″></script>
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

	//加载省级信息
	function initProvince(){
		htmlobj=$.ajax({url:"<%=basePath%>deptEmpCon/selProvince.hzyc",async:false});
		var value = htmlobj.responseText;
		var province = eval(value);
		var select = document.getElementById("province");
		//默认的全部文本
		var defaultOption = document.createElement("option");
		defaultOption.innerText = "全部";
		defaultOption.value = "城市";
		select.appendChild(defaultOption);
		for(var i = 0 ; i < province.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = province[i].name ;
			newOption.value = province[i].name;
			select.appendChild(newOption);
		}
	}
	
	//加载市级信息
	function initCity(select){
		//这种方式其他浏览器不可取
		var select1 = document.getElementById("province").value;
		htmlobj=$.ajax({url:"<%=basePath%>deptEmpCon/selCity.hzyc?code="+select1,async:false});
		var value = htmlobj.responseText;
		var city = eval(value);
		var select = document.getElementById("city");
		//清除子节点
		$("#city").empty();
		//默认的全部文本
		var defaultOption = document.createElement("option");
		defaultOption.innerText = "全部";
		defaultOption.value = "0";
		select.appendChild(defaultOption); 
		for(var i = 0 ; i < city.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = city[i].name ;
			newOption.value = city[i].name;
			select.appendChild(newOption);
		}
	}
	
	//加载一级部门
	function initDept1(){
		htmlobj=$.ajax({url:"<%=basePath%>deptEmpCon/selLevel1.hzyc",async:false});
		var value = htmlobj.responseText;
		var dept1 = eval(value);
		var select = document.getElementById("dept1");
		//默认的全部文本
		var defaultOption = document.createElement("option");
		defaultOption.innerText = "全部";
		defaultOption.value = "0";
		select.appendChild(defaultOption);
		for(var i = 0 ; i < dept1.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = dept1[i].deptName ;
			newOption.value = dept1[i].deptCode;
			select.appendChild(newOption);
		}
	}
	//加载二级部门
	function initDept2(select){
		//这种方式其他浏览器不可取
		var select1 = document.getElementById("dept1").value;
		htmlobj=$.ajax({url:"<%=basePath%>deptEmpCon/selLevel2.hzyc?code="+select1,async:false});
		var value = htmlobj.responseText;
		var dept2 = eval(value);
		var select = document.getElementById("dept2");
		//清除子节点
		$("#dept2").empty();
		//清空职位表
		$("#job").empty();
		//默认的全部文本
		var defaultOption = document.createElement("option");
		defaultOption.innerText = "全部";
		defaultOption.value = "0";
		select.appendChild(defaultOption); 
		for(var i = 0 ; i < dept2.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = dept2[i].deptName ;
			newOption.value = dept2[i].deptCode;
			select.appendChild(newOption);
		}
	}
	//加载二级部门的职位
	function initJob(){
		var select2 = document.getElementById("dept2").value;
		htmlobj=$.ajax({url:"<%=basePath%>deptEmpCon/selDeptByLevel2.hzyc?code="+select2,async:false});
		var value = htmlobj.responseText;
		var job = eval(value);
		var select = document.getElementById("job");
		//清除子节点
		$("#job").empty();
		//默认的全部文本
		var defaultOption = document.createElement("option");
		defaultOption.innerText = "全部";
		defaultOption.value = "0";
		select.appendChild(defaultOption); 
		for(var i = 0 ; i < job.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = job[i].jobName;
			newOption.value = job[i].jobCode;
			select.appendChild(newOption);
		}
		
		
	}
</script>
</head>
<body onload="initProvince();initDept1();">
	<form action="<%=basePath%>deptEmpCon/addEmp.hzyc"  method="post" enctype="multipart/form-data" >
	<table class="layui-table">
		<tr>
			<td>姓名</td>
			<td><input type="text" name="name" class="hzyc-input" /></td>
			<td>性别</td>
			<td><ex:dict type="sex" name="sex" classname="hzyc-select" /></td>
			<td rowspan="5" >
				二寸照片
				<br />
				身份证照
			</td>
			<td rowspan="5" colspan="2">
				<div style="position: relative;bottom:10px;">
					<div class="layui-input-block" style="margin:0 auto;">
						<div>
							<img src="<%=basePath%>images/no_img.jpg"
							style="width: 100px; height: 126px; border:2px solid #ccc" title="学员二寸照片"
							id="stuImg" alt="暂无上传照片" />
							<input type="file" name="img1" id="imgUpload1" style="display:none" onchange="previewFile1()" />
							<input type="button" id="btn" value="请选择 " class="layui-small-btn" onclick="document.getElementById('imgUpload1').click();"   />
						
						</div>
						<div>
							<img src="<%=basePath%>images/no_img.jpg"
							style="width: 100px; height: 126px; border:2px solid #ccc" title="学员二寸照片"
							id="img2" alt="暂无上传照片" />
							<input type="file" name="img2"  id="imgUpload2" style="display:none" onchange="previewFile2()" />
							<input type="button" id="btn" value="请选择 " class="layui-small-btn" onclick="document.getElementById('imgUpload2').click();" />
						</div>
						
					</div>
					
					</div>
			</td>
		</tr>
		<tr>
			<td>电话</td>
			<td colspan="3"><input type="text" name="tel" class="hzyc-input" /></td>
		</tr>
		<tr>
			<td>身份证号</td>
			<td colspan="3"><input type="text" name="idcard" class="hzyc-input" /></td>
		</tr>
		<tr>
			<td>出生日期</td>
			<td  ><input type="text" name="birthday" class="hzyc-input" id="test2" placeholder="点击展开时间" /></td>
			<td>入职时间</td>
			<td  ><input type="text" name="time" class="hzyc-input" id="test1" placeholder="点击展开时间" /></td>
		</tr>
		<tr>
			<td>省 </td>
				<td><select id="province" onchange="initCity(this)" class="hzyc-select" name="province" > </select></td>
			<td>市</td>
			<td><select id="city" class="hzyc-select" name="city" ></select></td>
		</tr>
		<tr>
			<td>一级部门 </td>
			<td><select class="hzyc-input" onchange="initDept2(this)" id="dept1" name="deptOne"></select></td>
			<td>二级部门</td>
			<td><select class="hzyc-input" id="dept2" name="deptTwo" onchange="initJob(this)"></select></td>
			<td>职位 </td>
			<td><select class="hzyc-input" id="job" name="jobCode"></select></td>
		</tr>
		<tr>
			<td>工号</td>
			<td><input type="text" class="hzyc-input" name="code" /></td>
			<td> </td>
			<td></td>
			<td> 备注</td>
			<td> <input type="text" class="hzyc-input" name="remark" /> </td>
		</tr>
	
	
	</table>
	<div style="width:300px;height:auto;margin:0 auto;margin-top:30px;">
		<ul>
			<li><input type="submit" value="保存"/></li>
			<li><input type="button" value="取消" /></li>
			<li><input type="reset" value="重置"/></li>
		</ul>
	</div>
	</form>
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