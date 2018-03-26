$(function(){
	//自动播放图片
	function exportTimer(){
		timer = setInterval(function(){
			var now = new Date();
			var myHours = now.getHours();
			if(myHours <= 12){
				var str = '<img src="img/main.jpg" title="图片"/>'
				$(".fang").html(str);
			}else {
				var Str = '<img src="img/main2.jpg" title="图片"/>'
				$(".fang").html(Str);
			}
		})
	};
	exportTimer();
	//头部
	$(".header").addClass("header-fix");
	$(".header-wrapper").addClass("mar-top");
	//手机号码格式验证
	$(".send-btn").click(function(){
		var userName = $(".user-name").val();
		var userPhone = $(".user-phone").val();
		$(".name-item").val(userName);
		$(".phone-item").val(userPhone);
		$(".header").removeClass("header-fix");
		$(".header-wrapper").removeClass("mar-top");
			var reg=/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;      
			if(!reg.test(userPhone)){        
				alert("请正确填写手机号！");      
			}else{
				$(".cover").show();
			} 
	});
	//进行手机号码格式验证
	$(".listening-special").click(function(){
		var userName = $(".name-special").val();
		var userPhone = $(".phone-special").val();
		$(".name-item").val(userName);
		$(".phone-item").val(userPhone);
		$(".header").removeClass("header-fix");
		$(".header-wrapper").removeClass("mar-top");
			var reg=/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;      
			if(!reg.test(userPhone)){        
				alert("请正确填写手机号！");      
			}else{
				$(".cover").show();
			} 
	});
	//关闭登录窗口
	$(".login-close").click(function(){
		$(".header").addClass("header-fix");
		$(".header-wrapper").addClass("mar-top");
		$(".cover").hide();
	});
	//滑动进行验证
	$("#slider2").slider({
		width: 288, // width
		height: 37, // height
		sliderBg: "rgb(134, 134, 131)", // 滑块背景颜色
		color: "#fff", // 文字颜色
		fontSize: 14, // 文字大小
		bgColor: "#009944", // 背景颜色
		textMsg: "按住滑块，拖拽验证", // 提示文字
		successMsg: "验证通过了哦", // 验证成功提示文字
		successColor: "#fff", // 滑块验证成功提示文字颜色
		time: 400, // 返回时间
		callback: function(result) { // 回调函数，true(成功),false(失败)
			if(result){
				$("#phone").removeAttr('readonly');  
				$(".send1").removeAttr('disabled');
				$(".submit-btn").removeAttr('disabled');
			}
		}
	});
	
	$(".school-btn").click(function(){
		var div = $(".school-list")
		if(div.css("display")!="block"){
			$(".school-btn").addClass("changed");
			div.css("display","block");
		}else{
			$(".school-btn").removeClass("changed");
			div.css("display","none");

		}
	})
	$(".college-btn").click(function(){
		var div = $(".college-list")
		if(div.css("display")!="block"){
			$(".college-btn").addClass("changed");
			div.css("display","block");
		}else{
			$(".college-btn").removeClass("changed");
			div.css("display","none");

		}
	})
	$(".class-btn").click(function(){
		var div = $(".class-list")
		if(div.css("display")!="block"){
			$(".class-btn").addClass("changed");
			div.css("display","block");
		}else{
			$(".class-btn").removeClass("changed");
			div.css("display","none");

		}
	});
	$(".school-list ul li").click(function(){
		var schoolName = $(this).text();
		$(".school-name").val(schoolName);
	});
	$(".college-list ul li").click(function(){
		var collegeName = $(this).text();
		$(".college-name").val(collegeName);
	});
	$(".class-list ul li").click(function(){
		var className = $(this).text();
		$(".class-name").val(className);
	});
	$(".school-name,.college-name").click(function(){
		$(".icon-list").hide();
		$(".icon-btn").removeClass("changed");
	});
	var mySwiper = new Swiper('.swiper-container',{
		loop: true,
		pagination: true,
		//滑⃓动⃓方⃓向⃓
		//horizontal 水⃓平⃓
		//vertical 垂⃓直⃓
	    direction: 'horizontal',
	    //自⃓动⃓播⃓放⃓时⃓间⃓间⃓隔⃓
	    // autoplay: 2000,
	    observer:true,
	    observeParents:true,
	    mousewheelControl : true,
    	mousewheelReleaseOnEdges : true,
    	spaceBetween: 50
	});
	$(".job-list").mouseover(function(){
		$(".job-hide").css("display","none");
	})
	$(".job-list").mouseout(function(){
		$(".job-hide").css("display","block");
	})
	$(".send1").click(function(){
		var time = 180;
		function timeCountDown(){
			if(time==0){
			    clearInterval(timer);
			    $('.div-phone button').html("发送验证码");
			    $(".send1").attr("disabled",false);	 
			}else{
				$('.div-phone button').html(time+"S后再发送");
			    time--;
			   	$(".send1").attr("disabled",true);
			}
		    
	    }
		timeCountDown();
		var timer = setInterval(timeCountDown,1000);
	})
})