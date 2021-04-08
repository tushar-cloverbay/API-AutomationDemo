package net.assuresign.scenario_3;

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

public class Scenario_3_8 extends Base{
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void sessionTokenDeprecated_44009(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_3_8 : Test for session token 44009 deprecated.");
		apiVersion = version;
		String URI = " https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/authentication/credentials";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\sessionTokenDeprecated_44009.json");
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.sessionToken", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitHybridCallDeprecated(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_3_8 : Test submit hybrid call for deprecated.");
		apiVersion = version;
		String URI = " https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submitHybridCallDeprecated.json");
		String depSessionToken = getSessionToken(version);
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json").header("Authorization", "X-AS-UserSessionToken "+depSessionToken);
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.envelopeID", notNullValue())
		.body("result.authToken", notNullValue());
	}
	
	
	public static String getSessionToken(String version) throws IOException {
		String URI = " https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/authentication/credentials";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\sessionTokenDeprecated_44009.json");
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		return JsonUtils.getKeyValue(response, "result.sessionToken");
	}
	
	
}
