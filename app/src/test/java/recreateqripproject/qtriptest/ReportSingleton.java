package recreateqripproject.qtriptest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import java.sql.Timestamp;

public class ReportSingleton {

    private static ReportSingleton instanciateReportSingletonObject = null;
    private static ExtentReports report;
    //public static ExtentReports extent;
    public static ExtentTest test;
    // private static Object instanciateOfReportSingleton;


    public static String getTimeStamp() {
        // Get time stamp for generating a unique username
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
    }


    private ReportSingleton() throws MalformedURLException
    {   
        
        System.out.println("Report singleton");
        System.out.println(System.getProperty("user.dir"));
        report = new ExtentReports("/home/crio-user/workspace/abhishekbindhani333-ME_QTRIP_QA/app/extent_customization_configs.xml"+ "ExtentReportResults_" + getTimeStamp() + ".html"); 
        report.loadConfig(new File("extent_customization_configs.xml"));
    } 

    public static ReportSingleton instanciateReportSingletonObject() throws MalformedURLException {
        System.out.println("InstanciateReportSingleObject calling multi --");
        if(instanciateReportSingletonObject==null)
        {
            instanciateReportSingletonObject = new ReportSingleton();
        }
        return instanciateReportSingletonObject;
    
    }

    public ExtentReports getExtendReport() {
        // System.out.println("getExtendReport calling multi --");
        // ExtentReports report = new ExtentReports("app/extent_customization_configs.xml");
        return report;
    }

    public static String takeScreenshot(WebDriver driver, String imageType, String filename) throws IOException {
        ScreenshotClass screenshotClass=new ScreenshotClass();
        return screenshotClass.ScreenShot(driver, imageType, filename);
    }
}