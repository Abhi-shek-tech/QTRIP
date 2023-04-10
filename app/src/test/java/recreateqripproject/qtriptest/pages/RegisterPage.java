package recreateqripproject.qtriptest.pages;


import org.openqa.selenium.WebDriver;
import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import recreateqripproject.qtriptest.SeleniumWrapper;


public class RegisterPage {
    WebDriver driver;
    String Url= "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastGeneratedUserName;
    @FindBy(xpath ="//input[@name='email']")
    WebElement emailID;
    @FindBy(xpath ="//input[@name='password']")
    WebElement pass;
    @FindBy(xpath ="//input[@name='confirmpassword']")
    WebElement cnfPassword;
    @FindBy(xpath ="//button[text()='Register Now']")
    WebElement register;
    // @FindBy(xpath ="")
    // WebElement

    public RegisterPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToRegisterURL(){
        if(!driver.getCurrentUrl().equalsIgnoreCase(this.Url)){
        driver.get(this.Url);
    }
    }

    public boolean registerNewUser(String username, String password, Boolean makeUsernameDynamic) throws InterruptedException{

    String test_data_username;
    if(makeUsernameDynamic){
        //Create a random userID of substring 8
       test_data_username=username+UUID.randomUUID().toString();    
       System.out.println(test_data_username);
    }
    else{
        test_data_username=username;
    }
    //emailID.sendKeys(test_data_username);
    Assert.assertTrue(SeleniumWrapper.sendKeys(emailID,test_data_username),"Input to userName failed");
    //pass.sendKeys(password);
    Assert.assertTrue(SeleniumWrapper.sendKeys(pass,password),"Input to password failed");
    //cnfPassword.sendKeys(password);
    Assert.assertTrue(SeleniumWrapper.sendKeys(cnfPassword,password),"Input to cnfPassword failed");
    //register.click();.
    Assert.assertTrue(SeleniumWrapper.click(register,driver),"Click sucessfully performed on register button");
    Thread.sleep(1000);
    System.out.println("The current url :: "+this.driver.getCurrentUrl());
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login"));
    Assert.assertEquals(this.driver.getCurrentUrl().endsWith("/login"),true, "Not redirected to login page after registration");

    this.lastGeneratedUserName=test_data_username;
    return this.driver.getCurrentUrl().endsWith("/login/");

   
    }
}
