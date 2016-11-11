package com.kybaby.kyinterface.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.kybaby.util.EncryptUtil;


public class HttpClientUtil {

	//private static Log log = LogFactory.getLog(HttpClientUtil.class);
	
	private final static String url = "http://develop.jkscw.com.cn/bsky/service";
	
	private final static String authentication = "kybb"+"kybb123456"+"kybbSMFWRegisterInfo";
	
	private static HttpClientUtil instance;
	public static HttpClientUtil getInstance(){
		if(instance==null)
			instance = new HttpClientUtil();
		return instance;
	}
	
	private static CloseableHttpClient getHttpClient(){
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(100);
		connManager.setDefaultMaxPerRoute(20);

		RequestConfig globalConfig = RequestConfig.custom().setConnectionRequestTimeout(5000) // 设置从connectManager获取Connection,超时时间，单位毫秒
				.setConnectTimeout(5000) // 设置连接超时时间，单位毫秒
				.setSocketTimeout(5000) // 请求获取数据的超时时间，单位毫秒
				.build();

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)// 设置全局请求配置
				.setConnectionManager(connManager)// 设置连接管理器
				.build();
		return httpClient;
	}
	
	private static HttpPost getHttpPost(String url){
		HttpPost httppost = new HttpPost(url);
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000) // 设置从connectManager获取Connection,超时时间，单位毫秒
				.setConnectTimeout(5000) // 设置连接超时时间，单位毫秒
				.setSocketTimeout(5000) // 请求获取数据的超时时间，单位毫秒
				.build();
		httppost.setConfig(config);
		return httppost;
	}
	
	public static void main(String[] args) throws Exception {
		//System.out.println(inocalutionOrderEdit("1634e0631c7248538bb9a93dc00680b4", "3"));
		System.out.println(SMFWEdit("840385f92a8047258011fbe8c9a0dca9","167"));
	}
	/**
	 * 修改计免订单状态
	 * @param orderId 巴蜀快医订单id
	 * @param status 订单状态
	 * @return
	 * @throws Exception
	 */
	public static String inocalutionOrderEdit(String orderId,String status){
		long time = System.currentTimeMillis();
		String sig =  EncryptUtil.getMD5Str(authentication+time);
		//修改计免订单状态
		String postJson ="{\"head\":"
				+ "{\"accountid\":\"kybb\","
				+ "\"code\":\"kybbJMEnd\","//方法名
				+ "\"timestamp\":\""+time+"\","
				+ "\"sig\":\""+sig+"\"},"
				+ "\"content\":"
				+ "{\"JM_REGISTER_ID\":\""+orderId+"\","//订单id
				+ "\"STATE\":\""+status+"\"}"//状态
				+ "}";
		
		CloseableHttpClient httpClient = getHttpClient();
		HttpPost httpPost = getHttpPost(url);
		
		StringEntity req_Entity = new StringEntity(postJson,"UTF-8");
		httpPost.setEntity(req_Entity);
		// 执行客户端请求
		CloseableHttpResponse response = null;
		String reponseStr = "";
		try {
			response = httpClient.execute(httpPost);
			
			reponseStr = getResponseContent(response);
			
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}finally{
			releaseConnection(httpClient, httpPost, response);
		}
		
		return reponseStr;
	}
	
	/**
	 * 上门服务修改订单数据u
	 * @param orderId 巴蜀快医订单id
	 * @param doctorId 医生id
	 * @return
	 * @throws Exception
	 */
	public static String SMFWEdit(String orderId,String doctorId){
		long time = System.currentTimeMillis();
		String sig =  EncryptUtil.getMD5Str(authentication+time);
		//上门服务修改订单数据u
		String postJson ="{\"head\":"
				+ "{\"accountid\":\"kybb\","
				+ "\"code\":\"kybbSMFWRegisterInfo\","//方法名
				+ "\"timestamp\":\""+time+"\","
				+ "\"sig\":\""+sig+"\"},"
				+ "\"content\":"
				+ "{\"JM_REGISTER_ID\":\""+orderId+"\","//订单id
				+ "\"BSKY_DOCTOR_ID\":\""+doctorId+"\"}"//医生id
				+ "}";
		
		CloseableHttpClient httpClient = getHttpClient();
		HttpPost httpPost = getHttpPost(url);
		
		StringEntity req_Entity = new StringEntity(postJson,"UTF-8");
		httpPost.setEntity(req_Entity);
		// 执行客户端请求
		CloseableHttpResponse response = null;
		String reponseStr = "";
		try {
			response = httpClient.execute(httpPost);
			
			reponseStr = getResponseContent(response);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}finally{
			releaseConnection(httpClient, httpPost, response);
		}
		return reponseStr;
	}
	
	public static void releaseConnection(CloseableHttpClient httpClient,HttpPost httpPost,CloseableHttpResponse response){
		if(httpClient!=null){
			try {
				httpClient.close();
				httpClient.getConnectionManager().shutdown();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		if(httpPost!=null){
			httpPost.releaseConnection();
		}
		if(response!=null){
			try {
				response.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	private static String getResponseContent(CloseableHttpResponse response) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		String str = "";
		try {
			HttpEntity res_Entity = response.getEntity();
			res_Entity = new BufferedHttpEntity(res_Entity);
			InputStream in = res_Entity.getContent();
			br = new BufferedReader(new InputStreamReader(in));

			if ((str = br.readLine()) != null) {
				sb.append(str);
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		return sb.toString();
	}
}
