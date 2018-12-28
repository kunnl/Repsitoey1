package test;


import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import pages.Sendsmspage;
import pages.Loginpage;

public class Gluecode
{
  public WebDriver driver;
  public WebDriverWait w;
  public Loginpage lp;
  public Sendsmspage sp;
  public Scenario s;
  public Properties pro;
  
  @Before
  public void method1(Scenario s) throws Exception
  {
   //use Scenario object for current scenario
	  this.s=s;
  //load properties file for current scenario
     pro=new Properties();
     FileInputStream fip =new FileInputStream("G:\\batch239\\w2SMS\\src\\test\\resources\\way2sms\\way2sms.properties");
     pro.load(fip);
  }
  @Given("^launch site using \"(.*)\"$")
  public void method2(String b) 
    {
	  //open browser for current scenario
	  if(b.equals(("chrome")))
	   {
		System.setProperty("webdriver.chrome.driver",pro.getProperty("cdriver"));
		driver=new ChromeDriver();
	   }
	  else 
		{
		  System.setProperty("webdriver.gecko.driver",pro.getProperty("ffdriver"));
		  driver= new FirefoxDriver();
		}
	  
     //create object for page classes for current Scenario
       lp=new Loginpage(driver);
       sp=new Sendsmspage(driver);
     //open site
       driver.get(pro.getProperty("urlg"));
       driver.get(pro.getProperty("urlw"));
     //define wait object
       w=new WebDriverWait(driver,20);
       w.until(ExpectedConditions.visibilityOf(lp.mbno));
       driver.manage().window().maximize();
    }
  @Then("^title contains \"(.*)\"$")
  public void method3(String a)
  {
	w.until(ExpectedConditions.visibilityOf(lp.mbno));
	String t=driver.getTitle();
	if(t.contains(a))
	{
	 s.write("Title test passed");
	}
  else
  {
    byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    s.embed(ssbytes, "title test failed");
    Assert.fail();
  }
 } 
 @And("^close site$")
 public void method4()
 {
  driver.close();
 }
 @When("^Enter mobile number as\"(.*)\"$")
 public void method5(String u)
 {
  w.until(ExpectedConditions.visibilityOf(lp.mbno));
  lp.fillmbno(u);
 }
 @And("^Enter password as\"(.*)\"$")
 public void method6(String p)
 {
  w.until(ExpectedConditions.visibilityOf(lp.pwd));
  lp.fillpwd(p);
 }
 @And("^click login$")
 public void method7()
 {
  ExpectedConditions.visibilityOf(lp.signin);
  lp.clicksignin();
 }
 @Then("^validate output for criteria\"(.*)\"$")
 public void method8(String c)  throws Exception
 {
   
  Thread.sleep(2000);
  try
  {
	if(c.equals("all_valid")&& sp.sendsms.isDisplayed())
	{
     s.write("Test passed for valid data");
	}
	else if(c.equals("all_valid")&& driver.findElement(By.xpath("(//*[@href='send-sms'])[5]")).isDisplayed())
	{
     s.write("Test passed for valid data");
	}
	else if(c.equals("mbno_blank")&& lp.mbno_blank_err.isDisplayed())
	{
		s.write("Test passed for blank mobile no");
	}
	else if(c.equals("pwd_blank")&& lp.pwd_blnk_err.isDisplayed())
	{
		s.write("Test passed for blank password");
	}
	else if(c.equals("mbno_invalid")&& lp.mbno_invalid_err.isDisplayed())
	{
		s.write("Test passed for invalid mobile number");
	}
	else if(c.equals("pwd_invalid")&& lp.pwd_invalid_err.isDisplayed())
	{
		s.write("Test passed for invalid password");
	}
	else
	{
		byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	    s.embed(ssbytes, "Login test failed");
	    Assert.fail();
	}
  }
  catch(Exception ex)
  {
	  byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	    s.embed(ssbytes,ex.getMessage());
	    Assert.fail();
  }
 }
 @When("^Do login with valid data$")
 public void method9(DataTable dt)
 {
  List<List<String>> l=dt.asLists(String.class);
  lp.fillmbno(l.get(1).get(0));
  lp.fillpwd(l.get(1).get(1));
  lp.clicksignin();
 }
 @And("^Do logout$")
 public void method10() throws Exception
 {
	 Thread.sleep(2000);
	 try 
	 {
	 if(sp.menu.isDisplayed())
	 {
	  sp.clickmenu();
	  w.until(ExpectedConditions.visibilityOf(sp.logout));
	  sp.logoutclick(); 
	 }
 }
   catch(Exception ex)
     {
	driver.findElement(By.xpath("//span[contains(text(),'Logout ')]")) .click(); 
	 }
 }
	@Then("^Home page will be Reopend$")
	public void method11()
	{
	 if(lp.mbno.isDisplayed())
	 {
		s.write("Logout succesfully");
	 }
	 else
	 {
		 byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		 s.embed(ssbytes,"unsuccesful logout");
		 Assert.fail();
	 }
	}
 }
	
	
	
	
	
	
	
       
  
  


