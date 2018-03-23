/**
 * 
 */
$(document).ready(function(){
	//先加载下二级部门和职位
	initDept1();
	initDept2();
	initJob();
	$("#dept1").change(function(){
		initDept2();
		initJob();
	});
	$("#dept2").change(function(){
		
		initJob();
	});
});
//加载一级部门
	function initDept1(){
		htmlobj=$.ajax({url:"../deptCon/selLevel1.hzyc",async:false});
		var value = htmlobj.responseText;
		var dept1 = eval(value);
		var select = document.getElementById("dept1");
	
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
		htmlobj=$.ajax({url:"../deptCon/selLevel2.hzyc?code="+select1,async:false});
		var value = htmlobj.responseText;
		var dept2 = eval(value);
		var select = document.getElementById("dept2");
		//清除子节点
		$("#dept2").empty();
		//清空职位表
		$("#job").empty();
		
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
		htmlobj=$.ajax({url:"../deptCon/selJob.hzyc?code="+select2,async:false});
		var value = htmlobj.responseText;
		var job = eval(value);
		var select = document.getElementById("job");
		//清除子节点
		$("#job").empty();
		
		for(var i = 0 ; i < job.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = job[i].jobName ;
			newOption.value = job[i].jobCode;
			select.appendChild(newOption);
		}
		
		
	}