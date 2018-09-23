package com.yunfenghui.common;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpTemplate {
	private static MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
	private static HttpClient httpClient = new HttpClient(httpConnectionManager);
	static {
		// 每主机最大连接数和总共最大连接数，通过hosfConfiguration设置host来区分每个主机
		httpClient.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(8);
		httpClient.getHttpConnectionManager().getParams().setMaxTotalConnections(48);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(3000);
		httpClient.getHttpConnectionManager().getParams().setTcpNoDelay(true);
		httpClient.getHttpConnectionManager().getParams().setLinger(1000);
		// 失败的情况下会进行1次尝试,成功之后不会再尝试
		httpClient.getHttpConnectionManager().getParams().setParameter(
				HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(1, false));
	}

	public static String post(String url, Map<String, String> params) throws IOException {
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		if (params != null && !params.isEmpty()) {
			NameValuePair[] pairs = new NameValuePair[params.size()];
			int i = 0;
			for (Entry<String, String> entry : params.entrySet()) {
				pairs[i++] = new NameValuePair(entry.getKey(), entry.getValue());
			}
			method.setRequestBody(pairs);
		}
		try {
			httpClient.executeMethod(method);
			return method.getResponseBodyAsString();
		} finally {
			method.releaseConnection();
		}
	}

}
