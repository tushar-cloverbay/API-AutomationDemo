package net.assuresign.scenario_8;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

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

public class Scenario_8_4 extends Base{	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void Signers_CompletedDoc(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_4 : Test for signers for completed documents.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/signers/"+"tushar.behera(qaapi-1)@cloverbaytechnologies.com";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\Signers_CompletedDoc.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response = request.put(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void Signers_withoutRequestBody(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_4 : Test for signers for completed documents.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/signers/"+"tushar.behera(qaapi-1)@cloverbaytechnologies.com";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response = request.put(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(500));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void Signers_invalidEnvelopeID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_4 : Test for signers for invalid envelope id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = "3ec42581-a4fe-468f-8684-ad01006f4e21invalid";
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/signers/"+"tushar.behera(qaapi-1)@cloverbaytechnologies.com";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\Signers_CompletedDoc.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response = request.put(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void Signers_invalidSignerID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_4 : Test for invalid signer id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/signers/"+"tusharbeheraqaap@cloverbaytechnologies.com";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\Signers_CompletedDoc.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response = request.put(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void Signers_emptySignerAndEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_4 : Test for empty envelope and signer id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+" "+"/signers/"+" ";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\Signers_CompletedDoc.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response = request.put(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(302));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void Signers_validInput(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_4 : Test for signers for valid input.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/signers/"+"tushar.behera(qaapi-1)@cloverbaytechnologies.com";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\Signers_CompletedDoc.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response = request.put(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
}

