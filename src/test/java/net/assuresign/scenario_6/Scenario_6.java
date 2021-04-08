package net.assuresign.scenario_6;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.base.Constants;
import net.assuresign.utils.TestUtils;

public class Scenario_6 extends Base{
	
	@Test(dataProvider = "version-data-provider",enabled = true)   //1
	public void getAllEmailDesignSets(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_6 : Test for get all email design sets for a given account.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/emaildesignsets";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.emailDesignSets", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)  //2
	public void getAllEmailDesignForValidEmail(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_6 : Test for get all email design sets for a given valid email.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
							   +"/emaildesignsets/"+Constants.EMAIL_ACCOUNT_ID+"/emailDesigns";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.emailDesigns", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)   //3
	public void retriveNotificationForValidEmail(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_6 : Test for get all default notifications for a given valid email.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
							   +"/emaildesignsets/"+Constants.EMAIL_ACCOUNT_ID+"/defaultEmailNotifications/en-us";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.defaultEmailNotifications", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//4
	public void retriveNotification_EmptyCulture(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_6 : Test for get notifications for a given email with empty culture.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
							   +"/emaildesignsets/"+Constants.EMAIL_ACCOUNT_ID+"/defaultEmailNotifications/";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", is("The requested resource was not found."));
	}
	
	
	@Test(dataProvider = "version-data-provider",enabled = true)   //5
	public void getAllEmailDesign_InvalidEmail(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_6 : Test for get all email design sets for invalid email account id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
							   +"/emaildesignsets/"+Constants.EMAIL_ACCOUNT_ID+"invalid/emailDesigns";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", is("The requested resource was not found."));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)   //6 ISSUE :Getting 200,Should be 400/404
	public void getAllEmailDesign_EmptyEmail(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_6 : Test for get all email design sets for empty email account id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
							   +"/emaildesignsets/"+" "+"/emailDesigns";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true) //7 ISSUE:getting 200,should be 400/404
	public void retriveNotification_InvalidCulture(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_6 : Test for get notifications for a given email with invalid culture.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
							   +"/emaildesignsets/"+Constants.EMAIL_ACCOUNT_ID+"/defaultEmailNotifications/invalid";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)     //8
	public void retriveNotificationForInvalidDesignsetID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_6 : Test for get notifications for an invalid Design set ID.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
							   +"/emaildesignsets/"+Constants.EMAIL_ACCOUNT_ID+"invalid/defaultEmailNotifications/en-us";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", is("The requested resource was not found."));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true) //9 ISSUE:Geeting 200 instead of 400
	public void retriveNotification_EmptyDesignsetID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_6 : Test for get notifications for a given email with empty culture.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
							   +"/emaildesignsets/"+" "+"/defaultEmailNotifications/en-us";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)));
	}
}
