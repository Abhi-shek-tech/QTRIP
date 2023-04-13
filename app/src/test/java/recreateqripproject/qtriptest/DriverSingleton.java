package recreateqripproject.qtriptest;


import java.net.MalformedURLException;
import java.net.URL;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {
     
    private WebDriver driver= null;
    private static DriverSingleton instanceOfSingletonBrowserClass = null;
    private DriverSingleton() throws MalformedURLException{
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions option=new FirefoxOptions();
            driver=new FirefoxDriver(option);
            //option.addArguments("--remote-allow-origins=*");
            // Launch Browser using Zalenium need docker
//            final DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setBrowserName(BrowserType.CHROME);
//            driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
            driver.manage().window().maximize();
//            driver.get("https://www.google.com/");
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
   public WebDriver getDriver(){

    return driver;
   }
   public void closeDriver(){
    driver.close();
   }
}