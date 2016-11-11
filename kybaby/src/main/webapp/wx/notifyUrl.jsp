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
//财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
//---------------------------------------------------------

BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));  
String line = null;  
StringBuilder sb = new StringBuilder();  
while ((line = br.readLine()) != null) {  
    sb.append(line);  
}  
//下面对sb进行处理
/*
首先按照<result_code>进行字符串拆分
*/
String[] resultCodeArr=sb.toString().split("result_code");
String resultCode=resultCodeArr[1];

/*
然后按照<return_code>进行字符串 拆分
*/
String[] returnCodeArr=sb.toString().split("return_code");
String returnCode=returnCodeArr[1];

if((resultCode!=null&&resultCode.contains("SUCCESS"))&&(returnCode!=null&&returnCode.contains("SUCCESS"))){
	//表示用户支付成功，获取到订单编号，对订单进行后续的处理
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
	//coon.write(params.toString());//post的关键所在！
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
	if(mes.equals("操作成功")){
		//处理成功之后，开始向用户发送通知短信
		String userMessage="http://kybabydev.yishangkeji.cn/main/sendMessage.action?action=toUser&sendWords=预约&orderNum="+orderNum;
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
		
		//处理成功之后，开始向医生发送短信
		
		String docMessage="http://kybabydev.yishangkeji.cn/main/sendMessage.action?action=toDoctor&sendWords=预约&orderNum="+orderNum;
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


//商户号
/* String partner = "1279175401";

//密钥
String key = "fmfkeaflmfelmflalefmalmfeklfmale";

//创建支付应答对象
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
//判断签名
if(resHandler.isTenpaySign()) {
	
	//通知id
	String notify_id = resHandler.getParameter("notify_id");
	System.out.println("++++++++++++++++++++++++++++++++++++++notify_id:"+notify_id);
	//创建请求对象
	RequestHandler queryReq = new RequestHandler(null, null);
	//通信对象
	TenpayHttpClient httpClient = new TenpayHttpClient();
	//应答对象
	ClientResponseHandler queryRes = new ClientResponseHandler();
	
	//通过通知ID查询，确保通知来至财付通
	queryReq.init();
	queryReq.setKey(key);
	queryReq.setGateUrl("https://gw.tenpay.com/gateway/verifynotifyid.xml");
	queryReq.setParameter("partner", partner);
	queryReq.setParameter("notify_id", notify_id);
	
	//通信对象
	httpClient.setTimeOut(5);
	//设置请求内容
	httpClient.setReqContent(queryReq.getRequestURL());
	System.out.println("queryReq:" + queryReq.getRequestURL());
	//后台调用
	if(httpClient.call()) {
		//设置结果参数
		queryRes.setContent(httpClient.getResContent());
		System.out.println("queryRes:" + httpClient.getResContent());
		queryRes.setKey(key);
			
			
		//获取返回参数
		String retcode = queryRes.getParameter("retcode");
		String trade_state = queryRes.getParameter("trade_state");
	
		String trade_mode = queryRes.getParameter("trade_mode");
			
		//判断签名及结果
		if(queryRes.isTenpaySign()&& "0".equals(retcode) && "0".equals(trade_state) && "1".equals(trade_mode)) {
			System.out.println("订单查询成功");
			//取结果参数做业务处理				
			System.out.println("out_trade_no:" + queryRes.getParameter("out_trade_no")+
					" transaction_id:" + queryRes.getParameter("transaction_id"));
			System.out.println("trade_state:" + queryRes.getParameter("trade_state")+
					" total_fee:" + queryRes.getParameter("total_fee"));
		        //如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
			System.out.println("discount:" + queryRes.getParameter("discount")+
					" time_end:" + queryRes.getParameter("time_end"));
			//------------------------------
			//处理业务开始
			//------------------------------
			
			//处理数据库逻辑
			//注意交易单不要重复处理
			//注意判断返回金额
			
			//------------------------------
			//处理业务完毕
			//------------------------------
			resHandler.sendToCFT("Success");
		}
		else{
				//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
				System.out.println("查询验证签名失败或业务错误");
				System.out.println("retcode:" + queryRes.getParameter("retcode")+
						" retmsg:" + queryRes.getParameter("retmsg"));
		}
	
	} else {

		System.out.println("后台调用通信失败");
			
		System.out.println(httpClient.getResponseCode());
		System.out.println(httpClient.getErrInfo());
		//有可能因为网络原因，请求已经处理，但未收到应答。
	}
}
else{
	System.out.println("通知签名验证失败");
} */

%>

