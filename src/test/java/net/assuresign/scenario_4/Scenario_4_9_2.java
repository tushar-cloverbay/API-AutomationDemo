package net.assuresign.scenario_4;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.base.Constants;
import net.assuresign.utils.JsonUtils;
import net.assuresign.utils.TestUtils;

public class Scenario_4_9_2 extends Base {
	@Test(dataProvider = "version-data-provider",enabled = false)
	public void submitPrepare_addDocumentCall(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_2 : Test for submitprepare "
				+ "to get prepare id for add document calls-200");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\submitPrepareAddDoc-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\submitPrepareAddDoc.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		if (version.equals("3.0") || version.equals("3.1")) {
			response.then().assertThat().statusCode(equalTo(200)).body("$", hasKey("messages"))
					.body("result.preparedEnvelopeId", notNullValue());
		} else {
			response.then().assertThat().statusCode(equalTo(200)).body("$", hasKey("messages"))
					.body("result.preparedEnvelopeID", notNullValue());
		}
	}
	
	@Test(dataProvider = "version-data-provider",enabled = false)
	public void addDocument_DynamicJB(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_2 : Test for submitPrepare/Add document-"
				+ "dynamic jotblock -200 Ok");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc.json",token);
		}
		System.out.println(preparedEID);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/"+preparedEID+"/documents";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJB-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJB.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat().statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = false)
	public void addDocument_DynamicJBOnlyParseDocTrue(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_2 : Test for submitPrepare/Add document-"
				+ "dynamic jotblock-with only parse document true-200");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc.json",token);
		}
		System.out.println(preparedEID);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/"+preparedEID+"/documents";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJBParseDocTrue-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJBParseDocTrue.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat().statusCode(equalTo(200));
	}
	@Test(dataProvider = "version-data-provider",enabled = false)
	public void addDocument_DynamicJBParsing_parseDocFalse(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_2 : Test for submitPrepare/Add document-"
				+ "dynamic jotblock with parsedocument false-400");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc.json",token);
		}
		System.out.println(preparedEID);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/"+preparedEID+"/documents";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJB1.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat().statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"));	
	}
	
	@Test(dataProvider = "version-data-provider",enabled = false)
	public void submitPrepare_DynamicJB_withoutParseDoc(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_2 : Test for submitPrepare/Add document-"
				+ "dynamic jotblock without parse document -400");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc.json",token);
		}
		System.out.println(preparedEID);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/"+preparedEID+"/documents";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJB2.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat().statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"));	
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void addDocument_Submit(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_9_2 : Test for submit Add-document-");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\submitPrepareAddDoc.json",token);
		}
		System.out.println(preparedEID);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare/"+preparedEID+"/documents";
		
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for submitPrepare/Add document dynamic jotblock -200 Ok");
		String payload1;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload1 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJB-"+version+".json");
		}else
		{
			payload1 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJB.json");
		}
		RequestSpecification request1 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload1);
		request1.header("Content-Type", "application/json");
		Response response1 = request1.post(URI);
		System.out.println(response1.getBody().asString());
		if(response1.getStatusCode()==200)
		{	extentTest.log(LogStatus.PASS, "Status Code : " + response1.getStatusCode());
			extentTest.log(LogStatus.PASS, "Response : " + response1.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "Status Code : " + response1.getStatusCode());
			extentTest.log(LogStatus.FAIL, "Response : " + response1.asPrettyString());
		}
		
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for submitPrepare/Add document-"
				+ "dynamic jotblock-with only parse document true-200");
		String payload2;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload2 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJBParseDocTrue-"+version+".json");
		}else
		{
			payload2 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-DynamicJBParseDocTrue.json");
		}
		RequestSpecification request2 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload2);
		request2.header("Content-Type", "application/json");
		Response response2 = request2.post(URI);
		System.out.println(response2.getBody().asString());
		if(response2.getStatusCode()==200)
		{	extentTest.log(LogStatus.PASS, "Status Code : " + response2.getStatusCode());
			extentTest.log(LogStatus.PASS, "Response : " + response2.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "Status Code : " + response2.getStatusCode());
			extentTest.log(LogStatus.FAIL, "Response : " + response2.asPrettyString());
		}
		String URI1 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/"+preparedEID;
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for submit Add-document-");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI1);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat().statusCode(equalTo(200));	
	}
	
}
