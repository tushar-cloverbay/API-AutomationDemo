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

public class Scenario_4_6 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_WithPwdToEditFile(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_6 : Test for getting Prepared Enveloped ID with pwd to edit file.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-pwdToEditFile-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-pwdToEditFile.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue())
		.body("details", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_WithPwdToOpenFile(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_6 : Test for getting Prepared Enveloped ID with pwd to open file.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-pwdToOpenFile-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-pwdToOpenFile.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("BAD_REQUEST"))
		.body("summary", notNullValue())
		.body("details", notNullValue());
	}
}
