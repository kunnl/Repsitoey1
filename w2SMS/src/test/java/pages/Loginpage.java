package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage
{
  public WebDriver driver;
  //locators
  @FindBy(name="mobileNo")
  public WebElement mbno;
  
  @FindBy(name="password")
  public WebElement pwd;
  
  @FindBy(xpath="(//*[contains(text(),'Login ')])[2]")
  public WebElement signin;
  
  @FindBy(xpath="//*[text()='Enter your mobile number']")
  public WebElement mbno_blank_err;
  
  @FindBy(xpath="(//*[text()='Enter password'])[2]")
  public WebElement pwd_blnk_err;
  
  @FindBy(xpath="(//*[contains(text(),'Try Again.')])[1]")
  public  WebElement pwd_invalid_err;
  
  @FindBy(xpath="//*[text()='Enter valid mobile number']")
  public WebElement mbno_invalid_err ;
  
  //operations
  public Loginpage(WebDriver driver)
  {
	 this.driver=driver;
	 PageFactory.initElements(driver,this);
	 
  }
  public void fillmbno(String x)
  {
	mbno.sendKeys(x);
  } 
  public void fillpwd(String x)
  {
	pwd.sendKeys(x);
  }
  public void clicksignin()
  {
	signin.click();
  }
}
