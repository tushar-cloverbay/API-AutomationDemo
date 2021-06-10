package net.assuresign.scenario_4;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.base.Constants;
import net.assuresign.utils.JsonUtils;
import net.assuresign.utils.TestUtils;

public class Scenario_4_9_1 extends Base{
	@Test(dataProvider = "version-data-provider",groups = { "ExcludeFor3.0" },enabled = true)
	public void putCall_submitPrepare(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
				+ "Test for put call-submit prepare-dynamic jotblock- with only parse document true 200ok");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepare-putCall-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepare-putCall.json",token);
		}
		System.out.println(preparedEID);
		
		if(version.equals("3.1")||version.equals("3.2")) {
			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock with page count specified-200ok");
			String URI1 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/" + preparedEID;
			String payload1 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_pageCountSpecified-"+version+".json");
			RequestSpecification request1 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload1);
			request1.header("Content-Type", "application/json");
			Response response1 = request1.put(URI1);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_pageCountSpecified: " + response1.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response1.getStatusCode()));
			System.out.println(response1.getBody().asString());
			if(response1.getStatusCode()!=200)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_pageCountSpecified: " + "should be 200");
				Assert.assertEquals(response1.getStatusCode(), 200);
			}
			
			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock with only parse document true-200ok");
			String URI2 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/" + preparedEID;
			String payload2 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_onlyParseDocTrue-"+version+".json");
			RequestSpecification request2 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload2);
			request2.header("Content-Type", "application/json");
			Response response2 = request2.put(URI2);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_onlyParseDocTrue: " + response2.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response2.getStatusCode()));
			System.out.println(response2.getBody().asString());
			if(response2.getStatusCode()!=200)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_onlyParseDocTrue: " + "should be 200");
				Assert.assertEquals(response2.getStatusCode(), 200);
			}
			
			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock with only parse document false-400");
			String URI3 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/" + preparedEID;
			String payload3 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_onlyParseDocFalse-"+version+".json");
			RequestSpecification request3 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload3);
			request3.header("Content-Type", "application/json");
			Response response3 = request3.put(URI3);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_onlyParseDocFalse: " + response3.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response3.getStatusCode()));
			System.out.println(response3.getBody().asString());
			if(response3.getStatusCode()!=400)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_onlyParseDocFalse: " + "should be 400");
				Assert.assertEquals(response3.getStatusCode(), 400);
			}
			
			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock with only parse document not specified-400");
			String URI4 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/" + preparedEID;
			String payload4 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_onlyParseDocNotSpecified-"+version+".json");
			RequestSpecification request4 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload4);
			request2.header("Content-Type", "application/json");
			Response response4 = request4.put(URI4);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_onlyParseDocNotSpecified: " + response4.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response4.getStatusCode()));
			System.out.println(response4.getBody().asString());
			if(response4.getStatusCode()!=400)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_onlyParseDocNotSpecified: " + "should be 400");
				Assert.assertEquals(response4.getStatusCode(), 400);
			}
			
			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock with negative page count-400");
			String URI5 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/" + preparedEID;
			String payload5 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_negativePageCount-"+version+".json");
			RequestSpecification request5 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload5);
			request2.header("Content-Type", "application/json");
			Response response5 = request5.put(URI5);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_negativePageCount: " + response5.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response5.getStatusCode()));
			System.out.println(response5.getBody().asString());
			if(response5.getStatusCode()!=400)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_negativePageCount: " + "should be 400");
				Assert.assertEquals(response5.getStatusCode(), 400);
			}
		}else {

			String URI1 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/" + preparedEID;
			String payload1 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_onlyParseDocTrue.json");
			RequestSpecification request1 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload1);
			request1.header("Content-Type", "application/json");
			Response response1 = request1.put(URI1);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_onlyParseDocTrue: " + response1.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response1.getStatusCode()));
			System.out.println(response1.getBody().asString());
			if(response1.getStatusCode()!=200)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_onlyParseDocTrue: " + "should be 200");
				Assert.assertEquals(response1.getStatusCode(), 200);
			}
			
			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock-200ok");
			String payload2 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB.json");
			RequestSpecification request2 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload2);
			request2.header("Content-Type", "application/json");
			Response response2 = request2.put(URI1);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB: " + response2.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response2.getStatusCode()));
			System.out.println(response2.getBody().asString());
			if(response2.getStatusCode()!=200)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB: " + "should be 200");
				Assert.assertEquals(response2.getStatusCode(), 200);
			}

			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock-with negtive page number-400");
			String payload3 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_negativePageNo.json");
			RequestSpecification request3 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload3);
			request3.header("Content-Type", "application/json");
			Response response3 = request3.put(URI1);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_negativePageNo: " + response3.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response3.getStatusCode()));
			System.out.println(response3.getBody().asString());
			if(response3.getStatusCode()!=400)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_negativePageNo: " + "should be 400");
				Assert.assertEquals(response3.getStatusCode(), 400);
			}
			
			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock-with parse document false-400");
			String payload4 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_parseDocFalse.json");
			RequestSpecification request4 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload4);
			request3.header("Content-Type", "application/json");
			Response response4 = request4.put(URI1);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_parseDocFalse: " + response4.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response4.getStatusCode()));
			System.out.println(response4.getBody().asString());
			if(response4.getStatusCode()!=400)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_parseDocFalse: " + "should be 400");
				Assert.assertEquals(response4.getStatusCode(), 400);
			}
			
			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock-with parse document not given-400");
			String payload5 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_parseDocNotGiven.json");
			RequestSpecification request5 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload5);
			request5.header("Content-Type", "application/json");
			Response response5 = request5.put(URI1);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_parseDocNotGiven: " + response5.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response5.getStatusCode()));
			System.out.println(response5.getBody().asString());
			if(response5.getStatusCode()!=400)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_parseDocNotGiven: " + "should be 400");
				Assert.assertEquals(response5.getStatusCode(), 400);
			}
			
			extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_1 : "
					+ "Test for put call-submit prepare-dynamic jotblock-with parse document not given-400");
			String payload6 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\putCall_submitPrepare_DynamicJB_pageNoNotSpecified.json");
			RequestSpecification request6 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload6);
			request5.header("Content-Type", "application/json");
			Response response6 = request6.put(URI1);
			extentTest.log(LogStatus.PASS, "Response putCall_submitPrepare_DynamicJB_pageNoNotSpecified: " + response6.asPrettyString());
			extentTest.log(LogStatus.PASS, "Status Code: " + Integer.toString(response6.getStatusCode()));
			System.out.println(response6.getBody().asString());
			if(response6.getStatusCode()!=400)
			{
				extentTest.log(LogStatus.FAIL, "Response Code putCall_submitPrepare_DynamicJB_pageNoNotSpecified: " + "should be 400");
				Assert.assertEquals(response6.getStatusCode(), 400);
			}			
		}
		

		
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/" + preparedEID;
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		if (version.equals("3.0") || version.equals("3.1")) {
			response.then().assertThat().statusCode(equalTo(200)).body("$", hasKey("messages"))
					.body("result.id", notNullValue());
		} else {
			response.then().assertThat().statusCode(equalTo(200)).body("$", hasKey("messages"))
					.body("result.envelopeID", notNullValue());
		}
		
	}
}
	