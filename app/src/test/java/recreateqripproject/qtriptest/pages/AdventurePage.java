package recreateqripproject.qtriptest.pages;

import java.sql.Date;
import java.time.Duration;
import java.util.List;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import recreateqripproject.qtriptest.SeleniumWrapper;
public class AdventurePage {
    WebDriver driver;
    String url;
    SoftAssert softAssert;
    Actions action;
    @FindBy(xpath = "(//div[@class='filter-bar-tile d-flex align-items-center']//div)[1]")
    WebElement click_clear1;
    @FindBy(xpath = "(//div[@class='filter-bar-tile d-flex align-items-center']//div)[2]")
    WebElement click_clear2;
    @FindBy(xpath ="//select[@id='duration-select']")
    WebElement filter_hours;
    @FindBy(xpath = "//select[@id='category-select']")
    WebElement filter_category;
    @FindBy(xpath ="//input[@name='search-adventures']")
    WebElement selectAdventure;
   // @FindBy(xpath ="(//h5[@class='text-left'])[1]")
    @FindBy(xpath="//div[@class='activity-card']")
    WebElement clickAdventure;
    @FindBy(xpath ="//div[@class='col-6 col-lg-3 mb-4']//div[@class='category-banner']")
    List<WebElement> adventures;
        public AdventurePage(WebDriver driver){
                this.driver=driver;
                PageFactory.initElements(driver, this);
            }
        public void adverntureUrl(){
            HomePage city=new HomePage(this.driver);
            this.url=city.currentUrl;
            if(!driver.getCurrentUrl().equalsIgnoreCase(url));
            {
                System.out.println("The correct url is not loaded");
                Assert.assertEquals(driver.getCurrentUrl(), this.url, "The correct url of adventure page is not loaded"); 
            }
        }
        public void clear(){
            WebDriverWait wait=new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(click_clear1));
            System.out.println(click_clear1.isDisplayed()+" yes displayed");
            Assert.assertTrue(SeleniumWrapper.click(this.click_clear1, driver),"click on clear failed");
            Assert.assertTrue(SeleniumWrapper.click(this.click_clear2, driver),"click on clear failed");
            
        }
        public void selectFilterValue(String durationFilter) throws InterruptedException{
            
            Assert.assertTrue(SeleniumWrapper.click(this.filter_hours, driver),"click on filter hours failed");
            Select select = new Select(filter_hours);
            select.selectByVisibleText(durationFilter);
            // List<WebElement> durations = select.getOptions();
            // for(int i=1; i<= durations.size(); i++) {
            //     if(durations.get(i).getText().contains(durationFilter)) {
            //         System.out.println("values of dropdown are:"+ durations.get(i).getText());
            //         durations.get(i).click();
            //         Thread.sleep(2000);
            //         break;
            //     }
           // }

            // Select select=new Select(filter_hours);
            // select.selectByVisibleText(durationFilter);
            // //softAssert= new SoftAssert();
            // SoftAssert sa = new SoftAssert();
            // sa.assertEquals(select.getFirstSelectedOption(), durationFilter, "The correct duration is not selected");
            // Thread.sleep(2000);
            // List<WebElement> data_selected= new ArrayList<WebElement>();
            // data_selected=driver.findElements(By.xpath("//div[@class='category-banner']"));
            // int count =0;
            // for(WebElement searched: data_selected) {
            // 	status=searched.getText().equalsIgnoreCase("Party");
            //     sa.assertTrue(status, "The correct category is not selected");
            // 	System.out.println(searched.getText());
            // 	count ++;

            // }
            // System.out.println(count);
            // sa.assertAll();
    }
    public void setCategoryValue(String category) throws InterruptedException{
        WebDriverWait wait= new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(filter_category));
        Assert.assertTrue(SeleniumWrapper.click(this.filter_category, driver),"click on filter category failed");
        //filter_category.click();
        Select select = new Select(filter_category);
        select.selectByVisibleText(category);
        SoftAssert sa = new SoftAssert();
        sa.assertAll();
        }

    public void getResultCount(String expectedFilteredResults){
        //Boolean status;
        //adventures = new ArrayList<WebElement>();
        WebDriverWait wait= new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(adventures));
        int count =0;
        for(WebElement searched: adventures) {
            System.out.println(searched.getText());
            count ++;
            SoftAssert softAssert=new SoftAssert();
            softAssert.assertEquals(count, Integer.parseInt(expectedFilteredResults)  , "Expected number of adventures are displayed");
        }

        System.out.println("The result after count"+count);
    }
    public void selectAdventure(String AdventureName) throws InterruptedException{
        selectAdventure.sendKeys(AdventureName);
        // WebDriverWait wait= new WebDriverWait(driver, 20);
        // wait.until(ExpectedConditions.visibilityOfAllElements(clickAdventure));
        Thread.sleep(2000);
        Assert.assertTrue(SeleniumWrapper.click(this.clickAdventure, driver),"Clicking on clearCategory failed");
        //clickAdventure.click();
        // WebDriverWait wait=new WebDriverWait(driver, 10);
        // wait.until(ExpectedConditions.visibilityOf(selectAdventure));
        // if(selectAdventure.getText()==AdventureName){
           
        // clickAdventure.click();
    //}
    }
}
