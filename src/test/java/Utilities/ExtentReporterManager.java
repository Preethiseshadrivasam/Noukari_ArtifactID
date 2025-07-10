package Utilities;



import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestCases.BaseClass;

public class ExtentReporterManager implements ITestListener {

	public ExtentReports extent;
	public ExtentSparkReporter sparkReporter;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testcontext) {
	   
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName= "Test-Report-" +timeStamp +".html";
		
	    sparkReporter  = new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReporter.config().setDocumentTitle("Noukari Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
	     extent= new ExtentReports();
	     extent.attachReporter(sparkReporter);
	     extent.setSystemInfo("Application", "Noukari");
	     extent.setSystemInfo("module", "Admin");
	     extent.setSystemInfo("subModule", "customers");
	     extent.setSystemInfo("user Name", System.getProperty("user.name"));
	     extent.setSystemInfo("Environment", "QA");
	     
	     String os =testcontext.getCurrentXmlTest().getParameter("os");
		 extent.setSystemInfo("Operating System", os);
	     
		 String browser =testcontext.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser ", browser);
		 
		List<String>  includedGroups= testcontext.getCurrentXmlTest().getIncludedGroups();
		if(includedGroups.isEmpty()) {
			extent.setSystemInfo("groups", includedGroups.toString());
		}
		
	  }
	
	 public  void onTestSuccess(ITestResult result) {
		    
		test=  extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed");
		
		 
		  }
	 
	 
	 public void onTestFailure(ITestResult result) {
		   
		 test=  extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.FAIL, result.getName()+"got failed");
			test.log(Status.INFO, result.getThrowable().getMessage());
			
			try {
				String imgPath= new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
			}
			catch(Exception e) {
				
				e.printStackTrace();
				
			}
		  }
	 
	 public  void onTestSkipped(ITestResult result) {
		 test=  extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, result.getName()+"got skiped");
			test.log(Status.INFO, result.getThrowable().getMessage());
		  }
	
	 public void onFinish(ITestContext context) {
		   extent.flush();
		   
		  String pathOfExtentReport=  System.getProperty("user.dir")+"\\reports\\" +repName;
		  File extentReport= new File(pathOfExtentReport);
		  
		  try {
			  Desktop.getDesktop().browse(extentReport.toURI());
		  }
		  catch(Exception e) {
			  e.printStackTrace();
					  
		  }
		  }
}