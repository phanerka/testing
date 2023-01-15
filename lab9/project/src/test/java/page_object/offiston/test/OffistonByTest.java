package page_object.offiston.test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page_object.offiston.page.*;

public class OffistonByTest {

    WebDriver driver;

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
        new OffistonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .loginIntoAccount()
                .searchBy("тетрадь");
        new OffistonBySearchPage(driver)
                .waitForSearchPageToLoad()
                .OpenFirstItemDetailsPage();

        new OffistonByItemPage(driver)
                .waitForItemPageToLoad()
                .addItemReview("test")
                .checkReviewCharacterAmountLimit();

    }

    @Test
    public void checkBrandSearchFilterTest() {
        new OffistonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("тетрадь");

        new OffistonBySearchPage(driver)
                .waitForSearchPageToLoad()
                .checkBrandFilterByName("pa");
    }

    @Test
    public void openShoppingCartPageTest() {
        new OffistonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .loginIntoAccount()
                .openProfilePage();

    }

    @Test
    public void commentLengthLimitTest() {
        new OffistonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .loginIntoAccount()
                .openProfilePage();
        new OffistonByProfilePage(driver)
                .waitForProfilePageToLoad()
                .openFeedbackForm()
                .enterFeedback();
        //        .checkFeedbackEmailVerification();

    }

    @Test
    public void maxItemQuantityTest() {
        new OffistonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("тетрадь");

        new OffistonBySearchPage(driver)
                .waitForSearchPageToLoad()
                .OpenFirstItemDetailsPage();

        new OffistonByItemPage(driver)
                .waitForItemPageToLoad()
                .addItemsToShoppingCart(9999)
                .verifyAmountOfItemsInShoppingCart(9999);

        //        .waitForPageToLoad()
    }

    @Test
    public void subscriptionEmailVerification() {
        new OffistonByHomePage(driver)
                .openPage()
                .subscribeByEmail("ayo")
                .CheckMailingVerification();

    }

    @Test
    public void checkNoResultsSearchTest() {
        new OffistonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("!!!");

        new OffistonBySearchPage(driver)
                .waitForSearchPageToLoad()
                .checkNoResultPage();

    }

    @Test
    public void itemsToShoppingCartAdditionTest() {
        new OffistonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("тетрадь");
        new OffistonBySearchPage(driver)
                .waitForSearchPageToLoad()
                .OpenFirstItemDetailsPage();
        new OffistonByItemPage(driver)
                .addItemsToShoppingCart(5)
                .waitForItemPageToLoad()
                .addItemsToShoppingCart(5)
                .verifyAmountOfItemsInShoppingCart(10);
    }


    @Test
    public void addZeroItemsToShoppingCartTest() {
        new OffistonByHomePage(driver)
                .waitForHomePageToLoad()
                .openPage()
                .searchBy("тетрадь");
        new OffistonBySearchPage(driver)
                .waitForSearchPageToLoad()
                .OpenFirstItemDetailsPage();
        new OffistonByItemPage(driver)
                .addItemsToShoppingCart(0)
                .waitForItemPageToLoad()
                .verifyAmountOfItemsInShoppingCart(0);
        // if true, assert false
    }

}
