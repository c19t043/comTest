<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.tenpay.RequestHandler" %>
<%@ page import="com.tenpay.ResponseHandler" %>   
<%@ page import="com.tenpay.client.ClientResponseHandler" %>    
<%@ page import="com.tenpay.client.TenpayHttpClient" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.util.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="org.dom4j.Document"%>
<%@ page import="org.dom4j.DocumentHelper"%>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.io.OutputStreamWriter" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONException" %>
<%@ page import="net.sf.json.JSONObject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//---------------------------------------------------------
//�Ƹ�֧ͨ��֪ͨ����̨֪ͨ��ʾ�����̻����մ��ĵ����п�������
//---------------------------------------------------------

BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));  
String line = null;  
StringBuilder sb = new StringBuilder();  
while ((line = br.readLine()) != null) {  
    sb.append(line);  
}  
//�����sb���д���
/*
���Ȱ���<result_code>�����ַ������
*/
String[] resultCodeArr=sb.toString().split("result_code");
String resultCode=resultCodeArr[1];

/*
Ȼ����<return_code>�����ַ��� ���
*/
String[] returnCodeArr=sb.toString().split("return_code");
String returnCode=returnCodeArr[1];

if((resultCode!=null&&resultCode.contains("SUCCESS"))&&(returnCode!=null&&returnCode.contains("SUCCESS"))){
	//��ʾ�û�֧���ɹ�����ȡ��������ţ��Զ������к����Ĵ���
	String[] orderNumArr=sb.toString().split("out_trade_no");
	String orderNum=orderNumArr[1];
	orderNum=orderNum.substring(10);
	int orderNumLength=orderNum.length();
	orderNum=orderNum.substring(0,orderNumLength-5);
	orderNum=orderNum.split("_")[0];
	System.out.println("orderNum=="+orderNum);
	String turnUrl="http://kybabydev.yishangkeji.cn/main/orderManage.action?action=paySuccess&orderNum="+orderNum;
	URL turnTo=new URL(turnUrl);
	URLConnection connection=turnTo.openConnection();
	connection.setDoOutput(true);
	OutputStreamWriter coon=new OutputStreamWriter(connection.getOutputStream(),"utf-8");
	//coon.write(params.toString());//post�Ĺؼ����ڣ�
	coon.flush();
	coon.close();
	String temp="";
	String result="";
	InputStream outStream=connection.getInputStream();
	BufferedReader reader=new BufferedReader(new InputStreamReader(outStream));
	while((temp=reader.readLine())!=null){
		result+=temp;
	}
	JSONObject obj = JSONObject.fromObject(result);
	String mes=obj.get("mes").toString();
	System.out.println("*****************notify_url:mes= "+mes);
	if(mes.equals("�����ɹ�")){
		//����ɹ�֮�󣬿�ʼ���û�����֪ͨ����
		String userMessage="http://kybabydev.yishangkeji.cn/main/sendMessage.action?action=toUser&sendWords=ԤԼ&orderNum="+orderNum;
		URL usrmsgUrl=new URL(userMessage);
		URLConnection usrConnection=usrmsgUrl.openConnection();
		usrConnection.setDoOutput(true);
		OutputStreamWriter usrCoon=new OutputStreamWriter(usrConnection.getOutputStream(),"utf-8");
		//usrCoon.write(params.toString());
		usrCoon.flush();
		usrCoon.close();
		String usrTemp="";
		String usrResult="";
		InputStream usrOutStream=usrConnection.getInputStream();
		BufferedReader usrReader=new BufferedReader(new InputStreamReader(usrOutStream));
		while((usrTemp=usrReader.readLine())!=null){
			usrResult+=usrTemp;
		}
		JSONObject usrObj=JSONObject.fromObject(usrResult);
		String usrMes=usrObj.get("mes").toString();
		System.out.println("*****************notify_url:usrMes= "+usrMes);
		
		//����ɹ�֮�󣬿�ʼ��ҽ�����Ͷ���
		
		String docMessage="http://kybabydev.yishangkeji.cn/main/sendMessage.action?action=toDoctor&sendWords=ԤԼ&orderNum="+orderNum;
		URL docmsgUrl=new URL(docMessage);
		URLConnection docConnection=docmsgUrl.openConnection();
		docConnection.setDoOutput(true);
		OutputStreamWriter docCoon=new OutputStreamWriter(docConnection.getOutputStream(),"utf-8");
		//docCoon.write(params.toString());
		docCoon.flush();
		docCoon.close();
		String docTemp="";
		String docResult="";
		InputStream docOutStream=docConnection.getInputStream();
		BufferedReader docReader=new BufferedReader(new InputStreamReader(docOutStream));
		while((docTemp=docReader.readLine())!=null){
			docResult+=docTemp;
		}
		JSONObject docObj=JSONObject.fromObject(docResult);
		String docMes=docObj.get("mes").toString();
		System.out.println("*****************notify_url:docMes= "+docMes);
	}
	ResponseHandler resHandler = new ResponseHandler(request, response);
	resHandler.sendToCFT("Success");
}


//�̻���
/* String partner = "1279175401";

//��Կ
String key = "fmfkeaflmfelmflalefmalmfeklfmale";

//����֧��Ӧ�����
ResponseHandler resHandler = new ResponseHandler(request, response);
resHandler.setKey(key);
System.out.println("++++++++++++++++++++++++++++++++++++++1");
BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));  
String line = null;  
StringBuilder sb = new StringBuilder();  
while ((line = br.readLine()) != null) {  
    sb.append(line);  
}  
System.out.println("+++++++++++++++++++++++++++sb:"+sb); 
//�ж�ǩ��
if(resHandler.isTenpaySign()) {
	
	//֪ͨid
	String notify_id = resHandler.getParameter("notify_id");
	System.out.println("++++++++++++++++++++++++++++++++++++++notify_id:"+notify_id);
	//�����������
	RequestHandler queryReq = new RequestHandler(null, null);
	//ͨ�Ŷ���
	TenpayHttpClient httpClient = new TenpayHttpClient();
	//Ӧ�����
	ClientResponseHandler queryRes = new ClientResponseHandler();
	
	//ͨ��֪ͨID��ѯ��ȷ��֪ͨ�����Ƹ�ͨ
	queryReq.init();
	queryReq.setKey(key);
	queryReq.setGateUrl("https://gw.tenpay.com/gateway/verifynotifyid.xml");
	queryReq.setParameter("partner", partner);
	queryReq.setParameter("notify_id", notify_id);
	
	//ͨ�Ŷ���
	httpClient.setTimeOut(5);
	//������������
	httpClient.setReqContent(queryReq.getRequestURL());
	System.out.println("queryReq:" + queryReq.getRequestURL());
	//��̨����
	if(httpClient.call()) {
		//���ý������
		queryRes.setContent(httpClient.getResContent());
		System.out.println("queryRes:" + httpClient.getResContent());
		queryRes.setKey(key);
			
			
		//��ȡ���ز���
		String retcode = queryRes.getParameter("retcode");
		String trade_state = queryRes.getParameter("trade_state");
	
		String trade_mode = queryRes.getParameter("trade_mode");
			
		//�ж�ǩ�������
		if(queryRes.isTenpaySign()&& "0".equals(retcode) && "0".equals(trade_state) && "1".equals(trade_mode)) {
			System.out.println("������ѯ�ɹ�");
			//ȡ���������ҵ����				
			System.out.println("out_trade_no:" + queryRes.getParameter("out_trade_no")+
					" transaction_id:" + queryRes.getParameter("transaction_id"));
			System.out.println("trade_state:" + queryRes.getParameter("trade_state")+
					" total_fee:" + queryRes.getParameter("total_fee"));
		        //�����ʹ���ۿ�ȯ��discount��ֵ��total_fee+discount=ԭ�����total_fee
			System.out.println("discount:" + queryRes.getParameter("discount")+
					" time_end:" + queryRes.getParameter("time_end"));
			//------------------------------
			//����ҵ��ʼ
			//------------------------------
			
			//�������ݿ��߼�
			//ע�⽻�׵���Ҫ�ظ�����
			//ע���жϷ��ؽ��
			
			//------------------------------
			//����ҵ�����
			//------------------------------
			resHandler.sendToCFT("Success");
		}
		else{
				//����ʱ�����ؽ��δǩ������¼retcode��retmsg��ʧ�����顣
				System.out.println("��ѯ��֤ǩ��ʧ�ܻ�ҵ�����");
				System.out.println("retcode:" + queryRes.getParameter("retcode")+
						" retmsg:" + queryRes.getParameter("retmsg"));
		}
	
	} else {

		System.out.println("��̨����ͨ��ʧ��");
			
		System.out.println(httpClient.getResponseCode());
		System.out.println(httpClient.getErrInfo());
		//�п�����Ϊ����ԭ�������Ѿ�������δ�յ�Ӧ��
	}
}
else{
	System.out.println("֪ͨǩ����֤ʧ��");
} */

%>

