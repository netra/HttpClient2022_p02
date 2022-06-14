package com.apex.PutDelete;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Delete_api_Test {
static final String Base_URL = "https://reqres.in/api/users/2";
	
	private HttpResponse sendAndReceiveDELETEMessage(String url) throws IOException, ClientProtocolException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		// step2 : create the request message
		HttpDelete request = new HttpDelete(url);
		
		
		// step3 : send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	private String deleteResponseString(HttpResponse response) throws IOException {
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
			
			HttpResponse response = sendAndReceiveDELETEMessage(url);

			//String result = deleteResponseString(response);
			
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());
			System.out.println(response.getStatusLine().toString());
			
			Assert.assertEquals(response.getStatusLine().getStatusCode(), 204);
		    Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "No Content");
		    
		    
	}
    

}
