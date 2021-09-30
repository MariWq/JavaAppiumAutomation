package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    public static final String
            login = "learnqa",
            password = "123qweASD";

    @Test
    public void testSaveTwoArticlesToListAndDeleteOneOfThem() { // задание ex5

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Test folder";
        System.out.println(article_title);

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMylist(name_of_folder);
        }else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if(Platform.getInstance().isIMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());
        }

        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Programming language");

        ArticlePageObject ArticlePageObjectTwo = ArticlePageObjectFactory.get(driver);
        ArticlePageObjectTwo.waitForTitleElement();
        String articleTwo_title = ArticlePageObjectTwo.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMylist(name_of_folder);
        }else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if(Platform.getInstance().isIMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    articleTwo_title,
                    ArticlePageObject.getArticleTitle());
        }
        // ArticlePageObjectTwo.addOneArticleMyTolist(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);

        Assert.assertEquals("Article title is't JavaScript ",
                articleTwo_title,
                "JavaScript");
    }

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMylist(name_of_folder);
        }else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if(Platform.getInstance().isIMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }/*else {
            MyListPageObject.openFolderByName();
        }*/
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToListAndDeleteOne() { // задание ex17

        //авторизация
        AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
        if(Platform.getInstance().isIMw()){

            Auth.openMainMenu();
            Auth.clickAuthButton();

            Auth.enterLoginData(login, password);
            Auth.submitForm();
            Auth.clickSkipButton();
        }

        //поиск статей
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        //отметить 2 статьи в избранное по description
        SearchPageObject.setWatchStarForArticle("Indonesian island");
        SearchPageObject.setWatchStarForArticle("High-level programming language");
        SearchPageObject.clickCancelSearch();

        //открыть список статей и убрать звездочку с одной из них
        Auth.openMainMenu();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickWatchList();

        MyListPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        MyListPageObject.swipeByArticleToDelete("Java");
    }
}
