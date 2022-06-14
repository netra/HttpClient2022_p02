package com.apex.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class USERAPI_TEST_POST {

	static final String Base_URL = "https://reqres.in/api/users/";

	private HttpResponse sendAndReceivePOSTMessage(String url) throws IOException, ClientProtocolException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		// step2 : create the request message
		HttpPost request = new HttpPost(url);

		
		  List<NameValuePair> urlParameters1 = new ArrayList<>();
		  urlParameters1.add(new BasicNameValuePair("name", "abc"));
		  urlParameters1.add(new BasicNameValuePair("job", "leader"));
		  request.setEntity(new StringEntity(urlParameters1.toString()));
		
		
		//request.setEntity(new UrlEncodedFormEntity(urlParameters1));
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
			System.out.println("The response is : "+result);
		}
		return result;
	}


	@Test
	public void testWithCorrectUserID() throws ClientProtocolException, IOException {
		String url = Base_URL + "2";

		HttpResponse response = sendAndReceivePOSTMessage(url);

		String result = getResponseString(response);
		System.out.println(result);
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
		
		
	}
	@Test
	public void testWithRegisterSuccessful() throws ClientProtocolException, IOException {
		String url = Base_URL + "register";

		HttpResponse response = sendAndReceivePOSTMessage(url );

		String result = getResponseString(response);

		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
		Assert.assertTrue(result.contains("abc"));
		
	}
	  
		@Test
		public void whenSendPostRequestUsingHttpClient_thenCorrect() 
		  throws ClientProtocolException, IOException {
		    CloseableHttpClient client = HttpClients.createDefault();
		    HttpPost httpPost = new HttpPost("https://reqres.in/api/users/2");

		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair("name", "morpheus"));
		    params.add(new BasicNameValuePair("job", "leader"));
		    httpPost.setEntity(new UrlEncodedFormEntity(params));
		    
		    CloseableHttpResponse response = client.execute(httpPost);
		    String result = getResponseString(response);
		    Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
			Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
			//Assert.assertTrue(response.contains("name"));
		    //assertThat(response.getStatusLine().getStatusCode(), equals(200));
		    client.close();
		}
		


	
	@Test
	public void testWithLoginUnsuccessful() {
		
	}



}
