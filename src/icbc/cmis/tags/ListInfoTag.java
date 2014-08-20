package icbc.cmis.tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import icbc.cmis.base.*;
import icbc.cmis.operation.*;

/**
 * Title:        cmis
 * Description:  cmis3
 * Copyright:    Copyright (c) 2001
 * Company:      icbc
 * @author icbc
 * @version 1.0
 */

public class ListInfoTag extends TagSupport {
    private KeyedDataCollection context;
    private IndexedDataCollection jspicoll;

    private int startIndex = -1;  //��ʼ��¼��
    private int endIndex = -1;    //��ֹ��¼��
    private int size=10;    //ÿҳ��¼��
    private int listSize;  //�б��¼����
    private int currentID; //��ǰ��¼��
    private String collectionId="operationData";
    private String indexName;
    private String listInfo=null;
    private int totalPage;//��ҳ��
    private int currentPage; //��ҳ��

    boolean quote = false;  //�Ƿ��Ѿ����м�¼�����ƵĿ���
//    private icbc.cmis.base.CMisSessionMgr sm;

  public ListInfoTag() {
  }

    public void setcollectionid(String cId){
        this.collectionId = cId;
    }

    public void setindexname(String indexName){
        this.indexName = indexName;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setQuote( boolean quote ) {
        this.quote = quote;
    }

    public String getIndexName( ) {
        return(this.indexName);
    }
    public String getListInfo( ) {
        return(this.listInfo);
    }

    public int getSize( ) {
        return this.size;
    }

    public boolean getQuote( ) {
        return( this.quote );
    }

  public int doStartTag() throws javax.servlet.jsp.JspException {


        String overFlowmsg=null;
        int bufBegIdx=0;
        int bufEndIdx=0;
        context = (KeyedDataCollection)pageContext.getRequest().getAttribute(collectionId);
//        sm = new icbc.cmis.base.CMisSessionMgr((HttpServletRequest)pageContext.getRequest());
        if (context == null)
           throw new JspTagException("ListInfoTag: Collection " + collectionId + " not found .");

       try{
        jspicoll = (IndexedDataCollection)context.getElement(indexName);

        if( quote ){
           listSize = Integer.parseInt((String)context.getValueAt("rowCount" + indexName));

           //��������
           bufBegIdx = Integer.parseInt((String)context.getValueAt("bufBegIdx" + indexName));
           //������β
           bufEndIdx = Integer.parseInt((String)context.getValueAt("bufEndIdx" + indexName));
        }else
           listSize = jspicoll.getSize();

        //��ȡ��ʼ��¼λ��
        String beginpositionName = "ListTagBeginPosition"+indexName;

		//add by zjfh-shanhy at 2007/04/04,start
		String isNewQuery = "false";
		if (context.isElementExist("newQuery")) {
			isNewQuery = (String) context.getValueAt("newQuery");
		}
			
		if (isNewQuery.equalsIgnoreCase("true")) {
			startIndex = 0;
			endIndex = size;
	//add by zjfh-shanhy at 2007/04/04,end
		} else{
			//��ȡ��ʼ��¼λ��
			if (context.isElementExist(beginpositionName)) {
				startIndex =
					Integer.parseInt(
						(String) context.getValueAt(beginpositionName));
				endIndex = startIndex + size;
			} else {
				startIndex = 0;
				endIndex = size;
			}				
		}

       totalPage    = (listSize%size==0)?(listSize/size):((listSize/size)+1);
       currentPage  = (endIndex%size)==0?(endIndex/size):(endIndex/size+1);

       if( listSize > 0 ){
          listInfo="��ѯ��"+listSize+"����¼����"+ totalPage+"ҳ����"+ currentPage+"ҳ";

          // add by yhua on 2002/10/25
          if (context.isElementExist( indexName+"IsOverflow"))
               overFlowmsg = (String)context.getValueAt( indexName+"OverflowMsg" );
          if( overFlowmsg!= null )
               listInfo = listInfo + "/" +overFlowmsg;
          // add end
       }else
          listInfo="δ��ѯ�������Ϣ";
        }catch(Exception ee){
            System.err.println("ListTag: Error printing attribute: " + ee);
            throw new JspTagException("��Ԫ�س���"+this.indexName);
        }

     return EVAL_BODY_INCLUDE;
  }


  public int doEndTag() throws javax.servlet.jsp.JspException {
    return EVAL_PAGE;
  }
}