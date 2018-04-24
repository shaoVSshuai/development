$(function(){
	//头部
	$(".navbar").addClass("header-fix");
	// $(".container-fluid").addClass("mar-top");

	//搜索切换
	var nowWidth = $(window).width();
	if(nowWidth < 768){
		$(".navbar-nav").hide();
		$(".hiddenNav").show();
	}else {
		$(".hiddenNav").hide();
		$(".navbar-nav").show()
	}
	
	//手机号码格式验证
	$(".send-btn").click(function(){
		var userName = $(".user-name").val();
		var userPhone = $(".user-phone").val();
		$(".name-item").val(userName);
		$(".phone-item").val(userPhone);
		var reg=/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;      
		if(!reg.test(userPhone)){        
			alert("请正确填写手机号！");      
		}else{
			$(".cover").show();
			$(".navbar").removeClass("header-fix");
			// $(".container-fluid").removeClass("mar-top");
		} 
	});
	//进行手机号码格式验证
	$(".listening-special").click(function(){
		var userName = $(".name-special").val();
		var userPhone = $(".phone-special").val();
		$(".name-item").val(userName);
		$(".phone-item").val(userPhone);
			var reg=/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;      
			if(!reg.test(userPhone)){        
				alert("请正确填写手机号！");      
			}else{
				$(".cover").show();
				$(".navbar").removeClass("header-fix");
				// $(".container-fluid").removeClass("mar-top");
			} 
	});
	//关闭登录窗口
	$(".close").click(function(){
		$(".navbar").addClass("header-fix");
		// $(".container-fluid").addClass("mar-top");
		$(".cover").hide();
		$(".Introduce").hide();
		//滑动验证码等重置
		reset();
	});
	$(".send1").click(function(){
		var time = 180;
		function timeCountDown(){
		    if(time==0){
			    clearInterval(timer);
			    //验证码重置
			    $('.div-phone button').html("发送验证码");
			    $(".send1").attr("disabled",false);	
			   	$(".send1").addClass("send1-special");

			   
				//滑动验证码等重置
				reset();
			   	
			}else{
				$('.div-phone button').html(time+"S后再发送");
			    time--;
			   	$(".send1").attr("disabled",true);
			   	$(".send1").removeClass("send1-special");
			}
		    
	    }
		timeCountDown();
		var timer = setInterval(timeCountDown,1000);
	})
	$(".teacherList .col-md-3").click(function(){
		$(".navbar").removeClass("header-fix");
		// $(".container-fluid").removeClass("mar-top");
		var index=$(this).index();
		$(".teacher-box .Introduce").eq(index).show().siblings().hide();
	})
	$(".course-list .col-md-3").click(function(){
		$(".navbar").removeClass("header-fix");
		// $(".container-fluid").removeClass("mar-top");
		var index=$(this).index();
		$(".course-box .Introduce").eq(index).show().siblings().hide();
	})
	$(".market-list .col-md-3").click(function(){
		$(".navbar").removeClass("header-fix");
		// $(".container-fluid").removeClass("mar-top");
		var index=$(this).index();
		$(".market-box .Introduce").eq(index).show().siblings().hide();
	})
	//就业弹框
	$(".panel").click(function(){
		$(".navbar").removeClass("header-fix");
		// $(".container-fluid").removeClass("mar-top");
		$(".jobPopup").show();

		var currentPage = 1; //当前页
		var totalPage = 1;   //我要访问的页
		//上一页
		$(".last").click(function(){
   			access --;
   			getList();

		})
		//下一页
		$(".next").click(function(){
                //访问页数+1
                access ++;
                getList();  
              
		})

		
		// getList();
	});
	
	
	var now = 1;
		var access = 1;
		//请求数据
		function getList(){
			
			var xml = new XMLHttpRequest();
			var jobStr = "";
			var name = '/cchzyc/images/employment/';
			xml.onreadystatechange =  function(){
			
				if(xml.readyState == 4){
					
					var dataarr = xml.responseText;
					var obj = JSON.parse(dataarr);
					if(dataarr == '[]'){

						//说明没有下一页了
						access = now;
					}else{
						now = access;
					}
					
					
					for(var i = 0 ; i < obj.length ; i ++){
						if(i >= 2){
							jobStr+='<li class="job-list-item job-list-special job-special"'+ 
			    			'v-for="item in list"><p class="huawei"><i class="iconFont"><img src="' +name + obj[i].companyLogoName+ '"></i></p><p class="student-name">'+obj[i].stuName+'</p><div class="job-cen"><p class="wire"></p></div><p>职位：'+obj[i].position+'</p></li>'+
			    			'<li class="job-list-item job-list-special jobSpecial"'+
			    			'v-for="item in list"><img src="'+ name  + obj[i].lifePhotoName + '"></li>';
					

						}else{
							jobStr+='<li class="job-list-item job-list-special jobSpecial"'+
			    			'v-for="item in list"><img src="'+ name  + obj[i].lifePhotoName + '"></li><li class="job-list-item job-list-special job-special"'+ 
			    			'v-for="item in list"><p class="huawei"><i class="iconFont"><img src="'+name + obj[i].companyLogoName+ '"></i></p><p class="student-name">'+obj[i].stuName+'</p><div class="job-cen"><p class="wire"></p></div><p>职位：'+obj[i].position+'</p></li>';
					
						}
			    	}
					if(dataarr == '[]'){
					}else{
						$(".boxPanel").html(jobStr);
							$(".boxPanel").hide();
						$(".boxPanel").fadeIn(1000);

						if(now == 1){
							$(".panelUl").html(jobStr);
						}
					}
				}

			};

			xml.open("post" , "/cchzyc/epmentCon/fenye.hzyc" , true);
			xml.setRequestHeader("Content-Type" , "application/x-www-form-urlencoded");
			
			xml.send("nowPage=" + access);
		}
	getList();
	$(".closePanel").click(function(){
		$(".jobPopup").hide();
		$(".navbar").addClass("header-fix");
		// $(".container-fluid").addClass("mar-top");
	})
	function getClientInfo(){  
	   var userAgentInfo = navigator.userAgent;  
	   var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");  
	   var agentinfo = null;  
	   for (var i = 0; i < Agents.length; i++) {  
	       if (userAgentInfo.indexOf(Agents[i]) > 0) { agentinfo = userAgentInfo; break; }  
	   }  
	   if(agentinfo){
	        var str2 = '<span class="login-title fl">拖拽验证：</span><div class="stage"><div class="slider" id="slider" style="width:237px;"><div class="label">向右滑动验证</div><div class="track" id="track"><div class="bg-green"></div></div><div class="button" id="btn"><div class="icon" id="icon"></div><div class="spinner" id="spinner"></div></div></div></div>';
	        $(".login-item-box").html(str2);
	   }else{
	        var str1 = '<span class="login-title fl">拖拽验证：</span><div id="slider2" class="slider"></div>';
	        $(".login-item-box").html(str1);
	        $("#slider2").slider({
				width: 288,
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
						$(".send1").addClass("send1-special");
						$(".submit-btn").addClass("submit-btn-special");
					}
				}

			});
	   	}     
	}
	//调用示例
	getClientInfo();
	function reset(){

		var userAgentInfo = navigator.userAgent;  
	   var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");  
	   var agentinfo = null;  
	   for (var i = 0; i < Agents.length; i++) {  
	       if (userAgentInfo.indexOf(Agents[i]) > 0) { agentinfo = userAgentInfo; break; }  
	   }  
	   if(agentinfo){
	         oBtn.style.left =  0;
            oTrack.style.width= 0;
            oIcon.style.display='block';
            oSpinner.style.display='none';   

            //提交按钮不可用
            $(".send1").attr("disabled",true);	
				$(".send1").removeClass("send1-special");
				$(".submit-btn").attr("disabled",true);	
				$(".submit-btn").removeClass("submit-btn-special");
	   }else{
		 		$(".send1").attr("disabled",true);	
				$(".send1").removeClass("send1-special");
				$(".submit-btn").attr("disabled",true);	
				$(".submit-btn").removeClass("submit-btn-special");
				getClientInfo();
 		}
	}

    var oBtn = document.getElementById('btn');
    var oW,oLeft;
    var oSlider=document.getElementById('slider');
    var oTrack=document.getElementById('track');
    var oIcon=document.getElementById('icon');
    var oSpinner=document.getElementById('spinner');
    var flag=1;
 
    //绿色的宽度
    var green = $("#slider").width() - 42; 
    oBtn.addEventListener('touchstart',function(e){
    	flag = 1;
        console.log(flag);
        if(flag==1){ 
            console.log(e);
            var touches = e.touches[0];
            oW = touches.clientX - oBtn.offsetLeft;
            oBtn.className="button";
            oTrack.className="track"; 
        }
        
    },false);
 
    oBtn.addEventListener("touchmove", function(e) {
        if(flag==1){
            var touches = e.touches[0];
            oLeft = touches.clientX - oW;
            if(oLeft < 0) {
                oLeft = 0;
            }else if(oLeft > green) {
                oLeft =  green ;
            }
            oBtn.style.left = oLeft + "px";
            oTrack.style.width=oLeft+ 'px';         
        }
        
    },false);
 
    oBtn.addEventListener("touchend",function test() { 
        if(oLeft>=(green)){
            oBtn.style.left =  green;
            oTrack.style.width= green;
            oIcon.style.display='none';
            oSpinner.style.display='block';             
            flag=1; 
            $("#phone").removeAttr('readonly');  
            $(".send1").removeAttr('disabled');
            $(".submit-btn").removeAttr('disabled'); 
            $(".send1").addClass("send1-special");
            $(".submit-btn").addClass("submit-btn-special");
        }else{
            oBtn.style.left = 0;
            oTrack.style.width= 0;
            flag = 1;
        }
        oBtn.className="button-on";
        oTrack.className="track-on";       
    },false);
})