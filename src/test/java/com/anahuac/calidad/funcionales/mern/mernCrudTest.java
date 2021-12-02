package com.anahuac.calidad.funcionales.mern;

import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class mernCrudTest {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  JavascriptExecutor js;
	  @Before
	  public void setUp() throws Exception {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    js = (JavascriptExecutor) driver;
	  }
	  
	  @Test
	  public void testadd() throws Exception {
	    driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).sendKeys("Leslie");
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("les27@gmail.com");
	    driver.findElement(By.name("age")).click();
	    driver.findElement(By.name("age")).sendKeys("20");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::span[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
	    pause(5000);
	    
	    String tag = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
	    assertThat("Nice one!",is(tag));
	  }


	  @Test
	  public void testUpdate() throws Exception {
		  driver.get("https://mern-crud.herokuapp.com/");
		  driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[1]")).click();
		  driver.findElement(By.name("name")).click();
		  driver.findElement(By.name("name")).clear();
		  driver.findElement(By.name("name")).sendKeys("Antonio");
		  driver.findElement(By.name("email")).click();
		  driver.findElement(By.name("email")).clear();
		  driver.findElement(By.name("email")).sendKeys("ldf@gmail.com");
		  driver.findElement(By.name("age")).click();
		  driver.findElement(By.name("age")).clear();
		  driver.findElement(By.name("age")).sendKeys("21");
		  driver.findElement(By.xpath("//div[3]/div[2]/div/i")).click();
		  driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[2]/following::div[1]")).click();
		  driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
		  pause(5000);
		  driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();
		  String tag = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
		    assertThat("Nice one!",is(tag));
		  
	  }
	  
	  

	  @Test
	  public void testdelete() throws Exception {
		    driver.get("https://mern-crud.herokuapp.com/");
		    	String bdelete = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[2]")).getText();
		        driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
		        pause(5000);
		        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();
		        pause(5000);
		        
			    assertThat("delete", is(not(bdelete)));
			    
		  } 

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	  
	  private void pause(long mils) {
		  try {
			  Thread.sleep(mils);
			  }catch(Exception e){
				  e.printStackTrace();	
			  }
		  }
	}