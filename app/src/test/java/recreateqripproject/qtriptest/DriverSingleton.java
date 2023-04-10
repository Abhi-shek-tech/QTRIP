package recreateqripproject.qtriptest;


import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class DriverSingleton {
     
    private RemoteWebDriver driver= null;
    private static DriverSingleton instanceOfSingletonBrowserClass = null;
    private DriverSingleton() throws MalformedURLException{
       
            // Launch Browser using Zalenium
            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(BrowserType.CHROME);
            driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
            driver.manage().window().maximize();
            System.out.println("createDriver()"); 
    
    }
   public static DriverSingleton getinstanceOfDriverSingletonClass(){
     if(instanceOfSingletonBrowserClass==null){
        try {
            instanceOfSingletonBrowserClass=new DriverSingleton();
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

    return instanceOfSingletonBrowserClass;

   }
   public RemoteWebDriver getDriver(){

    return driver;
   }
   public void closeDriver(){
    driver.close();
   }
}