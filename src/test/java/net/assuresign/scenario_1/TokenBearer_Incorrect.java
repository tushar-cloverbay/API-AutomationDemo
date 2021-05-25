package net.assuresign.scenario_1;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.base.Constants;
import net.assuresign.utils.JsonUtils;
import net.assuresign.utils.TestUtils;

public class TokenBearer_Incorrect extends Base {

	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_withoutSessionLength(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Testing apiUser without sessionlength");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_1\\apiUser_withoutSession.json");
		log.info("Payload generated");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		log.info("Requeste Submited");
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		System.out.println(responseBody);
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_withoutApiUsername(String version) throws IOException
	{
		extentTest.log(LogStatus.PASS, "Test Description : " + "Testing apiUser without API Username");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_1\\apiUser_withoutUsername.json");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asString();
		statusCode = Integer.toString(response.getStatusCode());
		System.out.println(requestBody);
		response.then().assertThat()
		.statusCode(equalTo(400));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_withoutApiKey(String version) throws IOException
	{
		extentTest.log(LogStatus.PASS, "Test Description : " + "Testing apiUser without API Key");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_1\\apiUser_withoutKey.json");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		System.out.println(responseBody);
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("details", hasItems("'Key' should not be empty."));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_withoutContextUsername(String version) throws IOException
	{
		extentTest.log(LogStatus.PASS, "Test Description : " + "Testing apiUser without Context-Username");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_1\\apiUser_withoutContextUsername.json");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		System.out.println(responseBody);
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("details", hasItems("'ContextUsername' should not be empty."));
		
	}
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_invalidApiKey(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Testing apiUser with invalid Key");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_1\\apiUser_incorrectKey.json");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asString();
		statusCode = Integer.toString(response.getStatusCode());
		System.out.println(responseBody);
		response.then().assertThat()
		.statusCode(equalTo(401))
		.body("errorCode", is("UNAUTHORIZED"))
		.body("summary", is("The provided key for the API User was not valid"));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_incorrectJson(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Testing apiUser with invalid Key");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_1\\apiUser_incorrectJson.json");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		System.out.println(responseBody);
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue())
		.body("details", notNullValue());

	}
	
}
