package com.apex.samples_TestNG;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAPI_Test_DELETE_TestNG extends ApexBaseAPITest implements UserAPIConstants {

	// Status Code
	private static final String STATUS_MESSAGE_OK = "OK";

	// Status Message
	private static final int STATUS_CODE_200 = 200;
	static final String Base_URL = "https://reqres.in/api/users/";

	@Test(priority = 4)
	public void testWithCorrectUserIDDelete() throws ClientProtocolException, IOException {
		long time1 = System.currentTimeMillis();

		String url = Base_URL + "2";

		HttpResponse response = ApexHttpUtil.sendAndReceiveDELETEMessage(url);

		String result = ApexHttpUtil.getResponseString(response);
		System.out.println(result);

		// basic validation
		performBasicValidations(response, STATUS_CODE_204, STATUS_MESSAGE_NO_CONTENT);

		// specific validation
		// status code, status message, tag, message data, tag repetion
		// string validation or we can use parsers

		long time2 = System.currentTimeMillis();
		long time = time2 - time1;
		System.out.println("Time take for response : " + time);

	}

	@Test
	public void f() {
	}

	private void performBasicValidations(HttpResponse response, int expCode, String expMessage) {

		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());

		Assert.assertEquals(response.getStatusLine().getStatusCode(), expCode);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), expMessage);
	}

}
