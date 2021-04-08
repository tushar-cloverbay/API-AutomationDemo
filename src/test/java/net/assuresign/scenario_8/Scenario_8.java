package net.assuresign.scenario_8;

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

public class Scenario_8 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)	//1
	public void getEnvelopesAnyFilter(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops for any filter.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/envelopes/";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given()
		.queryParam("dateFilter", "ANY").header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(200))
		.body("result.envelopes[0].envelope", notNullValue());
	}
	@Test(dataProvider = "version-data-provider",enabled = true)	//2
	public void getEnvelopes_ClosedWithNoFormData(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops with closed no formdate.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/envelopes";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given()
		.queryParam("dateFilter", "closed").header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("VALIDATION_FAILED"))
		.body("summary", is("Error validating request"));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//4
	public void getEnvelopes_ClosedWithInvalidFormData(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops with closed invalid formdate.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/envelopes";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given()
		.queryParam("dateFilter", "CLOSED").queryParam("fromDate", "22/1/2021")
		.header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("VALIDATION_FAILED"))
		.body("summary", is("Error validating request"));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//5
	public void getEnvelopes_ClosedWithFromDateLaterTodate(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops with closed formdate later then todate.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/envelopes";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given()
		.queryParam("dateFilter", "CLOSED").queryParam("fromDate", "1/1/2021")
		.queryParam("toDate", "12/1/2020").header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("VALIDATION_FAILED"))
		.body("summary", is("Error validating request"))
		.body("details[0]", is("FromDate must come prior to ToDate"));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//7
	public void getEnvelopes_InvalidEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops with invalid envelope id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/"+Constants.ENVELOPE_ID+"invalid";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("VALIDATION_FAILED"))
		.body("summary", is("Error validating request"))
		.body("details[0]", notNullValue());
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//8 ISSUE : Getting 200,Should be 400/404
	public void getEnvelopes_EmptyEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops with empty envelope id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelopes/";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//10 ISSUE : Getting 200,Should be 400/404
	public void getEnvelope_ForInvalidEnvelopes(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelop with invalid envelope id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+Constants.SIGNED_ENVELOPE_ID+"invalid/signingLinks";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//11 ISSUE : Getting 200,Should be 400/404
	public void getEnvelope_ForEmptyEnvelopes(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelop with empty envelope id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+" "+"/signingLinks";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(404)));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//14
	public void getEnvelope_WrongDocType(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops with wrong document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+Constants.UN_SIGNED_ENVELOPE_ID+"/accessLinks/COMPLETED";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(401)));
	}
	@Test(dataProvider = "version-data-provider",enabled = true)	//15
	public void getEnvelope_WithoutDocType(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops without document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+Constants.UN_SIGNED_ENVELOPE_ID+"/accessLinks/";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", is("The requested resource was not found."));
	}
	
	@Test(dataProvider = "version-data-provider",enabled = true)	//16
	public void getEnvelope_InvalidDocType(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops with invalid document type.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+Constants.UN_SIGNED_ENVELOPE_ID+"/accessLinks/invalid";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(anyOf(equalTo(400),equalTo(401)));
	}
	@Test(dataProvider = "version-data-provider",enabled = true)	//17
	public void getEnvelope_InvalidEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops with invalid envelope id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+"a9b9eaf5-9968-419a-8ed0-apf0004e2885"+"/accessLinks/ORIGINAL";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(400))
		.body("errorCode", is("VALIDATION_FAILED"))
		.body("summary", is("Error validating request"));
	}
	@Test(dataProvider = "version-data-provider",enabled = true)	//18
	public void getEnvelope_EmptyEnvelopID(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Test for get envelops with invalid envelope id.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version 
				+"/envelope/"+""+"/accessLinks/ORIGINAL";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.get(URI);
		responseBody = response.asPrettyString();
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat()
		.statusCode(equalTo(404))
		.body("errorCode", is("NOT_FOUND"))
		.body("summary", is("The requested resource was not found."));
	}

}
