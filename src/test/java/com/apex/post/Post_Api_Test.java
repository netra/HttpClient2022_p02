package com.apex.post;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Post_Api_Test {

	static final String Base_URL = "https://reqres.in/api/users/";
	
	private HttpResponse sendAndReceivePOSTMessage(String url, String str) throws IOException, ClientProtocolException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		// step2 : create the request message
		HttpPost request = new HttpPost(url);
		request.addHeader("Content-type", "application/JSON");
		request.setEntity(new StringEntity(str));
		// step3 : send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	private String postResponseString(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String result="";
		if (entity != null) {
			// return it as a String
		    result = EntityUtils.toString(entity);
			System.out.println(result);
		}
		return result;
		
		
	}
     @Test(priority=1)
	public void testPostWithValidData() throws ClientProtocolException, IOException {
    	 String url = Base_URL;
			String str ="{\"name\":\"Amy\",\"job\":\"leader\"}";
			
			HttpResponse response = sendAndReceivePOSTMessage(url, str);

			String result = postResponseString(response);
			
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());
			//System.out.println(response.getStatusLine().toString());
			
			Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
		    Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
		    
		    JSONObject obj = new JSONObject(result);
		    Assert.assertEquals(obj.get("name"),"Amy");
		    Assert.assertEquals(obj.get("job"),"leader");
	}
     @Test(priority=2)
 	public void testPostWithINValidData() throws ClientProtocolException, IOException {
     	 String url = Base_URL;
 			String str ="{$$$$}";
 			
 			HttpResponse response = sendAndReceivePOSTMessage(url, str);

 			String result = postResponseString(response);
 			
 			System.out.println(response.getStatusLine().getStatusCode());
 			System.out.println(response.getStatusLine().getReasonPhrase());
 			//System.out.println(response.getStatusLine().toString());
 			
 			Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
 		    Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
 		    
 		    
 	}
     @Test(priority=3)
  	public void testPostWithEmptyString() throws ClientProtocolException, IOException {
      	 String url = Base_URL;
  			String str ="";
  			
  			HttpResponse response = sendAndReceivePOSTMessage(url, str);

  			String result = postResponseString(response);
  			
  			System.out.println(response.getStatusLine().getStatusCode());
  			System.out.println(response.getStatusLine().getReasonPhrase());
  			//System.out.println(response.getStatusLine().toString());
  			
  			Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
  		    Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
  		    
  		    
  	}

	
}
