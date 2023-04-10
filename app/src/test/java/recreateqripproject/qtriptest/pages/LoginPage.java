package recreateqripproject.qtriptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import recreateqripproject.qtriptest.SeleniumWrapper;

public class LoginPage {
    WebDriver driver;
    String url= "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    // @FindBy(xpath ="//input[@name='email']")
    // WebElement email;
    
    @FindBy(name="email")
    WebElement email;
    @FindBy(xpath = "//input[@name='password']")
    WebElement pass;
    @FindBy(xpath = "//button[text()='Login to QTrip']")
    WebElement loginToQtrip;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToLoginUrl(){
        if(!driver.getCurrentUrl().equalsIgnoreCase(this.url)){
        driver.get(this.url);}
        //driver.manage().window().maximize();
    }

    public void performLogin(String emailID , String password) throws InterruptedException{
        //Boolean status;
        //email.sendKeys(emailID);
        Assert.assertTrue(SeleniumWrapper.sendKeys(this.email,emailID),"Input to email failed");
        //pass.sendKeys(password);
        Assert.assertTrue(SeleniumWrapper.sendKeys(this.pass,password),"Input to password failed");
        //loginToQtrip.click();
        Assert.assertTrue(SeleniumWrapper.click(loginToQtrip,driver),"Click on login action is performed");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/"));
        Assert.assertEquals(this.driver.getCurrentUrl(), "https://qtripdynamic-qa-frontend.vercel.app/","Not able to redirect to homepage after login ");
     
    }
}




