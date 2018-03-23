/**
 * 
 */
$(document).ready(function(){
	//�ȼ����¶������ź�ְλ
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
//����һ������
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
	//���ض�������
	function initDept2(select){
		//���ַ�ʽ�������������ȡ
		var select1 = document.getElementById("dept1").value;
		htmlobj=$.ajax({url:"../deptCon/selLevel2.hzyc?code="+select1,async:false});
		var value = htmlobj.responseText;
		var dept2 = eval(value);
		var select = document.getElementById("dept2");
		//����ӽڵ�
		$("#dept2").empty();
		//���ְλ��
		$("#job").empty();
		
		for(var i = 0 ; i < dept2.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = dept2[i].deptName ;
			newOption.value = dept2[i].deptCode;
			select.appendChild(newOption);
		}
	}
	//���ض������ŵ�ְλ
	function initJob(){
		var select2 = document.getElementById("dept2").value;
		htmlobj=$.ajax({url:"../deptCon/selJob.hzyc?code="+select2,async:false});
		var value = htmlobj.responseText;
		var job = eval(value);
		var select = document.getElementById("job");
		//����ӽڵ�
		$("#job").empty();
		
		for(var i = 0 ; i < job.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = job[i].jobName ;
			newOption.value = job[i].jobCode;
			select.appendChild(newOption);
		}
		
		
	}