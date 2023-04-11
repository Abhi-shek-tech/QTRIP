package recreateqripproject.qtriptest.tests;

import org.openqa.selenium.WebDriver;
import recreateqripproject.qtriptest.DP;
import recreateqripproject.qtriptest.DriverSingleton;
import recreateqripproject.qtriptest.ReportSingleton;
import recreateqripproject.qtriptest.pages.AdventurePage;
import recreateqripproject.qtriptest.pages.HomePage;

import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class testCase_02 {
    WebDriver driver;
    static ExtentTest test;
    static ExtentReports report;
    static Logger log = Logger.getLogger(testCase_02.class.getClass());
    // @Parameters({"emailID","password"})
    @BeforeTest(alwaysRun=true)
    public void setUp() throws MalformedURLException{
      DriverSingleton  driverSingleton=DriverSingleton.getinstanceOfDriverSingletonClass();
      driver=driverSingleton.getDriver();
      // ReportSingleton rpt = ReportSingleton.instanciateReportSingletonObject();
      // report = rpt.getExtendReport();
      ReportSingleton reportSingleton = ReportSingleton.instanciateReportSingletonObject();
      report = reportSingleton.getExtendReport();
      test = report.startTest("TestCase02");
    }

    @Test(description ="verify Search and Filters work fine in QTrip",priority = 2,groups="Search and Filter flow",dataProvider = "data-provider1",dataProviderClass = DP.class )
    public void TestCase02(String city, String category,String durationFilter,String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws InterruptedException, IOException{
      HomePage home=new HomePage(driver);
      AdventurePage adv= new AdventurePage(driver);
      
      try{
        test = report.startTest("verify Search and Filters work fine in QTrip");
        //System.out.println(driver);
            home.openHomePage();
            home.searchCity(city);
            home.assertAutoCompleteText(city);
            home.selectCity(city);
            log.info("selected city executed");
            adv.clear();
            Thread.sleep(5000);
            adv.getResultCount(ExpectedUnFilteredResults);
            log.info("initial result count executed");
            adv.selectFilterValue(durationFilter);
            adv.setCategoryValue(category);
            adv.getResultCount(ExpectedFilteredResults);
            log.info("Expected Filtered Results count executed");
            System.out.println("TestCase02 passing before log");
            test.log(LogStatus.PASS,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase02 Registering and login passed")));
            }
            catch(Exception e){
              
             System.out.println(e);  
            //e.printStackTrace();
            // System.out.println(ReportSingleton.takeScreenshot(driver,"png","TestCase02_Registering_and_login_failed"));
            // System.out.println(test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase02_Registering_and_login_failed")));
             test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase02_Registering_and_login_failed")), "TestCase02 Registering and login failed");
             System.out.println("Exception ---->");
            }   
          }
          @AfterSuite
          public void closure()
          {
             try {
              report.endTest(test);
              report.flush();
              driver.quit();
                }
             catch(Exception e) {
              System.out.println("Exception 123--------->");
              System.out.println(e);
              System.out.println(test);
             }
          }
}