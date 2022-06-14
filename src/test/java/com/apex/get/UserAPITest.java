package com.apex.get;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class UserAPITest {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// Step1 : create a http client
		HttpClient client = HttpClientBuilder.create().build();
		
		// step2 : create the request message
		HttpGet request = new HttpGet("https://reqres.in/api/users/2");
		
		// step3 : send and get the response message
		HttpResponse response = client.execute(request);
		
		// step4 : validate the response message
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		
		 HttpEntity entity = response.getEntity();
         if (entity != null) {
             // return it as a String
             String result = EntityUtils.toString(entity);
             System.out.println("REsult is : "+result);
         }
         if (response.getStatusLine().getStatusCode()==200) {
        	 System.out.println("Test Passed");
         }
         else {
        	 System.out.println("Test Failed");
         }
	}

}
