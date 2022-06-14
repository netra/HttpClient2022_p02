package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest  extends TestBase{
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prap.getProperty("URL");
		apiUrl = prap.getProperty("serviceURL");
		
		url = serviceUrl+apiUrl;
		
		
	}
	@Test(priority=1)
	public void getAPIResponseWithoutHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse= restClient.get(url);
		
		
		// 1. Status Code
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code : "+statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200");
				
				// 2. Json String
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API : "+responseJson);
				
				// single value assertion
				// per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("Value of per page is : "+perPageValue);
				
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				// total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Value of total is : "+totalValue);
				
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//get the value from JSON array
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[1]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[1]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[1]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[1]/first_name");
				
				
				System.out.println("The last name is : "+lastName);
				System.out.println("The ID is : "+id);
				System.out.println("The avatar is : "+avatar);
				System.out.println("The first name is : " +firstName);
				
				// 3. All Header
				Header[] headerArray = closeableHttpResponse.getAllHeaders();
				
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				
				for(Header header : headerArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Header Array : "+allHeaders);
		
	}
	
	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		HashMap<String,String> hashMap = new HashMap<String, String>();
		hashMap.put("Content-Type", "application/json");
		//hashMap.put("username","test@amazon.com");
		//hashMap.put("password","test123");
		//hashMap.put("Auth Token","1234");
		closeableHttpResponse= restClient.get(url);
		
		
		// 1. Status Code
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code : "+statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200");
				
				// 2. Json String
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API : "+responseJson);
				
				// single value assertion
				// per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("Value of per page is : "+perPageValue);
				
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				// total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Value of total is : "+totalValue);
				
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//get the value from JSON array
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[1]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[1]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[1]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[1]/first_name");
				
				
				System.out.println("The last name is : "+lastName);
				System.out.println("The ID is : "+id);
				System.out.println("The avatar is : "+avatar);
				System.out.println("The first name is : " +firstName);
				
				// 3. All Header
				Header[] headerArray = closeableHttpResponse.getAllHeaders();
				
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				
				for(Header header : headerArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Header Array : "+allHeaders);
		
	}

}
