package net.assuresign.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base {

	public static Properties prop;
	public static Logger log;

	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static String responseBody;
	public static String requestBody;
	public static String apiVersion;
	public static FileInputStream fis;

	public Base() {
		Constants.loadVariables();
<<<<<<< HEAD
=======
		
>>>>>>> master
		if (log == null) {
			log = Logger.getLogger("Assuresign");
			log.info("log object created");
		}

		if (prop == null) {
			try {
				fis = new FileInputStream(".\\src\\main\\java\\net\\assuresign\\configs\\QA.properties");
				prop = new Properties();
				prop.load(fis);
				log.info("Properties file loaded");

//			String browserName=prop.getProperty("browser");	
			} catch (FileNotFoundException e) {
				log.error("Properties file not found");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void endReport() {

	}

	@BeforeMethod
	public void reportUp(ITestResult result) {
		extentTest = extent.startTest(result.getMethod().getMethodName());
		requestBody = null;
		responseBody = null;
		apiVersion = null;

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in
																							// extent report

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS : " + result.getName());
			extentTest.log(LogStatus.PASS, "API Version : " + apiVersion + "\n");
//			extentTest.log(LogStatus.PASS, "Request : " + requestBody + "\n");
			extentTest.log(LogStatus.PASS, "Response : " + responseBody);
		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
	}

	@DataProvider(name = "version-data-provider")
	public Object[][] dpMethod() {
		double start = Constants.START_VERSION;
		int length = (int) ((Constants.END_VERSION - Constants.START_VERSION) * 10);
		Object[][] data = new Object[length + 1][1];
		for (int i = 0; i <= length; i++) {
			data[i][0] = Double.toString(start).substring(0, 3);
			start = start + 0.1;
		}
		return data;
	}

}
