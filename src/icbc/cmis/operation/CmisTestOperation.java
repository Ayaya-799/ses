package icbc.cmis.operation;

import java.util.Hashtable;
import icbc.cmis.base.*;
/**
 * Insert the type's description here.
 * Creation date: (2001-12-25 14:55:07)
 * @author: Administrator
 */
public class CmisTestOperation extends CmisOperation {
/**
 * CmisTestOperation constructor comment.
 */
public CmisTestOperation() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (2001-12-25 14:55:07)
 */
public void execute() throws Exception{
	try{
		//ҵ���߼�
		System.out.println("д���ҵ���߼�����...");
		IndexedDataCollection family = new IndexedDataCollection();
		KeyedDataCollection child = new KeyedDataCollection();
		DataElement name = new DataElement();
		name.setName("userName");
		name.setValue("����");
		child.addElement(name);

		DataElement age = new DataElement();
		age.setName("userAge");
		age.setValue("18");
		child.addElement(age);

		DataElement job = new DataElement();
		job.setName("userWork");
		job.setValue("student");
		child.addElement(job);
		//etc....,�������ӹ��ڴ��˵���������
		family.addElement(child);

		KeyedDataCollection father = new KeyedDataCollection();
		DataElement name1 = new DataElement();
		name1.setName("userName");
		name1.setValue("��������");
		father.addElement(name1);

		DataElement age1 = new DataElement();
		age1.setName("userAge");
		age1.setValue("48");
		father.addElement(age1);

		DataElement job1 = new DataElement();
		job1.setName("userWork");
		job1.setValue("ҽ��");
		father.addElement(job1);
		//etc....,�������ӹ��ڴ��˵���������
		
		family.addElement(father);

		//display family info

		for(int i = 0; i < family.getSize(); i++){
			KeyedDataCollection aMember = (KeyedDataCollection)family.getElement(i);
			System.out.println("����:"+(String)aMember.getValueAt("userName"));
			System.out.println("����:"+(String)aMember.getValueAt("userAge"));
			System.out.println("ְҵ:"+(String)aMember.getValueAt("userWork"));
		}

		
		//ҵ�������
		
		addSessionData("familyInfo",family);//�������������Ҫ����һ��������ʹ��,���浽session
		//�������ݲ�������ʱ����session���������
		removeSessionData("familyInfo");

		//����ж�̬����Ҫ����
		addDataField("opDynData",family);
		setOperationDataToSession();

		//���÷���ҳ��
		setReplyPage("//icbc//cmis//frame.jsp");
		
		
	}catch(Exception e){
		setReplyPage("//icbc//cmis//error.jsp");
	}
	
	
}
}
