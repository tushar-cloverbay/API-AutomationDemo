package net.assuresign.scenario_8;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.base.Constants;
import net.assuresign.utils.TestUtils;

public class Scenario_8_1 extends Base{
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getEnvelopes_ValidEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_1 : Test for get envelops with valid envelop id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/envelopes/"+SubmittedEnvelopeID;
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.envelope", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//12
	public void getEnvelope_ForValidEnvelopes(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_1 : Test for get envelop for valid envelope id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+SubmittedEnvelopeID+"/signingLinks";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.signingLinks", notNullValue());
	}		
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//9 ISSUE : Getting 200,Should be 400/404
	public void getEnvelope_ForSignedEnvelopes(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_1 : Test for get envelops for signed envelope id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+SubmittedEnvelopeID+"/signingLinks";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)));
	}

	@Test(dataProvider = "version-data-provider",enabled = true)	//13
	public void getEnvelope_ValidDoctypeEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_1 : Test for get envelops with valid envelope id and document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+SubmittedEnvelopeID+"/accessLinks/ORIGINAL";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.accessLink", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//13
	public void getHistory_ValidEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_1 : Test for get history with valid envelope id and document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/history";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//13
	public void getStatus_ValidEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_1 : Test for get status with valid envelope id and document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/status";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.envelopeID", notNullValue())
		.body("result.status", is("iN_PROGRESS"))
		.body("result.documentList", notNullValue());
	}
	@Test(dataProvider = "version-data-provider",enabled = true)	//13
	public void getValues_ValidEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_8_1 : Test for get values with valid envelope id and document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String SubmittedEnvelopeID = TestUtils.getSubmittedEnvelopeID(version,token);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+SubmittedEnvelopeID+"/values";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("messages", notNullValue())
		.body("result.senderInputValues", notNullValue());
	}		
}
