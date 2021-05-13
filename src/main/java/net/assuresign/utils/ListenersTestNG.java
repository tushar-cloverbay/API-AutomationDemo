package net.assuresign.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;

import com.relevantcodes.extentreports.ExtentReports;

import net.assuresign.base.Base;

public class ListenersTestNG extends Base implements ITestListener {
	 public void onStart(ITestContext context) { 
	 	extent = new ExtentReports(System.getProperty("user.dir") + "/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Assuresign");
		extent.addSystemInfo("User Name", "NA");
		extent.addSystemInfo("Environment", "QA");
	 }
	 
	 public void onFinish(ITestContext context) {
	 extent.flush();
	 extent.close();

//	 TestUtils.sendEmail("dmohan@assuresign.com");
//	 TestUtils.sendEmail("mvadgave@assuresign.com");
//	 TestUtils.sendEmail("mvadgave@assuresign.com");
	 }
	 
	 /*
	 public void onTestStart(ITestResult result) {
	 System.out.println("New Test Started" +result.getName());
	 }
	 
	 public void onTestSuccess(ITestResult result) {
	 System.out.println("onTestSuccess Method" +result.getName());
	 }
	 
	 public void onTestFailure(ITestResult result) {
	 System.out.println("onTestFailure Method" +result.getName());
	 }
	 
	 public void onTestSkipped(ITestResult result) {
	 System.out.println("onTestSkipped Method" +result.getName());
	 }
	 
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	 System.out.println("onTestFailedButWithinSuccessPercentage" +result.getName());
	 }*/
	}