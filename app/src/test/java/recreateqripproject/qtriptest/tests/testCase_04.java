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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_04 {
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
      test = report.startTest("TestCase04");
    }

    @Test(description ="verify booking history flow",priority = 4,groups = "Reliability Flow",dataProvider = "data-provider1",dataProviderClass = DP.class)
    public void TestCase04(String emailID,String password,String dataset1, String dataset2, String dataset3) throws InterruptedException, IOException{
       
        String[] dataArray={dataset1,dataset2,dataset3};
       
        HomePage home=new HomePage(driver);
        LoginPage logg=new LoginPage(driver);
        AdventurePage adPage=new AdventurePage(driver);
        AdventureDetailsPage adv=new AdventureDetailsPage(driver);
        HistoryPage historyPage = new HistoryPage(driver);
        
        for(int i=0;i<3;i++){
        String[] dataset=dataArray[i].split(";");
        try{
        logg.navigateToLoginUrl();
        logg.performLogin(emailID, password);
        home.isUserLoggedIn();
        home.searchCity(dataset[0]);
        home.assertAutoCompleteText(dataset[0]);
        home.selectCity(dataset[0]);
        Thread.sleep(1000);
        adPage.selectAdventure(dataset[1]);
        adv.bookAdventure(dataset[2], dataset[3], dataset[4]);
        Thread.sleep(1000);
        adv.isBookingSuccessful();
        Thread.sleep(1000);
        historyPage.getReservations();
               Thread.sleep(1000);
               System.out.println("TestCase04 passing before log");
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
    }
          @AfterSuite
          public void closure()
          {
              report.endTest(test);
              report.flush();
              driver.quit();
          }
      }
        
 
//  home.logOutUser(); 
//   //}
// }

// @AfterTest()
// public void tearDown() {
// driver.quit();
// }
// }
