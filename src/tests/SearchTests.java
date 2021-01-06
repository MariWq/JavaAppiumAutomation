package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCancelSearchText() { //задание ex3

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitSearchElementAndSendClear();
        SearchPageObject.waitSearchInvisibilityElement();
    }

    @Test
    public void testCheckPageTitle() { //задание ex6

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertTitlePage();
    }


    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitElementList();
        SearchPageObject.clickCancelSearch();
    }

    @Test
    public void testSearchByWord() {

        /*MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot send keys to search input",
                5);

        for (int i = 0; i < 3; i++) {
            MainPageObject.waitElementWithTextBy(By.id("org.wikipedia:id/page_list_item_title"),
                    "Java",
                    "Cannot find text 'Java'",
                    5);
        }*/
    }
}
