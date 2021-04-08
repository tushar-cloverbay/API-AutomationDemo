package net.assuresign.scenario_4;

import static org.hamcrest.Matchers.equalTo;
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

public class Scenario_4_12 extends Base{
	public static String getSessionToken(String version) throws IOException {
		String URI = " https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/authentication/credentials";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\sessionTokenDeprecated_44009.json");
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		return JsonUtils.getKeyValue(response, "result.sessionToken");
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_specifiedSchema(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for getting Prepared Enveloped ID with session token.");
		apiVersion = version;
		String token =getSessionToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\submitPrepareDeprecated.json");
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json").header("Authorization", "X-AS-UserSessionToken "+token);
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.preparedEnvelopeID", notNullValue())
		.body("result.setupUrl", notNullValue());
	}
}
