	//��ʼ������������������
	
	var vTotalNum;
	var vBeginPos;
	var vShowNum;
	var vQryNum;
	var vQryBeginPos;
	var vPageFlag = 0;		//0, from session; 1, from db
	var vTotalPages;
	var vPagesPerQry;
	var vCurPageId;
	
	function initParams(qryBeginPos, beginPos, totalNum, qryNum, showNum) {
		//alert("into initParams");
		vTotalNum = totalNum;
		vBeginPos = beginPos;
		vShowNum = showNum;
		vQryNum = qryNum;
		vQryBeginPos = qryBeginPos;
		vCurPageId = (vQryBeginPos*1 - 1 + vBeginPos*1 - 1) % vShowNum*1;
		
		if (vCurPageId == 0) {
			vCurPageId = (vQryBeginPos*1 - 1 + vBeginPos*1 - 1) / vShowNum*1 + 1;
		} else {
			vCurPageId = (vQryBeginPos*1 - 1 + vBeginPos*1 - 1 - vCurPageId*1) / vShowNum*1 + 1;
		}
		vPagesPerQry = vQryNum / vShowNum*1;
		if (vTotalNum % vShowNum*1 == 0) {
			vTotalPages = vTotalNum*1 / vShowNum*1;
		} else {
			vTotalPages = (vTotalNum*1 - vTotalNum%vShowNum*1) / vShowNum*1 +1;
		}
		
		//alert("vTotalNum="+vTotalNum+"\nvBeginPos="+vBeginPos+"\nvTotalPages="+vTotalPages+"\nvQryNum="+vQryNum);
	}
	
	//������һҳ
	function clickPrePage() {
		vBeginPos = vBeginPos*1 - vShowNum*1;
		vCurPageId = vCurPageId*1 - 1;
		if (vBeginPos <= 0) {
			vPageFlag = 1;
			vQryBeginPos = vQryBeginPos*1 - vQryNum*1;	//ǰһ�εĲ�ѯ��ʼλ��
			vBeginPos = vQryNum*1 - vShowNum*1 + 1;
		}
		
		submitForm();
	}

	//������һҳ
	function clickNextPage() {
		vBeginPos = vBeginPos*1 + vShowNum*1;
		vCurPageId = vCurPageId*1 + 1;
		if (vBeginPos > vQryNum) {
			vPageFlag = 1;
			vQryBeginPos = vQryBeginPos*1 + vQryNum*1;
			vBeginPos = 1;
		}
		
		submitForm();
	}
	
	//�����һҳ
	function clickFirstPage() {
		vBeginPos = 1;
		vCurPageId = 1;
		//if (vQryBeginPos > vQryNum) {
			vPageFlag = 1;
			vQryBeginPos = 1;
		//}
		
		submitForm();
	}
	
	function clickLastPage() {
		vCurPageId  = vTotalPages;
		var vtLastQryNum = vTotalNum % vQryNum; 
		if (vtLastQryNum == 0) {
			vtLastQryNum = vQryNum;
		}
		if (vTotalNum*1 - vtLastQryNum*1 + 1 != vQryBeginPos) {
			vPageFlag = 1;
			vQryBeginPos = vTotalNum*1 - vtLastQryNum*1 + 1;
		}
		var vtLastPageNum = vtLastQryNum*1 % vShowNum;
		if (vtLastPageNum == 0) {
			vtLastPageNum = vShowNum;
		}
		vBeginPos = vtLastQryNum*1 - vtLastPageNum*1 + 1;
		
		submitForm();
	}
	
	function goNthPage(pageid) {
		if (!checkPageId(pageid)) {
			return;
		}
		
		if (vCurPageId == pageid) {
			alert("���뵱ǰҳ�룡");
			return;
		}
		
		if (1 == pageid) {
			clickFirstPage();
		} else if (vTotalPages == pageid) {
			clickLastPage();
		} else {
			vCurPageId = pageid;
			var vtQryLeftPages = pageid % vPagesPerQry;	//ѡ���ҳ�������ڵĲ�ѯ��ҳ��
			var vtQryTimes, vtQryBeginPos;
			if (vtQryLeftPages == 0) {	//ĳ�β�ѯ�����һҳ
				vBeginPos = vQryNum*1 - vShowNum*1 + 1;
				vtQryTimes = pageid / vPagesPerQry;
			} else {	//ĳ�β�ѯ֮��ĵ�����ҳ
				vBeginPos = vShowNum * (vtQryLeftPages-1) + 1;
				vtQryTimes = (pageid - pageid%vPagesPerQry) / vPagesPerQry + 1;
			}
			vtQryBeginPos = vQryNum*1 * (vtQryTimes-1) + 1;
			if (vtQryBeginPos != vQryBeginPos) {
				vPageFlag = 1;
				vQryBeginPos = vtQryBeginPos;
			}
			
			submitForm();
		}
	} 
	
	function submitForm() {
		//alert("vBeginPos="+vBeginPos+"\nvQryBeginPos="+vQryBeginPos+"\nvPageFlag="+vPageFlag+"\nvTotalPages="+vTotalPages+"\nvCurPageId="+vCurPageId);
		document.thisForm.beginPos.value =vBeginPos;
		document.thisForm.qryBeginPos.value = vQryBeginPos;
		document.thisForm.pageFlag.value = vPageFlag;
		document.thisForm.submit();
	}
	
	function checkPageId(pageid) {
		//alert("input pageid is " + pageid);
		if (!isInteger(pageid) || (pageid<1) || (pageid>vTotalPages)) {
			alert("������1~" + vTotalPages +"֮���������");
			return false;
		} else {
			return true;
		} 
	}
	
	function hasFirstPage() {
		if (vCurPageId > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	function hasLastPage() {
		if (vCurPageId != vTotalPages) {
			return true;
		} else {
			return false;
		}
	}

	function showHref() {
		if (vTotalNum == 0) {
			document.all.controlTable.style.display = 'none';
		}
		var iHtml = "���� "+ vTotalPages +" ҳ/�� "+ vCurPageId +" ҳ��"
		document.all.lbInfo.innerHTML = iHtml;
		if (!hasFirstPage()) {
			document.all.lbFirstPage.style.display = 'none';
		}
		if (!hasLastPage()) {
			document.all.lbLastPage.style.display = 'none';
		}		
	}
	

