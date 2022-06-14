package com.apex.samples_TestNG;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserAPI_Test_GET_TestNG extends ApexBaseAPITest implements UserAPIConstants{
	
	 
	
	
	// Status Code
	private static final String STATUS_MESSAGE_OK = "OK";
	
	// Status Message
	private static final int STATUS_CODE_200 = 200;
	static final String Base_URL = "https://reqres.in/api/users/";

	
	@Test 
	public void testWithCorrectUserID() throws ClientProtocolException, IOException {
		long time1 = System.currentTimeMillis();
		
		String url = Base_URL + "2";

		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMessage(url);

		String result = ApexHttpUtil.getResponseString(response);
		System.out.println(result);
        
		// basic validation
		performBasicValidations(response,STATUS_CODE_200,STATUS_MESSAGE_OK);
		
		// specific validation
		// status code, status message, tag, message data, tag repetion
		//string validation or we can use parsers
		Assert.assertTrue(result.contains("id"));
		Assert.assertTrue(result.contains("janet.weaver@reqres.in"));
		long time2 = System.currentTimeMillis();
		long time = time2 - time1;
		System.out.println("Time take for response : " + time);
		
	}
	
	@Test
	public void testWithCharacter() throws ClientProtocolException, IOException {
         
		String url = Base_URL+"abc";
		
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMessage(url);

		String result = ApexHttpUtil.getResponseString(response);
		
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	   // Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "NOT FOUND");
	}
	
	@Test //
	public void testWithLongNumber() throws ClientProtocolException, IOException {
		
        String url = Base_URL+"22223456";
		
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMessage(url);

		String result = ApexHttpUtil.getResponseString(response);
		
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		//Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "NOT FOUND");
	}

	@Test
	public void testWithLockedMember() throws ClientProtocolException, IOException {
		
        String url = Base_URL+"";
		
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMessage(url);

		String result = ApexHttpUtil.getResponseString(response);
		
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	}
	

	private void performBasicValidations(HttpResponse response, int expCode, String expMessage) {
		
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), expCode);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), expMessage);
	}

}
