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

public class Scenario_4_11 extends Base{
	
	public static String getSessionToken(String version) throws IOException {
		String URI = " https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/authentication/credentials";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\sessionTokenDeprecated_44009.json");
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		return JsonUtils.getKeyValue(response, "result.sessionToken");
	}
	
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_deprecated(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_11 : Test for getting Prepared Enveloped ID with different file type");
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
		System.out.println(JsonUtils.getKeyValue(response, "result.preparedEnvelopeID"));
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.preparedEnvelopeID", notNullValue())
		.body("result.setupUrl", notNullValue());
	}
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submit_deprecated(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_11 : Test for Submit Prepare with WithSigner Password");
		apiVersion = version;
		String sessionToken = getSessionToken(version);
		
		String URI1 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		String payload1 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\submitPrepareDeprecated.json");
		RequestSpecification request1 = RestAssured.given().body(payload1);
		request1.header("Content-Type", "application/json").header("Authorization", "X-AS-UserSessionToken "+sessionToken);
		Response response1 = request1.post(URI1);
		responseBody = response1.asPrettyString();
		System.out.println(response1.getBody().asString());
		String preparedEID = JsonUtils.getKeyValue(response1, "result.preparedEnvelopeID");
		System.out.println(preparedEID);
		
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/" + preparedEID;
		System.out.println(URI);
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "X-AS-UserSessionToken "+sessionToken).header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.envelopeID", notNullValue())
		.body("result.authToken", notNullValue());
	}
}
