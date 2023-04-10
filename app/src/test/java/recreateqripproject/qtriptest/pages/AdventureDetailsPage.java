package recreateqripproject.qtriptest.pages;


import java.sql.Date;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import recreateqripproject.qtriptest.SeleniumWrapper;

public class AdventureDetailsPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@name='name']")
    WebElement nameOfCustomer;

    @FindBy(xpath="//input[@name='date']")
    WebElement dateOfBooking;

    @FindBy(xpath="//input[@type='number']")
    WebElement noOfPerson;
    @FindBy(xpath = "//button[@class='reserve-button']")
    WebElement reserve;
    @FindBy(xpath ="//div[@class='alert alert-success']")
    WebElement booked;

    public AdventureDetailsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public void bookAdventure(String Name,String Date,String Person) throws InterruptedException{
            Thread.sleep(1000);
            //nameOfCustomer.sendKeys(Name);
            Assert.assertTrue(SeleniumWrapper.sendKeys(this.nameOfCustomer, Name),"Input to Guest name failed");
            //dateOfBooking.sendKeys(Date);
            Assert.assertTrue(SeleniumWrapper.sendKeys(this.dateOfBooking, Date),"Input to Date failed");
            noOfPerson.clear();
            //noOfPerson.sendKeys(Person);
            Assert.assertTrue(SeleniumWrapper.sendKeys(this.noOfPerson, Person),"Input to personCount failed");
            Thread.sleep(1000);
            // Actions act=new Actions(driver);
            // act.moveToElement(reserve);
            // act.click();
            // act.perform();
            Assert.assertTrue(SeleniumWrapper.click(this.reserve, driver),"reservation failed");

        //this.reserveAdventureButton.click();
        // Assert.assertTrue(SeleniumWrapper.click(driver, this.reserveAdventureButton),"Clicking on reserveAdventureButton failed");
    }
    public boolean isBookingSuccessful(){
         FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofSeconds(1))
        .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions. visibilityOf(booked));
           if(booked.isDisplayed()){
            System.out.println("The booking was sucessful");
            return true;
            
           }
         return false;
            
    }
}