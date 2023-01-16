package framework.test;
import framework.creator.CommentCreator;
import framework.creator.UserCreator;
import framework.model.Comment;
import framework.model.User;
import framework.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;


public class OfficetonByTest extends CommonConditions {


    private User USER = UserCreator.withCredentialsFromProperty();

    private Comment COMMENT = CommentCreator.getCommentText();

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
        driver = null;
    }


    @Test
    public void checkItemReviewLimit() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .loginIntoAccount(USER)
                .searchBy("тетрадь");
        new OfficetonBySearchPage(driver)
                .waitForPageToLoad()
                .OpenFirstItemDetailsPage();

        Boolean reviewCharacterAmountLimitCheck = new OfficetonByItemPage(driver)
                .waitForPageToLoad()
                .addItemReview(COMMENT.getCommentText())
                .checkReviewCharacterAmountLimit();

        Assert.assertTrue(reviewCharacterAmountLimitCheck);
    }

    @Test
    public void checkBrandSearchFilterTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("тетрадь");

        Boolean verifyBrandFilterByNameCheck =  new OfficetonBySearchPage(driver)
                .waitForPageToLoad()
                .checkBrandFilterByName("pa");
        Assert.assertTrue(verifyBrandFilterByNameCheck);
    }
/*
    @Test
    public void openShoppingCartPageTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .loginIntoAccount(USER)
                .openProfilePage();

    }
*/
    @Test
    public void commentLengthLimitTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .loginIntoAccount(USER)
                .openProfilePage();
        Boolean feedbackMessageLimitCheck = new OfficetonByProfilePage(driver)
                .waitForPageToLoad()
                .openFeedbackForm()
                .enterFeedback(COMMENT.getCommentText())
                .checkFeedbackMessageVerification();

        Assert.assertTrue(feedbackMessageLimitCheck);
    }

    @Test
    public void maxItemQuantityTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("тетрадь");

        new OfficetonBySearchPage(driver)
                .waitForPageToLoad()
                .OpenFirstItemDetailsPage();

        Boolean restrictedAmountOfItemsInCart = new OfficetonByItemPage(driver)
                .waitForPageToLoad()
                .addItemsToShoppingCart(9999)
                .verifyAmountOfItemsInShoppingCart(9999);

        Assert.assertFalse(restrictedAmountOfItemsInCart);
    }

    @Test
    public void subscriptionEmailVerification() {
        Boolean mailVerificationCheck = new OfficetonByHomePage(driver)
                .openPage()
                .subscribeByEmail("ayo")
                .checkMailingVerification();

        Assert.assertTrue(mailVerificationCheck);

    }

    @Test
    public void checkNoResultsSearchTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("!!!");

        Boolean noResultPageCheck = new OfficetonBySearchPage(driver)
                .waitForPageToLoad()
                .checkNoResultPage();

        Assert.assertTrue(noResultPageCheck);
    }

    @Test
    public void itemsToShoppingCartAdditionTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("тетрадь");
        new OfficetonBySearchPage(driver)
                .waitForPageToLoad()
                .OpenFirstItemDetailsPage();
        Boolean addingAmountOfItemsInShoppingCartCheck = new OfficetonByItemPage(driver)
                .addItemsToShoppingCart(5)
                .waitForPageToLoad()
                .addItemsToShoppingCart(1)
                .waitForPageToLoad()
                .verifyAmountOfItemsInShoppingCart(6);

        Assert.assertTrue(addingAmountOfItemsInShoppingCartCheck);

    }


    @Test
    public void addZeroItemsToShoppingCartTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("тетрадь");
        new OfficetonBySearchPage(driver)
                .waitForPageToLoad()
                .OpenFirstItemDetailsPage();
        Boolean amountOfItemsInCartCheck = new OfficetonByItemPage(driver)
                .addItemsToShoppingCart(0)
                .waitForPageToLoad()
                .verifyAmountOfItemsInShoppingCart(0);
        Assert.assertFalse(amountOfItemsInCartCheck);
    }

}
