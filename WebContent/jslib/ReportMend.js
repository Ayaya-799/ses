//modified by yhua on 2004/06/16
function checkDataBalance(){
	if(mainForm.customerType.value=="01"){//����
	   if(mainForm.TA209990101.value*1 != mainForm.TA209990102.value*1+mainForm.TA209990103.value*1+mainForm.TA209990104.value*1){
			alert("��������Ӧ���ڴμ�����+���ɴ���+��ʧ����");
			mainForm.TA209990101.focus();
		   return false;
	   }
	   if(mainForm.TA209990101.value*1 < mainForm.TA209990102.value*1+mainForm.TA209990103.value*1+mainForm.TA209990104.value*1){
		   alert("�������ӦС�����з���֮�ͣ�");
		   mainForm.TA209990101.focus();
		   return false;
	   }
	   if(mainForm.TA209990107.value*1 < mainForm.TA209990108.value*1+mainForm.TA209990109.value*1){
		   alert("������й�ծ������ծȯ��ӦС�����з���֮�ͣ�");
		   mainForm.TA209990107.focus();
		   return false;
	   }
	}else if(mainForm.customerType.value=="06"){//����˾
	   if(mainForm.TA209990106.value*1 != mainForm.TA209990107.value*1+mainForm.TA209990108.value*1+mainForm.TA209990109.value*1){
		   alert("��������Ӧ�������з���֮�ͣ�");
		   mainForm.TA209990106.focus();
		   return false;
	   }
/*
modified by yhua on 2004/06/16
	}else if(mainForm.customerType.value=="05"){//֤ȯ
	   if(mainForm.TA209990105.value*1<mainForm.TA209990106.value*1){
	  	   alert("���д�ӦС�ڿͻ��ʽ��ţ�");
		   mainForm.TA209990105.focus();
		   return false;
	   }	
*/
	}   
	return true;
}	
