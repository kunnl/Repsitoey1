package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sendsmspage
{
  //locators
  public WebDriver driver;
  @FindBy(xpath="(//*[@href='send-sms'])[5]")
  public WebElement sendsms;
  
  @FindBy(xpath="//*[@class='fa icon-menu-bar']")
  public WebElement menu;
  
  @FindBy(xpath="//span[contains(text(),'Logout ')]")
  public WebElement logout;
  //operation
  public Sendsmspage(WebDriver driver)
  {
	 this.driver=driver;
	 PageFactory.initElements(driver,this);
  }
  public void clickmenu()
  {
	menu.click();
  }
  public void logoutclick()
  {
	logout.click();  
  }
}
