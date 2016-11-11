package com.wx.util;


import java.io.BufferedReader;




import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.apache.commons.httpclient.HttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;





//import com.ctc.wstx.util.StringUtil;
//import com.ibm.wsdl.util.StringUtils;
import com.wx.service.TokenService;
import com.wx.util.ehcache.EhcacheUtil;
import com.wx.util.wechat.MyX509TrustManager;
import com.wx.util.wechat.resp.Article;
import com.wx.domain.WxToken;
import com.wx.BaseAction;


/**
 * 通用工具类
 * 
 */
public class CommonUtil extends BaseAction {
	private static Logger log = Logger.getLogger(CommonUtil.class);
	private static final long serialVersionUID = 7484819000898944143L;
	@Autowired
	 private TokenService tokenService;
/*
	public final static String token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=APPID&corpsecret=APPSECRET";

	public final static String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}";

	public final static String sendMsgUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
	

	public final static String getUserInfoUrlAuthen = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENTID";

	public final static String creatUserUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
*/
	/**
	 * 发送https请求
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);

			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {

				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			HttpClient httpClient = new HttpClient();

		
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static WxToken getToken(String appid, String appsecret, WxToken token) {
		String token_url=wxBo.findPropertyValueByKey("token_url");
		//从数据库中获取token
		long createTime = token.getCreateTime().getTime();
		long currentTime = new Date().getTime();
		long expireIn=token.getExpiresIn();
		if(createTime+expireIn-30l>currentTime){
			return token;
		}
		
		//String requestUrl = CommonDef.token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);

		// 发起GET请求获取凭证
	
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				//token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
				token.setCreateTime(new Timestamp(new Date().getTime()));
				wxBo.tokenUpdate(token);
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}");
			}
		}
	
		return token;
	}
	
	/**
	 * 从request对象中获取
	 * @return
	 * @throws IOException
	 */
	public  static String getPostData(HttpServletRequest request) throws IOException{
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder postData = new StringBuilder();
        while((line = br.readLine())!=null){
            postData.append(line);
    }
       return postData.toString();
	}
	
	/**  
	 * 解析xml字符串成List<Map>  
	 *   
	 * @param String 
	 * @return List  
	 */  
	public static HashMap<String,String> parseXmlString(String xmlDoc) {   
	    // 创建一个新的字符串   
	    StringReader xmlString = new StringReader(xmlDoc);   
	    // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入   
	    InputSource source = new InputSource(xmlString);   
	    // 创建一个新的SAXBuilder   
	    SAXBuilder saxb = new SAXBuilder();   
	  
	    HashMap<String,String> result = new HashMap<String,String>();   
	    try {   
	       
	        // 通过输入源构造一个Document   
	        org.jdom.Document doc = saxb.build(source);   
	        // 取的根元素   
	        org.jdom.Element root = doc.getRootElement();   
	
	        // 得到根元素所有子元素的集合   
	        List node = root.getChildren();   
	        org.jdom.Element et = null;   
	        for (int i = 0; i < node.size(); i++) {   
	            et = (org.jdom.Element) node.get(i);// 循环依次得到子元素   
	           
	           
	            result.put(et.getName(), et.getText()); // 装入到Map中   
  
	        }   
	    } catch (JDOMException e) {   
	        e.printStackTrace();   
	    } catch (IOException e) {   
	        e.printStackTrace();   
	    }   
	    return result;   
	}  
	
	
	/**
	 * URL编码（utf-8）
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 组装文本客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param content 文本消息内容
	 * @param agentid 应用的APPID,在管理端可查询
	 * @return
	 */
	public static String makeTextMessage(String openId, String agentid,String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"agentid\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId,agentid, content);
	}

	/**
	 * 组装文本客服消息new
	 * 
	 * @param openId 消息发送对象
	 * @param content 文本消息内容
	 * @param agentid 应用的APPID,在管理端可查询
	 * @return
	 */
	public static String newMakeTextMessage(String openId, String agentid,String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId,content,agentid);
	}
	
	/**
	 * 组装群发消息message
	 * 
	 * @param group_id 群发组ID
	 * @param mediaId 消息ID
	 * @return
	 */
	public static String newMakeManyMessage(String groupId, String mediaId) {
String jsonMsg = "{\"filter\":{\"is_to_all\":false,\"group_id\":\"%s\"},\"text\":{\"content\":\"%s\"}, \"msgtype\":\"text\"}";
		return String.format(jsonMsg, groupId,mediaId);
	}
	
	
	/**
	 * 组装图片客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param mediaId 媒体文件id
	 * @return
	 */
	public static String makeImageMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}
	/**
	 * 组装图文客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param articleList 图文消息列表
	 * @return
	 */
	public static String makeNewsMessage(String openId, String agentid,List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"agentid\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg,openId,agentid, JSONArray.fromObject(articleList).toString().replaceAll("\"", "\\\""));
		// 将jsonMsg中的picUrl替换为picurl
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}
	
	/**
	 * 发送客服消息
	 * 
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return true | false
	 */
	public static boolean sendMessage(String accessToken, String jsonMsg) {
	
		boolean result = false;
		// 拼接请求地址发送客服消息
//		String requestUrl = CommonDef.sendMsgUrl.replace("ACCESS_TOKEN", accessToken);
//		String requestUrl = EhcacheUtil.get("sendMsgUrl").toString().replace("ACCESS_TOKEN", accessToken);

		// 拼接请求地址二维码
		String requestUrl = EhcacheUtil.get("qrcodeCreateUrl").toString().replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("客服消息发送成功 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
			} else {
				log.error("客服消息发送失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
			}
		}

		return result;
	}
	
	
	/**
	 * 发送消息到服务器
	 * @param 发送请求地址
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return true | false
	 */
	public static boolean sendToServer(String requestUrl,String accessToken, String jsonMsg) {
	
		boolean result = false;
		// 拼接请求地址
		//String requestUrl = CommonDef.sendMsgUrl.replace("ACCESS_TOKEN", accessToken);
		 requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

		
		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("消息发送成功 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
			} else {
				log.error("消息发送失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
			}
		}

		return result;
	}
	
	/**
	 * 群发消息
	 * 
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return true | false
	 */
	public static boolean sendManyMessage(String accessToken, String jsonMsg) {
	
		boolean result = false;
		// 拼接请求地址
		//String requestUrl = CommonDef.sendMsgUrl.replace("ACCESS_TOKEN", accessToken);
		String requestUrl = EhcacheUtil.get("sendMsgUrl").toString().replace("ACCESS_TOKEN", accessToken);

		
		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("客服消息发送成功 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
			} else {
				log.error("客服消息发送失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
			}
		}

		return result;
	}
	
	/**
	 * 创建用户接口
	 * 
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json格式的用户信息（包括用户ID及联系方式内容）
	 * @return true | false
	 */
	public static boolean createWechatUser(String accessToken, String jsonMsg) {
	
		boolean result = false;
		// 拼接请求地址
		//String requestUrl = CommonDef.creatUserUrl.replace("ACCESS_TOKEN", accessToken);
		String requestUrl = EhcacheUtil.get("creatUserUrl").toString().replace("ACCESS_TOKEN", accessToken);

		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("创建微信通讯录用户成功 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
			} else {
				log.error("创建微信通讯录用户失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
			}
		}

		return result;
	}
	/**
	 * 二维码创建
	 * @param accessToken
	 * @param jsonMsg
	 * @return
	 */
	public static String qrcodeCreate(String accessToken, String jsonMsg) {
		String url = "";
		// 拼接请求地址二维码
		String requestUrl = EhcacheUtil.get("qrcodeCreateUrl").toString().replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		
		if (null != jsonObject) {
			if(jsonObject.has("errcode")){
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				if (0 == errorCode) {
					log.info("客服消息发送成功 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
				} else {
					log.error("客服消息发送失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
				}
			}else if(jsonObject.has("url")){
				url=jsonObject.getString("url");
			}
		}
		return url;
	}
	
	/**
	 * 测试演示
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//IExpressService expressService = (IExpressService)com.ai.zhsq.core.util.BeanFactoryUtil.getContext().getBean("expressService");

//		Token tk = getToken(ConfigInfo.getValue("corpId"), ConfigInfo.getValue("corpSecret"),expressService);
		//Token tk = getToken("wxb84dacc6bcfa9ebe", "d064b4e191a90fd3a14da1cf64bd3749",new TokenServiceImpl());
		//WxToken tk = getToken("wxef7c4035759df877", "cEPIliQM64OK3SlW9aWnGT351VA5s87IHbjnXW2HXbR",new TokenServiceImpl());
		//String agentid = "0";
		//发送文本消息
		//String textMessage = newMakeTextMessage("oJ8CXsxh8vF3EUGW6x4PYw-eSMtc",agentid,"你好呀，小伙伴！");
		//sendMessage(tk.getAccessToken(),textMessage);
		//发送图文消息
//		 List<Article> list = new ArrayList<Article>();
//		 Article article = new Article();
//		 article.setTitle("智慧社区二维码图片");
//		 article.setPicUrl("http://121.40.208.244:8080/PUBLIC-DEV/uploads/1_1413543031339.jpg");
//		 article.setUrl("http://121.40.208.244:8080/PUBLIC-DEV/uploads/1_1413543031339.jpg");
//		 article.setDescription("请您到物业管理处，凭此二维码领取快递哟！");
//		 list.add(article);
//		 String newsMessage = makeNewsMessage("jrhy002",agentid,list);
//		 System.out.println(newsMessage);
	  //  sendMessage(tk.getAccessToken(),newsMessage);

		
	}
	
}