package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject{

    private static final String
            FOLDER_BY_NAME_TPL = "//android.widget.TextView[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE = "//*[@text='{TITLE}']";

    private static String getFolderXpatchByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE.replace("{TITLE}", article_title);
    }

    public MyListPageObject(AppiumDriver driver){
        super(driver);
    }

    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXpatchByName(name_of_folder);
        this.waitForElementAndClick(By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                5);
    }

    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath = getFolderXpatchByName(article_title);
        this.waitForElementPresentBy(By.xpath(article_xpath),
                "Cannot find save article by title " +  article_title,
                15);
    }

    public void waitForArticleToDissapearByTitle(String article_title){
        String article_xpath = getFolderXpatchByName(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath),
                "Saved article still present with title " +  article_title,
                15);
    }

    public void swipeByArticleToDelete(String article_title)
    {   this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpatchByName(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath),
                "Cannot find title of article " + article_title);
        this.waitForArticleToDissapearByTitle(article_title);
    }
}
