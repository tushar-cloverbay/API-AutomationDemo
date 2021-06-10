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
	public void deprecatedUserSessionToken(String version) throws IOException{
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_11 : Test for getting deprecated User Session Token");
		apiVersion = version;
		String URI = " https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/authentication/credentials";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\sessionTokenDeprecated_44009.json");
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat().statusCode(equalTo(200))
		.body("result.sessionToken", notNullValue());;
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_deprecated(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_11 : Test for getting Prepared Enveloped ID with different file type");
		apiVersion = version;
		String token =getSessionToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\submitPrepareDeprecated-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\submitPrepareDeprecated.json");
		}
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json").header("Authorization", "X-AS-UserSessionToken "+token);
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		if (version.equals("3.0") || version.equals("3.1")) {
			response.then().assertThat().statusCode(equalTo(200)).body("$", hasKey("messages"))
					.body("result.preparedEnvelopeId", notNullValue());
		} else {
			response.then().assertThat().statusCode(equalTo(200)).body("$", hasKey("messages"))
					.body("result.preparedEnvelopeID", notNullValue());
		}
	}
	
}
