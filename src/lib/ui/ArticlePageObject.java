package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_TITLE = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresentBy(By.id(TITLE), "Cannot find article title on page", 5);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getText(); //getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                5);
    }

    public void addArticleToMylist(String nameOfFolder){

        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5);

        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to open article to reading list",
                5);

        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find button to create new article list ",
                5);

        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of articles",
                5);

        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT),
                nameOfFolder,
                "Cannot put text into articles folder  input",
                5);

        this.waitForElementAndClear(By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5);
    }

    public void closeArticle(){

        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_TITLE),
                "Cannot close article",
                5);
    }

    public void assertTitlePage(){
       this.assertElementPresent(By.id(TITLE), "An element supposed to be present");
    }

    public void addOneArticleMyTolist(String name_of_folder){

        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5);

        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to open article to reading list",
                5);

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
    }
}
