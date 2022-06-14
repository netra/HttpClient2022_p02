package com.apex.samples_TestNG;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class UserAPI_Test_GET_TestNGTest_DD extends ApexBaseAPITest implements UserAPIConstants {

	@DataProvider(name ="user_ids")
	public Object[][] getSuccessfulIDs() {
//		Object[][] userInfo = { { "2", "janet.weaver@reqres.in", "janet" },
//				{ "1", "george.bluth@reqres.in", "george" } };

		Object[][] retObjArr =getTableArray("/Users/netra/eclipse-workspace/user-API-samples/user_ids.xls","sheet1","UserSuccessIds");	
	  return (retObjArr);
	}

	@Test(dataProvider ="user_ids")
	public void testWithCorrectUserID(String id, String email, String firstName)
			throws ClientProtocolException, IOException {
		long time1 = System.currentTimeMillis();

		String url = Base_URL +id;

		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMessage(url);

		String result = ApexHttpUtil.getResponseString(response);
		System.out.println(result);

		// basic validation
		performBasicValidations(response, STATUS_CODE_200, STATUS_MESSAGE_OK);

		// specific validation
		// status code, status message, tag, message data, tag repetion
		// string validation or we can use parsers
		Assert.assertTrue(result.contains(id));
		Assert.assertTrue(result.contains(email));
		Assert.assertTrue(result.contains(firstName));
		long time2 = System.currentTimeMillis();
		long time = time2 - time1;
		System.out.println("Time take for response : " + time);

	}

	private void performBasicValidations(HttpResponse response, int expCode, String expMessage) {

		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());

		Assert.assertEquals(response.getStatusLine().getStatusCode(), expCode);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), expMessage);
	}
	
	
	public static String[][] getTableArray(String xlFilePath, String sheetName, String tableName){
        String[][] tabArray=null;
        try{
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet sheet = workbook.getSheet(sheetName);
            int startRow,startCol, endRow, endCol,ci,cj;
            Cell tableStart=sheet.findCell(tableName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                               

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
        }
        catch (Exception e)    {
            System.out.println("error in getTableArray()");
        }

        return(tabArray);
    }

	
    

}
