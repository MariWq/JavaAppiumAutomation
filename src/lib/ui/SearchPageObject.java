package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]",
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']",
        SEARCH_LIST = "id:org.wikipedia:id/page_list_item_title",
        SEARCH_ARTICLE_NAME = "xpath://*[@text='{ARTICLENAME}']";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getTitleName(String article_name){
        return SEARCH_ARTICLE_NAME.replace("{ARTICLENAME}", article_name);
    }
    /*TEMPLATES METHODS*/

    public void initSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5);
        this.waitForElementPresentBy(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element",
                5);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresentBy(SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5);
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        this.waitForElementPresentBy(getTitleName(title),
                "Cannot find title article " + title,
                5);
        this.waitForElementPresentBy(getTitleName(description),
                "Cannot find title description " + description,
                5);
    }

    public void waitElementList(){
        this.waitForElementPresentBy(SEARCH_LIST,
                "Cannot find list item",
                5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT,
                search_line,
                "Cannot find and click search chancel button",
                5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpatch = getResultSearchElement(substring);
        this.waitForElementPresentBy(search_result_xpatch,
                "Cannot find search result with substring " + substring,
                5);
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpatch = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpatch,
                "Cannot find and click search result with substring " + substring,
                5);
    }

    public void waitSearchElementAndSendClear(){
        this.waitForElementAndSendClear(SEARCH_INIT_ELEMENT,
                "Cannot find search input",
                5);
    }

    public void waitSearchInvisibilityElement(){
        this.waitInvisibilityOfElementBy(SEARCH_LIST,
                "Element find",
                5);
    }
}
