package framework.test;
import framework.creator.UserCreator;
import framework.model.User;
import framework.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class OfficetonByTest extends CommonConditions {


    private User USER = UserCreator.withCredentialsFromProperty();

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

        new OfficetonByItemPage(driver)
                .waitForPageToLoad()
                .addItemReview("test")
                .checkReviewCharacterAmountLimit();

    }

    @Test
    public void checkBrandSearchFilterTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("тетрадь");

        new OfficetonBySearchPage(driver)
                .waitForPageToLoad()
                .checkBrandFilterByName("pa");
    }

    @Test
    public void openShoppingCartPageTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .loginIntoAccount(USER)
                .openProfilePage();

    }

    @Test
    public void commentLengthLimitTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .loginIntoAccount(USER)
                .openProfilePage();
        new OfficetonByProfilePage(driver)
                .waitForPageToLoad()
                .openFeedbackForm()
                .enterFeedback();
        //        .checkFeedbackEmailVerification();

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

        new OfficetonByItemPage(driver)
                .waitForPageToLoad()
                .addItemsToShoppingCart(9999)
                .verifyAmountOfItemsInShoppingCart(9999);

        //        .waitForPageToLoad()
    }

    @Test
    public void subscriptionEmailVerification() {
        new OfficetonByHomePage(driver)
                .openPage()
                .subscribeByEmail("ayo")
                .checkMailingVerification();

    }

    @Test
    public void checkNoResultsSearchTest() {
        new OfficetonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("!!!");

        new OfficetonBySearchPage(driver)
                .waitForPageToLoad()
                .checkNoResultPage();

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
        new OfficetonByItemPage(driver)
                .addItemsToShoppingCart(5)
                .waitForPageToLoad()
                .addItemsToShoppingCart(1)
                .waitForPageToLoad()
                .verifyAmountOfItemsInShoppingCart(6);
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
        new OfficetonByItemPage(driver)
                .addItemsToShoppingCart(0)
                .waitForPageToLoad()
                .verifyAmountOfItemsInShoppingCart(0);
        // if true, assert false
    }

}
