package net.assuresign.scenario_11;

import static org.hamcrest.Matchers.equalTo;
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

public class Scenario_11 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void environments_validUsername(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_11 :Test for environments for valid username.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/users/"+Constants.USERNAME+"/environments";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.environments", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void environments_InvalidUsername(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_11 : Test for environments for invalid username.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/users/"+"mvadgavebfdh@assuresign.com"+"/environments";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void environments_EmptyUsername(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_11 : Test for environments for invalid username.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://qa-account.assuresign.net/api/v"+version+"/users/"+" "+"/environments";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", notNullValue());
	}
}
