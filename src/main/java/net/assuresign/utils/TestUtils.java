package net.assuresign.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.base.Constants;

public class TestUtils extends Base{
	
	
	public static String getToken(String version) throws IOException
	{
		String URI = "https://qa-account.assuresign.net/api/v" + version + "/authentication/apiUser";
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_2\\apiUser.json");
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		String token = JsonUtils.getKeyValue(response, "result.token");
		if(token==null)
		{
			token = JsonUtils.getKeyValue(response, "token");
		}
//		if(version.equals("3.7"))
//		{
//			String token = JsonUtils.getKeyValue(response, "result.token");
//			return token;
//		}else
//		{
//			String token = JsonUtils.getKeyValue(response, "token");
//			return token;
//		}	
		return token;		
	}
	
	public static String getPreparedEnvelopeID(String version,String payloadFilePath,String token) throws IOException {
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+ version +"/submit/prepare";
		extentTest.log(LogStatus.PASS, "API URI : " + URI);
		String payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\"+payloadFilePath);  //..Scenario_4\\apiUser.json
		requestBody = payload;
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		System.out.println(response.asPrettyString());
		String preparedEnvelopeID = JsonUtils.getKeyValue(response, "result.preparedEnvelopeID");
		if(preparedEnvelopeID==null)
		{
		preparedEnvelopeID = JsonUtils.getKeyValue(response, "result.preparedEnvelopeId");
		}
		return preparedEnvelopeID;
	}
	
	public static String getSubmittedEnvelopeID(String version,String token) throws IOException {
		String URI = "https://"+Constants.ENV+".assuresign.net/api/documentnow/v"+version+"/submit";
		String payload;
		if(version.equals("3.0")||version.equals("3.1")||version.equals("3.2"))
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\submitHybridCall-"+version+".json");
		}else
		{
			payload = JsonUtils.payloadGenerator("Inputs\\"+Constants.ENV+"\\Scenario_8\\submitHybridCall.json");
		}
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer "+token).body(payload);
		request.header("Content-Type", "application/json");
		Response response = request.post(URI);
		System.out.println(response.asPrettyString());
		String SubmittedEnvelopeID = JsonUtils.getKeyValue(response, "result.envelopeID");
		if(SubmittedEnvelopeID==null)
		{
			SubmittedEnvelopeID = JsonUtils.getKeyValue(response, "result.id");
		}
		System.out.println(SubmittedEnvelopeID);
		return SubmittedEnvelopeID;
	}

	public static String getResposeString(Response response){
		log.info("Converting Response to String");
		String strResponse = response.getBody().asString();
		log.debug(strResponse);
		return strResponse;
	}
	
	public static JsonPath jsonParser(String response){
		log.info("Parsing String Response to JSON");
		JsonPath jsonResponse = new JsonPath(response);
		log.debug(jsonResponse);
		return jsonResponse;
	}
	

	public static XmlPath xmlParser(String response){
		log.info("Parsing String Response to XML");
		XmlPath xmlResponse = new XmlPath(response);
		log.debug(xmlResponse);
		return xmlResponse;
	}
	
	public static int getStatusCode(Response response){
		log.info("Getting Response Code");
		int statusCode = response.getStatusCode();
		log.info(statusCode);
		return statusCode;
	}
	
	public static String getStatusMessage(Response response){
		log.info("Getting Response Message");
		String responseMessage = response.getStatusLine();
		log.info(responseMessage);
		return responseMessage;
	}
	
	public static String getVersion() {
		String version;
		if(Constants.START_VERSION == Constants.END_VERSION)
		{
			version = Double.toString(Constants.START_VERSION);
		}else
		{
			version = Double.toString(Constants.START_VERSION)+" to "+Double.toString(Constants.END_VERSION);
		}
		return version;
	}
	public static void sendEmail(String email)
	{

        String to = email;
        String from = "tushar.behera@cloverbaytechnologies.com";
        
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tushar.behera@cloverbaytechnologies.com", "tolffzlmvtiwivzw");
            }

        });
        //session.setDebug(true);
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("API Automation report for "+Constants.ENV+" "+getVersion());
            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();
            try {
                File f =new File(".\\ExtentReport.html");
                attachmentPart.attachFile(f);
                textPart.setText("Please find the attachment for html report.");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);
            } catch (IOException e) {
                e.printStackTrace();
            }
            message.setContent(multipart);
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
	
	
	
	
	
}
