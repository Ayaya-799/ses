/*��ӡ��ҳ*/
function printFirstPage(assess_year,assess_month,assess_kind,client_name,approve_bank,approve_date){
    icbcprint.printStartPage();

   



    var sText = "";
    sText=assess_year +"��"+ assess_month+"�����õȼ���������";
    if (assess_month=="00"){
    sText = assess_year + "������õȼ���������";}
    
    icbcprint.printText(sText, 150, 600, 1950, 700, false, "center", "����", 240, "010");

	if (assess_month=="00"){
    sText = assess_kind;
    icbcprint.printText(sText, 150, 800, 1950, 900, false, "center", "����", 180, "010");
			}
    icbcprint.printBeginTable(470, 1850, 1400, 150, 0);

    sText = "�ͻ����ƣ�";
    icbcprint.printText(sText, 0, 0, 340, 150, false, "left", "����", 180, "000");

    sText = client_name;
    icbcprint.printText(sText, 340, 0, 1400, 150, false, "left", "����", 180, "110");

    icbcprint.printEndTable(0);

    icbcprint.printBeginTable(470, 2000, 1400, 300, 0);

    sText = "������λ��";
    	if (assess_month=="00"){
    sText = "����λ��";}
    icbcprint.printText(sText, 0, 0, 340, 150, false, "left", "����", 180, "000");

    sText = approve_bank;
    icbcprint.printText(sText, 340, 0, 1400, 150, false, "left", "����", 180, "110");
	
    sText = "�������ڣ�";
    	if (assess_month=="00"){
    sText = "����ʱ�䣺";}
    icbcprint.printText(sText, 0, 150, 340, 300, false, "left", "����", 180, "000");
    
 var year = approve_date.substring(0,4);
    var month = approve_date.substring(4,6);
    var day = approve_date.substring(6,8);
    
    sText = year+"��"+month+"��"+day+"��";
    icbcprint.printText(sText, 340, 150, 1400, 300, false, "left", "����", 180, "110");

    icbcprint.printEndTable(0);

    icbcprint.printEndPage();
}

/*��ӡ�ڶ�ҳ���ͻ��ſ�*/
/*function printCustInfo(cust_infoArry,stock_infoArry,form){*/
  function printCustInfo(cust_infoArry,stock_infoArry,trade_infoArry){	
    icbcprint.printStartPage();

    var sText = "һ���ͻ��������";
    icbcprint.printText(sText, 150, 300, 1950, 360, false, "left", "����", 140, "000");

    sText = "1���ͻ��ſ�";
    icbcprint.printText(sText, 150, 370, 1950, 430, false, "left", "����", 120, "010");

    icbcprint.printBeginTable(218, 430, 1790, 975,0);

    sText = "�ͻ�����:";
    icbcprint.printText(sText, 0, 0, 260, 65, false, "left", "����", 120, "000");

    sText = cust_infoArry[0];
    icbcprint.printText(sText, 260, 0, 1790, 65, false, "left", "����", 120, "000");

    sText = "�ͻ���ַ��";
    icbcprint.printText(sText, 0, 65, 260, 130, false, "left", "����", 120, "000");

    sText = cust_infoArry[8];
               var flag = cust_infoArry[8];
   
 	if (flag=="null"){sText="";}
    icbcprint.printText(sText, 260, 65, 1790, 130, false, "left", "����", 120, "000");

    sText = "�ͻ������ˣ�";
    icbcprint.printText(sText, 0, 130, 310, 195, false, "left", "����", 120, "000");
    
    sText = cust_infoArry[1];
   icbcprint.printText(sText, 310, 130, 930, 195, false, "left", "����", 120, "000");
   
    sText = "�������ڣ�";
    icbcprint.printText(sText, 930, 130, 1190, 195, false, "left", "����", 120, "000");
   
    var year = cust_infoArry[2].substring(0,4);
    var month = cust_infoArry[2].substring(4,6);
    var day = cust_infoArry[2].substring(6,8);
    sText = year+"��"+month+"��"+day+"��";
    
               var flag = cust_infoArry[2];
   
 	if (flag=="null"){sText="";}
    icbcprint.printText(sText, 1190, 130, 1790, 195, false, "left", "����", 120, "000");
    
    sText = "�����Ŵ���ϵ��ʱ�䣺";
    icbcprint.printText(sText, 0, 195, 510, 260, false, "left", "����", 120, "000");
 
   var year = cust_infoArry[23].substring(0,4);
    var month = cust_infoArry[23].substring(4,6);
    var day = cust_infoArry[23].substring(6,8);
    sText = year+"��"+month+"��"+day+"��";
    
    		var flag = cust_infoArry[23];
   
 		if (flag=="null"){sText="";}
   icbcprint.printText(sText, 510, 195, 930, 260, false, "left", "����", 120, "000");
   
   sText = "ע���ʱ���";
    icbcprint.printText(sText, 930, 195, 1190, 260, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[9]+"��Ԫ";
                var flag = cust_infoArry[9];
   
 	if (flag=="null"){sText="";}
   icbcprint.printText(sText, 1190, 195, 1790, 260, false, "left", "����", 120, "000");
   
   sText = "������õȼ���";
    icbcprint.printText(sText, 0, 260, 360, 325, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[19];
   icbcprint.printText(sText, 360, 260, 930, 325, false, "left", "����", 120, "000");
   
   sText = "�ͻ����ͣ�";
    icbcprint.printText(sText, 930, 260, 1190, 325, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[32];
   icbcprint.printText(sText, 1190, 260, 1790, 325, false, "left", "����", 120, "000");
   
    sText = "�������ʣ�";
    icbcprint.printText(sText, 0, 325, 260, 390, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[3];
   icbcprint.printText(sText, 260, 325, 930, 390, false, "left", "����", 120, "000");
   
   sText = "������ҵ��";
    icbcprint.printText(sText, 930, 325, 1190, 390, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[4];
   icbcprint.printText(sText, 1190, 325, 1790, 390, false, "left", "����", 120, "000");

   sText = "ְ��������";
    icbcprint.printText(sText, 0, 390, 260, 455, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[24];
                var flag = cust_infoArry[24];
   
 	if (flag=="null"){sText="";}
   icbcprint.printText(sText, 260, 390, 930, 455, false, "left", "����", 120, "000");
   
   sText = "������Ա������";
    icbcprint.printText(sText, 930, 390, 1340, 455, false, "left", "����", 120, "000");
 
   var flag = cust_infoArry[25];
   sText = cust_infoArry[25];
 	if (flag=="null"){sText="";}
 	
   
   icbcprint.printText(sText, 1340, 390, 1790, 455, false, "left", "����", 120, "000");
   
     sText = "�����ල�ִ��룺";
    icbcprint.printText(sText, 0, 455, 410, 520, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[27];
                var flag = cust_infoArry[27];
   
 	if (flag=="null"){sText="";}
   icbcprint.printText(sText, 410, 455, 930, 520, false, "left", "����", 120, "000");
   
   sText = "�Ƿ����й�˾��";
    icbcprint.printText(sText, 930, 455, 1340, 520, false, "left", "����", 120, "000");
 
   var flag = cust_infoArry[20];
    if (flag=="0"){sText="����";}
    if (flag=="1"){sText="��";}
    if (flag==""){sText="";}
    icbcprint.printText(sText, 1340, 455, 1790, 520, false, "left", "����", 120, "000");
   
  

   sText = "������ϵ��";
    icbcprint.printText(sText, 0, 520, 260, 585, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[5];
   icbcprint.printText(sText, 260, 520, 930, 585, false, "left", "����", 120, "000");
   
   sText = "�����ʸ�";
    icbcprint.printText(sText, 930, 520, 1190, 585, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[10];
   icbcprint.printText(sText, 1190, 520, 1790, 585, false, "left", "����", 120, "000");


  sText = "����������ʽ��";
    icbcprint.printText(sText, 0, 585, 360, 650, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[26];
                var flag = cust_infoArry[26];
   
 	if (flag=="null"){sText="";}
   icbcprint.printText(sText, 360, 585, 930, 650, false, "left", "����", 120, "000");
   
   sText = "�Ƿ��ſͻ���";
    icbcprint.printText(sText, 930, 585, 1340, 650, false, "left", "����", 120, "000");
 
    var flag = cust_infoArry[15];
    if (flag=="0"){sText="����";}
    if (flag=="1"){sText="��";}
    if (flag==""){sText="";}
   icbcprint.printText(sText, 1340, 585, 1790, 650, false, "left", "����", 120, "000");
   
     sText = "������ͷ��ҵ�����ƣ�";
    icbcprint.printText(sText, 0, 650, 460, 715, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[7];
                var flag = cust_infoArry[7];
   
 	if (flag=="null"){sText="";}
   icbcprint.printText(sText, 460, 650, 1790, 715, false, "left", "����", 120, "000");
   
   sText = "���������";
    icbcprint.printText(sText, 0, 715, 260, 780, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[6];
                var flag = cust_infoArry[6];
   
 	if (flag=="null"){sText="";}
   icbcprint.printText(sText, 260, 715, 1790, 780, false, "left", "����", 120, "000");
   
  sText = "���д����е����ƣ�";
    icbcprint.printText(sText, 0, 780, 460, 845, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[28];
                var flag = cust_infoArry[28];
   
 	if (flag=="null"){sText="";}
   icbcprint.printText(sText, 460, 780, 1790, 845, false, "left", "����", 120, "000");
   
   sText = "���±���ʱ�䣺";
    icbcprint.printText(sText, 0, 845, 360, 910, false, "left", "����", 120, "000");
 
   var year = cust_infoArry[29].substring(0,4);
    var month = cust_infoArry[29].substring(4,6);
    
    sText = year+"��"+month+"��";
                 var flag = cust_infoArry[29];
   
 	if (flag=="null"){sText="";}
   icbcprint.printText(sText, 360, 845, 1790, 910, false, "left", "����", 120, "000");

 sText = "����ļ����ţ�";
    icbcprint.printText(sText, 0, 910, 360, 975, false, "left", "����", 120, "000");
 
   sText = cust_infoArry[28];
                var flag = cust_infoArry[28];
   
 	if (flag=="null"){sText="";}
   icbcprint.printText(sText, 360, 910, 1790, 975, false, "left", "����", 120, "000");


    icbcprint.printEndTable(0);
    
  /*  �ɶ����      */  
  sText = "�ͻ���Ҫ�ɶ�ʵ��Ͷ�ʶ��Ȩ������";
	    icbcprint.printText(sText, 218, 1150, 1950, 1210, false, "left", "����", 120, "010");

	    var len = stock_infoArry.length;
	    icbcprint.printBeginTable(218, 1210, 1700, 60+60*len,0);
	    
	    sText = "���";
	    icbcprint.printText(sText, 0, 0, 80, 60, true, "center", "����", 100, "000");

	    sText = "��Ҫ�ɶ�����";
	    icbcprint.printText(sText, 80, 0, 630, 60, true, "center", "����", 100, "000");
	    
	    sText = "���õȼ�";
	    icbcprint.printText(sText, 630, 0, 780, 60, true, "center", "����", 100, "000");
	    
	    sText = "ʵ���ʱ�����Ԫ��";
	    icbcprint.printText(sText, 780, 0, 1100, 60, true, "center", "����", 100, "000");

	    sText = "ʵ��Ͷ�ʶ��Ԫ��";
	    icbcprint.printText(sText, 1100, 0, 1500, 60, true, "center", "����", 100, "000");

	    sText = "��Ȩ����";
	    icbcprint.printText(sText, 1500, 0, 1700, 60, true, "center", "����", 100, "000");

	    var cell_height = 60;
	    for (var i=0; i<len; i++) {
			sText =i+1;
			icbcprint.printText(sText, 0, 60+i*cell_height, 80, 120+i*cell_height, true, "center", "����", 100, "000");

			sText = stock_infoArry[i][0];
	           
			icbcprint.printText(sText, 80, 60+i*cell_height, 630, 120+i*cell_height, true, "center", "����", 100, "000");

			sText = stock_infoArry[i][1];
			icbcprint.printText(sText, 630, 60+i*cell_height, 780, 120+i*cell_height, true, "center", "����", 100, "000");
			
			sText = stock_infoArry[i][2];
			icbcprint.printText(sText, 780, 60+i*cell_height, 1100, 120+i*cell_height, true, "center", "����", 100, "000");
			
			sText = stock_infoArry[i][3];
			icbcprint.printText(sText, 1100, 60+i*cell_height, 1500, 120+i*cell_height, true, "center", "����", 100, "000");
			
			sText = stock_infoArry[i][4];
			icbcprint.printText(sText, 1500, 60+i*cell_height, 1700, 120+i*cell_height, true, "center", "����", 100, "000");
	     }

	    icbcprint.printEndTable(0);

   /*   end--�ɶ����     */
   
   
   
    /*  ���׶������      */  
  sText = "�ͻ����׶��󼰽��������";
	    icbcprint.printText(sText, 218, 1350, 1950, 1410, false, "left", "����", 120, "010");

	    var len = stock_infoArry.length;
	    icbcprint.printBeginTable(218, 1410, 1700, 60+60*len,0);
	    
	    sText = "���";
	    icbcprint.printText(sText, 0, 0, 80, 60, true, "center", "����", 100, "000");

	    sText = "���׶�������";
	    icbcprint.printText(sText, 80, 0, 830, 60, true, "center", "����", 100, "000");
	    
	    sText = "���õȼ�";
	    icbcprint.printText(sText, 830, 0, 980, 60, true, "center", "����", 100, "000");
	    
	    sText = "���׽���Ԫ��";
	    icbcprint.printText(sText, 980, 0, 1700, 60, true, "center", "����", 100, "000");

	    

	    

	
	    for (var i=0; i<len; i++) {
			sText =i+1;
			icbcprint.printText(sText, 0, 60+i*60, 80, 120+i*60, true, "center", "����", 100, "000");

			sText = trade_infoArry[i][0];
	           
			icbcprint.printText(sText, 80, 60+i*60, 830, 120+i*60, true, "center", "����", 100, "000");

			sText = trade_infoArry[i][1];
			icbcprint.printText(sText, 830, 60+i*60, 980, 120+i*60, true, "center", "����", 100, "000");
			
			sText = trade_infoArry[i][2];
			icbcprint.printText(sText, 980, 60+i*60, 1700, 120+i*60, true, "center", "����", 100, "000");
			
			
	     }

	    icbcprint.printEndTable(0);

   /*   end--���׶������     */

    sText = "2���ͻ���ʷ���";
    icbcprint.printText(sText, 150, 1420, 1790, 1485, false, "left", "����", 120, "010");

    sText = cust_infoArry[11];
    icbcprint.printText(sText, 218, 1485, 1790, 1600, false, "left", "����", 120, "000");
    
    icbcprint.printEndPage();

}
/*��ӡ�ڶ�ҳ�������������*/

function printFinancing(){
  	icbcprint.printStartPage();
  	
    sText = "���������������";
    icbcprint.printText(sText, 150, 300, 1950, 360, false, "left", "����", 140, "000");
         	
  	sText = "��λ����Ԫ";
  	  icbcprint.printText(sText, 1700, 360, 1990, 420, false, "left", "����", 100, "000");
  	  
       icbcprint.printBeginTable(180,420, 1760, 1320, 0);  	
       
        sText = "�����弶����";
        icbcprint.printText(sText,0 , 0, 220, 420, true, "center", "����", 100, "000");
        
        	sText = "�����������";
        	icbcprint.printText(sText,220 , 0, 670, 60, true, "left", "����", 100, "000");
        	
        	sText = "��ע�������";
        	icbcprint.printText(sText,220 , 60, 670, 120, true, "left", "����", 100, "000");
        	
        	sText = "�μ��������";
        	icbcprint.printText(sText,220 , 120, 670, 180, true, "left", "����", 100, "000");
  	  
         	sText = "���ɴ������";
        	icbcprint.printText(sText,220 , 180, 670, 240, true, "left", "����", 100, "000");
        	
        	sText = "��ʧ�������";
        	icbcprint.printText(sText,220 , 240, 670, 300, true, "left", "����", 100, "000");
        	
        	sText = "����������ϼ�";
        	icbcprint.printText(sText,220 , 300, 670, 360, true, "left", "����", 100, "000");
        	
        	sText = "�����У�����ҵ���γɵ渶";
        	icbcprint.printText(sText,220 , 360, 670, 420, true, "left", "����", 100, "000");
        	
        	for (var i=0;i<20;i++)
        		{
        			sText = "";
        			icbcprint.printText(sText, 670,  0+60*i, 920, 60+60*i, true, "left", "����", 100, "000");
        			
        			sText = "";
        			icbcprint.printText(sText, 1460,  0+60*i, 1720, 60+60*i, true, "left", "����", 100, "000");
        		}
        		
			sText = "";
        			icbcprint.printText(sText, 670,  1200, 920, 1260, true, "left", "����", 100, "000");
        			
        			sText = "";
        			icbcprint.printText(sText, 1080,  1200, 1720, 1260, true, "left", "����", 100, "000");        		
			
        sText = "���";
        icbcprint.printText(sText,920 , 0, 1080, 120, true, "center", "����", 100, "000");			
        
        	sText = "�����ͬҵ����ۼƶ�";
        	icbcprint.printText(sText,1080 , 0, 1460, 60, true, "left", "����", 100, "000");
        	
        	sText = "���������";
        	icbcprint.printText(sText,1080 , 60, 1460, 120, true, "left", "����", 100, "000");
        	
        sText = "�ع�";
        icbcprint.printText(sText,920 , 120, 1080, 420, true, "center", "����", 100, "000");			
        	
	sText = "����Ȼع��ۼƶ�";
        	icbcprint.printText(sText,1080 , 120, 1460, 180, true, "left", "����", 100, "000");
        	
        	sText = "����ع����";
        	icbcprint.printText(sText,1080 , 180, 1460, 240, true, "left", "����", 100, "000");	
        	
        	sText = "����";
        	icbcprint.printText(sText,1080 , 240, 1140, 420, true, "left", "����", 100, "000");	
        		
        		sText = "ծȯ�ع����";
        		icbcprint.printText(sText,1140 , 240, 1460, 300, true, "left", "����", 100, "000");	
        				
  	  	sText = "Ʊ�ݻع����";
        		icbcprint.printText(sText,1140 , 300, 1460, 360, true, "left", "����", 100, "000");	
        		
        		sText = "�Ŵ��ʲ��ع����";
        		icbcprint.printText(sText,1140 , 360, 1460, 420, true, "left", "����", 100, "000");	
        		
         sText = "�����ʻ�͸֧";
        icbcprint.printText(sText,0 , 420, 220, 480, true, "center", "����", 100, "000");			          		
        
        	 sText = "�����ʻ�͸֧���";
        	icbcprint.printText(sText,220 , 420, 670, 480, true, "left", "����", 100, "000");			          		
  	  	
        sText = "���ּ�ת����";
        icbcprint.printText(sText, 0 , 480, 220, 660, true, "center", "����", 100, "000");			          		
        
        	 sText = "��ǰ�������";
        	icbcprint.printText(sText,220 , 480, 670, 540, true, "left", "����", 100, "000");			          		

	sText = "��ǰת�������";
        	icbcprint.printText(sText,220 , 540, 670, 600, true, "left", "����", 100, "000");			          		
        	
        	sText = "������ۼ����ּ�ת�������";
        	icbcprint.printText(sText,220 , 600, 670, 660, true, "left", "����", 90, "000");			          		
        	
         sText = "��Ѻ����";
        icbcprint.printText(sText, 920 , 420, 1080, 660, true, "center", "����", 100, "000");			          		        	
        
        	 sText = "��Ѻ�������";
        	icbcprint.printText(sText,1080 , 420, 1460, 480, true, "left", "����", 100, "000");			          		
        	
        	sText = "����";
        	icbcprint.printText(sText,1080 , 480, 1140, 660, true, "center", "����", 100, "000");			          		
        	
        		sText = "��ծ�����ծȯ��Ѻ";
        		icbcprint.printText(sText,1140 , 480, 1460, 540, true, "left", "����", 100, "000");			          		
        		
        		sText = "��Ʊ��Ѻ����";
        		icbcprint.printText(sText,1140 , 540, 1460, 600, true, "left", "����", 100, "000");			          		
  	  	
  	  	sText = "������ҵծȯ��Ѻ";
        		icbcprint.printText(sText,1140 , 600, 1460, 660, true, "left", "����", 100, "000");			          		
  	  	
             	  	
         sText = "��Ѻ����";
        icbcprint.printText(sText, 0 , 660, 220, 840, true, "center", "����", 100, "000");			          		        	  	  
        
        	sText = "��Ѻ�������";
        	icbcprint.printText(sText, 220 , 660, 670, 720, true, "left", "����", 100, "000");			          		
        	
        	sText = "����";
        	icbcprint.printText(sText,220 , 720, 280, 840, true, "center", "����", 100, "000");			          		
        	
        		sText = "���ڴ������";
        		icbcprint.printText(sText,280 , 720, 670, 780, true, "left", "����", 100, "000");			          		
        		
        		sText = "�г��ڴ������";
        		icbcprint.printText(sText,280 , 780, 670, 840, true, "left", "����", 100, "000");			          		
  	  	
  	  	
           sText = "���ô���";
        icbcprint.printText(sText, 920 , 660, 1080, 840, true, "center", "����", 100, "000");			          		        	
        
        	 sText = "���ô������";
        	icbcprint.printText(sText,1080 , 660, 1460, 720, true, "left", "����", 100, "000");			          		
        	
        	sText = "����";
        	icbcprint.printText(sText,1080 , 720, 1140, 840, true, "center", "����", 100, "000");			          		
        	
        		sText = "���ڴ������";
        		icbcprint.printText(sText,1140 , 720, 1460, 780, true, "left", "����", 100, "000");			          		
        		
        		sText = "�г��ڴ������";
        		icbcprint.printText(sText,1140 , 780, 1460, 840, true, "left", "����", 100, "000");		  	
  	  	
  	
  	             	  	
         sText = "���������ҵ��";
        icbcprint.printText(sText, 0 , 840, 220, 1260, true, "center", "����", 100, "000");			 
  	
  	  sText = "���������ҵ�����";
        	icbcprint.printText(sText, 220 , 840, 670, 900, true, "left", "����", 100, "000");	
        	
        	  sText = "����";
        	icbcprint.printText(sText, 220 , 900, 280, 1200, true, "center", "����", 100, "000");	
        	
        		sText = "Ͷ�������";
        		icbcprint.printText(sText, 280 , 900, 670, 960, true, "left", "����", 100, "000");	
  	
  		sText = "��������";
        		icbcprint.printText(sText, 280 , 960, 670, 1020, true, "left", "����", 100, "000");	
        		
        		sText = "���������";
        		icbcprint.printText(sText, 280 , 1020, 670, 1080, true, "left", "����", 100, "000");	
        		
        		sText = "���������";
        		icbcprint.printText(sText, 280 , 1080, 670, 1140, true, "left", "����", 100, "000");	
        		
        		sText = "���������";
        		icbcprint.printText(sText, 280 , 1140, 670, 1200, true, "left", "����", 100, "000");	
        	
  	  sText = "��ǰ�γ����渶���";
        	icbcprint.printText(sText, 220 , 1200, 670, 1260, true, "left", "����", 100, "000");	
        	
        sText = "����";
        icbcprint.printText(sText, 920 , 840, 1080, 960, true, "center", "����", 100, "000");		
        
        	sText = "����ҵ�����";
        	icbcprint.printText(sText, 1080 , 840, 1460, 900, true, "left", "����", 100, "000");		
  	
  	sText = "��ǰ����ҵ��渶���";
        	icbcprint.printText(sText, 1080 , 900, 1460, 960, true, "left", "����", 100, "000");		
        	
        sText = "�ж�ҵ��";
        icbcprint.printText(sText, 920 , 960, 1080, 1080, true, "center", "����", 100, "000");		
        
        	sText = "��ǰ�ж����";
        	icbcprint.printText(sText, 1080 , 960, 1460, 1020, true, "left", "����", 100, "000");		
  	
  	sText = "��ǰ�渶���";
        	icbcprint.printText(sText, 1080 , 1020, 1460, 1080, true, "left", "����", 100, "000");	
  	  	
         sText = "����ҵ��";
        icbcprint.printText(sText, 920 , 1080, 1080, 1200, true, "center", "����", 100, "000");		
        
        	sText = "��ǰ�������";
        	icbcprint.printText(sText, 1080 , 1080, 1460, 1140, true, "left", "����", 100, "000");		
  	
  	sText = "��ǰ�����渶���";
        	icbcprint.printText(sText, 1080 , 1140, 1460, 1200, true, "left", "����", 100, "000");
  
     sText = "��ע��";
        icbcprint.printText(sText, 920 , 1200, 1080, 1260, true, "center", "����", 100, "000");	
  
  	  	
    icbcprint.printEndTable(0);
    
          icbcprint.printEndPage();	  	

}
/*END  �����������*/

/*��ӡ�ڶ�ҳ������ָ��*/
function printFinanceInfo(ckind,year,financial_nameArry,financial_dataArry2,financial_dataArry1,financial_dataArry0,cust_infoArry){
	
	icbcprint.printStartPage();

     sText = "�����ͻ���Ҫ��������";
     icbcprint.printText(sText, 150, 300, 1950, 360, false, "left", "����", 140, "000");
     
      icbcprint.printBeginTable(180, 360, 1760, 600,0);
     
     sText = "���ڲ��񱨱��Ƿ񾭹���ƣ�";
     icbcprint.printText(sText, 0, 0, 720, 60, false, "left", "����", 120, "000");
     
      var flag = cust_infoArry[12];
    if (flag=="0"){sText="����";}
    if (flag=="1"){sText="��";}
    if (flag==""){sText="";}
     icbcprint.printText(sText, 720, 0, 980, 60, false, "left", "����", 120, "000");
     
     sText = "�羭����ƣ���ƽ��ۣ�";
     icbcprint.printText(sText, 0, 60, 720, 120, false, "left", "����", 120, "000");
     
     sText = cust_infoArry[14];
     icbcprint.printText(sText, 720, 60, 1500, 120, false, "left", "����", 120, "000");
     
     sText = "��������";
     icbcprint.printText(sText, 0, 120, 720, 180, false, "left", "����", 120, "000");
     
       sText = cust_infoArry[30];
        var flag = cust_infoArry[30];
   
 	if (flag=="null"){sText="";}
     icbcprint.printText(sText, 100, 180, 1790, 240, false, "left", "����", 120, "000");
     
     sText = "���ʦ���������ƣ�";
     icbcprint.printText(sText, 0,240 , 720, 180, false, "left", "����", 120, "000");
     
     sText =  cust_infoArry[13];
      var flag = cust_infoArry[13];
   
 	if (flag=="null"){sText="";}
     icbcprint.printText(sText, 720,240 , 1790, 300, false, "left", "����", 120, "000");

     icbcprint.printEndTable(0);

     sText = "�ͻ���������Ҫ����ָ�������";
     icbcprint.printText(sText, 180, 660, 1950, 720, false, "left", "����", 120, "000");

     icbcprint.printBeginTable(180, 720, 1760, 920,1);

     sText = "ָ��";
     icbcprint.printText(sText, 0, 0, 560, 80, true, "center", "����", 100, "000");

     var num = parseInt(year)-3;
     sText = num+"��ĩ";
     icbcprint.printText(sText, 560, 0, 960, 80, true, "center", "����", 100, "000");

     num = num+1;
     sText = num+"��ĩ";
     icbcprint.printText(sText, 960, 0, 1360, 80, true, "center", "����", 100, "000");

     num = num+1;
     sText = num+"��ĩ";
     icbcprint.printText(sText, 1360, 0, 1760, 80, true, "center", "����", 100, "000");

     var cell_height = 60;
     
     
     
     if(ckind=="01")
     {
     	
     	/*data_order �����ǲ���ָ��Ĵ�ӡ˳�� ����ҵ���ƶ���ͬ��˳��			*/
     	var data_order = new Array(0,1,18,4,5,7,8,11,19,13,14,15,16,17);	
     	
     	
     	
     	
     	
     	   for (var i=0; i<14; i++) {
     	   	
     	   	var j=data_order[i];
     	   	
     	   	
		sText = financial_nameArry[j];
		icbcprint.printText(sText, 0, 80+i*cell_height, 560, 140+i*cell_height, true, "left", "����", 100, "000");

		sText = financial_dataArry2[j];
		icbcprint.printText(sText, 560, 80+i*cell_height, 960, 140+i*cell_height, true, "center", "����", 100, "000");

		sText = financial_dataArry1[j];
		icbcprint.printText(sText, 960, 80+i*cell_height, 1360, 140+i*cell_height, true, "center", "����", 100, "000");

                sText = financial_dataArry0[j];
		icbcprint.printText(sText, 1360, 80+i*cell_height, 1760, 140+i*cell_height, true, "center", "����", 100, "000");

	}

     	    	
     	
     }
     
       if(ckind=="04")
     {
     	var data_order = new Array(0,18,1,19,5,6,7,8,9,10,13,14,15,16,17);	
     	
     	
     	   for (var i=0; i<15; i++) {
     	   	
     	   	var j=data_order[i];
     	   	
     	   	
		sText = financial_nameArry[j];
		icbcprint.printText(sText, 0, 80+i*cell_height, 560, 140+i*cell_height, true, "left", "����", 100, "000");

		sText = financial_dataArry2[j];
		icbcprint.printText(sText, 560, 80+i*cell_height, 960, 140+i*cell_height, true, "center", "����", 100, "000");

		sText = financial_dataArry1[j];
		icbcprint.printText(sText, 960, 80+i*cell_height, 1360, 140+i*cell_height, true, "center", "����", 100, "000");

                sText = financial_dataArry0[j];
		icbcprint.printText(sText, 1360, 80+i*cell_height, 1760, 140+i*cell_height, true, "center", "����", 100, "000");

	}

     	    	
     	
     }
     
     
       if(ckind=="05")
     {
     	var data_order = new Array(0,1,2,4,5,6,7,8,9,10,11,13,14,15,16,17);	
     	
     	
     	   for (var i=0; i<16; i++) {
     	   	
     	   	var j=data_order[i];
     	   	
     	   	
		sText = financial_nameArry[j];
		icbcprint.printText(sText, 0, 80+i*cell_height, 560, 140+i*cell_height, true, "left", "����", 100, "000");

		sText = financial_dataArry2[j];
		icbcprint.printText(sText, 560, 80+i*cell_height, 960, 140+i*cell_height, true, "center", "����", 100, "000");

		sText = financial_dataArry1[j];
		icbcprint.printText(sText, 960, 80+i*cell_height, 1360, 140+i*cell_height, true, "center", "����", 100, "000");

                sText = financial_dataArry0[j];
		icbcprint.printText(sText, 1360, 80+i*cell_height, 1760, 140+i*cell_height, true, "center", "����", 100, "000");

	}

     	    	
     	
     }
     
     
       if(ckind=="06")
     {
     	var data_order = new Array(0,18,2,19,4,5,6,7,8,9,10,13,14,15,16,17);	
     	
     	
     	   for (var i=0; i<16; i++) {
     	   	
     	   	var j=data_order[i];
     	   	
     	   	
		sText = financial_nameArry[j];
		icbcprint.printText(sText, 0, 80+i*cell_height, 560, 140+i*cell_height, true, "left", "����", 100, "000");

		sText = financial_dataArry2[j];
		icbcprint.printText(sText, 560, 80+i*cell_height, 960, 140+i*cell_height, true, "center", "����", 100, "000");

		sText = financial_dataArry1[j];
		icbcprint.printText(sText, 960, 80+i*cell_height, 1360, 140+i*cell_height, true, "center", "����", 100, "000");

                sText = financial_dataArry0[j];
		icbcprint.printText(sText, 1360, 80+i*cell_height, 1760, 140+i*cell_height, true, "center", "����", 100, "000");

	}

     	    	
     	
     }
     
     
     
       if(ckind=="07")
     {
     	var data_order = new Array(0,1,2,3,4,5,6,7,9,18,13,14,15,16,17);	
     	
     	
     	   for (var i=0; i<15; i++) {
     	   	
     	   	var j=data_order[i];
     	   	
     	   	
		sText = financial_nameArry[j];
		icbcprint.printText(sText, 0, 80+i*cell_height, 560, 140+i*cell_height, true, "left", "����", 100, "000");

		sText = financial_dataArry2[j];
		icbcprint.printText(sText, 560, 80+i*cell_height, 960, 140+i*cell_height, true, "center", "����", 100, "000");

		sText = financial_dataArry1[j];
		icbcprint.printText(sText, 960, 80+i*cell_height, 1360, 140+i*cell_height, true, "center", "����", 100, "000");

                sText = financial_dataArry0[j];
		icbcprint.printText(sText, 1360, 80+i*cell_height, 1760, 140+i*cell_height, true, "center", "����", 100, "000");

	}

     	    	
     	
     }
     
     if(ckind=="02"||ckind=="03")
     {
     
     for (var i=0; i<financial_nameArry.length; i++) {
		sText = financial_nameArry[i];
		icbcprint.printText(sText, 0, 80+i*cell_height, 560, 140+i*cell_height, true, "left", "����", 100, "000");

		sText = financial_dataArry2[i];
		icbcprint.printText(sText, 560, 80+i*cell_height, 960, 140+i*cell_height, true, "center", "����", 100, "000");

		sText = financial_dataArry1[i];
		icbcprint.printText(sText, 960, 80+i*cell_height, 1360, 140+i*cell_height, true, "center", "����", 100, "000");

                sText = financial_dataArry0[i];
		icbcprint.printText(sText, 1360, 80+i*cell_height, 1760, 140+i*cell_height, true, "center", "����", 100, "000");

	}
	
	}

     icbcprint.printEndTable(10);

     icbcprint.printEndPage();
}

/*     END********** ����ָ��     */


/**����ҳ �ʲ���ծ��************/

	
  	function printzc(zc_dataArry,zc_dataArry2){
  	icbcprint.printStartPage();
  	 sText = "�ʲ���ծ��";     
     icbcprint.printText(sText, 150, 300, 1950, 360, false, "center", "����", 120, "000");
     
     	sText = zc_dataArry2[0].substring(0,4)+"��12��";
     icbcprint.printText(sText, 150, 360, 1950, 420, false, "center", "����", 120, "000");
     
        icbcprint.printBeginTable(180, 420, 1760, 600,0);
     
     	sText="��������:";
     	 icbcprint.printText(sText, 0, 0, 170, 60, false, "left", "����", 100, "000");
     	 
     	 var flag = zc_dataArry2[2];
    if (flag=="77"){sText="�Ǳ���";}
    if (flag=="88"){sText="����";}
    if (flag==""){sText="";}
     	 icbcprint.printText(sText, 170, 0, 380, 60, false, "left", "����", 100, "000");
     	 
     	 
     	 
          sText="�����Ƿ�ϲ�:";
           icbcprint.printText(sText, 380, 0, 640, 60, false, "left", "����", 100, "000");
           
       var flag = zc_dataArry2[1];
    if (flag=="0"){sText="����";}
    if (flag=="1"){sText="��";}
    if (flag==""){sText="";}
     icbcprint.printText(sText, 640, 0, 740, 60, false, "left", "����", 100, "000");
     
     
     
	     sText = "��λ����Ԫ";
  	  icbcprint.printText(sText, 1300, 0, 1560, 60, false, "left", "����", 100, "000");
  	  
  	  icbcprint.printEndTable(0);
     
   var len = zc_dataArry.length;
   
icbcprint.printBeginTable(180, 480, 1760, 60+60*len,0);
     
  	  sText = "��Ŀ";
	    icbcprint.printText(sText, 0, 0, 660, 60, true, "center", "����", 100, "000");

	    sText = "�����";
	    icbcprint.printText(sText, 660, 0, 1100, 60, true, "center", "����", 100, "000");
	    
	    sText = "��ĩ��";
	    icbcprint.printText(sText, 1100, 0, 1500, 60, true, "center", "����", 100, "000");
	    
	
	    
	      for (var i=1; i<len; i++) {
			sText =zc_dataArry[i][0];
			icbcprint.printText(sText, 0, i*60, 660, 60+i*60, true, "left", "����", 100, "000");

			sText = zc_dataArry[i][1];
	           
			icbcprint.printText(sText, 660, i*60, 1100, 60+i*60, true, "right", "����", 100, "000");

			sText = zc_dataArry[i][2];
			icbcprint.printText(sText, 1100, i*60, 1500, 60+i*60, true, "right", "����", 100, "000");
			
			
	     }
  	
  	
  	   icbcprint.printEndTable(0);
  icbcprint.printEndPage();
}  


/**     END �ʲ���ծ��       **/





/**����ҳ �����************/

	
  	function printsy(sy_dataArry,sy_dataArry2){
  	icbcprint.printStartPage();
  	 sText = "�����";     
     icbcprint.printText(sText, 150, 300, 1950, 360, false, "center", "����", 120, "000");
     
     	sText = sy_dataArry2[0].substring(0,4)+"��12��";
     icbcprint.printText(sText, 150, 360, 1950, 420, false, "center", "����", 120, "000");
     
        icbcprint.printBeginTable(180, 420, 1760, 600,0);
     
     	sText="��������:";
     	 icbcprint.printText(sText, 0, 0, 170, 60, false, "left", "����", 100, "000");
     	 
     	 var flag = sy_dataArry2[2];
    if (flag=="77"){sText="�Ǳ���";}
    if (flag=="88"){sText="����";}
    if (flag==""){sText="";}
     	 icbcprint.printText(sText, 170, 0, 380, 60, false, "left", "����", 100, "000");
     	 
     	 
     	 
          sText="�����Ƿ�ϲ�:";
           icbcprint.printText(sText, 380, 0, 640, 60, false, "left", "����", 100, "000");
           
       var flag = sy_dataArry2[1];
    if (flag=="0"){sText="����";}
    if (flag=="1"){sText="��";}
    if (flag==""){sText="";}
     icbcprint.printText(sText, 640, 0, 740, 60, false, "left", "����", 100, "000");
     
     
     
	     sText = "��λ����Ԫ";
  	  icbcprint.printText(sText, 1300, 0, 1560, 60, false, "left", "����", 100, "000");
  	  
  	  icbcprint.printEndTable(0);
     
   var len = sy_dataArry.length;
   
icbcprint.printBeginTable(180, 480, 1760, 60+60*len,0);
     
  	  sText = "��Ŀ";
	    icbcprint.printText(sText, 0, 0, 660, 60, true, "center", "����", 100, "000");

	    sText = "�����";
	    icbcprint.printText(sText, 660, 0, 1100, 60, true, "center", "����", 100, "000");
	    
	    sText = "��ĩ��";
	    icbcprint.printText(sText, 1100, 0, 1500, 60, true, "center", "����", 100, "000");
	    
	
	    
	      for (var i=1; i<len; i++) {
			sText =sy_dataArry[i][0];
			icbcprint.printText(sText, 0, i*60, 660, 60+i*60, true, "left", "����", 100, "000");

			sText = sy_dataArry[i][1];
	           
			icbcprint.printText(sText, 660, i*60, 1100, 60+i*60, true, "right", "����", 100, "000");

			sText = sy_dataArry[i][2];
			icbcprint.printText(sText, 1100, i*60, 1500, 60+i*60, true, "right", "����", 100, "000");
			
			
	     }
  	
  	
  	   icbcprint.printEndTable(0);
  icbcprint.printEndPage();
}  


/**     END �����       **/




/**����ҳ �ֽ�������************/

	
  	function printxj(xj_dataArry,xj_dataArry2){
  	icbcprint.printStartPage();
  	 sText = "�ֽ�������";     
     icbcprint.printText(sText, 150, 300, 1950, 360, false, "center", "����", 120, "000");
     
     	sText = xj_dataArry2[0].substring(0,4)+"��12��";
     icbcprint.printText(sText, 150, 360, 1950, 420, false, "center", "����", 120, "000");
     
        icbcprint.printBeginTable(180, 420, 1760, 600,0);
     
     	sText="��������:";
     	 icbcprint.printText(sText, 0, 0, 170, 60, false, "left", "����", 100, "000");
     	 
     	 var flag = xj_dataArry2[2];
    if (flag=="77"){sText="�Ǳ���";}
    if (flag=="88"){sText="����";}
    if (flag==""){sText="";}
     	 icbcprint.printText(sText, 170, 0, 380, 60, false, "left", "����", 100, "000");
     	 
     	 
     	 
          sText="�����Ƿ�ϲ�:";
           icbcprint.printText(sText, 380, 0, 640, 60, false, "left", "����", 100, "000");
           
       var flag = xj_dataArry2[1];
    if (flag=="0"){sText="����";}
    if (flag=="1"){sText="��";}
    if (flag==""){sText="";}
     icbcprint.printText(sText, 640, 0, 740, 60, false, "left", "����", 100, "000");
     
     
     
	     sText = "��λ����Ԫ";
  	  icbcprint.printText(sText, 1300, 0, 1560, 60, false, "left", "����", 100, "000");
  	  
  	  icbcprint.printEndTable(0);
     
   var len = xj_dataArry.length;
   
icbcprint.printBeginTable(180, 480, 1760, 60+60*len,0);
     
  	  sText = "��Ŀ";
	    icbcprint.printText(sText, 0, 0, 660, 60, true, "center", "����", 100, "000");

	    sText = "�����";
	    icbcprint.printText(sText, 660, 0, 1100, 60, true, "center", "����", 100, "000");
	    
	    sText = "��ĩ��";
	    icbcprint.printText(sText, 1100, 0, 1500, 60, true, "center", "����", 100, "000");
	    
	
	    
	      for (var i=1; i<len; i++) {
			sText =xj_dataArry[i][0];
			icbcprint.printText(sText, 0, i*60, 660, 60+i*60, true, "left", "����", 100, "000");

			sText = xj_dataArry[i][1];
	           
			icbcprint.printText(sText, 660, i*60, 1100, 60+i*60, true, "right", "����", 100, "000");

			sText = xj_dataArry[i][2];
			icbcprint.printText(sText, 1100, i*60, 1500, 60+i*60, true, "right", "����", 100, "000");
			
			
	     }
  	
  	
  	   icbcprint.printEndTable(0);
  icbcprint.printEndPage();
}  


/**     END �ֽ�������       **/

/**����ҳ ���ݲ�¼��************/

	
  	function printsj(sj_dataArry,sj_dataArry2){
  	icbcprint.printStartPage();
  	 sText = "���ݲ�¼��";     
     icbcprint.printText(sText, 150, 300, 1950, 360, false, "center", "����", 120, "000");
     
     	sText = sj_dataArry2[0].substring(0,4)+"��12��";
     icbcprint.printText(sText, 150, 360, 1950, 420, false, "center", "����", 120, "000");
     
        icbcprint.printBeginTable(180, 420, 1760, 600,0);
     
	     sText = "��λ����Ԫ";
  	  icbcprint.printText(sText, 1300, 0, 1560, 60, false, "left", "����", 100, "000");
  	  
  	  icbcprint.printEndTable(0);
     
   var len = sj_dataArry.length;
   
icbcprint.printBeginTable(180, 480, 1760, 60+60*len,0);
     
  	  sText = "��Ŀ";
	    icbcprint.printText(sText, 0, 0, 660, 60, true, "center", "����", 100, "000");

	    sText = "�����";
	    icbcprint.printText(sText, 660, 0, 1100, 60, true, "center", "����", 100, "000");
	    
	    sText = "��ĩ��";
	    icbcprint.printText(sText, 1100, 0, 1500, 60, true, "center", "����", 100, "000");
	    
	
	    
	      for (var i=1; i<len; i++) {
			sText =sj_dataArry[i][0];
			icbcprint.printText(sText, 0, i*60, 660, 60+i*60, true, "left", "����", 100, "000");

			sText = sj_dataArry[i][1];
	           
			icbcprint.printText(sText, 660, i*60, 1100, 60+i*60, true, "right", "����", 100, "000");

			sText = sj_dataArry[i][2];
			icbcprint.printText(sText, 1100, i*60, 1500, 60+i*60, true, "right", "����", 100, "000");
			
			
	     }
  	
  	
  	   icbcprint.printEndTable(0);
  icbcprint.printEndPage();
}  


/**     END ���ݲ�¼��       **/


/***   �����Ʒֱ�***/

function  printscore(ration_score,ration_infoArry,nature_nameArry,nature_score,nature_infoArry){
	   var enptype = ration_infoArry[24];
	   var typeFlag = ration_infoArry[25];
	icbcprint.printStartPage();   
	

	   var sText = "�ġ����������Ʒֱ�";
     icbcprint.printText(sText, 150, 300, 1950, 360, false, "left", "����", 140, "000");
   if (enptype=="01"||enptype=="04"||enptype=="05"||enptype=="06"||enptype=="07") {


	
	
  

     sText = "�������۵÷���ϸ";
     icbcprint.printText(sText, 150, 370, 1950, 460, false, "center", "����", 120, "010");     

    var x=190;
    var y=460;

     icbcprint.printBeginTable(x, y, x+1510, y+720, 1);

     sText = "��������";
     icbcprint.printText(sText, 0, 0, 300, 120, true, "center", "����", 100, "000");
     
      sText = "����ָ��";
     icbcprint.printText(sText, 300, 0, 1050, 60, true, "center", "����", 100, "000");

     sText = "ָ��";
     icbcprint.printText(sText, 300, 60, 850, 120, true, "center", "����", 100, "000");

     sText = "Ȩ����";
     icbcprint.printText(sText, 850, 60, 1050, 120, true, "center", "����", 100, "000");

     sText = "�����÷�";
     icbcprint.printText(sText, 1050, 0, 1250, 120, true, "center", "����", 100, "000");

     sText = "����ϵ��";
     icbcprint.printText(sText, 1250, 0, 1450, 120, true, "center", "����", 100, "000");

     sText = "������÷�";
     icbcprint.printText(sText, 1450, 0, 1700, 120, true, "center", "����", 100, "000");
     
     		
     		
     		
     		 if (enptype=="01"){
     sText = "һ��������";
     icbcprint.printText(sText, 0, 120, 300, 300, true, "center", "����", 100, "000");

     sText = "������ȫ��";
     icbcprint.printText(sText, 0, 300, 300, 480, true, "center", "����", 100, "000");

     sText = "����ӯ����";
     icbcprint.printText(sText, 0, 480, 300, 660, true, "center", "����", 100, "000");

     
     sText = "���������ܵ÷�";
     icbcprint.printText(sText, 0, 660, 300, 720, true, "center", "����", 100, "000");
     
     sText = "��������";
     icbcprint.printText(sText, 300, 120, 850, 180, true, "center", "����", 100, "000");
     
     sText = "�����";
     icbcprint.printText(sText, 300, 180, 850, 240, true, "center", "����", 100, "000");
     
     sText = "�����ع�����";
     icbcprint.printText(sText, 300, 240, 850, 300, true, "center", "����", 100, "000");
     
     sText = "�����������";
     icbcprint.printText(sText, 300, 300, 850, 360, true, "center", "����", 100, "000");
     
     sText = "����׼��������";
     icbcprint.printText(sText, 300, 360, 850, 420, true, "center", "����", 100, "000");
     
     sText = "�ʱ�������";
     icbcprint.printText(sText, 300, 420, 850, 480, true, "center", "����", 100, "000");
     
     sText = "���ʲ�������";
     icbcprint.printText(sText, 300, 480, 850, 540, true, "center", "����", 100, "000");
     
     sText = "��Ϣ������";
     icbcprint.printText(sText, 300, 540, 850, 600, true, "center", "����", 100, "000");
     
     sText = "������������";
     icbcprint.printText(sText, 300, 600, 850, 660, true, "center", "����", 100, "000");
     
    
     
		     	
     		
     			
     			sText =ration_score[1];
     			 icbcprint.printText(sText, 850, 60+60*1, 1050, 120+60*1, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[0];
     			 icbcprint.printText(sText, 1050, 60+60*1, 1250, 120+60*1, true, "center", "����", 100, "000");
     			 
     			
     			 
     			 sText =ration_score[2];
     			 icbcprint.printText(sText, 850, 60+60*2, 1050, 120+60*2, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[1];
     			 icbcprint.printText(sText, 1050, 60+60*2, 1250, 120+60*2, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[13];
     			 icbcprint.printText(sText, 850, 60+60*3, 1050, 120+60*3, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[26];
     			 icbcprint.printText(sText, 1050, 60+60*3, 1250, 120+60*3, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[5];
     			 icbcprint.printText(sText, 850, 60+60*4, 1050, 120+60*4, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[4];
     			 icbcprint.printText(sText, 1050, 60+60*4, 1250, 120+60*4, true, "center", "����", 100, "000");
          		
          		
          	
          		sText =ration_score[6];
     			 icbcprint.printText(sText, 850, 60+60*5, 1050, 120+60*5, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[5];
     			 icbcprint.printText(sText, 1050, 60+60*5, 1250, 120+60*5, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[8];
     			 icbcprint.printText(sText, 850, 60+60*6, 1050, 120+60*6, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[7];
     			 icbcprint.printText(sText, 1050, 60+60*6, 1250, 120+60*6, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[9];
     			 icbcprint.printText(sText, 850, 60+60*7, 1050, 120+60*7, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[8];
     			 icbcprint.printText(sText, 1050, 60+60*7, 1250, 120+60*7, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[14];
     			 icbcprint.printText(sText, 850, 60+60*8, 1050, 120+60*8, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[27];
     			 icbcprint.printText(sText, 1050, 60+60*8, 1250, 120+60*8, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[12];
     			 icbcprint.printText(sText, 850, 60+60*9, 1050, 120+60*9, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[11];
     			 icbcprint.printText(sText, 1050, 60+60*9, 1250, 120+60*9, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 
     			 
     			 
          		sText = ration_infoArry[13];
     			 icbcprint.printText(sText, 1250, 120, 1450, 300, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[14];
     			 icbcprint.printText(sText, 1450, 120, 1700, 300, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[15];
     			 icbcprint.printText(sText, 1250, 300, 1450, 480, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[16];
     			 icbcprint.printText(sText, 1450, 300, 1700, 480, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[17];
     			 icbcprint.printText(sText, 1250, 480, 1450, 660, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[18];
     			 icbcprint.printText(sText, 1450, 480, 1700, 660, true, "center", "����", 100, "000");
          	          			
          		sText =  ration_infoArry[23];
     		icbcprint.printText(sText, 300, 660, 1700, 720, true, "center", "����", 100, "000");
          	}
		
     
     
     
     
         if (enptype=="04")
     	{
    sText = "һ����ȫ��";
     icbcprint.printText(sText, 0, 120, 300, 240, true, "center", "����", 100, "000");

     sText = "������ծ����";
     icbcprint.printText(sText, 0, 240, 300, 360, true, "center", "����", 100, "000");

     sText = "����ӯ������";
     icbcprint.printText(sText, 0, 360, 300, 480, true, "center", "����", 100, "000");
     
     sText = "�ġ��ɳ���";
     icbcprint.printText(sText, 0, 480, 300, 600, true, "center", "����", 100, "000");
     
     sText = "�塢�ʲ��ṹ";
     icbcprint.printText(sText, 0, 600, 300, 720, true, "center", "����", 100, "000");

     
     sText = "���������ܵ÷�";
     icbcprint.printText(sText, 0, 720, 300, 780, true, "center", "����", 100, "000");
     
     sText = "�ʱ�������";
     icbcprint.printText(sText, 300, 120, 850, 180, true, "center", "����", 100, "000");
     
     sText = "�����ʲ�����";
     icbcprint.printText(sText, 300, 180, 850, 240, true, "center", "����", 100, "000");
     
     sText = "�ϼ���������";
     icbcprint.printText(sText, 300, 240, 850, 300, true, "center", "����", 100, "000");
     
     sText = "��ծ��/EBITDA";
     icbcprint.printText(sText, 300, 300, 850, 360, true, "center", "����", 100, "000");
     
     sText = "���ʲ�������";
     icbcprint.printText(sText, 300, 360, 850, 420, true, "center", "����", 100, "000");
     
     sText = "Ӫҵ����������";
     icbcprint.printText(sText, 300, 420, 850, 480, true, "center", "����", 100, "000");
     
     sText = "������������";
     icbcprint.printText(sText, 300, 480, 850, 540, true, "center", "����", 100, "000");
     
     sText = "Ӫҵ����������";
     icbcprint.printText(sText, 300, 540, 850, 600, true, "center", "����", 100, "000");
     
     sText = "�����ʲ�ռ��";
     icbcprint.printText(sText, 300, 600, 850, 660, true, "center", "����", 100, "000");
     
     sText = "Ӧ�տ�ռ��";
     icbcprint.printText(sText, 300, 660, 850, 720, true, "center", "����", 100, "000");
     
     
     			
     
			sText =ration_score[1];
     			 icbcprint.printText(sText, 850, 60+60*1, 1050, 120+60*1, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[0];
     			 icbcprint.printText(sText, 1050, 60+60*1, 1250, 120+60*1, true, "center", "����", 100, "000");
     			 
     			 

     
     			sText =ration_score[13];
     			 icbcprint.printText(sText, 850, 60+60*2, 1050, 120+60*2, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[26];
     			 icbcprint.printText(sText, 1050, 60+60*2, 1250, 120+60*2, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[4];
     			 icbcprint.printText(sText, 850, 60+60*3, 1050, 120+60*3, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[3];
     			 icbcprint.printText(sText, 1050, 60+60*3, 1250, 120+60*3, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[14];
     			 icbcprint.printText(sText, 850, 60+60*4, 1050, 120+60*4, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[27];
     			 icbcprint.printText(sText, 1050, 60+60*4, 1250, 120+60*4, true, "center", "����", 100, "000");
     
     
     
     
     	for(var i=5;i<11;i++)
     		{
     			
     			sText =ration_score[i+1];
     			 icbcprint.printText(sText, 850, 60+60*i, 1050, 120+60*i, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[i];
     			 icbcprint.printText(sText, 1050, 60+60*i, 1250, 120+60*i, true, "center", "����", 100, "000");
          		}
          		
          	
          		
          			sText = ration_infoArry[13];
     			 icbcprint.printText(sText, 1250, 120, 1450, 240, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[14];
     			 icbcprint.printText(sText, 1450, 120, 1700, 240, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[15];
     			 icbcprint.printText(sText, 1250, 240, 1450, 360, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[16];
     			 icbcprint.printText(sText, 1450, 240, 1700, 360, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[17];
     			 icbcprint.printText(sText, 1250, 360, 1450, 480, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[18];
     			 icbcprint.printText(sText, 1450, 360, 1700, 480, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[19];
     			 icbcprint.printText(sText, 1250, 480, 1450, 600, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[20];
     			 icbcprint.printText(sText, 1450, 480, 1700, 600, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[21];
     			 icbcprint.printText(sText, 1250, 600, 1450, 720, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[22];
     			 icbcprint.printText(sText, 1450, 600, 1700, 720, true, "center", "����", 100, "000");
          	          			
          		sText =  ration_infoArry[23];
     		icbcprint.printText(sText, 300, 720, 1700, 780, true, "center", "����", 100, "000");
          	}
     
     
     
      if (enptype=="05"){
     sText = "һ���ʲ���ȫ��";
     icbcprint.printText(sText, 0, 120, 300, 300, true, "center", "����", 100, "000");

     sText = "������ծ����ָ��";
     icbcprint.printText(sText, 0, 300, 300, 480, true, "center", "����", 100, "000");

     sText = "����ӯ������ָ��";
     icbcprint.printText(sText, 0, 480, 300, 660, true, "center", "����", 100, "000");

     sText = "�ġ��ɳ���";
     icbcprint.printText(sText, 0, 660, 300, 780, true, "center", "����", 100, "000");
     
     sText = "���������ܵ÷�";
     icbcprint.printText(sText, 0, 780, 300, 840, true, "center", "����", 100, "000");
     
     sText = "���ʲ����ն�";
     icbcprint.printText(sText, 300, 120, 850, 180, true, "center", "����", 100, "000");
     
     sText = "��Ӫ֤ȯ����";
     icbcprint.printText(sText, 300, 180, 850, 240, true, "center", "����", 100, "000");
     
     sText = "һ�����׼����";
     icbcprint.printText(sText, 300, 240, 850, 300, true, "center", "����", 100, "000");
     
     sText = "��˾�����ʲ���ծ��";
     icbcprint.printText(sText, 300, 300, 850, 360, true, "center", "����", 100, "000");
     
     sText = "��˾������������";
     icbcprint.printText(sText, 300, 360, 850, 420, true, "center", "����", 100, "000");
     
     sText = "�ѻ���Ϣ����";
     icbcprint.printText(sText, 300, 420, 850, 480, true, "center", "����", 100, "000");
     
     sText = "���ʲ�������";
     icbcprint.printText(sText, 300, 480, 850, 540, true, "center", "����", 100, "000");
     
     sText = "Ӫҵ����������";
     icbcprint.printText(sText, 300, 540, 850, 600, true, "center", "����", 100, "000");
     
     sText = "��Ӫ֤ȯ������";
     icbcprint.printText(sText, 300, 600, 850, 660, true, "center", "����", 100, "000");
     
     sText = "���ʱ�������";
     icbcprint.printText(sText, 300, 660, 850, 720, true, "center", "����", 100, "000");
     
     sText = "������������";
     icbcprint.printText(sText, 300, 720, 850, 780, true, "center", "����", 100, "000");
     
     			for(var i=1;i<4;i++)
     		{
     			
     			sText =ration_score[i];
     			 icbcprint.printText(sText, 850, 60+60*i, 1050, 120+60*i, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[i-1];
     			 icbcprint.printText(sText, 1050, 60+60*i, 1250, 120+60*i, true, "center", "����", 100, "000");
          		}
     
     
     
     
     	for(var i=4;i<12;i++)
     		{
     			
     			sText =ration_score[i+1];
     			 icbcprint.printText(sText, 850, 60+60*i, 1050, 120+60*i, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[i];
     			 icbcprint.printText(sText, 1050, 60+60*i, 1250, 120+60*i, true, "center", "����", 100, "000");
          		}
          		
          	
          		
          			sText = ration_infoArry[13];
     			 icbcprint.printText(sText, 1250, 120, 1450, 300, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[14];
     			 icbcprint.printText(sText, 1450, 120, 1700, 300, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[15];
     			 icbcprint.printText(sText, 1250, 300, 1450, 480, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[16];
     			 icbcprint.printText(sText, 1450, 300, 1700, 480, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[17];
     			 icbcprint.printText(sText, 1250, 480, 1450, 660, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[18];
     			 icbcprint.printText(sText, 1450, 480, 1700, 660, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[19];
     			 icbcprint.printText(sText, 1250, 660, 1450, 780, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[20];
     			 icbcprint.printText(sText, 1450, 660, 1700, 780, true, "center", "����", 100, "000");
          	          			
          		sText =  ration_infoArry[23];
     		icbcprint.printText(sText, 300, 780, 1700, 840, true, "center", "����", 100, "000");
          	}
		
     
     
     
                                
              if (enptype=="06")
     	{
    sText = "һ����ȫ��";
     icbcprint.printText(sText, 0, 120, 300, 240, true, "center", "����", 100, "000");

     sText = "������ծ����";
     icbcprint.printText(sText, 0, 240, 300, 360, true, "center", "����", 100, "000");

     sText = "����ӯ������";
     icbcprint.printText(sText, 0, 360, 300, 480, true, "center", "����", 100, "000");
     
     sText = "�ġ���չ����";
     icbcprint.printText(sText, 0, 480, 300, 660, true, "center", "����", 100, "000");
     
     sText = "�塢�ʲ�����ծ�ṹ";
     icbcprint.printText(sText, 0, 660, 300, 780, true, "center", "����", 100, "000");

     
     sText = "���������ܵ÷�";
     icbcprint.printText(sText, 0, 780, 300, 840, true, "center", "����", 100, "000");
     
     sText = "�ʱ�������";
     icbcprint.printText(sText, 300, 120, 850, 180, true, "center", "����", 100, "000");
     
     sText = "�����������";
     icbcprint.printText(sText, 300, 180, 850, 240, true, "center", "����", 100, "000");
     
     sText = "��������";
     icbcprint.printText(sText, 300, 240, 850, 300, true, "center", "����", 100, "000");
     
     sText = "��ծ��/EBITDA";
     icbcprint.printText(sText, 300, 300, 850, 360, true, "center", "����", 100, "000");
     
     sText = "���ʲ�������";
     icbcprint.printText(sText, 300, 360, 850, 420, true, "center", "����", 100, "000");
     
     sText = "Ӫҵ����������";
     icbcprint.printText(sText, 300, 420, 850, 480, true, "center", "����", 100, "000");
     
     sText = "������������";
     icbcprint.printText(sText, 300, 480, 850, 540, true, "center", "����", 100, "000");
     
     sText = "���������";
     icbcprint.printText(sText, 300, 540, 850, 600, true, "center", "����", 100, "000");
     
     sText = "�عɹ�˾���ʲ�������";
     icbcprint.printText(sText, 300, 600, 850, 660, true, "center", "����", 100, "000");
     
     sText = "�����ʽ����";
     icbcprint.printText(sText, 300, 660, 850, 720, true, "center", "����", 100, "000");
     
     sText = "���ڸ�ծ����";
     icbcprint.printText(sText, 300, 720, 850, 780, true, "center", "����", 100, "000");
     
     
     
     	
     
			sText =ration_score[1];
     			 icbcprint.printText(sText, 850, 60+60*1, 1050, 120+60*1, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[0];
     			 icbcprint.printText(sText, 1050, 60+60*1, 1250, 120+60*1, true, "center", "����", 100, "000");
     			 
     			 

     
     			sText =ration_score[13];
     			 icbcprint.printText(sText, 850, 60+60*2, 1050, 120+60*2, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[26];
     			 icbcprint.printText(sText, 1050, 60+60*2, 1250, 120+60*2, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[3];
     			 icbcprint.printText(sText, 850, 60+60*3, 1050, 120+60*3, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[2];
     			 icbcprint.printText(sText, 1050, 60+60*3, 1250, 120+60*3, true, "center", "����", 100, "000");
     			 
     			 
     			 
     			 sText =ration_score[14];
     			 icbcprint.printText(sText, 850, 60+60*4, 1050, 120+60*4, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[27];
     			 icbcprint.printText(sText, 1050, 60+60*4, 1250, 120+60*4, true, "center", "����", 100, "000");
     
     
     
     
     	for(var i=5;i<11;i++)
     		{
     			
     			sText =ration_score[i+1];
     			 icbcprint.printText(sText, 850, 60+60*i, 1050, 120+60*i, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[i];
     			 icbcprint.printText(sText, 1050, 60+60*i, 1250, 120+60*i, true, "center", "����", 100, "000");
          		}
          		
          	
          		
          			sText = ration_infoArry[13];
     			 icbcprint.printText(sText, 1250, 120, 1450, 240, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[14];
     			 icbcprint.printText(sText, 1450, 120, 1700, 240, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[15];
     			 icbcprint.printText(sText, 1250, 240, 1450, 360, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[16];
     			 icbcprint.printText(sText, 1450, 240, 1700, 360, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[17];
     			 icbcprint.printText(sText, 1250, 360, 1450, 480, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[18];
     			 icbcprint.printText(sText, 1450, 360, 1700, 480, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[19];
     			 icbcprint.printText(sText, 1250, 480, 1450, 660, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[20];
     			 icbcprint.printText(sText, 1450, 480, 1700, 660, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[21];
     			 icbcprint.printText(sText, 1250, 660, 1450, 780, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[22];
     			 icbcprint.printText(sText, 1450, 660, 1700, 780, true, "center", "����", 100, "000");
          	          			
          		sText =  ration_infoArry[23];
     		icbcprint.printText(sText, 300, 780, 1700, 840, true, "center", "����", 100, "000");
          	}
            
		
		
  if (enptype=="07")
     	{
    sText = "һ����ȫ��";
     icbcprint.printText(sText, 0, 120, 300, 240, true, "center", "����", 100, "000");

     sText = "������ծ����";
     icbcprint.printText(sText, 0, 240, 300, 420, true, "center", "����", 100, "000");

     sText = "����ӯ������";
     icbcprint.printText(sText, 0, 420, 300, 600, true, "center", "����", 100, "000");
     
     sText = "�ġ���չ����";
     icbcprint.printText(sText, 0, 600, 300, 720, true, "center", "����", 100, "000");
     
     

     
     sText = "���������ܵ÷�";
     icbcprint.printText(sText, 0, 720, 300, 780, true, "center", "����", 100, "000");
     
     sText = "����ҵ���ģ��";
     icbcprint.printText(sText, 300, 120, 850, 180, true, "center", "����", 100, "000");
     
     sText = "�ܲ����ʲ�����";
     icbcprint.printText(sText, 300, 180, 850, 240, true, "center", "����", 100, "000");
     
     sText = "��˾�����ʲ���ծ��";
     icbcprint.printText(sText, 300, 240, 850, 300, true, "center", "����", 100, "000");
     
     sText = "��˾��踺ծ��";
     icbcprint.printText(sText, 300, 300, 850, 360, true, "center", "����", 100, "000");
     
     sText = "��˾������������";
     icbcprint.printText(sText, 300, 360, 850, 420, true, "center", "����", 100, "000");
     
     sText = "��˾���ʲ�������";
     icbcprint.printText(sText, 300, 420, 850, 480, true, "center", "����", 100, "000");
     
     sText = "��˾Ӫҵ����������";
     icbcprint.printText(sText, 300, 480, 850, 540, true, "center", "����", 100, "000");
     
     sText = "�����ʲ�������";
     icbcprint.printText(sText, 300, 540, 850, 600, true, "center", "����", 100, "000");
     
     sText = "�����ʲ�������";
     icbcprint.printText(sText, 300, 600, 850, 660, true, "center", "����", 100, "000");
     
     sText = "��˾������������";
     icbcprint.printText(sText, 300, 660, 850, 720, true, "center", "����", 100, "000");
     
     
     
     
     
     	for(var i=1;i<9;i++)
     		{
     			
     			sText =ration_score[i];
     			 icbcprint.printText(sText, 850, 60+60*i, 1050, 120+60*i, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[i-1];
     			 icbcprint.printText(sText, 1050, 60+60*i, 1250, 120+60*i, true, "center", "����", 100, "000");
          		}
          		
          		var i=9;
          		sText =ration_score[13];
     			 icbcprint.printText(sText, 850, 60+60*i, 1050, 120+60*i, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[26];
     			 icbcprint.printText(sText, 1050, 60+60*i, 1250, 120+60*i, true, "center", "����", 100, "000");
     			 
     			 
     			 	var i=10;
          		sText =ration_score[i];
     			 icbcprint.printText(sText, 850, 60+60*i, 1050, 120+60*i, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[i-1];
     			 icbcprint.printText(sText, 1050, 60+60*i, 1250, 120+60*i, true, "center", "����", 100, "000");
          	
          	
          	
          	
          		
          			sText = ration_infoArry[13];
     			 icbcprint.printText(sText, 1250, 120, 1450, 240, true, "center", "����", 100, "000");
     			
     			sText =  ration_infoArry[14];
     			 icbcprint.printText(sText, 1450, 120, 1700, 240, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[15];
     			 icbcprint.printText(sText, 1250, 240, 1450, 420, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[16];
     			 icbcprint.printText(sText, 1450, 240, 1700, 420, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[17];
     			 icbcprint.printText(sText, 1250, 420, 1450, 600, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[18];
     			 icbcprint.printText(sText, 1450, 420, 1700, 600, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[19];
     			 icbcprint.printText(sText, 1250, 600, 1450, 720, true, "center", "����", 100, "000");
     			 
     			 sText =  ration_infoArry[20];
     			 icbcprint.printText(sText, 1450, 600, 1700, 720, true, "center", "����", 100, "000");
     			 
     			
          	          			
          		sText =  ration_infoArry[23];
     		icbcprint.printText(sText, 300, 780, 1700, 840, true, "center", "����", 100, "000");
          	}
     		
     		
     		
     		
     		
     		
     		
     		
     		
     			
		
		
  icbcprint.printEndTable(10);	
  icbcprint.printEndPage();
  icbcprint.printStartPage();
}
  
  
  	  var sText = "�������۵÷���ϸ";
     	icbcprint.printText(sText, 150, 270, 1950, 360, false, "center", "����", 120, "010");	
  
   icbcprint.printBeginTable(190, 360, 190+1310, 360+1620, 1);
   
     sText = "��������";
     icbcprint.printText(sText, 0, 0, 300, 60, true, "center", "����", 100, "000");

     sText = "����ָ��";
     icbcprint.printText(sText, 300, 0, 800, 60, true, "center", "����", 100, "000");

     sText = "�÷�";
     icbcprint.printText(sText, 800, 0, 1150, 60, true, "center", "����", 100, "000");

     sText = "ʵ�ʵ÷�";
     icbcprint.printText(sText, 1150, 0, 1500, 60, true, "center", "����", 100, "000");
     
     	sText = "֧������";
     	icbcprint.printText(sText, 0, 60, 300, 420, true, "center", "����", 100, "000");
     	
     	sText = "����״��";
     	icbcprint.printText(sText, 0, 420, 300, 720, true, "center", "����", 100, "000");
     	
     	sText = "�ڿ�ˮƽ";
     	icbcprint.printText(sText, 0, 720, 300, 960, true, "center", "����", 100, "000");
     	
     	sText = "�г�������";
     	icbcprint.printText(sText, 0, 960, 300, 1200, true, "center", "����", 100, "000");
     	
     	sText = "��Ӫ����";
     	icbcprint.printText(sText, 0, 1200, 300, 1380, true, "center", "����", 100, "000");
     	
     	sText = "����״��";
     	icbcprint.printText(sText, 0, 1380, 300, 1560, true, "center", "����", 100, "000");
     	
     	if(typeFlag=="15"){
     	sText = "���������ܵ÷�";
     	icbcprint.printText(sText, 0,1560, 300, 1620, true, "center", "����", 100, "000");
	}
	
	if(typeFlag=="16"){
		
	sText = "��Ӫ���";
     	icbcprint.printText(sText, 0, 1560, 300, 1740, true, "center", "����", 100, "000");
		
	sText = "���������ܵ÷�";
     	icbcprint.printText(sText, 0,1740, 300, 1800, true, "center", "����", 100, "000");
	}
	
	 
	if (typeFlag=="17")
	{
		
	sText = "���б��������";
     	icbcprint.printText(sText, 0, 1560, 300, 1680, true, "center", "����", 100, "000");
		
	sText = "���������ܵ÷�";
     	icbcprint.printText(sText, 0,1680, 300, 1740, true, "center", "����", 100, "000");
	}
     	
     	var j=25;
     	
     	if(typeFlag=="15"){j=25;}
     	if(typeFlag=="16"){j=28;}
     	if(typeFlag=="17"){j=27;}
     	
     	
     	
     	for (var i=0;i<j;i++)
     		{
     			sText = nature_nameArry[i];
     			icbcprint.printText(sText, 300, 60+i*60, 800, 120+i*60, true, "center", "����", 100, "000");		
     			
     			sText = nature_score[i];
     			icbcprint.printText(sText, 800, 60+i*60, 1150, 120+i*60, true, "center", "����", 100, "000");		
     	
     			sText = nature_infoArry[i];
     			icbcprint.printText(sText, 1150, 60+i*60, 1500, 120+i*60, true, "center", "����", 100, "000");		
   		}
   		

	
   		
   	if(typeFlag=="15"){
   	sText = nature_infoArry[28];
     	icbcprint.printText(sText, 300, 1560, 1500, 1620, true, "center", "����", 100, "000");
  	}
  	
  	if(typeFlag=="16"){
   	sText = nature_infoArry[28];
     	icbcprint.printText(sText, 300, 1740, 1500, 1800, true, "center", "����", 100, "000");
  	}
  	
  	if(typeFlag=="17"){
   	sText = nature_infoArry[28];
     	icbcprint.printText(sText, 300, 1680, 1500, 1740, true, "center", "����", 100, "000");
  	}
  	
   icbcprint.printEndTable(10);			
  
  icbcprint.printEndPage();
		
	}



/*** END �����Ʒֱ�***/

/**************  ��������******************/

function pringEvaluateResult(evaluate_infoArry,opinion_infoArry){
     icbcprint.printStartPage();

	var y=300;
     var sText = "�塢��������";
     icbcprint.printText(sText, 150, y, 1950, 360, false, "left", "����", 120, "000");

    	icbcprint.printBeginTable(180, y+70, 1700, 600, 0);
    	
    	    sText = "   ���񱨱�ʱ��";
	     icbcprint.printText(sText, 0, 0, 400, 60, true, "left",  "����", 100, "000");
	
	     var rdate = evaluate_infoArry[14];
	     var year = rdate.substring(0,4);
	     var month = rdate.substring(4,6);
	     sText = year+"��"+month+"��";
	     icbcprint.printText(sText, 400, 0, 700, 60, true, "center",  "����", 100, "000");
	     
	         sText = "   �������ʱ�ʶ";
	     icbcprint.printText(sText, 700, 0, 1000, 60, true, "left",  "����", 100, "000");

	     sText = "";
	     var flag = evaluate_infoArry[0];
	     if (flag=="0"){sText = "��������÷ָ��ڻ������������۵÷�25%";}
	     if (flag=="1"){sText = " ";}
	     icbcprint.printText(sText, 1000, 0, 1700, 60, true, "center",  "����", 100, "000");
	     
	       sText = "   �������������۵÷�";
	     icbcprint.printText(sText, 0, 60, 400, 120, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[1];
	     icbcprint.printText(sText, 400, 60, 700, 120, true, "center",  "����", 100, "000");

	     sText = "   ʵ�����۵÷�";
	     icbcprint.printText(sText, 700, 60, 1000, 120, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[2];
	     icbcprint.printText(sText, 1000, 60, 1700, 120, true, "center",  "����", 100, "000");
	     
	       sText = "   �ۺϲ�����ϵ��";
	     icbcprint.printText(sText, 0, 120, 400, 180, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[3];
	     icbcprint.printText(sText, 400, 120, 700, 180, true, "center",  "����", 100, "000");
	     
	         sText = "   ֧������ϵ��";
	     icbcprint.printText(sText, 700, 120, 1000, 180, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[4];
	     icbcprint.printText(sText, 1000, 120, 1700, 180, true, "center",  "����", 100, "000");
	     
	    sText = "   �������۵÷�";
	     icbcprint.printText(sText, 0, 180, 400, 240, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[5];
	     icbcprint.printText(sText, 400,180, 700, 240, true, "center",  "����", 100, "000");
	     
	     sText = "   �ۺ����۵÷�";
	     icbcprint.printText(sText, 700, 180, 1000, 240, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[6];
	     icbcprint.printText(sText, 1000, 180, 1700, 240, true, "center",  "����", 100, "000");
	     
	      sText = "   �ۺ����۽��";
	     icbcprint.printText(sText, 0, 240, 400, 300, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[7];
	     icbcprint.printText(sText, 400,240, 700, 300, true, "center",  "����", 100, "000");
	     
	     sText = "   ��ҵ�����÷�";
	     icbcprint.printText(sText, 700, 240, 1000, 300, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[8];
	     icbcprint.printText(sText, 1000, 240, 1700, 300, true, "center",  "����", 100, "000");
	     
	     sText = "  ��ҵ�������";
	     icbcprint.printText(sText, 0, 300, 400, 360, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[9];
	     icbcprint.printText(sText, 400,300, 700, 360, true, "center",  "����", 100, "000");

	     sText = "   Ԥ��ָ�꾯ʾ�����";
	     icbcprint.printText(sText, 0, 360, 700, 420, true, "left",  "����", 100, "000");
	     
	     var flag = evaluate_infoArry[10];
	      sText = "��";
	       if (flag==""){ sText = "��";}
	      icbcprint.printText(sText, 700, 360, 1700, 420, true, "left",  "����", 100, "000");
	      
	   
	       	sText = flag;	
                      icbcprint.printText(sText, 0, 420, 1700, 600, true, "left",  "����", 100, "000");
                      
                      sText = "  ���������������޶���";
	     icbcprint.printText(sText, 0, 600, 700, 660, true, "left",  "����", 100, "000");
	     
	     var flag = evaluate_infoArry[11];
	      sText = "��";
	       if (flag==""){ sText = "��";}
	      icbcprint.printText(sText, 700, 600, 1700, 660, true, "left",  "����", 100, "000");
	      
	   
	       	sText = flag;	
                      icbcprint.printText(sText, 0, 660, 1700, 840, true, "left",  "����", 100, "000");
                      
                      
                          sText = "   ���������õȼ�";
	     icbcprint.printText(sText, 0, 840, 400, 900, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[12];
	     icbcprint.printText(sText, 400,840, 700, 900, true, "center",  "����", 100, "000");
	     
	     sText = "   ���۽���";
	     icbcprint.printText(sText, 700, 840, 1000, 900, true, "left",  "����", 100, "000");

	     sText = evaluate_infoArry[13];
	     icbcprint.printText(sText, 1000, 840, 1700, 900, true, "center",  "����", 100, "000");
	     
	     
	     
	     
	     
	     
	     sText = "�Կͻ�����״�����ۺ����ۣ����������������������˵��֧�ֵ��������ɣ��ر�Ҫ���ش��������˵����";
     icbcprint.printText(sText, 160, 800, 1700, 1520, false, "left",  "����", 120, "010");

     var cell_height = 370;
     for(var i=0;i<opinion_infoArry.length;i++){
         icbcprint.printBeginTable(150, 1520+cell_height*i, 1750, 70, 1);

         sText = opinion_infoArry[i][4];  //�����˵�������
         icbcprint.printText(sText, 0, 0, 210, 70,  false, "left",  "����", 120, "000");

         sText = opinion_infoArry[i][5]+"   �����";
         icbcprint.printText(sText, 210, 0, 1750, 70, false, "left",  "����", 120, "000");

         icbcprint.printEndTable(0);

         sText = opinion_infoArry[i][7];
         icbcprint.printText(sText, 250, 1590+cell_height*i, 1900, 1660+cell_height*i, false, "left",  "����", 120, "000");

         icbcprint.printBeginTable(150, 1660+cell_height*i, 1750, 210, 1);

         sText = "�����Ϸ�������������ÿͻ����õȼ�Ϊ��";
         icbcprint.printText(sText, 0, 0, 850, 70, false, "left",  "����", 120, "000");

         sText = "  "+opinion_infoArry[i][9]+"  ";
         icbcprint.printText(sText, 850, 0, 1650, 70, false, "right",  "����", 120, "100");

         sText = "��";
         icbcprint.printText(sText, 1650, 0, 1750, 70, false, "left",  "����", 120, "000");

         sText = opinion_infoArry[i][5]; //�����˽�ɫ
         icbcprint.printText(sText, 0, 70, 1400, 140, false, "right",  "����", 120, "000");

         sText = "  "+opinion_infoArry[i][1]+"  "; //����
         icbcprint.printText(sText, 1400, 70, 1750, 140, false, "center",  "����", 120, "100");

         var year = opinion_infoArry[i][8].substring(0,4);
         var month = opinion_infoArry[i][8].substring(4,6);
         var day = opinion_infoArry[i][8].substring(6,8);
         sText = year+"��"+month+"��"+day+"��"; //�����˴���ʱ��
         icbcprint.printText(sText, 0, 140, 1750, 210, false, "right",  "����", 120, "000");

         icbcprint.printEndTable(0);
                }
	}

/*******************END ��������*********************/


/*  ��ӡ���*/
function printEndInfo(approve_bank,print_date){

     sText = "������������������������������������������������������������������������";
     icbcprint.printText(sText, 150, 2000, 1950, 2100, false, "center", "����", 140, "000");

     icbcprint.printBeginTable(1000, 2200, 900, 260, 0);

     sText = "������λ��";
     icbcprint.printText(sText, 0, 0, 300, 130, false, "center", "����", 140, "010");

     sText = approve_bank;
     icbcprint.printText(sText, 300, 0, 900, 130, false, "left", "����", 140, "010");

     sText = "��ӡ���ڣ�";
     icbcprint.printText(sText, 0, 130, 300, 260, false, "center", "����", 140, "010");

     var year = print_date.substring(0,4);
     var month = print_date.substring(4,6);
     var day = print_date.substring(6,8);
     sText = year+"��"+month+"��"+day+"��";
     icbcprint.printText(sText, 300, 130, 900, 260, false, "left", "����", 140, "010");

     icbcprint.printEndTable(0);

     icbcprint.printEndPage();
}