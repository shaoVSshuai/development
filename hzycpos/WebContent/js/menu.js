window.onload = function test(){	
		
			var pid = localStorage.getItem("ai");
			
					var allLi = "";
					//加入第一个默认导航
					allLi += '<li class="active" >'+
					'<a href="#" class="dropdown-toggle">'+
						'<i class="menu-icon fa fa-tachometer"></i>'+
						'<span class="menu-text">总控制台 </span>'+
					'</a>'+ 
					'<b class="arrow fa fa-angle-down"></b>'+
					'</li>';
					var obj = JSON.parse(pid);
					for(var i = 0 ; i < obj.pList.length ; i++){
						var mName = obj.pList[i].mName;
						var mUrl = obj.pList[i].mUrl;
						var imgUrl  = obj.pList[i].imgUrl;
						var secondObj = obj.pList[i].list;
						var ppid = obj.pList[i].id;
						
						
						allLi += '<li class="hsub" id="ul-' + ppid + '">'+
						'<a href="#" class="dropdown-toggle">'+
							'<i class="menu-icon fa fa-tachometer"></i>'+
							'<span class="menu-text">'+mName+' </span>'+
							'<b class="arrow fa fa-angle-down"></b>'+
						'</a>'+
						'<ul class="submenu">' ;
							for(var j = 0 ; j < secondObj.length ; j++){
								var pmName = secondObj[j].mName;
								var pmUrl = secondObj[j].mUrl;
								var pimgUrl  = secondObj[j].imgUrl;
								var id = secondObj[j].id;
								var pid = secondObj[j].pId;
								allLi += '<li id="li-' + id + '">'+
								'<a href="javascript:open(\''+ pmUrl +'\', \''+ pid +'\',  \''+ id +'\');" >'+
								'<i class="menu-icon fa fa-caret-right"></i>'+
									pmName +
								'</a>'+
								'<b class="arrow"></b>'+
								'</li>';
							}
							
							allLi += '</ul>'+	'</li>';
					}
					
					var ularray = document.getElementsByClassName("nav nav-list");
					ularray[0].innerHTML = allLi;
					//看看选中哪个
					var pid = localStorage.getItem("pid" );
					var id = localStorage.getItem("id");
					$("#"+pid).addClass("active open hsub");
					$("#"+id).addClass("active");
        
		}
	   function open(url,pid,id){
			var curWwwPath = window.document.location.href;
			var pathName = window.document.location.pathname;
		    //获取带"/"的项目名，如：/uimcardprj
		    var pos = curWwwPath.indexOf(pathName);
		     var localhostPath = curWwwPath.substring(0, pos);
		    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		    var basePath = localhostPath + projectName + "/";
			//选中的父节点
			localStorage.setItem("pid" ,  "ul-"+pid);
			//选中的子id
			localStorage.setItem("id" , "li-"+id);
			//页面跳转
			window.location.href = basePath + url ;
			
		}

/*function addSelectMenu(mid){
	//append menu child
	
	   var value = localStorage.getItem("ai");
		var json = JSON.parse(value).pList;
		 var allFlag = 0;
		for(var i = 0 ; i < json.length  ; i++){
			//找到全部应用
			if(json[i].id == '57'){
				//记录全部应用的下标
				allFlag = i;
			}
		} 
		var length  = json.length > 12 ? 12 : json.length;
		var allLi1 = "";
		for(var i = 0 ; i < length  ; i++){
				//不写成11 是因为不是每个人都有超过12个应用
				if(i < length - 1	){
					//当前选中收银
					if(json[i].id == mid){
						allLi1 += '<li ><a href="'+json[i].mUrl+'" target="bottom">'+
						'<i class="iconfont" >'+json[i].imgUrl+'</i><span id="a-'+i+'" title="'+json[i].mName+'" >'+json[i].mName+'</span></a></li>';
					}else{
						allLi1 += '<li onclick="addAClass(this,\''+json[i].id+'\')"><a href="'+json[i].mUrl+'" target="bottom">'+
						'<i class="iconfont">'+json[i].imgUrl+'</i><span id="a-'+i+'" title="'+json[i].mName+'">'+json[i].mName+'</span></a></li>';
					//}
					
 					//全部应用
				}else{
					//这是最后应用
					allLi1 += '<li onclick="addAClass(this)"><a href="all_app.html" target="bottom">'+
					'<i class="iconfont">&#xe714;</i><span id="a-'+i+'" title="全部应用">全部应用</span></a></li>';
				}
				
				
		}
		$("#menu").append(allLi1);
}
function addAClass(li,pid){
	$(li).addClass("menuactive").siblings().removeClass("menuactive");
	//存储一个id用来显示二级菜单用
	localStorage.setItem("pid",pid);
	//刷新子页面frame
	
}*/
