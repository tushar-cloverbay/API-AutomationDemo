package net.assuresign.scenario_12;

import static org.hamcrest.Matchers.*;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.base.Constants;
import net.assuresign.utils.JsonUtils;
import net.assuresign.utils.TestUtils;

public class Scenario_12 extends Base{
	@Test(dataProvider = "version-data-provider",enabled = true)	
	public void collectionRun_submit(String version) throws IOException {
		extentTest.log(LogStatus.PASS, "Test Description : " + "Scenario_12 : Test for collection run submit.");
		apiVersion = version;
		String token =TestUtils.getToken(version);
		extentTest.log(LogStatus.PASS, "Token Bearer request:: ::token : " + token);
		if(token==null) {
			extentTest.log(LogStatus.FAIL, "Token Bearer request:: ::token : " + token);
			Assert.assertTrue(false);
		}
		String preparedEnvelopeID;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			preparedEnvelopeID = TestUtils.getPreparedEnvelopeID(version, "Scenario_12\\submitPrepare-"+version+".json",token);
		}else
		{
			preparedEnvelopeID = TestUtils.getPreparedEnvelopeID(version, "Scenario_12\\submitPrepare.json",token);
		}
		System.out.println(preparedEnvelopeID);	
		extentTest.log(LogStatus.PASS, "::: SubmitPrepare envelope pdf ::: ::: preparedEnvelopeID : " + preparedEnvelopeID);
		if(preparedEnvelopeID==null) {
			extentTest.log(LogStatus.FAIL, "::: SubmitPrepare envelope pdf ::: ::: preparedEnvelopeID : " + preparedEnvelopeID);
			Assert.assertTrue(false);
		}
		//SubmitPrepare 
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/prepare/"+preparedEnvelopeID+"/documents";
		System.out.println(URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_12\\submitPrepareDoc.json");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response = request.post(URI);
		System.out.println(response.asPrettyString());
		System.out.println("SubmitPrepare --------------"+response.statusCode());
		if(response.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: SubmitPrepare envelope pdf ::: ::: Status Code : "+response.statusCode()
			+" ::: Response : "+response.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "::: SubmitPrepare envelope pdf ::: ::: Status Code : "+response.statusCode()
			+" ::: Response : "+response.asPrettyString());
		}
		//PUT Call
		String URI2 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/prepare/"+preparedEnvelopeID;
		String payload2 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_12\\putCall.json");
		RequestSpecification request2 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload2)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response2 = request2.put(URI2);
		System.out.println(response2.asPrettyString());
		System.out.println("PUT Call --------------"+response2.statusCode());
		if(response2.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: PUT Call ::: ::: Status Code : "+response2.statusCode()
			+" ::: Response : "+response2.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, ":::PUT Call::: :::Status Code : "+response2.statusCode()
			+" ::: Response : "+response2.asPrettyString());
		}
		//Get Envelop id
		String URI3 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit/"+preparedEnvelopeID;
		String payload3 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_12\\getEnvelopeid.json");
		RequestSpecification request3 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload3)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response3 = request3.post(URI3);
		System.out.println(response3.asPrettyString());
		String envelopeID = JsonUtils.getKeyValue(response3, "result.envelopeID");
		if(envelopeID==null)
		{
			envelopeID = JsonUtils.getKeyValue(response3, "result.id");
		}
		System.out.println("Get Envelop id --------------"+response3.statusCode());
		if(response3.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: Get Envelop id ::: ::: Status Code : "+response3.statusCode()
			+" ::: Response : "+response3.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "::: Get Envelop id ::: ::: Status Code : "+response3.statusCode()
			+" ::: Response : "+response3.asPrettyString());
		}
		//Get Status
		String URI4 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/envelopes/"+envelopeID+"/status";
		RequestSpecification request4 = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response4 = request4.get(URI4);
		System.out.println(response4.asPrettyString());
		System.out.println("Get Status --------------"+response4.statusCode());
		if(response4.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: Get Status ::: ::: Status Code : "+response4.statusCode()
			+" ::: Response : "+response4.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "::: Get Status ::: ::: Status Code : "+response4.statusCode()
			+" ::: Response : "+response4.asPrettyString());
		}
		//Get Values
		String URI5 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/envelopes/"+envelopeID+"/values";
		RequestSpecification request5 = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response5 = request5.get(URI5);
		System.out.println(response5.asPrettyString());
		System.out.println("Get Values --------------"+response5.statusCode());
		if(response5.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: Get Values ::: ::: Status Code : "+response5.statusCode()
			+" ::: Response : "+response5.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "::: Get Values ::: ::: Status Code : "+response5.statusCode()
			+" ::: Response : "+response5.asPrettyString());
		}
		//Get Envelopes
		String URI6 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/envelopes/"+envelopeID;
		RequestSpecification request6 = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response6 = request6.get(URI6);
		System.out.println(response6.asPrettyString());
		System.out.println("Get Envelopes --------------"+response6.statusCode());
		if(response6.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: Get Envelopes ::: ::: Status Code : "+response6.statusCode()
			+" ::: Response : "+response6.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "::: Get Envelopes ::: ::: Status Code : "+response6.statusCode()
			+" ::: Response : "+response6.asPrettyString());
		}
		//Put Signers
		String URI7 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/envelopes/"+envelopeID+"/signers/"+Constants.envelopeSignerID;
		String payload7 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_12\\putEnvelope.json");
		RequestSpecification request7 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload7)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response7 = request7.put(URI7);
		System.out.println(response7.asPrettyString());
		System.out.println("Put Signers --------------"+response7.statusCode());
		if(response7.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: Put Signers ::: ::: Status Code : "+response7.statusCode()
			+" ::: Response : "+response7.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "::: Put Signers ::: ::: Status Code : "+response7.statusCode()
			+" ::: Response : "+response7.asPrettyString());
		}
		//Get signingLinks
		String URI8 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/envelope/"+envelopeID+"/signingLinks";
		RequestSpecification request8 = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response8 = request8.get(URI8);
		System.out.println(response8.asPrettyString());
		System.out.println("Get signingLinks --------------"+response8.statusCode());
		if(response8.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: Get signingLinks ::: ::: Status Code : "+response8.statusCode()
			+" ::: Response : "+response8.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "::: Get signingLinks ::: ::: Status Code : "+response8.statusCode()
			+" ::: Response : "+response8.asPrettyString());
		}
		//Put Cancel
		String URI9 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/envelopes/"+envelopeID+"/cancel";
		String payload9 = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_12\\putCancel.json");
		RequestSpecification request9 = RestAssured.given().header("Authorization", "Bearer "+token).body(payload9)
		.header("X-AS-User-Agent", "site24x7/1.0.0").header("Content-Type", "application/json");
		Response response9 = request9.put(URI9);
		System.out.println(response9.asPrettyString());
		System.out.println("Put Signers --------------"+response9.statusCode());
		if(response9.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: Put Cancel ::: ::: Status Code : "+response9.statusCode()
			+" ::: Response : "+response9.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "::: Put Cancel ::: ::: Status Code : "+response9.statusCode()
			+" ::: Response : "+response9.asPrettyString());
		}
		//Get History
		String URI10 = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/envelopes/"+envelopeID+"/history";
		RequestSpecification request10 = RestAssured.given().header("Authorization", "Bearer "+token)
		.header("X-AS-User-Agent", "site24x7/1.0.0");
		Response response10 = request10.get(URI10);
		System.out.println(response10.asPrettyString());
		System.out.println("Get signingLinks --------------"+response10.statusCode());
		if(response10.statusCode()==200) {
			extentTest.log(LogStatus.PASS, "::: Get History ::: ::: Status Code : "+response10.statusCode()
			+" ::: Response : "+response10.asPrettyString());
		}else {
			extentTest.log(LogStatus.FAIL, "::: Get History ::: ::: Status Code : "+response10.statusCode()
			+" ::: Response : "+response10.asPrettyString());
		}
	}
}
