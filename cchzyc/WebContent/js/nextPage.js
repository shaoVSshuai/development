/**
 * ���÷�ҳ
 */

function pageChange(page,flag){
 		var n = document.getElementById("nowPage").value;
 		var p = document.getElementById("pageSize").value;
 		var m = document.getElementById("maxPage").value;
 		//�������ʼ��
 		var sPage ;
 		//���ǵ�ǰҳ
 		var nPage;
 		
 		switch(flag){
	 		case 'top':
	 			nPage = parseInt(page)-1;
	 			sPage = (parseInt(nPage)-1) * p;
	 			break;
	 		case 'bottom':
	 			nPage = parseInt(page)+1;
	 			sPage = ( parseInt(nPage)-1) * p;
		 		break;
	 		case 'start':
	 			nPage = 1;
	 			sPage = (parseInt(page)-1) * p;
		 		break;
	 		case 'end':
	 			nPage = page;
	 			sPage = (parseInt(page)-1) * p;
		 		break;
 		}
 		document.getElementById("startPage").value = sPage;
 		document.getElementById("nowPage").value = nPage;
 		//�ύ
 		document.getElementById("form").submit();
 	}
 	