package net.assuresign.scenario_5;

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

public class Scenario_5 extends Base{
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getTemplate(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_5 : Test for get template.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/templates";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getValidTemplate(String version) throws IOException {
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/templates";
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		System.out.println(response.getBody().asString());
		String templateID = JsonUtils.getKeyValue(response, "result.templates[0].templateID");
		
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_5 : Test for get valid template.");
		apiVersion = version;
		String URI2 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/templates/"+templateID;
		extentTest.log(LogStatus.PASS, "API URI : " + URI2);
		RequestSpecification request2 = RestAssured.given().header("Authorization", "Bearer "+token);
		request2.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response2 = request2.get(URI2);
		responseBody = response2.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response2.getTime() +" milliseconds");
		System.out.println(response2.getBody().asString());
		System.out.println(response2.getStatusCode());
		response2.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.content.documents", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)   
	public void getValidUnAuthorizedTemplate(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_5 : Test for get valid unauthorized template.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/templates/"+Constants.TEMPLATE_ID;
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(401),equalTo(404)));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void getInValidTemplate(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_5 : Test for get invalid template.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String invalidtemplateID = "8f310101-3142-4465-95c8-acef00f5d4d6";
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/templates/"+invalidtemplateID;
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)   //ISSUE : GETTING 200,SHOUD BE 400/404
	public void getTemplateByEmptyID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_5 : Test for get template by empty template id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String templateID = " ";
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/templates/"+templateID;
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)));
	}
}
