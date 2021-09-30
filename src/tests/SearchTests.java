package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search")})
    @Description("Test search text")
    @Step("Starting test testCancelSearchText")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCancelSearchText() { //задание ex3

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitSearchElementAndSendClear();
        SearchPageObject.waitSearchInvisibilityElement();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @Description("Test check page title")
    @Step("Starting test testCheckPageTitle")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckPageTitle() { //задание ex6

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertTitlePage();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @Description("Search three title of search")
    @Step("Search test testCheckTitleAndDescriptionOfArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckTitleAndDescriptionOfArticle(){ // ex9
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Indonesian island");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "High-level programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Object-oriented programming language");
    }


    @Test
    @Features(value = {@Feature(value="Search")})
    @Description("Test cansel search")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitElementList();
        SearchPageObject.clickCancelSearch();
    }
}
