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

public class Scenario_3_5 extends Base {
	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submit_jotBlockParsingWithPageCount(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
				"Test Description : " + "Scenario_3_5 : Test for submit dynamic jot block parsing with page count.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingWithPageCount-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingWithPageCount.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
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

	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submitValidate_jotBlockParsingWithPageCount(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
				"Test Description : " + "Scenario_3_5 : Test for submit/validate dynamic jot block parsing with page count.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit/validate";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingWithPageCount-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingWithPageCount.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat().statusCode(equalTo(200))
		.body("$", hasKey("messages"))
		.body("result", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submit_jotBlockParsingParseDocTrue(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
				"Test Description : " + "Scenario_3_5 : Test for submit dynamic jot block with parse document true.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingParseDocTrue-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingParseDocTrue.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
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

	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submitValidate_jotBlockParsingParseDocTrue(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
				"Test Description : " + "Scenario_3_5 : Test for submit/validate dynamic jot block with parse document true.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit/validate";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingParseDocTrue-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingParseDocTrue.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat().statusCode(equalTo(200))
		.body("$", hasKey("messages"))
		.body("result", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submit_jotBlockParsingDocTrueDuplicateJotBlock(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
		"Test Description : " + "Scenario_3_5 : Test for submit dynamic jot block with parse document true and duplicate jot block.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingDocTrueDuplicateJotBlock-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingDocTrueDuplicateJotBlock.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
		System.out.println(response.getBody().asString());
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

	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submitValidate_jotBlockParsingDocTrueDuplicateJotBlock(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
				"Test Description : " + "Scenario_3_5 : Test for submit/validate dynamic jot block with parse document true and duplicate jot block.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit/validate";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingDocTrueDuplicateJotBlock-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingDocTrueDuplicateJotBlock.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat().statusCode(equalTo(200))
		.body("$", hasKey("messages"))
		.body("result", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submit_jotBlockParsingNegativePageCount(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
		"Test Description : " + "Scenario_3_5 : Test for submit dynamic jot block with negative page count.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submitValidate_jotBlockParsingNegativePageCount-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submitValidate_jotBlockParsingNegativePageCount.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat().statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"));
	}

	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submitValidate_jotBlockParsingNegativePageCount(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
				"Test Description : " + "Scenario_3_5 : Test for submit/validate dynamic jot block with negative page count.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit/validate";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submitValidate_jotBlockParsingNegativePageCount-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submitValidate_jotBlockParsingNegativePageCount.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat().statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"));
	}
	
	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submit_jotBlockParsingParseDocFalse(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
				"Test Description : " + "Scenario_3_5 : Test for submit dynamic jot block with parse document false.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingParseDocFalse-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingParseDocFalse.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat().statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"));
	}

	@Test(dataProvider = "version-data-provider", enabled = true)
	public void submitValidate_jotBlockParsingParseDocFalse(String version) throws IOException {
		extentTest.log(LogStatus.PASS,
				"Test Description : " + "Scenario_3_5 : Test for submit/validate dynamic jot block with parse document false.");
		apiVersion = version;
		String token = TestUtils.getToken(version);
		String URI = "https://" + Constants.ENV + ".assuresign.net/api/documentnow/v" + version + "/submit/validate";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingParseDocFalse-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_jotBlockParsingParseDocFalse.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() + " milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat().statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"));
	}

}