package recreateqripproject.qtriptest.pages;

import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.poi.sl.draw.DrawAutoShape;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import recreateqripproject.qtriptest.SeleniumWrapper;

public class HistoryPage {
    WebDriver driver;
    String Url="https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";
    @FindBy(xpath = "//a/strong[contains(text(),'here')]")
    WebElement reservationPageLink;
    @FindBy(xpath ="//li[@class='nav-item']")
    WebElement reservations;
    @FindBy(xpath ="//tbody/tr/th")
    List<WebElement> transaction_IDs;
    @FindBy(xpath ="//tbody/tr")
    List<WebElement> table_body_rows;
    @FindBy(xpath = "//button[text()='Cancel']")
    WebElement cancel;
    String transact_ID;
    ArrayList<String> rowTexts;
    public HistoryPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
     }

    // public void getReservation(){
    //     FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>(driver)
    //     .withTimeout(Duration.ofSeconds(10))
    //     .pollingEvery(Duration.ofSeconds(1))
    //     .ignoring(NoSuchElementException.class);
    //     wait.until(ExpectedConditions.elementToBeClickable(reservations));
    //     reservations.click();
        //driver.navigate().to(Url);
    //  for(WebElement all:all_Transaction_IDs)
    //  transact_ID.add(all.getText());
    // WebDriverWait wait=new WebDriverWait(driver, 10);
    // wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/"));
    //}
    // public void cancelReservation(String name, String date, String person) throws InterruptedException{
    //     Thread.sleep(5000);
    //     for(WebElement allrow:table_body_rows){ 
    //         System.out.println(table_body_rows);
    //         rowTexts.add(allrow.getText());
    //         System.out.println(allrow.getText());
    //             if((rowTexts.contains(name)) && (rowTexts.contains(date))&& (rowTexts.contains(person))){
    //                transact_ID= transaction_IDs.getText();
    //                System.out.println(transact_ID);
    //                cancel.click();
    //             }
    //     }
        public List<WebElement> getReservations() {
            reservationPageLink.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfAllElements(transaction_IDs));
            return transaction_IDs;
        }
//}
        public void cancelReservation(String transaction) throws InterruptedException {
            for (int j = 0; j < transaction_IDs.size(); j++) {
                if (transaction_IDs.get(j).getText().equals(transaction)) {
                    //cancel.click();
                    Assert.assertTrue(SeleniumWrapper.click(cancel, driver),"Click on cancel failed");

                }
            }
        }

}
// package qtriptest.pages;

// import qtriptest.SeleniumWrapper;
// import java.text.SimpleDateFormat;
// import java.util.List;
// import org.openqa.selenium.By;
// import org.openqa.selenium.NoSuchElementException;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.FindBy;
// import org.openqa.selenium.support.PageFactory;
// import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
// import org.openqa.selenium.support.pagefactory.ByChained;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.testng.Assert;

// public class HistoryPage {
//     @FindBy(linkText = "Reservations")
//     WebElement reserveButton;
//     WebDriver driver;
//     String reserveUrl="https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";
//     public String transactionId;
//     WebElement cancelButton;
//     WebElement viewReservationButton;
//     By by;

//     public HistoryPage(WebDriver driver)
//     {
//         this.driver = driver;
//         PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);
//     }
//     public void getReservations(String guestName) throws InterruptedException
//     {
//         if(!this.driver.getCurrentUrl().equals(reserveUrl))
//         {
//             this.driver.get(reserveUrl);
//         }
//         Thread.sleep(5000);
//         List<WebElement> allReservation;
//         allReservation = this.driver.findElements(By.tagName("tr"));
//         // by = By.tagName("tr");
//         // allReservation = SeleniumWrapper.findElementWithRetry(driver,by,3);
//         this.transactionId = allReservation.get(allReservation.size()-1).findElement(By.xpath("th")).getText();
//         // by = By.xpath("th");
//         // this.transactionId = allReservation.get(allReservation.size()-1).SeleniumWrapper.findElementWithRetry(driver,by,3).getText();
//     }
//     public void reservationHistory(String strAdventureId,String transactionId)
//     {
//         System.out.println("The transaction id :: "+transactionId);
//         System.out.println("The strAdcenture id :: "+strAdventureId);
//        // this.viewReservationButton = this.driver.findElement(By.xpath("//th[text()='"+transactionId+"']/following-sibling::td//*[text()='Visit Adventure']"));
//         by = By.xpath("//th[text()='"+transactionId+"']/following-sibling::td//*[text()='Visit Adventure']");
//        this.viewReservationButton = SeleniumWrapper.findElementWithRetry(driver,by,3);
//         this.viewReservationButton.click();
//         // Assert.assertTrue(SeleniumWrapper.click(driver, this.viewReservationButton),"Clicking on viewReservationButton failed");
//         Assert.assertTrue(this.driver.getCurrentUrl().contains("adventures/detail")&&this.driver.getCurrentUrl().endsWith(strAdventureId),"Not landed in adventure detail page");
//     }
//     public void cancelReservation(String transactionId) throws InterruptedException
//     {
//         this.cancelButton=this.driver.findElement(new ByChained(By.id(transactionId),By.className("cancel-button")));
//         System.out.println("The cancel button string :: "+this.cancelButton);
//         this.cancelButton.click();
//          Assert.assertTrue(SeleniumWrapper.click(driver, this.cancelButton),"Clicking on cancelButton failed");
//         Thread.sleep(5000);
//         this.driver.navigate().refresh();
//         System.out.println("Transaction id after refresh : "+transactionId);
//         Thread.sleep(5000);
//         try
//         {
//              this.driver.findElement(By.xpath("//*[contains(@id,'"+transactionId+"')]"));
//             by = By.xpath("//*[contains(@id,'"+transactionId+"')]");
//            // SeleniumWrapper.findElementWithRetry(driver,by,3);
//             // Assert.assertTrue(false,"Cancellation unsuccessful");
//         }
//         catch(NoSuchElementException excception)
//         {
//             Assert.assertTrue(true,"Cancellation unsuccessful");
//         }
//     }
//     public boolean checkReservationAvail(String adventureNameSTR,String guestNameSTR,String dateSTR)
//     {
//         // WebElement guestNameWE = this.driver.findElement(By.xpath("//td[contains(text(),'"+guestNameSTR+"')]"));
//         by = By.xpath("//td[contains(text(),'"+guestNameSTR+"')]");
//        WebElement guestNameWE = SeleniumWrapper.findElementWithRetry(driver,by,3);
//         System.out.println(" adventurename :: "+guestNameWE.getText());
//         // WebElement adventureNameWE = guestNameWE.findElement(By.xpath("following-sibling::td[contains(text(),'"+adventureNameSTR+"')]"));
//         // System.out.println(" adventureNameWE :: "+adventureNameWE.getText());
//         return guestNameWE.isDisplayed();
//     }
// }