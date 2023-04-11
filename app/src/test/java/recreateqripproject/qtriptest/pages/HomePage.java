package recreateqripproject.qtriptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import recreateqripproject.qtriptest.SeleniumWrapper;


public class HomePage {
    WebDriver driver;
    //open the hompage URL
    String Url="https://qtripdynamic-qa-frontend.vercel.app/";
    String currentUrl;
    Actions action;
    //@FindBy(xpath ="//a[text()='Register']")
    @FindBy(xpath ="//a[@class='nav-link login register']")
    WebElement clickRegister;
    @FindBy(xpath ="//div[text()='Logout']")
    WebElement logoutButton;
    @FindBy(xpath ="//input[@id='autocomplete']")
    WebElement inputCity;
    @FindBy(xpath ="//ul[@id='results']")
    WebElement availabilityOfCity;
    @FindBy(xpath ="//div[@class='tile']")
    WebElement allCities;
    // @FindBy(xpath ="")
    // WebElement
    public HomePage(WebDriver driver){
       this.driver=driver;
       PageFactory.initElements(driver, this);
    }
    
    public void openHomePage(){
    if(!driver.getCurrentUrl().equals(this.Url)){
        driver.navigate().to(Url);;
    }
    }

    public void clickRegister() throws InterruptedException{
        Thread.sleep(2000);
        //clickRegister.click();
        Assert.assertTrue(SeleniumWrapper.click(this.clickRegister, driver),"Clicking on registerButton failed");
        Assert.assertEquals(this.driver.getCurrentUrl().endsWith("/register/"), true,"Not redirected to register page");
    }
    public boolean isUserLoggedIn(){
        // Boolean status;
        // WebDriverWait waitTillLoads=new WebDriverWait(driver,10);
        // waitTillLoads.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Logout']")));
        // //Thread.sleep(1000);
        // String logutAvailable= logoutButton.getText();
        // status= logutAvailable.equalsIgnoreCase("Logout");
        // SoftAssert softAssert=new SoftAssert();
        // softAssert.assertTrue(status, "Page is not able to login");
        // softAssert.assertAll();
        
        return logoutButton.isDisplayed();
    }


        public void logOutUser(){
            if(logoutButton.isDisplayed())
            //logoutButton.click();
            System.out.println(logoutButton.isDisplayed()+" the logoutbutton is displayed");
            Assert.assertTrue(SeleniumWrapper.click(logoutButton, driver),"Clicking on logoutButton failed");
        
        }


    public void searchCity(String City) throws InterruptedException {
       inputCity.clear();
       Thread.sleep(5000);
       inputCity.sendKeys(City);
        
        // action=new Actions(this.driver);
        // action.moveToElement(inputCity);
        // action.sendKeys(inputCity, City);
        // action.perform();
        // if(status)
        // {
        // availabilityOfCity.click();
        // Thread.sleep(3000);
        // currentUrl=driver.getCurrentUrl();
        // }
        //  else
        // {
        //     SoftAssert softAssert= new SoftAssert();
        //     softAssert.assertTrue(availabilityOfCity.getText().equalsIgnoreCase("No City found"), "The expected city"+City+"is not available"); 
        //     softAssert.assertAll(); 
        // }
    }
    public boolean assertAutoCompleteText(String City) throws InterruptedException{
        Boolean status;
        //inputCity.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        status=availabilityOfCity.getText().equalsIgnoreCase(City);
        
        if(!status)
        inputCity.clear();
        Assert.assertTrue(status, "No City found - " + City);
        return status;
    }
     public void selectCity(String City) throws InterruptedException {
        
        Boolean status;
        status=assertAutoCompleteText(City);
        if(status){
            Assert.assertTrue(SeleniumWrapper.click(this.availabilityOfCity, driver),"Clicking on availability Of City failed");
            // action= new Actions(driver);
            // action.moveToElement(availabilityOfCity);
            // action.click();
            // action.perform();
            //availabilityOfCity.click();
        }



        // status = homePage.searchForProduct(TC3_ProductNameToSearchFor);
        // assertTrue(status, "Unable to search for given product - " + TC3_ProductNameToSearchFor);

        // // Fetch the search results
        // List<WebElement> searchResults = homePage.getSearchResults();

        // // Verify the search results are available
        // assertFalse(searchResults.size() == 0,
        //         "There were no results for the given search string - " + TC3_ProductNameToSearchFor);

        // for (WebElement webElement : searchResults) {
        //     // Create a SearchResult object from the parent element
        //     SearchResult resultelement = new SearchResult(webElement);

        //     // Verify that all results contain the searched text
        //     String elementText = resultelement.getTitleofResult();
        //     assertTrue(elementText.toUpperCase().contains(TC3_ProductNameToSearchFor),
        //             "Test Results contains un-expected values: " + elementText);
        // }
     }   
        
}
