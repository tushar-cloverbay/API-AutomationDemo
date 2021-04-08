package net.assuresign.scenario_10;

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

public class Scenario_10 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_validRequest(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for valid request for a specific user.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_validRequest.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(202))
		.body("result.apiKey", notNullValue())
		.body("result.apiUsername", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_invalidRequest(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for invalid request.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_invalidRequest.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_noSpecificUser(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for no specific user.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_noSpecificUser.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_noRequest(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for no request.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_noRequest.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(500))
		.body("errorCode", is("INTERNAL_SERVER_ERROR"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_noBaseUserName(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for no base user.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_noBaseUserName.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_noValueBaseUserName(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for no value for base user.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_noValueBaseUserName.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_noExpDate(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for no expire date.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_noExpDate.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(202))
		.body("result.apiKey", notNullValue())
		.body("result.apiUsername", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_noScope(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for no scope.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_noScope.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_invalidScope(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for invalid scope.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_invalidScope.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_validRequestSpecificEnv(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for valid request for a specific Environment.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_validRequestSpecificEnv.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(202))
		.body("result.apiKey", notNullValue())
		.body("result.apiUsername", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_invalidEnvironment(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user for valid request for a invalid Environment.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_invalidEnvironment.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_withoutEnvironment(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user without environment.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_withoutEnvironment.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void apiUser_organizationScopes(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_10 : Test api user with organization scopes.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/apiUsers";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_10\\apiUser_organizationScopes.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(202))
		.body("result.apiKey", notNullValue())
		.body("result.apiUsername", notNullValue());
	}			
}
