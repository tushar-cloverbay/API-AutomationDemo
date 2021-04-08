package net.assuresign.scenario_2;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.base.Constants;
import net.assuresign.utils.BaseAssertion;
import net.assuresign.utils.JsonUtils;
import static org.hamcrest.Matchers.*;

public class TokenBearer_Correct extends Base{

	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_validateStatusCode(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for validating Status code");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_2\\apiUser.json");
		log.info("Payload generated");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		log.info("Requeste Submited");
		responseBody = response.asPrettyString();
		System.out.println(responseBody);
		BaseAssertion.verifyStatusCode(response, 200);
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_validateResponseTime(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for validating Response time : Should be <10000");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_2\\apiUser.json");
		log.info("Payload generated");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		log.info("Requeste Submited");
		responseBody = response.asPrettyString();
		System.out.println(responseBody);
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		BaseAssertion.verifyResponseTime(response,10000);
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_validateHeaders(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for validating Response Herders");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_2\\apiUser.json");
		log.info("Payload generated");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		log.info("Requeste Submited");
		responseBody = response.asPrettyString();
		System.out.println(responseBody);
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		response.then().assertThat()
		.header("Transfer-Encoding", is("chunked"))
		.header("Content-Type", is("application/json; charset=utf-8"))
		.header("Vary", is("Accept"))
		.header("Server", is("Microsoft-HTTPAPI/2.0"))
		.header("Link", is("</apiUser.xml>; rel=\"application/xml\""))
		.header("Request-Context", notNullValue())
		.header("Date", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_validateParameters(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for validating Response Parameters");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_2\\apiUser.json");
		log.info("Payload generated");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		log.info("Requeste Submited");
		responseBody = response.asPrettyString();
		System.out.println(responseBody);
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		response.then().assertThat()
		.statusCode(200);
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void tokenBearer_validateTokenSchema(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for validating Response Parameters");
		apiVersion = version;
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_2\\apiUser.json");
		log.info("Payload generated");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		log.info("Requeste Submited");
		responseBody = response.asPrettyString();
		System.out.println(responseBody);
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		response.then().assertThat()
		.statusCode(200);
	}
	

	
}
