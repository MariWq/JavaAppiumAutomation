import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AdnroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","D:\\QA Courses\\AppiumMobile\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void findElementByText(){

        assertElementHasText(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia" ,
                "Input does not have text 'Search Wikipedia'");
    }

    @Test
    public void cancelSearchTextTest(){

        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot send keys to search input",
                5);

        waitForElementPresentBy(By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find list item",
                5);

        waitForElementAndClear(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5);

        waitInvisibilityOfElementBy(By.id("org.wikipedia:id/page_list_item_title"),
                "Element find",
                5);
    }

    @Test
    public void searchByWordTest(){
    }


    private WebElement waitForElementPresentBy(By by, String error_message, long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private boolean waitInvisibilityOfElementBy(By by, String error_message, long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresentBy(by, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by,  String value, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresentBy(by, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresentBy(by, error_message, timeOutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String value, String error_message){
        WebElement element = waitForElementPresentBy(by, error_message, 5);
        Assert.assertEquals(error_message, value, element.getAttribute("text"));
    }

}
