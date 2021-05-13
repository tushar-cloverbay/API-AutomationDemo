package net.assuresign.scenario_3;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
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

public class Scenario_3_3 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submit_LargeFile(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_3_3 : Test for submit with specified schema : large file.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_LargeFile-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_LargeFile.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		if(version.equals("3.0")||version.equals("3.1"))
		{
			response.then().assertThat()
			.statusCode(equalTo(200))
			.body("$", hasKey("messages"))
			.body("result.id", notNullValue())
			.body("result.authToken", notNullValue());
		}else
		{
			response.then().assertThat()
			.statusCode(equalTo(200))
			.body("$", hasKey("messages"))
			.body("result.envelopeID", notNullValue())
			.body("result.authToken", notNullValue());
		}
	}
	
	@Test(dataProvider = "version-data-provider",groups = { "ExcludeForOld" },enabled = true)
	public void submit_SSTemplateLargeFile(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_3_3 : Test for submit with Valid Template ID.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_SSTemplateLargeFile.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("$", hasKey("messages"))
		.body("result.envelopeID", notNullValue())
		.body("result.authToken", notNullValue());
	}
}
