package com.apex.get;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.apex.samples_TestNG.ApexHttpUtil;

public class UserAPI_Test_GET {
    
	static final String Base_URL = "https://reqres.in/api/users/";
	
	private HttpResponse sendAndReceiveGETMessage(String url) throws IOException, ClientProtocolException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		// step2 : create the request message
		HttpGet request = new HttpGet(url);
		// step3 : send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	private String getResponseString(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String result="";
		if (entity != null) {
			// return it as a String
		    result = EntityUtils.toString(entity);
			System.out.println(result);
		}
		return result;

	}
	
	@Test
	public void testWithCorrectUserID() throws ClientProtocolException, IOException {
		String url = Base_URL+"2";
		
		HttpResponse response = sendAndReceiveGETMessage(url);

		String result = getResponseString(response);
		System.out.println(result);
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	}

	

	@Test
	public void testWithCharacter() throws ClientProtocolException, IOException {
         
		String url = Base_URL+"abc";
		
		HttpResponse response = sendAndReceiveGETMessage(url);

		String result = getResponseString(response);
		
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	   // Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "NOT FOUND");
	}

	@Test //ApexHttpUtil.
	public void testWithLongNumber() throws ClientProtocolException, IOException {
		
        String url = Base_URL+"22223456";
		
		HttpResponse response = sendAndReceiveGETMessage(url);

		String result = getResponseString(response);
		
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		//Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "NOT FOUND");
	}

	@Test
	public void testWithLockedMember() throws ClientProtocolException, IOException {
		
        String url = Base_URL+"";
		
		HttpResponse response = sendAndReceiveGETMessage(url);

		String result = getResponseString(response);
		
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	}
}
