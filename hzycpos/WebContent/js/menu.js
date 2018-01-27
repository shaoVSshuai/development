function addSelectMenu(mid){
	//append menu child
	
	   var value = localStorage.getItem("ai");
		var json = JSON.parse(value).pList;
		/* var allFlag = 0;
		for(var i = 0 ; i < json.length  ; i++){
			//找到全部应用
			if(json[i].id == '57'){
				//记录全部应用的下标
				allFlag = i;
			}
		} */
		var length  = json.length > 12 ? 12 : json.length;
		var allLi1 = "";
		for(var i = 0 ; i < length  ; i++){
				//不写成11 是因为不是每个人都有超过12个应用
				if(i < length - 1	){
					//当前选中收银
					/*if(json[i].id == mid){
						allLi1 += '<li ><a href="'+json[i].mUrl+'" target="bottom">'+
						'<i class="iconfont" >'+json[i].imgUrl+'</i><span id="a-'+i+'" title="'+json[i].mName+'" >'+json[i].mName+'</span></a></li>';
					}else{*/
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
	
}
