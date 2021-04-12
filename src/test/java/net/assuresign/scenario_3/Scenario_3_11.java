package net.assuresign.scenario_3;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

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

public class Scenario_3_11 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submit_withPwdToOpenFile(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_3_11 : Test for submit with Password to open file.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_withPwdToOpenFile.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submit_withPwdToEditFile(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_3_11 : Test for submit with Password to edit file.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_3\\submit_withPwdToEditFile.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400));   //Should be 400 but coming 200 due to Bug.
	}
}
