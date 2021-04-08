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

public class Scenario_4_15 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getPreparedEnvelopedID_InvalidFileName(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for getting Prepared Enveloped ID with invalid file name");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-InvalidFileName.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue())
		.body("details", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getPreparedEnvelopedID_InvalidFileValue(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for getting Prepared Enveloped ID with File to upload as invalid value");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-InvalidFileValue.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(500))
		.body("errorCode", is("INTERNAL_SERVER_ERROR"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getPreparedEnvelopedID_WithoutBase64File(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for getting Prepared Enveloped ID without Base64 file provided");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-NoBase64File.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.preparedEnvelopeID", notNullValue())
		.body("result.setupUrl", notNullValue())
		.body("messages[0].details", notNullValue())
		.body("messages[0].messageType", is("warning"));
	}
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_WithoutBase64File(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for Submit Prepare without Base64 file provided");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\preparedEID-NoBase64File.json",token);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/" + preparedEID;
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\getEnvelopID.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(500))
		.body("errorCode", is("INTERNAL_SERVER_ERROR"))
		.body("summary", is("Errors Occurred"))
		.body("details", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getPreparedEnvelopedID_FileToUploadNull(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for getting Prepared Enveloped ID with file to upload as null.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-FileNull.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.preparedEnvelopeID", notNullValue())
		.body("result.setupUrl", notNullValue())
		.body("messages[0].details", notNullValue())
		.body("messages[0].messageType", is("warning"));
	}
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_FileToUploadNull(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for Submit Prepare with file to upload as null.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\preparedEID-FileNull.json",token);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/" + preparedEID;
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\getEnvelopID.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(500))
		.body("errorCode", is("INTERNAL_SERVER_ERROR"))
		.body("summary", is("Errors Occurred"))
		.body("details", notNullValue());
	}

	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getPreparedEnvelopedID_FileToUploadEmptyString(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for getting Prepared Enveloped ID with file to upload as empty string.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-FileEmptyString.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.preparedEnvelopeID", notNullValue())
		.body("result.setupUrl", notNullValue())
		.body("messages[0].details", notNullValue())
		.body("messages[0].messageType", is("warning"));
	}
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_FileToUploadEmptyString(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for Submit Prepare with file to upload as empty string.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\preparedEID-FileEmptyString.json",token);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/" + preparedEID;
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\getEnvelopID.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(500))
		.body("errorCode", is("INTERNAL_SERVER_ERROR"))
		.body("summary", is("Errors Occurred"))
		.body("details", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getPreparedEnvelopedID_DocMoreThen128Char(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for getting Prepared Enveloped ID with Documents more then 128 characters.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-Doc128Char.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.preparedEnvelopeID", notNullValue())
		.body("result.setupUrl", notNullValue())
		.body("messages[0].details", notNullValue())
		.body("messages[0].messageType", is("warning"));
	}
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_DocMoreThen128Char(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for Submit Prepare with Documents more then 128 characters.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\preparedEID-Doc128Char.json",token);
		String URI = "https://qa-test.assuresign.net/api/documentnow/v"+ version +"/submit/" + preparedEID;
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\getEnvelopID.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(500))
		.body("errorCode", is("INTERNAL_SERVER_ERROR"))
		.body("summary", is("Errors Occurred"))
		.body("details", notNullValue());
	}
}
