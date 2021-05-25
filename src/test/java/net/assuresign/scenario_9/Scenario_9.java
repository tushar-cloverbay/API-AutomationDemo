package net.assuresign.scenario_9;

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

public class Scenario_9 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getSSOToken(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_9 : Test for get envelops without document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/authentication/sso";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_9\\getSSOToken.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.ssoToken", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getSSOToken_XASUserContext(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_9 : Test for get envelops without document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/authentication/sso";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_9\\getSSOToken.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload)
		.header("X-AS-UserContext", "mvadgave@assuresign.com:e66c80af-0014-4f9e-9c4d-94851aa28ac8");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.ssoToken", notNullValue());
	}
}
