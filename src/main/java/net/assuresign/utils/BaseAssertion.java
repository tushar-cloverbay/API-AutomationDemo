package net.assuresign.utils;

import org.testng.Assert;
import io.restassured.response.Response;
import net.assuresign.base.Base;



public class BaseAssertion extends Base{
	
	public static void verifyTrue(boolean flag){
		Assert.assertTrue(flag);
	}
	
	public static void verifyFalse(boolean flag){
		Assert.assertFalse(flag);
	}

	public static void verifyStatusCode(Response response, int status){
		Assert.assertEquals(TestUtils.getStatusCode(response), status);
	}
	
	public static void verifyStatusMessage(Response response, String status){
		Assert.assertEquals(TestUtils.getStatusCode(response), status);
	}
	
	public static void verifyResponseTime(Response response,long maxTime)
	{
		if(response.getTime() >= maxTime)
		{
			log.info("Response time is greater then expected");
			Assert.assertTrue(false);
		}
	}
}
