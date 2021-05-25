package net.assuresign.scenario_4;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
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

public class Scenario_4_5 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submitPrepare_WithMissingDocumentName(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_5 : Test for getting Prepared Enveloped ID with Missing Document Name");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-MissingDocName-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\preparedEID-MissingDocName.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
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
	
	@Test(dataProvider = "version-data-provider",enabled = true)
	public void submit_WithMissingDocumentName(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_4_5 : Test for Submit Prepare with Missing Document Name");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		String preparedEID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\preparedEID-MissingDocName-"+version+".json",token);
		}else
		{
			preparedEID = TestUtils.getPreparedEnvelopeID(version, "Scenario_4\\preparedEID-MissingDocName.json",token);
		}
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/" + preparedEID;
//		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_4\\getEnvelopID.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		responseBody = response.asPrettyString();
		statusCode = Integer.toString(response.getStatusCode());
		extentTest.log(LogStatus.PASS, "Response Time : " + response.getTime() +" milliseconds");
		System.out.println(response.getBody().asString());
		response.then().assertThat().statusCode(equalTo(400))
		.body("errorCode", is("VALIDATION_FAILED"))
		.body("details", notNullValue());
	}
}
