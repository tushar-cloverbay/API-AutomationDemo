package net.assuresign.scenario_8;

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

public class Scenario_8_3 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void Signers_NotCompletedEnvelop(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_3 : Test for download not completed envelope.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		System.out.println(token);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/download";
		System.out.println(URI);
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\Signers_NotCompletedEnvelop.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("messages[0].details", notNullValue())
		.body("messages[0].messageType", is("error"));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//3
	public void getEnvelopes_ClosedWithValidFormData(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_3 : Test for get envelops with closed valid formdate.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/envelopes";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given()
		.queryParam("dateFilter", "CLOSED").queryParam("envelopeName", "dummyPDF")
		.queryParam("emailAddress", Constants.EMAIL_ADDRESS).queryParam("fromDate", "1/1/2021")
		.queryParam("queryChildAccounts", "true").header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
}
