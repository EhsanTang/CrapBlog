package cn.crap.common.utils.http;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

/**
 * @author Lijiannan
 * @version 1.0
 * @description
 * @time 2016/7/25 15:24
 */
public class HttpUtil {

	/**
	 * 默认http请求配置
	 */
	private static RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000)
			.build();

	/**
	 * http get 请求
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String get(String url) throws Exception {
		return get(url, null, defaultRequestConfig, null);
	}

	/**
	 * @Description
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postJSON(String url, Map<String, String> params) throws Exception {
		return post(url, params, null, defaultRequestConfig, true);
	}
	
	/**
	 * @Description
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> params) throws Exception {
		return post(url, params, null, defaultRequestConfig, false);
	}

	/**
	 * @Description httpGet请求
	 * @param url
	 * @param config
	 * @param headers
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String get(String url,  Map<String, String> params, RequestConfig config, Map<String, String> headers) throws Exception {

		if (params != null && params.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (sb.length() != 0) {
					sb.append("&");
				}
				sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			}

			url = url + "?" + sb.toString();
		}
		HttpGet method = new HttpGet(url);
		if (config == null) {
			method.setConfig(defaultRequestConfig);
		} else {
			method.setConfig(config);
		}

		return getMethod(method, headers);
	}

	/**
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> params, Map<String, String> headers, RequestConfig config, boolean isJSON) throws Exception {
		HttpPost method = new HttpPost(url);
		String type = "application/x-www-form-urlencoded";
		if(isJSON){
			type = "application/json";
		}
		
		if (params != null && params.size() > 0) {
			if(isJSON){
				BasicHttpEntity entity = new BasicHttpEntity();
				String body = JSON.toJSONString(params);
				entity.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
				entity.setContentLength(body.getBytes("UTF-8").length);
			}else{
				List<NameValuePair> paires = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					paires.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				HttpEntity entity = new UrlEncodedFormEntity(paires, "utf-8");
				method.setEntity(entity);
			}
		}

		if (config == null) {
			method.setConfig(defaultRequestConfig);
		} else {
			method.setConfig(config);
		}
		return postMethod(method, headers,type);
	}

	/**
	 * @Description 
	 * @param method
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	private static String postMethod(HttpUriRequest method, Map<String, String> headers, String type) throws Exception {
		HttpClient client = HttpClients.createDefault();
		method.setHeader("Accept", type);
		method.setHeader("Content-Type", type+";charset=utf-8");
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				method.setHeader(entry.getKey(), entry.getValue());
			}
		}

		HttpResponse response = client.execute(method);
		int status = response.getStatusLine().getStatusCode();
		if (status < 200 || status >= 300) {
			throw new ClientProtocolException("Path:" + method.getURI() + "-Unexpected response status: " + status);
		}
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity, "UTF-8");
		return body;
	}

	
	/**
	 * @Description 
	 * @param method
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	private static String getMethod(HttpUriRequest method, Map<String, String> headers) throws Exception {
		HttpClient client = HttpClients.createDefault();
		method.setHeader("charset", "utf-8");
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				method.setHeader(entry.getKey(), entry.getValue());	
			}
		}
		HttpResponse response = client.execute(method);
		int status = response.getStatusLine().getStatusCode();
		if (status < 200 || status >= 300) {
			throw new ClientProtocolException("Path:" + method.getURI() + "-Unexpected response status: " + status);
		}
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity, "UTF-8");
		return body;
	}
}
