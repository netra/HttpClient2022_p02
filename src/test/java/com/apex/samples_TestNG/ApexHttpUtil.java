package com.apex.samples_TestNG;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ApexHttpUtil {
	
	public static HttpResponse sendAndReceiveGETMessage(String url) throws IOException, ClientProtocolException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		// step2 : create the request message
		HttpGet request = new HttpGet(url);
		// step3 : send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public static HttpResponse sendAndReceivePOSTMessage(String url, String message) throws IOException, ClientProtocolException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		// step2 : create the request message
		StringEntity entity = new StringEntity(message);
		HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		// step3 : send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public static HttpResponse sendAndReceivePUTMessage(String url, String message) throws IOException, ClientProtocolException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		// step2 : create the request message
		StringEntity entity = new StringEntity(message);
		HttpPut request = new HttpPut(url);
		request.setEntity(entity);
		// step3 : send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public static HttpResponse sendAndReceiveDELETEMessage(String url) throws IOException, ClientProtocolException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		// step2 : create the request message
		HttpDelete request = new HttpDelete(url);
		// step3 : send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}

	public static String getResponseString(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String result = "";
		if (entity != null) {
			// return it as a String
			result = EntityUtils.toString(entity);
			System.out.println(result);
		}
		return result;
	}

}
