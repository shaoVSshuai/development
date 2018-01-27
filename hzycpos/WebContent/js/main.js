var allGoodsJson ;
$(function(){
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var pos = curWwwPath.indexOf(pathName);
     var localhostPath = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    var basePath = localhostPath + projectName + "/";
	var a = window.location.search.split("=");
	// var c = a.user.address;
	// console.log(c);
	// console.log(a);
	var idd = a[1];
	console.log(idd);
	// console.log(a);
	// var a = window.location.search.split("=");
	// var id = a[1];
	//设置div的高度
	var windowHeight = $(window).height();
	$(".content").height(windowHeight - 80);
	$(".shop-list").height(windowHeight - 80 - 4*66-2);
	//设置div的宽度
	var kindWidth = $(".kind").width();
	$(".container").width(kindWidth);
	//tabs选项卡
	// $.ajax({
	// 	url: 'login.html',
	// 	type: 'get',
	// 	data: "aa",
	// 	success: function(data){
	// 		alert("成功了");
	// 	},
	// 	error: function(data){
	// 		alert("没有成功");
	// 	}
	// })
	$.ajax({
		url: basePath + 'goodsKind/selAllGoods.action',
		type: "post",
		dataType: "JSON",
		data: {
		},
		success: function(res){
			allGoodsJson = res;
			var nthTabs;
			nthTabs = $("#editor-tabs").nthTabs();
			for (var i = 0;i < res.goodsType.length;i++){
				var allGoods = "";
				var gList = res.goodsType[i].goodsList;
				var prefix = '<ul class="details-list clearfix">';
				var suffix = '</ul>';
				for(var j = 0;j < gList.length;j++){
					allGoods += '<li class="clearfix" onclick="addGoods(this)" >'+
								'<span class="shop-img">'+
								'<img src="images/images.jpg">'+
								'</span>'+
								'<span class="shop-details fr">'+
								'<p class="name" title="'+gList[j].goodName+'" id="good-'+gList[j].id+'" >'+gList[j].goodName+'</p>'+
								'<p class="shop-surplus clearfix">'+
									'<span class="shop-price fl">￥<span class="good-price">'+gList[j].goodPrice+'</span></span>'+
									'<span class="stock">库存20</span>'+
								'</p>'+
								'</span>'+
								'</li>';
				}
				allGoods = prefix + allGoods + suffix;
					nthTabs.addTab({
						id: res.goodsType[i].id,
						title: res.goodsType[i].name,
						content: allGoods,
						active:true,
						allowClose:false,
					}).setActTab("#"+res.goodsType[0].id);
			}
		},
		error: function(res){
			alert("失败");
		}
	})

	$(".post-icon").click(function(){
		qingqiu();
	});
	$(".search").keyup(function(){
		qingqiu();
	});
	function qingqiu(){
		//生成一个div覆盖原div
		var div = document.createElement("div");
		var searchText = $(".search").val();
		var allGoods = "";
		var prefix = '<ul class="details-list clearfix">';
		var suffix = '</ul>';
		
		if(searchText.trim() != ""){
			for(var i = 0;i < allGoodsJson.goodsType.length;i++){
				for(var j = 0;j < allGoodsJson.goodsType[i].goodsList.length;j++){
					var easyName = allGoodsJson.goodsType[i].goodsList[j].easyPinyin;
					var fullName = allGoodsJson.goodsType[i].goodsList[j].fullPinyin;
					var goodsName = allGoodsJson.goodsType[i].goodsList[j].goodName;
					
					var goodPrice = allGoodsJson.goodsType[i].goodsList[j].goodPrice;
					
					var goodId = allGoodsJson.goodsType[i].goodsList[j].id;
					//比对满足条件的商品
					
						if(easyName.indexOf(searchText) != -1 || fullName.indexOf(searchText) != -1 ||goodsName.indexOf(searchText) != -1){
						allGoods += 
									'<li class="clearfix" onclick="addGoods(this)" >'+
									'<span class="shop-img">'+
									'<img src="images/images.jpg">'+
									'</span>'+
									'<span class="shop-details fr">'+
									'<p class="name" title="'+goodsName+'" id="good-'+goodId+'" >'+goodsName+'</p>'+
									'<p class="shop-surplus clearfix">'+
										'<span class="shop-price fl">￥<span class="good-price">'+goodPrice+'</span></span>'+
										'<span class="stock">库存20</span>'+
									'</p>'+
									'</span>'+
									'</li>';		
						}	
				}
			}
			allGoods = prefix + allGoods + suffix;
			$("#newDiv").remove();
			var newDiv = $("<div></div>");
			newDiv.attr("id","newDiv");
			newDiv.css({"background":"#d7e9ed","position":"absolute","top":"20px","width":"100%","height":"100%","z-index":"1000"});
			newDiv.html(allGoods);
			$(".tab-content").css({"position":"relative"});
			$(".tab-content").append(newDiv);
					
		}else{
			//清除覆盖的div
			$("#newDiv").remove();
		}
	}
	// qingqiu();
	//清空所选商品列表
	$(".empty").click(function(){
		if(confirm('确认清空所选商品列表')){
			$(".shop-item").remove();
		}
		total();
	})
})

function total(){
		var sum = 0;
		var b = $(".shop-item .addNub")
		for(var i = 0;i < b.length;i++){
			sum += parseFloat(b.get(i).innerHTML);
		}
		$(".operation-list-special .add-money").text(sum);
	}
function addGoods(my){
		//商品名
		var name = $(my).find(".name").text();
		//价格
		var price = $(my).find(".good-price").text();
		//获取ID
		var goodId = $(my).find(".name").attr("id");
		var text = ' <div class="shop-item clearfix" id="choose-'+goodId+'">'+
					'<span class="shop-name fl">'+ name +'</span>'+
					'<div class="operate-shop clearfix fl">1</div>'+
					'<a href="javascript:;" class="remove fr" onclick="removeGood(\'choose-'+goodId+'\')" >'+
					'	<i class="iconfont">&#xe65c;</i>'+
					'</a>'+
					'<span class="addNub fr">'+ price +'</span>'+
					'</div>';
		//在添加之前 判断列表中是否已有该商品
		//如果有直接加数量 ， 否则才追加
		var isHave = false;
		$(".shop-item").each(function(){
			//列表已有id
			var haveId = $(this).attr("id");
			console.log(haveId);
			//alert("good-"+goodId);
			if(haveId == ("choose-"+goodId) ) {
				//说明列表中已有该商品
				isHave = true;
			}

		});
		if(isHave){
			//那么我不能追加商品
			//要将数量加1 小计增加 总计增加
			var oldNumber = $("#choose-"+goodId).find(".operate-shop").text();
			oldNumber ++;
			$("#choose-"+goodId).find(".operate-shop").text(oldNumber);
			//数量增加
			var newPrice = price * parseInt(oldNumber);
			$("#choose-"+goodId).find(".addNub").text(newPrice);
			//小计增加
		}else{
			$(".shop-list").append(text);
		}
		total();
		//计算总计
}
function removeGood(id){
	//删除该行
	if(confirm('确认删除此已选商品?')){
		$("#"+id).remove();
		total();
	}
}

