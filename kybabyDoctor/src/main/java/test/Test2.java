package test;

import java.util.ArrayList;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Test2 {
	
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<String>();
		al.addAll(null);
	}
	
	public static void main1(String[] args) throws Exception{
		
/*		String url="http://www.zuidaima.com/";  
		Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));  
		URL serverUrl = new URL(url);  
		HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection(proxy); */
		
		
		HttpClient httpclient = new HttpClient();
		
		HttpMethod method = null;
		
		method = new PostMethod();
		
		//Header header = new Header();
		NameValuePair nameValuePair = null ;
			//nameValuePair = new NameValuePair(name,value);
		method.setRequestHeader((Header) nameValuePair);
		
		HttpMethodParams params = new HttpMethodParams();
		//params.setParameter(name, value);
		method.setParams(params);
		
		URI uri = null;
		String url = "";
		uri = new HttpURL(url);
		method.setURI(uri);
		
		httpclient.executeMethod(method);
		
	}
}
