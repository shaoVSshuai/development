<%@page import="java.util.List"%>
<%@page import="com.hzyc.website.beans.Course"%>
<%@ taglib prefix="ex" uri="../WEB-INF/tags/dict.tld"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/cchzyc/css/iconfont.css">
	<link rel="stylesheet" href="/cchzyc/css/jquery.slider.css" />
	<!-- <link rel="stylesheet" href="/cchzyc/css/swiper-3.4.2.min.css"> -->
	<link rel="stylesheet" href="/cchzyc/css/bootstrap.min.css">
	<link href="/cchzyc/css/slider.css" rel="stylesheet">
	<link rel="stylesheet" href="/cchzyc/css/index.css">
	<script type="text/javascript" src="/cchzyc/js/jquery-3.2.0.min.js"></script>
	<!-- // <script type="text/javascript" src="/cchzyc/js/vue.js"></script> -->
	<script type="text/javascript" src="/cchzyc/js/jquery.slider.min.js"></script>
	<script type="text/javascript" src="/cchzyc/js/flexible.js"></script>
	<script type="text/javascript" src="/cchzyc/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/cchzyc/js/main.js"></script>
	<title>合众易成</title>
	<script>
		$(document).ready(function(){
			$(".submit-btn").click(function(){
				//所有表单选项
				var value =  "name=" + $("#name").val() + "&tel="+ $("#tel").val() + "&applyLesson=" + $("#applyLesson").val() 
						+ "&school=" + $("#school").val() + "&stuDept=" + $("#stuDept").val() +"&classes=" + $("#classes").val() 
						+ "&validCode=" + $("#phone").val() ;
				if( $("#name").val().trim() == "" || $("#tel").val().trim()=="" || $("#applyLesson").val().trim() =="" || $("#school").val().trim() == "" || $("#stuDept").val().trim() == "" || $("#classes").val().trim() == "" || $("#phone").val().trim() == "" ){
					alert("请填写完整信息..");
				}else{
					 $.ajax({
						   type: "POST",
						   url: "<%=basePath%>index/addAudition.hzyc",
						   data: value,
						   success: function(msg){
							   var info = "";
							   if(msg == "101"){
								   info = "提交成功!";
								   alert(info);
								   $(".cover").hide();
							   }else if(msg == "102"){
								   info = "验证失败,请检查验证码填写...";
								   alert(info);
							   }else if(msg == "103"){
								   info = "提交失败,错误代码"+msg;
								   alert(info);
							}
							
						}
					});
				}
			   
			});
			
			$(".send1").click(function(){
				var value =  $("#tel").val();
				$.ajax({
					   type: "POST",
					   url: "<%=basePath%>index/getMessage.hzyc",
					   data:{ 
						   phone:value
					   }
				});
			});
			
		});
		
		
			
	</script>
	
</head>
<body>
	<nav class="navbar navbar-default navbar-inverse site-navbar" role="navigation">
		<div class="container"> 
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand clearfix" href="#">
					<span class="logo-img"></span>
					<span class="name">合众易成</span>
				</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav clearfix">
					<li>
						<input type="text" name="name" class="user-name nameSpecial" placeholder="姓名">
					</li>
					<li>
						<input type="text" name="phone" class="user-phone phoneSpecial" placeholder="手机号码">
					</li>
					<li>
						<button class="listening send-btn">申请试听</button>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="header-wrapper">
			<div class="context">
				<img src="<%=basePath %>/img/main2.jpg">
			</div>
			<div class="box">
				<h2>合众易成，做你生活中的IT导师</h2>
				<div class="box-submit clearfix fl">
					<input type="text" name="name" class="user-name name-special" placeholder="姓名">
					<input type="text" name="phone" class="user-phone phone-special" placeholder="手机号码">
					<button type="submit" class="listening listening-special">申请试听</button>
				</div>
			</div>
		</div>
	</div>
	<div class="introduce">
		<div class="container">
			<div class="company">
				<h1 class="title">长春合众易成信息技术咨询服务有限公司</h1>
				<p>
					公司目前拥有两家子公司，在无锡投资无锡创科软件股份有限公司,在吉林市有长春合众易成吉林分公司
				</p>
				<p>
					公司的创始人及技术团队在人口信息管理,电子商务,应用集成,ERP,CRM,商务智能方面具有多年丰富的软件及硬件系统的开发,实施,咨询经验。
				</p>
				<p>
					公司目前与国内多家公司合作，协助软件研发，技术革新，并提供商业智能解决方案。
				</p>
				<p>
					公司全体员工用多年积累的丰富开发和实施经验以及职业教育经验，用国际化思想,潮流化技术,精英化队伍。
				</p>
				<p>
					标准化服务为国家培养IT行业精英之才，为企业提供全方位技术解决方案及咨询服务。
				</p>
				<p>
					公司秉承”自由,平等,开放,合作,创新,共赢“的经营理念，为每一个客户着想。
				</p>
				<p>
					将公司的利益融入客户的利益，并不断更新自我，推动行业技术进步。
				</p>
				<p>
					秉承”专业兴教,教育兴产,产业兴国“的理念，共同推动中国软件产业及人才培养的发展。
				</p>
				<p>
					让更多有梦想从事IT行业的年轻精英有一个实现梦想的舞台，成为世界IT精英的摇篮！
				</p>
			</div>
		</div>
		<%
		List<Course> cList = (List<Course>)request.getAttribute("courseList");
		if(cList!=null && cList.size()>0){
			%>
			<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="course-name">
					<div class="cut"></div>
					<h3 class="title">精品课程</h3>
				</div>
			</div>
		</div>
		<div class="row course-list">
			<%
				
				for(int i = 0 ;i<cList.size();i++){
				%>
				<div class="col-md-3">
				<li class="course-item">
					<p class="android">
						<img style="margin-top:20px;width:64px;height:67px;" src="<%=basePath %>/images/course/<%=cList.get(i).getIconName()%>" id="img"/>
					</p>
					<p class="title">
						<%=cList.get(i).getCourseName() %>
					</p>
					<p class="label">
						<%=cList.get(i).getTitle() %>
					</p>
					<p>
						<span class="wire"></span>
					</p>
					<p>
						<%=cList.get(i).getApplication() %>
					</p>
				</li>
			</div>
			<%}%>
		</div>
	</div>
		<% }%>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="team-name">
						<div class="cut"></div>
						<h3 class="title">教师团队</h3>
					</div>
				</div>
			</div>
			<div class="row teacherList">
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="<%=basePath %>/img/teacher.jpg">
						<div class="teacherBox">
							<p class="teacher-title">董事长</p>
							<p class="wire"></p>
							<p class="teacher-name">腾飞</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="<%=basePath %>/img/people.jpg">
						<div class="teacherBox">
							<p class="teacher-title">训导总监</p>
							<p class="wire"></p>
							<p class="teacher-name">乐毅</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="<%=basePath %>/img/teacher2(1).jpg">
						<div class="teacherBox">
							<p class="teacher-title">技术总监</p>
							<p class="wire"></p>
							<p class="teacher-name">隋春雨</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="<%=basePath %>/img/teacher4.jpg">
						<div class="teacherBox">
							<p class="teacher-title">Web前端</br>高级讲师</p>
							<p class="wire"></p>
							<p class="teacher-name">关艺馨</p>
						</div>
					</li>
				</div>
			</div>
		</div>
		<div class="get-job">
			<div class="container">
				<div class="row">
					<div class="job-name">
						<div class="cut"></div>
						<h3 class="title title-special">就业喜报</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<ul class="clearfix panel panelUl">
	                    </ul>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row marker-top">
				<div class="col-md-12">
					<div class="team-name">
						<div class="cut"></div>
						<h3 class="title">市场团队</h3>
					</div>
				</div>
			</div>
			<div class="row market-list">
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="<%=basePath %>/img/teacher6.jpg">
						<div class="teacherBox">
							<p class="teacher-title">社招中心</br>总经理</p>
							<p class="wire"></p>
							<p class="teacher-name">齐武春</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="<%=basePath %>/img/teacher8.jpg">
						<div class="teacherBox">
							<p class="teacher-title">吉林分校</br>市场部总经理</p>
							<p class="wire"></p>
							<p class="teacher-name">安全</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="<%=basePath %>/img/teacher3(1).jpg">
						<div class="teacherBox">
							<p class="teacher-title">长春分校</br>市场部总经理</p>
							<p class="wire"></p>
							<p class="teacher-name">曹彦萍</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="<%=basePath %>/img/teacher7.jpg">
						<div class="teacherBox">
							<p class="teacher-title">校企部</br>总经理</p>
							<p class="wire"></p>
							<p class="teacher-name">王天昊</p>
						</div>
					</li>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="footer-container">
						<div class="footer-title">
							<div class="footer-cut"></div>
							<h3 class="title">专业兴教.教育兴产.产业兴国</h3>
						</div>
						<div class="join">
							<img src="<%=basePath %>/img/sao.png">
							<span class="join-our">扫微信关注我们</span>
						</div>
						<p>公司地址：长春市前进大街与修正路交汇，修正服务外包大厦2209室</p>
						<p>长春合众易成咨询服务技术有限公司©</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="cover">
		<div class="login hiddenLogin">
			<div class="clear-close clearfix">
				<div class="close">
					<i class="iconfont">&#x3443;</i>
				</div>
			</div>
			<div class="login-list">
				<div class="login-item clearfix">
				<span class="login-title">学校：</span>
				 <ex:dict type="university" classname="text-item school-name" id="school" name="school" />
				
			</div>
			<div class="login-item clearfix">
				<span class="login-title">学院：</span>
				<ex:dict type="school_dept" classname="text-item college-name"  id="stuDept" name="stuDept" />
				<span class="login-title grade-title">年级：</span>
				<select class="text-item grade" name="classes" id="classes" >
					<option>大四</option>
					<option>大三</option>
					<option>大二</option>
					<option>大一</option>
				</select>
			</div>
			<div class="login-item clearfix">
				<span class="login-title">申请课程：</span>
				<ex:dict type="class_type" classname="text-item class-name" id="applyLesson" name="applyLesson" />
				
			</div>
			<div class="login-item clearfix">
				<span class="login-title">姓名：</span>
				<input type="text" class="text-item name-item item-special" readonly="readonly" placeholder="数字字典" name="name" id="name">
			</div>
		    <div class="login-item clearfix">
				<span class="login-title">手机号：</span>
				<input type="text" class="text-item phone-item item-special" readonly="readonly" placeholder="请输入手机号" name="tel" id="tel">
			</div>
		    <div class="login-item clearfix login-item-box">
			</div>
			<div class="div-phone login-item clearfix">
		        <label for="phone" class="login-title">验证码：</label>
		        <input type="text" id="phone" placeholder="请输入验证码" readonly>
		       <button href="javascript:;" class="send1" disabled>发送验证码</button>
		    </div>
			<div class="login-item login-submit">
				<button type="button" class="submit-btn" disabled>提交</button>
			</div>
				
			</div>
		</div>
	</div>
	<div class="course-box">
		<div class="Introduce">
			<div class="bomb-box">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="bomb-img fl">
						<div class="course-img">
							<i class="iconfont">&#xe721;</i>
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">android开发</p>
						<div class="teacherTitle">
							环境搭建、配置虚拟机、介绍android系统LinearLayout、RelativeLayout布局讲解、常用控件、Activity加载布局文件GridView和ListView使用、BaseAdapter和ArrayAdapter用法ScrollView使用，静态加载和动态加载UI控件Activity生命周期、传值和回传值、现场保护、自定义适配器、ListView图文混排Menu、DialogAndroid数据存储、File存储、SQLite数据库ContentProvider、Http通信异步任务、Fragment详解、NotificationBroadcastReceiver广播、Service《电子商务APP》《即时通讯APP》等项目开发
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="bomb-box">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="bomb-img fl">
						<div class="course-img">
							<i class="iconfont">&#xe676;</i>
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">java开发</p>
						<div class="teacherTitle">
							Java句法、语法基础、面向对象编程数组、字符串、Math、Date、SimpleDateFormat常用类使用数据结构List（Vector、Stack）、Set、Map详解
							Java异常处理、捕获机制MySQL数据库、JDBC技术、批量处理、事务处理Java I/O流技术、预处理技术、Java多线程技术Java图形化技术、RCP技术《航空售票系统》，《影院售票系统》项目开发HTML、DIV、CSS技术JSP、Servlet技术、MVC开发模式、Tomcat服务器、文件上传、下载技术Oracle数据库、连接池技术JavaScript语言、Dom模型及元素操作、Ajax技术、JSon技术文本编辑器等项目常规应用MyBatis框架详解、SpringMVC框架详解Hibernate\Struts2\Spring专题《电子商务平台》项目开发
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="bomb-box">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="bomb-img fl">
						<div class="course-img">
							<i class="iconfont">&#xe61e;</i>
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">web开发</p>
						<div class="teacherTitle">
							HTML概述、标题、段落、图像、列表、超链接、表格的使用、框架，表单控件及多媒体H5新语义化标签及新特性CSS概述、基本语法、优先级、选择器及伪类盒模型、表格、列表、轮廓、定位、浮动主流浏览器兼容性及静态企业站及展示站实践CSS3用户列表、动画、过度移动端web布局、响应式web前端开发
							移动端电商网站实践HTML概述、标题、段落、图像、列表、超链接、表格的使用、框架，表单控件及多媒体H5新语义化标签及新特性CSS概述、基本语法、优先级、选择器及伪类盒模型、表格、列表、轮廓、定位、浮动主流浏览器兼容性及静态企业站及展示站实践CSS3用户列表、动画、过度移动端web布局、响应式web前端开发移动端电商网站实践
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="bomb-box">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="bomb-img fl">
						<div class="course-img">
							<i class="iconfont">&#xe633;</i>
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">专题课程</p>
						<div class="teacherTitle">
							Maven：Apache Maven 是一种创新的软件项目管理工具，提供了一个项目对象模型（POM）文件的新概念来管理项目的构建，相关性和文档。最强大的功能就是能够自动下载项目依赖库。GIT：Git是目前世界上最先进的分布式版本控制系统。团队协作开发、多任务并行的好帮手。Redis：可基于内存亦可持久化的日志型、Key-Value数据库，高可用高并发保障的神器。Dubbo：一个分布式、高性能、透明化的RPC服务框架，大型互联网应用多系统之间交互和服务管理利器。EasyUI：一种基于jQuery的用户界面插件集合，名副其实的能快速地在流行的 jQuery 核心和 HTML5 上建立程序页面UI插件。
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="teacher-box">
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="<%=basePath %>/img/teacher.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">董事长</p>
						<p class="wire"></p>
						<p class="teacher-name">腾飞</p>
						<div class="teacherTitle">
							拥有多 年上市企业  BI 、 DI 、 OA 、 CRM 、ERP  等大型项目研发经验 ，于 2006  年起主持研发  ezETL 、ezOLAP  等重要商业智能核心产品 ，于 2008  年参与研发北京奥运门禁监控系统 。擅长大数据开发、Oracle  数据库优化、C  语言研发、 JavaEE开发 、Android  开发 ，并在从事研发期间多次为公司攻坚技术难关，为公司获得多项行业技术专利，是能力比较全面的  IT  行业技术专家。在 IT  技能培训行业里，拥有  7  年的培训经验 ，培养了  2340  名行业技术精英 ， 是不可多得的大学生技术导师.从教期间自主设计了一整套科学合理的阶段教学方法和培训方案，成为培训行业标准。
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="<%=basePath %>/img/people.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">训导总监</p>
						<p class="wire"></p>
						<p class="teacher-name">乐毅</p>
						<div class="teacherTitle">
							拥有多 年企业  OA 、 O2O  电子商务平台等大型项目研发经验 ，曾经参与跨国项目 《 几内亚户籍管理系统 》 的设计与研发 ，
							擅长 JAVAEE  开发、 Android  客户端 APP  开发、iOS  客户端 APP  开发，在参与项目期间为公司培养大量移动端开发技术力量，是能力比较全面的 IT  行业技术专家。在  IT  技能培训行业 ， 拥有  9  年的培训经验 ， 培养了  3620  名行业技术精英，是名副其实的技术名师，并在从教期间为公司培养了大量年轻教师 ，并总结形成一整套教师培养管理考核办法 ，成为行业标准。
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="<%=basePath %>/img/teacher2(1).jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">技术总监</p>
						<p class="wire"></p>
						<p class="teacher-name">隋春雨</p>
						<div class="teacherTitle">
							拥有7年的软件开发经验，3年的JAVAEE教学经验，七年间曾经参与过京东商城礼品卡系统、中国移动内容计费sysmgr月报系统、中国移动内容计费业务监控系统、案例管理系统、中国银行核心参数管理系统、中国银行公共信息管理系统等大型应用软件开发，3年的讲师经历指导帮助了近千名大学生走进IT行业，是行业内为数不多的既能从事开发又能从事教学的复合型专家。
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="<%=basePath %>/img/teacher4.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">Web前端高级讲师</p>
						<p class="wire"></p>
						<p class="teacher-name">关艺馨</p>
						<div class="teacherTitle">
							拥有多 年上市企业 WEB端 O2O 、 B2C 电子商务平台等大型项目研发经验 ，于 2013  年独立开发 大型电子商务网站 前端业务，于 2014  年独立开发 O2O 产品库 ，  于 2015  年独立开发 大型在线试听网站，擅长H5开发，移动端响应式开发，微信小程序开发，JavaScript开发 ，熟悉 Java、PHP等后台开发语言。担任前端开发组长，并在从事开发工作过程中协助其他组员解决难题。是能力比较全面的 WEB前端开发工程师。在 IT  技能培训行业里，拥有  2  年的培训经验 ， 培养了  200  名行业技术精英 ， 结合BAT实际应用场景独创新颖教学方案。教学思路清晰，管理严格.
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="market-box">
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="<%=basePath %>/img/teacher6.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">社招中心总经理</p>
						<p class="wire"></p>
						<p class="teacher-name">齐武春</p>
						<div class="teacherTitle">
							有15年的人力资源管理咨询经验，10年的市场营销经验，曾为长春市近120余家企业做过管理培训、人资培训、代理招聘、企业营销策划，积累了丰富的行业经验，并开阔了人生视野，对多个行业的发展有独到的见解。在9年多的IT职业教育从业期间，累计为长春市各个高校做职业教育及专业认识课程456场，一对一为近1560多名学生做过人生规划和职业指导，并成功推荐指导近3000多名学生成功就业于国内知名IT企业，并通过多年的职业规划经验，总结编写了《职业规划工作指导手册》，目前该手册已经成为吉林省IT职业教育领域职业规划的的工作指导
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="<%=basePath %>/img/teacher8.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">吉林分校市场部总经理</p>
						<p class="wire"></p>
						<p class="teacher-name">安全</p>
						<div class="teacherTitle">
							致力于IT咨询服务事业，拥有6年的人力资源管理咨询经验，5年的市场营销经验，曾任吉林省人才委员会高级职业规划师，IT教育服务中心特约讲师。曾在吉林大学、长春理工大学、北华大学、吉林电子信息技术学院、吉林化工学院等多家大学做过企业宣讲、大学生职业规划及就业指导课程。主持过吉林大学、北华大学、东北电力大学校园招聘。为近1000名在校大学生做过职业规划和就业指导，为全国输送近500位IT人才，近100位高级工程师。2015年在吉林市建立合众易成吉林分公司，培养了大批技术人才与销售人才，成功推荐公司学员入职北京中科软、北京亚信联创、北京江融信科技有限公司、北京宇信易诚等多家知名企业。
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="<%=basePath %>/img/teacher3(1).jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">长春分校市场部总经理</p>
						<p class="wire"></p>
						<p class="teacher-name">曹艳萍</p>
						<div class="teacherTitle">
							拥有14年的教育咨询、管理工作经验，积累了丰富的行业经验。曾任长春树仁专修学院持生办主任、长春市嘉恒职业学校副校长、吉林省UNIX计算机培训中心市场总经理、北京领测国际科技有限公司市场总监、现任合众易成科技有限公司市场部经理，曾带领并指导了10届学生成功就业。目前学员遍布全国各地，华为、浪朝、海信、完美世界、神州太月、等大中型IT公司，成为IT行业精英！
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="<%=basePath %>/img/teacher7.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">校企部总经理</p>
						<p class="wire"></p>
						<p class="teacher-name">王天昊</p>
						<div class="teacherTitle">
							曾任日本朝日新闻西日本本部社长助理曾任河津不动产株式会社社长助理兼西日本部总经理曾任英可思（长春）科技有限公司中国总部总经理助理拥有在海外六年的工作和学习经历，曾经工作于日本朝日新闻株式会社和不动产公司，回国后就职于一家跨国公司，具有较强的国际化背景，拥有国际领先的管理办法和指导思想，带给公司一些先进的企业文化理念和成熟的管理制度，并多次参加商务部门、工信部门、科技部门、教育部门等高端会议，掌握行业前沿发展动态，为公司未来发展方向及业务拓展制定良好策略。
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="jobPopup">
		<div class="overBox">
			<div class="btn-box closePanel">
				<i class="iconfont">&#x3443;</i>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="panelBox clearfix">
		                    <ul class="clearfix panel boxPanel">
		                    </ul>
				        </div>
					</div>
				</div>
			</div>
			<div class="btn-box">
		    	<button class="last">上一页</button>
		    	<button class="next">下一页</button>
		    </div>
		</div>
	</div>

	<script type="text/javascript" src="/cchzyc/js/slider.js"></script>
</body>
</html>