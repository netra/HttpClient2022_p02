package com.apex.post;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public final class Post_api_Test_1 {
	
	private static HttpResponse sendPOST(String post, String json) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-type", "application/JSON");
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"name\":\"mk\",");
        json.append("\"job\":\"leader\"");
        json.append("}");

        // send a JSON data
        post.setEntity(new StringEntity(json.toString()));
        HttpResponse response = client.execute(post);
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
	
	public static void main(String[] args) throws IOException {
		
		HttpResponse result;
		try {
			HttpResponse result = sendPOST(post, json);
			//result = sendPOST("https://reqres.in/api/users/");
			String result1 = postResponseString(result);
			System.out.println(result1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
	}

}
