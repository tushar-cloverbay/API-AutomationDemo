package net.assuresign.scenario_8;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;



import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.base.Constants;
import net.assuresign.utils.JsonUtils;
import net.assuresign.utils.TestUtils;

public class Scenario_8_2 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)	
	public void cancel_ValidEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_2 : Test for cancel with valid envelope id and document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+Constants.CANCELLED_ENVELOPE_ID+"/cancel";
		System.out.println(URI);
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\cancel_ValidEnvelopID.json");
		System.out.println(payload);
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
	public void cancel_AlreadyCancelledEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_2 : Test for cancel with valid envelope id and document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		System.out.println(token);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/cancel";
		System.out.println(URI);
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\cancel_ValidEnvelopID.json");
		System.out.println(payload);
		RequestSpecification request = RestAssured.given().body(payload).header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response = request.put(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400));
	}
}
