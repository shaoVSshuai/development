<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/dict.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String fCode = request.getParameter("flag");
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学员信息添加</title>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery1.4.2.js" charset=”utf-8″></script>

<script type="text/javascript">
	
	/**
		1.获取出生日期 （截取身份证号）	 
	
	*/
	function getBirthday(){
		
		var idcard = document.getElementsByName("idcard");
		if( idcard[0].value.length == 0){
			alert("请先输入身份证号码~");
		}else{
			var birthday = idcard[0].value;
			var info = birthday.substring(6,10)+"-"+birthday.substring(10,12)+"-"+birthday.substring(12,14);
			document.getElementsByName("birthday")[0].value = info ;
		}
	}
	
	/**
		2.date控件点选日期
	
	*/
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#inputDate' //指定元素
	  });
	});
	
	
	/**
		3.图片上传预览
		
	*/
	function previewFile() {
		
		var preview = document.querySelector('img');
		var file  = document.getElementById("imgUpload").files[0];
		var reader = new FileReader();
		
		reader.onloadend = function () {
			preview.src = reader.result;
		};
		if (file) {
			reader.readAsDataURL(file);
		} 
	}
	
	/**
		4.提交信息tips
		
	*/
	layui.use('layer', function(){
		  var layer = layui.layer;
		  var code = <%=fCode%> ;
		  if(code != null && code == 1){
			  layer.msg('添加成功 ！！');
		  }
		  if(code != null && code == 2){
			  layer.msg('添加失败了 ！！');
		  }
	}); 
	
	/**
		
		6.动态加载入学年份(根据当前年份，不用维护)
			
	*/
	function initDate(){
		
		var selectObj = document.getElementById("dateSelectId");
		var date = new Date(); 
		var nowYear = date.getFullYear();
		for(var i = 0; i<= 9 ;i++){
			
			var optionObj = document.createElement("option");
			optionObj.value = nowYear - i;
			optionObj.innerHTML = nowYear - i;
			selectObj.appendChild(optionObj);
			
		}
	}
	
	/**
		7.加载职业规划师
	
	*/
	function initPlanner(){
		
		htmlobj=$.ajax({url:"<%=basePath%>empCon/selPlanner.hzyc",async:false});
		var value = htmlobj.responseText;
		var emp = eval(value);
		var select = document.getElementById("planner");
		for(var i = 0 ; i < emp.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = emp[i].name ;
			newOption.value = emp [i].jobCode;
			select.appendChild(newOption);
		}
		var xinzeng = document.createElement("option");
		xinzeng.innerText = "新增" ;
		xinzeng.value = "n";
		select.appendChild(xinzeng);
	}
	
	
	//** layui form监听事件 
	layui.use('form', function(){
		  var form = layui.form; 
		 
			//学校追加
			form.on('select(school)', function(data){
				
				var selectObj = data.elem;
				var index = selectObj.selectedIndex ;
				if(selectObj.options[index].value == "n"){
					var input = document.getElementsByName("schoolNew")[0];
					input.removeAttribute("disabled");
					input.value="";
					input.placeholder="请填写新增信息…";
					input.onblur = function() {
						selectObj.options[index].value = input.value;
					};
				}
			});
		  
		  	//学院追加
			form.on('select(dept)', function(data){
				var selectObj = data.elem;
				var index = selectObj.selectedIndex ;
				if(selectObj.options[index].value == "n"){
					var input = document.getElementsByName("deptNew")[0];
					input.removeAttribute("disabled");
					input.value="";
					input.placeholder="请填写新增信息…";
					input.onblur = function() {
						selectObj.options[index].value = input.value;
					};
				}
			});
			
			//专业追加
		 	form.on('select(major)', function(data){
		 		var selectObj = data.elem;
				var index = selectObj.selectedIndex ;
				if(selectObj.options[index].value == "n"){
					var input = document.getElementsByName("majorNew")[0];
					input.removeAttribute("disabled");
					input.value="";
					input.placeholder="请填写新增信息…";
					input.onblur = function() {
						selectObj.options[index].value = input.value;
					};
				}
			});
		 	
		 	//规划师追加
		 	form.on('select(planner)', function(data){
		 		var selectObj = data.elem;
				var index = selectObj.selectedIndex ;
				if(selectObj.options[index].value == "n"){
					var input = document.getElementsByName("plannerNew")[0];
					input.removeAttribute("disabled");
					input.value="";
					input.placeholder="请填写新增信息…";
					input.onblur = function() {
						selectObj.options[index].value = input.value;
					};
				}
			});
		 	
		 	//监听省份下拉框
		 	form.on('select(province)', function(data){
		 		var selectObj = data.elem;
				var index = selectObj.selectedIndex ;
				var provinceCode  = selectObj.options[index].value ;
				xhr=$.ajax({
					url:"../AddressUtil/getAddress.hzyc",
					async:false,
					data:{
						targetCode:provinceCode
					}
				});
				//取值
				var info = JSON.parse(xhr.responseText) ;
				var infoObj = document.getElementById("city");
				//重置节点
				var cLength = infoObj.childNodes.length;
				for(var i=0,len=cLength;i<len;i++){
					infoObj.removeChild(infoObj.lastChild);
				}
				//添加节点
				for(var code in info){
					var newOption = document.createElement("option");
					newOption.value = code;
					newOption.innerHTML = info[code];
					infoObj.appendChild(newOption);  
				}
				//刷新控件
				form.render();
		 	});
		 	
		 	//监听城市下拉框
		 	form.on('select(city)', function(data){
		 		var selectObj = data.elem;
				var index = selectObj.selectedIndex ;
				var cityCode  = selectObj.options[index].value ;
				xhr=$.ajax({
					url:"../AddressUtil/getAddress.hzyc",
					async:false,
					data:{
						targetCode:cityCode
					}
				});
				//取值
				var info = JSON.parse(xhr.responseText) ;
				var infoObj = document.getElementById("town");
				//重置节点
				var cLength = infoObj.childNodes.length;
				for(var i=0,len=cLength;i<len;i++){
					infoObj.removeChild(infoObj.lastChild);
				}
				//添加节点
				for(var code in info){
					var newOption = document.createElement("option");
					newOption.value = code;
					newOption.innerHTML = info[code];
					infoObj.appendChild(newOption);  
				}
				form.render();
		 	});
		  //但是，如果你的HTML是动态生成的，自动渲染就会失效
		  //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
		  form.render();
	});
	
	/**
		8.onload 事件
	
	*/
	
	window.onload=function(){
		//加载入学年份		
		initDate();
		//加载规划师
		initPlanner();
	};
	
	
	
	/**
		9.数据校验
		
	*/
	
	  
	
</script>
<style type="text/css">

	.layui-fluid{
	
		margin-top:60px;
		margin-left:100px;
	}
	
	
</style>
</head>

<body >
	
	<form action="<%=basePath%>stuCon/add.hzyc" method="post" enctype="multipart/form-data" class="layui-form">
		
	<div class="layui-fluid">
	 		<div class="layui-row">
					<div class="layui-row">
						<div class="layui-col-md4">
							 <div class="layui-form-item">
								<label class="layui-form-label">★姓名</label>
							 	<div class="layui-input-block">
									<input type="text"  name="name"   lay-verify="required" autocomplete="off" class="layui-input" />
								</div>
							 </div>
							  <div class="layui-form-item">
								<label class="layui-form-label">★性别</label>
								<input type="radio" name="sex" value="1" title="男" checked/>
			     				<input type="radio" name="sex" value="2" title="女" />
							  </div>
							 
							 <div class="layui-form-item">
								<label class="layui-form-label">★学校</label>
							 	<div class="layui-input-block">
									<ex:dict type="university" layFilter="school" name="school" classname="layui-select" plus="y"  /> 
									<input name="schoolNew" type="text" lay-verify="required" class="layui-input" size="10" value="-请添加-" disabled="disabled" />
								</div>
							 </div>
							 <div class="layui-form-item">
								<label class="layui-form-label">★学院</label>
							 	<div class="layui-input-block">
									<ex:dict type="school_dept" layFilter="dept" name="dept" classname="layui-select" plus="y" /> 
									<input name="deptNew" type="text" class="layui-input" lay-verify="required" size="10" value="-请添加-" disabled="disabled" />
								</div>
							 </div>
							 <div class="layui-form-item">
								<label class="layui-form-label">★专业</label>
							 	<div class="layui-input-block">
									<ex:dict type="major" layFilter="major" name="major" classname="layui-select" plus="y" /> 
									<input type="text"name="majorNew" class="layui-input" lay-verify="required" size="10" disabled="disabled" value="-请添加-">
								</div>
							 </div>
							 <div class="layui-form-item">
								<label class="layui-form-label">★学制</label>
							 	<div class="layui-input-block">
									<ex:dict type="s_length"   name="eLength" classname="layui-select"/>
								</div>
							 </div>
							 <div class="layui-form-item">
								<label class="layui-form-label">★入学时间</label>
							 	<div class="layui-input-block">
									<select id="dateSelectId" class="hzyc-select" name="period"></select>
								</div>
							 </div>
						</div>
						
<!-- 我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	 -->
						
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">★身份证号</label>
							  	<div class="layui-input-block">
									<input type="text"  name="idcard" lay-verify="required|identity" autocomplete="off" class="layui-input" />
								</div> 
							  </div>
							<div class="layui-form-item">
								<label class="layui-form-label">★出生日期</label>
							 	<div class="layui-input-block">
									<input placeholder="点击展开日期" name="birthday"  lay-verify="required|date"   class="layui-input" id="inputDate" />
									<input type="button" name="birthday" value="从身份证获取"  class="layui-btn" onclick="getBirthday()"/> 
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">★籍贯</label>
								<div class="layui-input-block">
									<select  id="province" class="hzyc-select" lay-verify="required" lay-filter="province" name="province" >
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
					                <select id="city" class="layui-select" lay-verify="required" lay-filter="city" name="city" ></select>
					                <select id="town" class="layui-select" lay-verify="required" name="town" ></select>
								</div>
							</div>
							<div class="layui-form-item">
									<label class="layui-form-label">★家庭住址</label>
								 	<div class="layui-input-block">
										<input type="text"  name="address" lay-verify="required" autocomplete="off" class="layui-input"/>
									</div>
							</div>
							<div class="layui-form-item">
									<label class="layui-form-label">★手机</label>
								 	<div class="layui-input-block">
										<input type="text"  name="tel" lay-verify="required|phone" autocomplete="off" class="layui-input" />
									</div>
							</div>
							<div class="layui-form-item">
									<label class="layui-form-label">★QQ</label>
								 	<div class="layui-input-block">
										<input type="text"  name="qq" lay-verify="required|number" autocomplete="off" class="layui-input" />
									</div>
						    </div>	 
						    <div class="layui-form-item">
									<label class="layui-form-label">★微信</label>
								 	<div class="layui-input-block">
										<input type="text"  name="wx" lay-verify="required" autocomplete="off" class="layui-input" />
									</div>
						    </div>
						    
						</div>
						
<!-- 我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	我是一条分割线	 -->
						
						<div class="layui-col-md3">
								<div class="layui-form-item">
									<label class="layui-form-label">★学员照片:</label>
								 	<div class="layui-input-block">
										<img src="<%=basePath%>images/no_img.jpg"
										style="width: 130px; height: 156px; border:2px solid #ccc" title="学员二寸照片"
										id="stuImg" alt="暂无上传照片" />
									</div>
									<input type="file" name="img1" id="imgUpload" style="display:none" onchange="previewFile()" />
									<input type="button" id="btn" value="请选择照片" class="layui-btn" onclick="document.getElementById('imgUpload').click();" style="margin-left:125px; margin-top:10px" />
							    </div>
							    <div class="layui-form-item">
									<label class="layui-form-label">★合众班号</label>
								 	<div class="layui-input-block">
										<input type="text"  name="classes" lay-verify="required" autocomplete="off" class="layui-input" />
									</div>
						    	</div>
						    	<div class="layui-form-item">
									<label class="layui-form-label">★学员状态</label>
								 	<div class="layui-input-block">
										<ex:dict type="student_status" name="state"/>
									</div>
						    	</div>
						    	<div class="layui-form-item">
									<label class="layui-form-label">★规划师</label>
								 	<div class="layui-input-block">
										<select name="planner" id="planner" class="layui-select" lay-filter="planner">
										</select>
										<input type="text" lay-verify="required" name="plannerNew" autocomplete="off" class="layui-input" disabled="disabled" value="-请添加-" />
									</div>
						    	</div>
						    	<div class="layui-form-item">
								 	<div class="layui-input-block">
										<button lay-submit lay-filter="allSubmit" class="layui-btn">提交</button>  
										<input 	type="reset" onclick="javascript:if(confirm('确认重置所有选项吗?')) return;else return false;" class="layui-btn" value="重置"/>
									</div>
						       </div>
						</div>
				</div>
			</div>	
	</div>	
			
	</form>	     
	<script>
layui.use('form', function(){
  var form = layui.form;
  
  //各种基于事件的操作，下面会有进一步介绍
});
</script>
			
</body>

</html>
