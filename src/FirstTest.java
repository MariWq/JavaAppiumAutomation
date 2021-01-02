import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        driver.rotate(ScreenOrientation.PORTRAIT);
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

        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot send keys to search input",
                5);

        for(int i = 0; i < 3; i++) {
            waitElementWithTextBy(By.id("org.wikipedia:id/page_list_item_title"),
                    "Java",
                    "Cannot find text 'Java'",
                    5);
        }
    }

    @Test
    public void saveTwoArticlesToListAndDeleteOneOfThem(){

        String listName = "Articles";

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot send keys to 'Search Wikipedia' input",
                5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot send keys to search input",
                5);

        WebElement firstArticle = waitForElementAndClick(By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find title of article 'Object-oriented programming language'",
                5);

        waitForElementPresentBy(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                5);

        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5);

        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to open article to reading list",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button to create new article list ",
                5);

        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                listName,
                "Cannot put text into articles folder  input",
                5);

        waitForElementAndClear(By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article",
                5);

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot send keys to 'Search Wikipedia' input",
                5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot send keys to search input",
                5);

        WebElement secondArticle = waitForElementAndClick(By.xpath("//*[@text='Programming language']"),
                "Cannot find title of article 'Programming language'",
                5);

        waitForElementPresentBy(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                5);

        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5);

        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to open article to reading list",
                5);

        waitForElementAndClick(By.xpath("//android.widget.TextView[@text='" + listName + "']"),
                "Cannot find button to open article options",
                5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article",
                5);

        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My list",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find created folder",
                5);

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot swipe articles");

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Element on the page",
                10);

        waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find element JavaScript",
                5);

        WebElement pageJavaScript = waitForElementPresentBy(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find page title JavaScript",
                10);

        assertEquals("Article title is't JavaScript ",
                pageJavaScript.getText(),
                "JavaScript");
    }

    @Test
    public void checkPageTitle(){

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot send keys to 'Search Wikipedia' input",
                5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot send keys to search input",
                5);

        waitForElementAndClick(By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find title of article 'Object-oriented programming language'",
                5);

        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title page ");
    }


    private Boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
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

    private boolean waitElementWithTextBy(By by, String value, String error_message, long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(by, value));
    }

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while(driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes){
                waitForElementPresentBy(by, "Cannot find element" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresentBy(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();

        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmoumtOfElement(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementPresent(By by, String error_message) {
        int amout_of_elements = getAmoumtOfElement(by);
        if (amout_of_elements == 0){
            String default_message = "An element " + by.toString() + " supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
}