package recreateqripproject.qtriptest.tests;

import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import recreateqripproject.qtriptest.DP;
import recreateqripproject.qtriptest.DriverSingleton;
import recreateqripproject.qtriptest.ReportSingleton;
import recreateqripproject.qtriptest.pages.HomePage;
import recreateqripproject.qtriptest.pages.LoginPage;
import recreateqripproject.qtriptest.pages.RegisterPage;


public class testCase_01 {
    static WebDriver driver;
    ExtentTest test;
    ExtentReports report;
 
    @BeforeTest(alwaysRun=true)
    public void setUp() throws MalformedURLException{
        DriverSingleton driverSingleton= DriverSingleton.getinstanceOfDriverSingletonClass();
        driver=driverSingleton.getDriver();

        ReportSingleton reportSingleton = ReportSingleton.instanciateReportSingletonObject();
        report = reportSingleton.getExtendReport();
        test = report.startTest("TestCase01");
    }

    @Test(description ="verify user logged in to QTrip",priority = 1,groups="Login Flow",dataProvider = "data-provider1",dataProviderClass = DP.class)
    //@Parameters({"emailID","password"}) 
    public void TestCase01(String emailID,String password) throws InterruptedException, IOException 
    {
       
        
        HomePage home=new HomePage(driver);
        RegisterPage registerPage=new RegisterPage(driver);
        LoginPage login=new LoginPage(driver);
        try{
            home.openHomePage();
            Thread.sleep(1000);
            home.clickRegister();
            Thread.sleep(1000);
            registerPage.registerNewUser(emailID, password, true);
            login.navigateToLoginUrl();
            login.performLogin(registerPage.lastGeneratedUserName, password);
            home.isUserLoggedIn();
            System.out.println("TestCase01 passing before log");
            
            test.log(LogStatus.PASS,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login passed")), "TestCase01 Registering and login passed");
        }
        catch(Exception exception)
        {
            test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login failed")), "TestCase01 Registering and login failed");
        }
        finally
        {
            home.logOutUser();
        }
    }
    @AfterSuite
    public void closure()
    {
        report.endTest(test);
        report.flush();
        driver.quit();
    }
}