<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../WEB-INF/dict.tld"%>
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
	<form action="<%=basePath%>empCon/addEmp.hzyc"  method="post" enctype="multipart/form-data" >
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
			<td>入职时间</td>
			<td  ><input type="text" name="birthday" class="hzyc-input" id="test2" placeholder="点击展开时间" /></td>
			<td>入职时间</td>
			<td  ><input type="text" name="time" class="hzyc-input" id="test1" placeholder="点击展开时间" /></td>
		</tr>
		<tr>
			<td>省 </td>
			<td>
				<select  id="province" onchange="loadCity()" class="hzyc-select" lay-verify="required" lay-filter="province" name="province" >
		            <option value="11">北京市</option>
		            <option value="12">天津市</option>
		            <option value="13">河北省</option>
		            <option value="14">山西省</option>
		            <option value="15">内蒙古自治区</option>
		            <option value="21">辽宁省</option>
		            <option value="22">吉林省</option>
		            <option value="23">黑龙江省</option>
		            <option value="31">上海市</option>
		            <option value="32">江苏省</option>
		            <option value="33">浙江省</option>
		            <option value="34">安徽省</option>
		            <option value="35">福建省</option>
		            <option value="36">江西省</option>
		            <option value="37">山东省</option>
		            <option value="41">河南省</option>
		            <option value="42">湖北省</option>
		            <option value="43">湖南省</option>
		            <option value="44">广东省</option>
		            <option value="45">广西壮族自治区</option>
		            <option value="46">海南省</option>
		            <option value="50">重庆市</option>
		            <option value="51">四川省</option>
		            <option value="52">贵州省</option>
		            <option value="53">云南省</option>
		            <option value="54">西藏自治区</option>
		            <option value="61">陕西省</option>
		            <option value="62">甘肃省</option>
		            <option value="63">青海省</option>
		            <option value="64">宁夏回族自治区</option>
		            <option value="65">新疆维吾尔自治区</option>
		            <option value="71">台湾省</option>
		            <option value="81">香港特别行政区</option>
		            <option value="82">澳门特别行政区</option>
		        </select>
			</td>
			<td>市</td>
			<td>
				<select id="city" class="layui-select" lay-verify="required" lay-filter="city" name="city" >
		            <option value="11">北京市</option>
		            <option value="12">天津市</option>
		            <option value="13">河北省</option>
		            <option value="14">山西省</option>
		            <option value="15">内蒙古自治区</option>
		            <option value="21">辽宁省</option>
		            <option value="22">吉林省</option>
		         </select>
			</td>
		</tr>
		<tr>
			<td>一级部门 </td>
			<td><select class="hzyc-input" id="dept1" name="deptOne"></select></td>
			<td>二级部门</td>
			<td><select class="hzyc-input" id="dept2" name="deptTwo"></select></td>
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