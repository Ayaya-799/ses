function isAlpha(theStr,alpha) {
       if (isEmpty(theStr)) return(false);
       else if (theStr.indexOf(alpha) == -1) return(false);
       return(true);
}
function check_protocl_sub(theStr){
	checkLength(theStr);
	if(theStr.value.length!=21){
		alert('��Э���ű���Ϊ21λ');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(0,1),'I')){
		alert('��Э���ŵ�����ĸ����ΪI');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(1,2),'M')&&!isAlpha(theStr.value.substring(1,2),'R')&&!isAlpha(theStr.value.substring(1,2),'T')&&!isAlpha(theStr.value.substring(1,2),'S')&&!isAlpha(theStr.value.substring(1,2),'Z')){
		alert('��Э���ŵĵڶ�����ĸ����ΪM,R,S,Z��T');/*zyh����S,Z�ַ� 20030425*/
		//theStr.focus();
		return false;
	}
	if(!isAlpha('ABCDEFGHIJKLMNOPQRSTUVWXYZ',theStr.value.substring(20,21))){
		alert('��Э���ŵ�ĩλ����Ϊ��ĸ');	
		//theStr.focus();
		return false;
	}
	if(!isInt(theStr.value.substring(2,20))){
		alert('��Э���ŵ�3-20λ����Ϊ����');
		//theStr.focus();
		return false;
	}
	return(true);
}
function checkje(theStr){
	checkLength(theStr);		
	if((ret=isReal(theStr.value,16,2))!="ok"){
		alert('���Ϊ��������16λ���֣�С������2λ����'+ret);
		//theStr.focus();
		return false;
	}
	return true;		
}
function checksz(theStr){
	checkLength(theStr);		
	if(!isInt(theStr.value)){
		alert('�������Ϊ����');
		//theStr.focus();
		return false;
	}
	return true;		
}
function check_main_protocl(theStr){
	checkLength(theStr);
	if(theStr.value.length!=10){
		alert('��Э���ű���Ϊ10λ');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(0,1),'G')){
		alert('��Э���ŵ�����ĸ����ΪG');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(1,2),'M')&&!isAlpha(theStr.value.substring(1,2),'R')&&!isAlpha(theStr.value.substring(1,2),'T')&&!isAlpha(theStr.value.substring(1,2),'S')&&!isAlpha(theStr.value.substring(1,2),'Z')){
		alert('��Э���ŵĵڶ�����ĸ����ΪM,R,S,Z��T');/*zyh����S,Z�ַ� 20030425*/
		//theStr.focus();
		return false;
	}
	if(!isInt(theStr.value.substring(2,10))){
		alert('��Э���ŵ�3-10λ����Ϊ����');
		//theStr.focus();
		return false;
	}
	return(true);
}
function check_protocl_spe(theStr){
	checkLength(theStr);
	if(theStr.value.length!=21){
		alert('ר��Э���ű���Ϊ21λ');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(0,1),'S')){
		alert('ר��Э���ŵ�����ĸ����ΪS');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(1,2),'M')&&!isAlpha(theStr.value.substring(1,2),'R')&&!isAlpha(theStr.value.substring(1,2),'T')&&!isAlpha(theStr.value.substring(1,2),'S')&&!isAlpha(theStr.value.substring(1,2),'Z')){
		alert('ר��Э���ŵĵڶ�����ĸ����ΪM,R,S,Z��T');    /*zyh����S,Z�ַ� 20030425*/
		//theStr.focus();
		return false;
	}
	if(!isAlpha('ABCDEFGHIJKLMNOPQRSTUVWXYZ',theStr.value.substring(20,21))){      /*zyh�޸�20030425*/
		alert('ר��Э���ŵ�ĩλ����Ϊ��ĸ');
		//theStr.focus();
		return false;
	}
	if(!isInt(theStr.value.substring(2,20))){
		alert('ר��Э���ŵ�3-20λ����Ϊ����');
		//theStr.focus();
		return false;
	}
	return(true);
}
function check_protocl_chi(theStr){
	checkLength(theStr);
	if(theStr.value.length!=21){
		alert('����Э���ű���Ϊ21λ');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(0,1),'I')){
		alert('����Э���ŵ�����ĸ����ΪI');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(1,2),'M')&&!isAlpha(theStr.value.substring(1,2),'R')&&!isAlpha(theStr.value.substring(1,2),'T')&&!isAlpha(theStr.value.substring(1,2),'S')&&!isAlpha(theStr.value.substring(1,2),'Z')){
		alert('����Э���ŵĵڶ�λ����ΪM,R,S,Z��T');/*zyh����S,Z�ַ� 20030425*/
		//theStr.focus();
		return false;
	}
	if(!isAlpha('ABCDEFGHIJKLMNOPQRSTUVWXYZ',theStr.value.substring(20,21))){
		alert('����Э���ŵ�ĩλ����Ϊ��ĸ');
		//theStr.focus();
		return false;
	}
	if(!isInt(theStr.value.substring(2,20))){
		alert('����Э���ŵ�3-20λ����Ϊ����');
		//theStr.focus();
		return false;
	}
	return(true);
}
function check_protocl_all(theStr){
	checkLength(theStr);
	if(theStr.value.length!=21){
		alert('����Э��/ר��Э���ű���Ϊ21λ');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(0,1),'I')&&!isAlpha(theStr.value.substring(0,1),'S')){
		alert('����Э��/ר��Э���ŵ���λ����ΪI��S');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(1,2),'M')&&!isAlpha(theStr.value.substring(1,2),'R')&&!isAlpha(theStr.value.substring(1,2),'T')&&!isAlpha(theStr.value.substring(1,2),'S')&&!isAlpha(theStr.value.substring(1,2),'Z')){
		alert('����Э��/ר��Э���ŵĵڶ�λ����ΪM,R,S,Z��T');/*zyh����S,Z�ַ� 20030425*/
		//theStr.focus();
		return false;
	}
	if(!isAlpha('ABCDEFGHIJKLMNOPQRSTUVWXYZ',theStr.value.substring(20,21))){
		alert('����Э��/ר��Э���ŵ�ĩλ����Ϊ��ĸ');
		//theStr.focus();
		return false;
	}
	if(!isInt(theStr.value.substring(2,20))){
		alert('����Э��/ר��Э���ŵ�3-20λ����Ϊ����');
		//theStr.focus();
		return false;
	}
	return(true);
}
