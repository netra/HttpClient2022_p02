package com.apex.PutDelete;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Put_api_Test {
	
static final String Base_URL = "https://reqres.in/api/users/";
	
	private HttpResponse sendAndReceivePUTMessage(String url, String str) throws IOException, ClientProtocolException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		// step2 : create the request message
		HttpPut request = new HttpPut(url);
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
			String str ="{\"name\":\"newNameJonh\",\"job\":\"leader\"}";
			
			HttpResponse response = sendAndReceivePUTMessage(url, str);

			String result = postResponseString(response);
			
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());
			System.out.println(response.getStatusLine().toString());
			
			Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		    Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
		    
		    JSONObject obj = new JSONObject(result);
		    Assert.assertEquals(obj.get("name"),"newNameJonh");
		    Assert.assertEquals(obj.get("job"),"leader");
	}
     

}
