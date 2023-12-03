package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.crm.qa.utilis.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream ip;
    public static Logger logger = Logger.getLogger(TestBase.class);
    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
 
	
	
	public TestBase() {
		 htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/logs/ExtentReport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
		 prop = new Properties();

		try {
			ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		 PropertyConfigurator.configure(System.getProperty("user.dir")+
				 "\\src\\main\\resources\\log4j.properties");

		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT__WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(TestUtil.SCRIPT_TIMEOUT));
		logger.info("Browser initialized" + browserName);
		driver.get(prop.getProperty("url"));
		logger.info("Opening url" + prop.getProperty("url"));
	}
}
