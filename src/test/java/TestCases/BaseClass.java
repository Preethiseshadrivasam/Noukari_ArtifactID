package TestCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Properties p;
	
	@BeforeClass
	@Parameters({"os","browser"})
	
	public void setUp(String os,String br) throws IOException {
		
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		if(br.equalsIgnoreCase("chrome")) {
		
		System.setProperty("webDriver.chrome.driver", "D:\\Software\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    driver.get(p.getProperty("appURL"));
	
	}
	
	else if(br.equalsIgnoreCase("edge")){
		System.setProperty("webDriver.edge.driver", "D:\\Software\\edgedriver_win64\\msedgedriver.exe");
		driver=new EdgeDriver();
		driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	driver.get(p.getProperty("appURL"));
	
	}
	}
	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
	public String randomString() {
		
		String generatedString= RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
   public String randomNumbers() {
		
		String generatedNumber= RandomStringUtils.randomNumeric(5);
		return generatedNumber;
	}
   public String randomAplphaNumaric() {
		
		String generatedString= RandomStringUtils.randomAlphabetic(3);
		String generatedNumber= RandomStringUtils.randomNumeric(3);
		return (generatedString+generatedNumber);
	}
   
   public String captureScreen(String tName) {
	   

		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takesScreenShot= (TakesScreenshot)driver;
		
		File sourceFile= takesScreenShot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir")+"\\screenShots\\"+ tName + "_ " +timeStamp + ".png";
		
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
		
		
		
	
	   
   }
}
