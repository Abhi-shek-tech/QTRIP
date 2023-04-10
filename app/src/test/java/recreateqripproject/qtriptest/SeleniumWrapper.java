package recreateqripproject.qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumWrapper {
    
    public static boolean click(WebElement elementToClick,WebDriver driver){
        Boolean status;
            status=elementToClick.isDisplayed();
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].style.border='4px solid red'", elementToClick);
            Assert.assertTrue(status,"The element to click is available"); 
            Actions act=new Actions(driver);
            WebElement myElement = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(elementToClick));
            executor.executeScript("arguments[0].scrollIntoView();", myElement);
            try{
                act.moveToElement(myElement);
                act.click();
                act.perform();
                
                status=true;
                //System.out.println("the click has successfully been performed from selenium wrapper");
              }
              catch(NoSuchElementException e1)
                     {
                         System.out.println("Button has disappeared");
                         //e1.printStackTrace();
                     }

        
                     return status; 
        }
                     
    
    
    public static boolean sendKeys(WebElement inputBox,String keysToSend){
        if(inputBox.isDisplayed()){
        inputBox.clear();
        inputBox.sendKeys(keysToSend);
        
        return true;
        }
        return false;
    }
    public static boolean navigate(WebDriver driver,String url) {
        {
        if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
            return true;
        }
        else
        return false;  
    }
//     public static WebElement findElementWithRetry(WebDriver driver ,By by,int retryCount) {
//         //for(int i=0)
//         return null; 
//     }
}
}

