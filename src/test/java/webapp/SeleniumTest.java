package webapp;

import java.util.Date;
import java.util.List;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	private static String username = "1";
	private static String password = "1";
	private static String testVideo = "D:\\design\\videos\\test_video3.mp4";
	
	
	@BeforeClass
	public static void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080");
		wait = new WebDriverWait(driver, 60);
	}
	
	@Test
	public void testSelenium() {
		//sign in
		WebElement element = driver.findElement(By.id("user"));
		element.sendKeys(username);
		element = driver.findElement(By.id("pass"));
		element.sendKeys(password);
		element = driver.findElement(By.id("sub"));
		element.click();
		try { Thread.sleep(1000); } 
		catch (InterruptedException ignored) {}
		/*
		//upload a test video
		element = driver.findElement(By.name("myFile"));
		element.sendKeys(testVideo);
		element = driver.findElement(By.id("fileSubmit"));
		element.click();
		//wait until results return
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("outputVid")));
		element = driver.findElement(By.id("outputVid"));
		*/
		//information page tests
		long startTime = System.currentTimeMillis();
		element = driver.findElement(By.id("menu_refer"));
		element.click();
		element = driver.findElement(By.id("meetteam"));
		element.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("orsh")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nick")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("shen")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mikalai")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("irene")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vero")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wen")));
		System.out.println("Took " + (System.currentTimeMillis() - startTime) + "milliseconds to open meet team page.");
		
		startTime = System.currentTimeMillis();
		element = driver.findElement(By.id("menu_refer"));
		element.click();
		element = driver.findElement(By.id("research"));
		element.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("objectdetection")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("actionrecog")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("etc")));
		System.out.println("Took " + (System.currentTimeMillis() - startTime) + "milliseconds to open research page.");
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.close();
	}

}

