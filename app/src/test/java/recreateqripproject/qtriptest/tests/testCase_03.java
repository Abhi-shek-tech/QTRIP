package recreateqripproject.qtriptest.tests;

import recreateqripproject.qtriptest.DP;
import recreateqripproject.qtriptest.DriverSingleton;
import recreateqripproject.qtriptest.ReportSingleton;
import recreateqripproject.qtriptest.pages.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

    public class testCase_03 {
    static RemoteWebDriver driver;
    List<WebElement> reservationId;
    ExtentTest test;
    ExtentReports report;
    @BeforeTest
    public void setUp() throws MalformedURLException{
      DriverSingleton  driverSingleton=DriverSingleton.getinstanceOfDriverSingletonClass();
      driver=driverSingleton.getDriver();

      ReportSingleton reportSingleton = ReportSingleton.instanciateReportSingletonObject();
      report = reportSingleton.getExtendReport();
      test = report.startTest("TestCase03");
    }

    @Test(description ="verify booking and cancellation flow",priority = 3,groups="Booking and Cancellation Flow",dataProvider = "data-provider1",dataProviderClass = DP.class)
    public void TestCase03(String emailID,String password,String City,String AdventureName, String Name, String Date, String Person) throws InterruptedException, IOException{
      
        HomePage home=new HomePage(driver);
        LoginPage logg=new LoginPage(driver);
        AdventurePage adPage=new AdventurePage(driver);
        AdventureDetailsPage adv=new AdventureDetailsPage(driver);
        HistoryPage historyPage = new HistoryPage(driver);
      try{
        
        logg.navigateToLoginUrl();
        logg.performLogin(emailID, password);
        home.isUserLoggedIn();
        home.searchCity(City);
        home.assertAutoCompleteText(City);
        home.selectCity(City);
        Thread.sleep(1000);
        adPage.selectAdventure(AdventureName); 
        adv.bookAdventure(Name, Date, Person);
        adv.isBookingSuccessful();
        reservationId = historyPage.getReservations();
        String transId = reservationId.get(reservationId.size() - 1).getText();
        historyPage.cancelReservation(transId);
        home.logOutUser();
        System.out.println("TestCase03 passing before log");
        test.log(LogStatus.PASS,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase03 Registering and login passed")), "TestCase03 Registering and login passed");
      }
      catch(Exception e){
        test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase03 Registering and login passed")), "TestCase03 Registering and login passed");
      }
      // finally
      // {
      //   home.logOutUser();
      // }
    }
  
    @AfterSuite
    public void closure()
    {
        report.endTest(test);
        report.flush();
        driver.quit();
    }
  }

        // @AfterTest()
        // public void tearDown() {
        // driver.quit();
        // }
        // }