package net.assuresign.scenario_7;

import static org.hamcrest.Matchers.anyOf;
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
import net.assuresign.utils.JsonUtils;
import net.assuresign.utils.TestUtils;

public class Scenario_7 extends Base {
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void listsAccounts_ValidUsername(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_7 : Test for lists of accounts the specified user may access.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/users/"+Constants.USERNAME+"/accounts";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.userAccounts[0].accountID", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void listsAccounts_InvalidUsername(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_7 : Test for lists of accounts the specified user may access.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/users/mvadgave@mailinator.com/accounts";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(403),equalTo(404)))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void listsAccounts_EmptyUsername(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_7 : Test for lists of accounts the specified user may access.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/users/ /accounts";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)));
	}
}
