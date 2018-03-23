<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 
    <title>SVG中国地图导航</title>
	<!-- 地图引用 -->
    <script type="text/javascript" src="../js/lib/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../js/lib/raphael-min.js"></script>
    <script type="text/javascript" src="../js/res/chinaMapConfig.js"></script>
    <script type="text/javascript" src="../js/map-min.js"></script>
  
	<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all">
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
    
    <style>

        *{margin:0;padding:0;border: none;}

        body { color: #333; text-align: center; font: 12px "微软雅黑";}

        .mapTipText{width: 280px;height: 110px;background-color: #ffffff;}

        .mapTipText .mapTipImg{height: 66px; width: 66px; float: left;border: 2px solid #ffffff; border-radius: 50%;overflow: hidden;margin: -12px 5px 0 -12px;}

        .mapTipText .mapTipImg img{width: 100%;height: 100%;}

        .mapTipText .mapTipList{float: left;margin-left: 4px;}

        .mapTipText .mapTipList h2{text-align: left;}

        .mapTipText .mapTipList h2 a{font-size: 24px; color: #262626;text-decoration:none;}

        .mapTipText .mapTipList h2 a:hover{ color: #0085d2;}

        .mapTipText .mapTipList h2 a span{font-size: 16px;margin-left: 3px;}

        .mapTipText .mapTipList ul{ width: 203px;padding-right: 10px;}

        .mapTipText .mapTipList ul li{list-style: none;float: left;padding: 7px 3px 0 3px;}

        .mapTipText .mapTipList ul li a{color: #262626;text-decoration:none;}

        .mapTipText .mapTipList ul li a:hover{background-color:#2ebcfe;color:#ffffff;}


		.text{
		
			font-weight:bold;
			font-size:17px;
		
		}
    </style>

    <script type="text/javascript">

        $(function(){
        	
	        $('#ChinaMap').SVGMap({
	        	
	        	showTips:false,
	        	
                mapWidth: 585,
                
                mapHeight: 491,
                
                clickCallback: function(stateData, obj){
                    $('#ClickCallback').html('点击了：'+obj.name);
                }
            });
	       
        });
        
      
        
        //页面元素加载完毕异步请求数据，加载转圈图层
        $(document).ready(function (){
        	layui.use('layer', function(){ //独立版的layer无需执行这一句
        		  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句	
        		 	 	var index ;
        		  		$.ajax({
        		  			
			        		url:'../stuCon/selStudentDistribution.hzyc',
			        		type:'POST',
			        		async:true,
			        		dateType:'JSON',
			        		date: province = '',
			        		beforeSend:function(){
			        			index = layer.load(0, {
			        				shade: false
			        			});
			        		},
			        		success:function(msg){
			        			layer.close(index);
			        			var info = JSON.parse(msg);
			        			var ul =document.getElementById('stuName');
			        			for(var i = 0 ; i < info.length;i++){
			        				var li = document.createElement('li');
			        				li.innerHTML="<a href='"+info[i].code+"'>"+info[i].name+"</a>";
			        				ul.appendChild(li);
			        			}
			        		}
			        	});
        		  
        	});  
        	  $("path").click(function(){
              	alert($(this).next().text());
              });
        	  
        	  $("tspan").click(function(){
                	alert($(this).text());
              });
        });
        
        

    </script>
</head>
<body>
<span id="HoverCallback">12312312</span>
<div class="wrap">

   
	
    <div class="itemCon" style="float: left">

        <div id="ChinaMap" style="margin:25px;"></div>

        <div id="stateTip" style="position: absolute;left: 100%;text-align: left;display:inline;"></div>

    </div>
    
    
    <div class="buttonArea" style="margin-left:10px;float:left;width:495px;height:540px;background:#ccc">

        	<div class="someButton" style="margin:25px 10px 25px 10px;width:470px;height:120px;background:#fff;border-radius:5px;" >
        		
        		<input type="button" name="birthday" value="条件查询区"  class="layui-btn" onclick="getBirthday()"/> 
        		
        		
        		
        		
        	</div>
        	
        	<div class="somePicture" style="margin:25px 10px 25px 10px;width:470px;height:350px;background:#fff;border-radius:5px;" >
        		
        		<input type="button" name="birthday" value="统计图区"  class="layui-btn" onclick="getBirthday()"/> 
        		
        	</div>
        	
        	

    </div>

    <div id="mapTipContent" style="width: 900px; margin: 0 auto;display: none">
    
	
		<!-- 每一个省份的详情  mapTipText0 有对应的编号 0是黑龙江 1是吉林 2是辽宁 … chinaMapConfig.js中可查-->
		
        <div class="mapTipText mapTipText0">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        
        <div class="mapTipText mapTipText1">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText2">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        
        <div class="mapTipText mapTipText3">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText4">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText5">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText6">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText7">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText8">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText9">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText10">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText11">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText12">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText13">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText14">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText15">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText16">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText17">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText18">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText19">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText20">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText21">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText22">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText23">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText24">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText25">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText26">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText27">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText28">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText29">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText30">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText31">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText32">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>
        <div class="mapTipText mapTipText33">

            <div class="mapTipImg"><img src="../images/heilongjiang.jpg" alt=""/></div>

            <div class="mapTipList">
 
                <h2><a href="">黑龙江</a><span class="text">人数:</span><span id="sNum">35</span><span class="text">占比:</span><span id="sPro">3%</span></h2>

                <ul id="stuName">

                </ul>

            </div>

        </div>

    </div>

</div>
	
</body>
</html>