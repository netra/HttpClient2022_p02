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

public class UserApi_GET {
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
		String result = "";
		if (entity != null) {
			// return it as a String
			result = EntityUtils.toString(entity);
			System.out.println(result);
		}
		return result;

	}

	@Test(priority=1)
	public void testWithCorrectUserID() throws ClientProtocolException, IOException {
		String url = Base_URL + "2";

		HttpResponse response = sendAndReceiveGETMessage(url);

		getResponseString(response);
		 
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	}

	@Test
	public void testWithNonNumber() throws ClientProtocolException, IOException {
		String url = Base_URL + "abc";

		HttpResponse response = sendAndReceiveGETMessage(url);

		//String result = getResponseString(response);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	}

	@Test(priority=2)
	public void testWithLongNumber() throws ClientProtocolException, IOException {
		String url = Base_URL + "2345678";

		HttpResponse response = sendAndReceiveGETMessage(url);

		//String result = getResponseString(response);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	}
	@Test(priority=3)
	public void testWithSpecialCharecter() throws ClientProtocolException, IOException {
		String url = Base_URL + "@@&&!!";

		HttpResponse response = sendAndReceiveGETMessage(url);

		//String result = getResponseString(response);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
		
	}
	@Test(priority=4)
	public void testWithBlank() throws ClientProtocolException, IOException {
		String url = Base_URL + "";

		HttpResponse response = sendAndReceiveGETMessage(url);

		//String result = getResponseString(response);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	}
	@Test(priority=5)
	public void testWithLockedMember() throws ClientProtocolException, IOException {
		String url = Base_URL + "123456789";

		HttpResponse response = sendAndReceiveGETMessage(url);

		//String result = getResponseString(response);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	}
	@Test(priority=6)
	public void testWithAlphaNumeric() throws ClientProtocolException, IOException {
		String url = Base_URL + "avgsc345678vds9";

		HttpResponse response = sendAndReceiveGETMessage(url);

		String result = getResponseString(response);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	}
	@Test
	public void testWithValidId() {
	}
	@Test
	public void testWithInvalidId() {
	}
	@Test
	public void testWithRegisteredIncorrectUsername() {
	}
	@Test
	public void testWithNonRegisteredUsername() {
	}
	@Test
	public void testWithInvalidUser() {
	}
	@Test
	public void testWithSingleUserFound() {
	}
	@Test
	public void testWithSingleUserNotFound() {
	}
	
	
	@Test(priority=7)
	public void testWithListOfResourceFound() throws ClientProtocolException, IOException {
		String url = "https://reqres.in/api/unknown";

		HttpResponse response = sendAndReceiveGETMessage(url);

		getResponseString(response);
		 
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
		
	}
	@Test(priority=8)
	public void testWithSingleResourceNotFound() throws ClientProtocolException, IOException {
		String url = "https://reqres.in/api/unknown/27";

		HttpResponse response = sendAndReceiveGETMessage(url);

		getResponseString(response);
		 
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
		
	}
	@Test(priority=9)
	public void testWithSingleResource() throws ClientProtocolException, IOException {
		
		String url = "https://reqres.in/api/unknown/2";

		HttpResponse response = sendAndReceiveGETMessage(url);

		getResponseString(response);
		 
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
		
	}
	@Test(priority=10)
	public void testWithDelayedResponse() throws ClientProtocolException, IOException {
		String url = "https://reqres.in/api/users?delay=3";

		HttpResponse response = sendAndReceiveGETMessage(url);

		getResponseString(response);
		 
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	}
}
