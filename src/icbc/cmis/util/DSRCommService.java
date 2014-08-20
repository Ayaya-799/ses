package icbc.cmis.util;
import icbc.cmis.base.CmisConstance;
import icbc.cmis.base.XMLReader;

import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.xml.parser.ErrorListener;
import com.ibm.xml.parser.Parser;
import com.icbc.util.comm.ConnectionPoolDriver;
/**
 * @author xgl
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

/**
 *20050508 edit by chenj 
 *�޸��߼�  if (cycle != null) ȡcycle��                      
 *         else ȡ ROW_REQ; 
 */

public class DSRCommService implements ErrorListener {

	/**
	 * Constructor for DSRCommService.
	 */
	private Hashtable typeTable = new Hashtable(5);
	public static Hashtable areaList = new Hashtable(100);
	private static Hashtable dsrTemplete = new Hashtable(20);
	private Hashtable modulTable = new Hashtable(3);
	public static Hashtable tranCodeMap = new Hashtable(10);
	//add by chenj for new trade type 20040121
	public static Hashtable tranTyoeMap = new Hashtable(10);
	//add end
	public static Hashtable tranModulsReg = new Hashtable(10);
	public static final int io_i = 1;
	public static final int io_o = 2;
	public static final int io_io = 3;
	public static int dsrCycle = 1;
	public DSRCommService() {
		super();
	}

	public static Vector getADsrTranTmplete(String dsrCode) {
		return (Vector) dsrTemplete.get(dsrCode);
	}

	private Document loadXMLFile(String filePath) throws Exception {
		String tmpPath = null;
		if (filePath.indexOf(":") != -1) {
			if (filePath.indexOf(":") <= 2) {
				tmpPath = "file:///" + filePath;
				java.net.URL aURL = new java.net.URL(tmpPath);
				tmpPath = aURL.toString();
			} else
				tmpPath = filePath;
		} else {
			tmpPath = filePath;
		}
		Parser parser = new Parser("XMLLoader", this, null);
		try {
			try {
				parser.parse(tmpPath);
			} catch (Exception e) {
				System.out.println(
					"Warning - XMLLoader.loadXMLFile(String fileName): The XML parser has not read correctly the XML file , check file "
						+ tmpPath
						+ ": "
						+ e.toString());
				java.net.URL aURL = getClass().getResource(tmpPath);
				System.out.println(
					"XMLLoader.loadXMLFile(): Now Load the XML file As: "
						+ aURL.toString());
				parser.parse(aURL.toString());
			}
		} catch (org.xml.sax.SAXException se) {
			System.out.println("Not properly parsed: " + se.toString());
			throw new Exception("Not properly parsed: " + se.toString());
		} catch (java.io.IOException ioe) {
			System.out.println("Not properly parsed: " + ioe.toString());
			throw new Exception(ioe.toString());
		}
		Document doc = parser.getDocument();
		if (doc == null) {
			System.out.println("Error - check file: " + tmpPath);
			throw new Exception("�����ļ���" + tmpPath + " �Ƿ���Ч��");
		}

		return doc;
	}

	private String getAttrValue(Node aNode, String attrName, boolean isNeed)
		throws Exception {
		try {
			String value = null;
			try {
				value =
					aNode.getAttributes().getNamedItem(attrName).getNodeValue();
			} catch (Exception e) {
				if (isNeed) {
					throw new Exception(
						"���� �ڵ�["
							+ aNode.toString()
							+ "]������["
							+ attrName
							+ "]������ȷ,Exception:"
							+ e.toString());
				}
			}

			return value;
		} catch (Exception e) {
			throw e;
		}
	}

	private void getDsrType(Document doc) throws Exception {
		NodeList aNodeList = doc.getElementsByTagName("TYPES");
		if (aNodeList == null) {
			throw new Exception("��ȷ��Types�ڵ�������ȷ");
		} else {
			Node tmpNode = aNodeList.item(0);
			NodeList tmpNodeList = tmpNode.getChildNodes();
			for (int i = 0; i < tmpNodeList.getLength(); i++) {
				Node aNode = tmpNodeList.item(i);
				if (isElementNode(aNode)) {
					String id = getAttrValue(aNode, "id", true);
					String classStr = getAttrValue(aNode, "class", true);
					String defaultValue = getAttrValue(aNode, "value", false);
					DsrDataElementType dataType = null;
					try {
						dataType =
							(DsrDataElementType) Class
								.forName(classStr)
								.newInstance();
					} catch (ClassNotFoundException e) {
						throw new Exception(
							"��ȷ��Types�ڵ��е�����[" + id + "]��class������ȷ");
					}
					if (defaultValue != null) {
						dataType.setDefaultValue(defaultValue);
					}
					typeTable.put(id, dataType);

				}

			}
		}
		return;
	}

	private void getAreaMapTable(Document doc) throws Exception {
		NodeList aNodeList = doc.getElementsByTagName("AREAS");
		if (aNodeList == null) {
			throw new Exception("��ȷ��AREAS�ڵ�������ȷ");
		} else {
			Node areasNode = aNodeList.item(0);
			NodeList list = areasNode.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node areaNode = list.item(i);
				if (isElementNode(areaNode)) {
					String area = getAttrValue(areaNode, "id", true);
					String dsrPoolName =
						getAttrValue(areaNode, "dsrPoolName", true);
						String appNo =
						getAttrValue(areaNode, "appNo", true);
					String[] tA = {dsrPoolName,appNo};
					NodeList subAreaList = areaNode.getChildNodes();
					if (subAreaList == null || subAreaList.getLength() <= 0) {
						areaList.put(area, tA);
					} else {
						for (int j = 0; j < subAreaList.getLength(); j++) {
							Node subArea = subAreaList.item(j);
							if (isElementNode(subArea)) {
								addSubArea(subArea, tA);
							}
						}
					}
				}
			}
		}

	}

	private void addSubArea(Node areaNode, String[] appNoAndPoolName)
		throws Exception {
		NodeList list = areaNode.getChildNodes();
		if (list == null || list.getLength() <= 0) {
			String id = getAttrValue(areaNode, "id", true);
			areaList.put(id, appNoAndPoolName);

		} else {
			for (int i = 0; i < list.getLength(); i++) {
				Node area = list.item(i);
				if (isElementNode(area)) {
					addSubArea(area, appNoAndPoolName);
				}
			}
		}
	}

	private static boolean isElementNode(Node aNode) {
		if (aNode.getNodeType() == Node.ELEMENT_NODE)
			return true;
		else
			return false;

	}

	private void getPublicModules(Document doc) throws Exception {
		NodeList modul = doc.getElementsByTagName("modules");
		if (modul == null || modul.getLength() == 0) {
			return;
		} else {
			NodeList aNodeList = modul.item(0).getChildNodes();
			if (aNodeList != null) {
				for (int i = 0; i < aNodeList.getLength(); i++) {
					Node aNode = aNodeList.item(i);
					if (isElementNode(aNode)) {
						String id = getAttrValue(aNode, "id", true);
						modulTable.put(id, (Vector)getModulItems(aNode,"",1).get("vItems"));//update by wlx 20061108 part cycle
					}
				}
			}

		}
		String dsrCycle1 = getCycles();
		try{
			dsrCycle = Integer.parseInt(dsrCycle1);
		}catch(Exception e){
			dsrCycle = 1;
		}

	}
/**
 * update by wlx 20061108 ԭ�ȷ���vector�����ڷ���hashtable
 * ����˶������ڵ�cyclepart�Ĵ���
 * ԭ�ȸ���cycle���ⲿ��ѭ�����ñ������������ⲿ����ѭ����ֱ���ڲ���װѭ����
 * @param aNode
 * @param newOrOld
 * @return
 * @throws Exception
 */
	private Hashtable getModulItems(Node aNode,String newOrOld,int cycle) throws Exception {
		Hashtable retHash = new Hashtable();//=========add by wlx 20061108 part cycle====================//
		NodeList subList = aNode.getChildNodes();
		Vector vItems = new Vector(10);
		Hashtable ht_ret = new Hashtable();
		int numOfCycle = 0;
		int beginOfCycle = 0;
		if(newOrOld.equals("new")){//�µĲ���ѭ��
			for (int j = 0; j < subList.getLength(); j++) {
				Node subNode = subList.item(j);
				if (isElementNode(subNode)) {
					if(subNode.getNodeName().trim().equals("cyclePart")){//����ͨѶ����ѭ�����֣���Ҫ�õ�cycle
						ht_ret.put("beginOfCycle",String.valueOf(beginOfCycle));//��ʼѭ��λ�ã���0��
						NodeList subNodeList = subNode.getChildNodes();
						for(int t=0;t<cycle;t++){
							for(int k = 0; k < subNodeList.getLength(); k++){
								Node subNodesubNode = subNodeList.item(k);
								if (isElementNode(subNodesubNode)) {
									vItems.addElement(getDsrDataInstance(subNodesubNode));	
									if(t==0){
										numOfCycle ++ ;//ѭ����������
									}
								}
							}
						}
					}else{
						vItems.addElement(getDsrDataInstance(subNode));//��ѭ���Ĳ��֣���Ȼ����	
						beginOfCycle ++;
					}
				}
			}
			ht_ret.put("numOfCycle",String.valueOf(numOfCycle));//ѭ������,��
		}else{//ԭ��������ѭ��
			ht_ret.put("beginOfCycle",String.valueOf(0));//��ʼѭ��λ�ã���0��
			for(int m=0;m<cycle;m++){
				for (int i = 0; i < subList.getLength(); i++) {
					Node subNode = subList.item(i);
					if (isElementNode(subNode)) {
						vItems.addElement(getDsrDataInstance(subNode));
						if(m==0){
							numOfCycle ++ ;//ѭ����������
						}
					}
				}
			}
			ht_ret.put("numOfCycle",String.valueOf(numOfCycle));//ѭ������
		}
		ht_ret.put("vItems",vItems);
		return ht_ret;
	}

	private DsrDataElementType getDsrDataInstance(Node subNode)
		throws Exception {
		String dataName = getAttrValue(subNode, "id", true);
		String type = getAttrValue(subNode, "type", true);
		String value = getAttrValue(subNode, "value", false);
		int  ioType = Integer.parseInt(getAttrValue(subNode, "ioType", true));
		String format = getAttrValue(subNode, "format", false);
		String mapField = getAttrValue(subNode, "mapField", false);
		DsrDataElementType dataType1 = (DsrDataElementType) typeTable.get(type);
		if (dataType1 == null) {
			throw new Exception(
				"��ȷ�Ͻڵ�[" + subNode.getNodeName() + "]��type�����Ƿ�������ȷ");
		}
		DsrDataElementType dataType = (DsrDataElementType) dataType1.clone();
		dataType.setDataName(dataName);
		dataType.setDataValue(value);
		dataType.setIOType(ioType);
		dataType.setFormat(format);
		dataType.setMapField(mapField);
		return dataType;
	}

	private void genTemplate(Document doc) throws Exception {

		getDsrType(doc);
		//getAreaMapTable(doc);
		getPublicModules(doc);
		genTransTemplate(doc);
		return;
	}
	
	
	private void genTransTemplate(Document doc) throws Exception {
		NodeList transList = doc.getElementsByTagName("TRADES");
		if (transList == null || transList.getLength() == 0) {
			//throw new Exception("Dsr XML �ļ���δ���彻�ף�");
			return;
		} else {
			NodeList trans = transList.item(0).getChildNodes();
			for (int i = 0; i < trans.getLength(); i++) {
				Node tran = trans.item(i);
				if (isElementNode(tran)) {
					String tranId = getAttrValue(tran, "id", true);
					String dsrTranName = getAttrValue(tran, "dsrName", true);
					String dsrTranType = getAttrValue(tran, "type", false);
					if(dsrTranType==null) dsrTranType="0";
					dsrTemplete.put(tranId, genTran(tran));
					tranCodeMap.put(tranId, dsrTranName);
					tranTyoeMap.put(tranId, dsrTranType );
				}
			}

		}
	}
	
	private String getCycles(){
		Vector v = (Vector)modulTable.get("WORK");
		if(v == null||v.isEmpty()) return null;
		for(int i = 0;i<v.size();i++){
			DsrDataElementType type = (DsrDataElementType)v.elementAt(i);
			if(type.getDataName().equals("ROW_REQ")){
				return type.getDataValue();
			}
		}
		return null;
	}
	
	private Vector genTran(Node aNode) throws Exception {
		Vector vTran = new Vector(20);
		String tranId = getAttrValue(aNode, "id", true);
		String dsrTranName = getAttrValue(aNode, "dsrName", true);
		NodeList list = aNode.getChildNodes();
		String getOrPut = "";//���뻹����� add by wlx 20061130
		for (int i = 0; i < list.getLength(); i++) {
			Node item = list.item(i);
			if (isElementNode(item)) {
				String tagName = item.getNodeName();
				if (tagName.equals("module")) {
					String times = null;
					
					String alias = getAttrValue(item, "alias", false);
					String iCollName = getAttrValue(item, "iCollName", false);
					String newOrOld = getAttrValue(item, "newOrOld", false);//==================add by wlx 20061108 part cycle====================//
					if(newOrOld == null || newOrOld.length() == 0){
						newOrOld = "";//==================add by wlx 20061108 part cycle====================//
					}
					if(iCollName != null && iCollName.trim().length() != 0){
					//20050508 edit by chenj 
						times = getAttrValue(item, "cycle", false);
						//times = getCycles();
						if(times == null||times.trim().length() == 0){
							//times = getAttrValue(item, "cycle", false);
							times = getCycles();
						}
					//20050508 edit end by chenj	
					}
					int cycle = 1;
					if (times != null && times.trim().length() != 0) {
						try {
							cycle = Integer.parseInt(times);
						} catch (NumberFormatException e) {
							throw new Exception(
								"����["
									+ tranId
									+ "]������cycle��ֵ["
									+ cycle
									+ "]��Ч");
						}
					}
					String modulName = null;
					String refId = getAttrValue(item, "refid", false);
					Vector vTmp = null;
					Hashtable ht_module = new Hashtable();//add by wlx 20061108 part cycle
					int beginOfCycle = 0;//add by wlx 20061108 ��ʼѭ��λ��
					int numOfCycle = 0;//add by wlx 20061108 ѭ�����ڰ����ĸ���������
					if (refId != null) {
						modulName = refId;
						vTmp = (Vector) modulTable.get(refId);
					} else {
						modulName = getAttrValue(item, "id", true);
						ht_module = getModulItems(item,newOrOld,cycle);//===update by wlx 20061108 part cycle======//
						vTmp = (Vector)ht_module.get("vItems");//===update by wlx 20061108 part cycle======//
						beginOfCycle = Integer.parseInt((String)ht_module.get("beginOfCycle"));//===update by wlx 20061108 part cycle======//
						numOfCycle = Integer.parseInt((String)ht_module.get("numOfCycle"));//===update by wlx 20061108 part cycle======//
					}
					
					Vector vModul = new Vector(10);
					int iCount = 0;
					int oCount = 0;
					
					
					//for (int j = 0; j < cycle; j++) {//update by wlx 20061108 part cycle
						for (int k = 0; k < vTmp.size(); k++) {
							DsrDataElementType type = (DsrDataElementType)vTmp.elementAt(k);
							int ioType = type.getIOType();
							if( ioType == io_i){
								iCount++;
								getOrPut="0";//add by wlx 20061201
							}else if(ioType == io_o){
								oCount++;
								getOrPut="1";//add by wlx 20061201
							}else if(ioType == io_io){
								iCount++;
								oCount++;
								getOrPut="2";//add by wlx 20061201
							}else {
								throw new Exception(
								"����["
									+ tranId
									+ "]��������["
									+ type.getDataName()
									+ "]���󣬲�֪����ioType["+ioType+"]");
							}
							vModul.add(type.clone());
							
						}
					//}
					
					
					String[] aTmp = new String[9];
					aTmp[0] = modulName;
					//aTmp[1] = String.valueOf(iCount/cycle);
					//aTmp[2] = String.valueOf(oCount/cycle);
					aTmp[1] = String.valueOf(beginOfCycle);//===ѭ����ʼλ��update by wlx 20061108 part cycle===/////
					aTmp[2] = String.valueOf(numOfCycle);//===��ѭ�����ֶθ���update by wlx 20061108 part cycle===/////					
					aTmp[3] = String.valueOf(iCount);
					aTmp[4] = String.valueOf(oCount);
					aTmp[5] = alias;
					aTmp[6] = iCollName;
					aTmp[7] = String.valueOf(cycle);//add by wlx 20061108 ѭ������,Ĭ��Ϊ1��
					if(iCollName != null){
						aTmp[8] = getOrPut;//add by wlx 20061108 ѭ������,Ĭ��Ϊ1��
					}else{
						aTmp[8] = "0";//add by wlx 20061108 ѭ������,Ĭ��Ϊ1��
					}
					vModul.insertElementAt(aTmp,0); 
					vTran.add(vModul);		
				} 

			}
		}
		return vTran;
	}

	public void initialization() throws Exception {
			String fileName =
				(String) CmisConstance.getParameterSettings().get("dsrXMLFileName");
		String fileArea =
			(String) CmisConstance.getParameterSettings().get("dsrXMLFileArea");//add by wlx 20061106���������ļ��е�area�ڵ�
				String poolFile =
				(String) CmisConstance.getParameterSettings().get("dsrPoolFileName");
			if (fileName == null || fileName.trim().length() == 0) {
				throw new Exception("��ȷ��DSR XML �ļ����������Ƿ���ȷ");
			}
		if (fileArea == null || fileArea.trim().length() == 0) {//add by wlx 20061106���������ļ��е�area�ڵ�
			throw new Exception("��ȷ��DSR Area XML �ļ����������Ƿ���ȷ");
		}
			if (poolFile == null || poolFile.trim().length() == 0) {
				throw new Exception("��ȷ��DSR Pool XML �ļ����������Ƿ���ȷ");
			}
			String rootPath =
				(String) CmisConstance.getParameterSettings().get("fileRootPath");
			if (rootPath.endsWith(System.getProperty("file.separator"))){
				fileName = rootPath + fileName;
				fileArea = rootPath + fileArea;//add by wlx 20061106���������ļ��е�area�ڵ�
				poolFile = rootPath + poolFile;
			}else{
				fileName =
					rootPath + System.getProperty("file.separator") + fileName;
				fileArea =
					rootPath + System.getProperty("file.separator") + fileArea;//add by wlx 20061106���������ļ��е�area�ڵ�
				poolFile = rootPath +System.getProperty("file.separator") + poolFile;	
				}
		Document doc = loadXMLFile(fileName);
		Document docArea = loadXMLFile(fileArea);//add by wlx 20061106���������ļ��е�area�ڵ�
		try{
			ConnectionPoolDriver.initialize("file:///"+poolFile);
		}catch(Exception ee){
			System.out.println("DSR ͨ�ŷ����ʼ��ʧ�ܣ�����Ӧ�÷�������DSR�������������Ƿ�����,��DSR�������Ƿ��������������Է���:telnet DSR������IP��ַ DSR����˿ںš�\n������Ϣ��"+ee.getMessage());
		}
		genTemplate(doc);
		getAreaMapTable(docArea);//add by wlx 20061106���������ļ��е�area�ڵ�
		return;
	}

	public int error(
		String fileName,
		int lineNo,
		int charOffset,
		Object key,
		String msg) {
		System.out.println(
			key.toString() + "Error in line[ " + lineNo + "] : " + msg);
		return 0;
	}
}
