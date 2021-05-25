package net.assuresign.scenario_13;

import static org.hamcrest.Matchers.*;
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

public class Scenario_13 extends Base{
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_putCall1(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test Submit Prepare for put call.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare.json",token);
		}
		System.out.println(preparedEID);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/prepare/"+preparedEID;
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitPrepare_putCall1.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json").header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.put(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}

	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitValidate_Adhoc(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test submit for validate adhoc.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/validate";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitValidate_Adhoc.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitValidate_hybridCall(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test submit for validate adhoc.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/validate";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitValidate_hybridCall.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)			
	public void submitValidate_ssTemplate(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test submit validate for ss template.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/validate";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitValidate_ssTemplate.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitValidate_adhoc400(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test submit validate for ss template.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/validate";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitValidate_adhoc400.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}

	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_documents(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test Submit Prepare for documents.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare.json",token);
		}
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/prepare/"+preparedEID+"/documents";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitPrepare_documents.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.documents[0].documentID", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_documentsInvalidID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test Submit Prepare for documents.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID = "fe6e5995-d799-4156-9344-acfb00bd572einvalid";
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/prepare/"+preparedEID+"/documents";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitPrepare_documentsInvalidID.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_documentsEmptyID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test Submit Prepare for documents.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID = "";
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/prepare/"+preparedEID+"/documents";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitPrepare_documentsInvalidID.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_documentsPwdToOpenFile(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test Submit Prepare for documents.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare.json",token);
		}
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/prepare/"+preparedEID+"/documents";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitPrepare_documentsPwdToOpenFile.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_documentsPwdToEditFile(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test Submit Prepare for documents.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare.json",token);
		}
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/prepare/"+preparedEID+"/documents";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitPrepare_documentsPwdToEditFile.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_withoutDocName(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_13 : Test Submit Prepare for documents.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_13\\submitPrepare.json",token);
		}
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/prepare/"+preparedEID+"/documents";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_13\\submitPrepare_withoutDocName.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.documents[0].documentID", notNullValue());
	}
}
