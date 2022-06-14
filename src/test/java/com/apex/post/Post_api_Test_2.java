package com.apex.post;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Post_api_Test_2 {

	public static void main(String[] args) throws IOException {
		
		// create client
		CloseableHttpClient client = HttpClients.createDefault();
		// create request object
		HttpPost post = new HttpPost("https://jsonplaceholder.typicode.com/posts");
		try {
		  String json = "{\"userId\":1,\"body\":\"Dummy\"}";
		  StringEntity entity = new StringEntity(json);
		  post.setEntity(new UrlEncodedFormEntity(json));
		  // send request
		  CloseableHttpResponse response = client.execute(post);
		  // read response      
		  String responseStr = EntityUtils.toString(response.getEntity());
		  System.out.println(responseStr);
		} catch (ClientProtocolException e) {
		   e.printStackTrace();
		} catch (IOException e) {
		   e.printStackTrace();
		} finally {
		   if(client != null) {
		     client.close();
		   }
		}

	}

}
