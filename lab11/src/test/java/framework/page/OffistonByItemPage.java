package framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class OffistonByItemPage extends AbstractPage {

    public OffistonByItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("Opened Item Page");
    }
    @FindBy(xpath = "//div[@class='buy-view']/div/div[@class='p-crd-info__buy-block']/div[@class='count-control p-crd-info__count-control hidden-xs js-add-one-box']/input")
    WebElement amountOfItemsToAddToCartInput;

    @FindBy(xpath = "//button[contains(@class, 'btn btn-success btn-full-w js_orderButton')]")
    WebElement addToShoppingCartButton;

    @FindBy(xpath = "//textarea[@id='form-message-FIELDS[MESSAGE]-form_reviews']")
    WebElement addReviewInput;


    // a href="#prod-tab-review" - switch to this tab
    // textarea class="comment-textarea"
    public OffistonByItemPage addItemReview(String comment) {

        By shoppingCartButtonByXpath = By.xpath("//*[@class=\"item cabinet hint-cabinet\"]/div/a");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButtonByXpath)).click();
        //shoppingCartButton.click();
        logger.info("opened Shopping Cart Page");
        return this;
    }

    // checkReviewLimit

    public Boolean checkReviewCharacterAmountLimit() {
        // doing implicit because I can't be sure *what* affects amount of characters being limited
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        return (addReviewInput.getText().length() > 500);
    }

        // input[@class='count-control__input js-add-one-box-input']
    // button[@class='btn btn-success btn-full-w js-orderButton']
    public OffistonByItemPage addItemsToShoppingCart(int quantity) {

        amountOfItemsToAddToCartInput.clear();
        amountOfItemsToAddToCartInput.sendKeys(Integer.toString(quantity));
        addToShoppingCartButton.click();

        //By shoppingCartButtonByXpath = By.xpath("//button[contains(@class, 'btn btn-success btn-full-w js_orderButton')]");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButtonByXpath)).click();
        //shoppingCartButton.click();
        logger.info("opened Shopping Cart Page");
        return this;
    }

    // checks
    public Boolean verifyAmountOfItemsInShoppingCart(int amount) {
        try {
            // //span[@class='total-count-txt'][contains(text(), '9999')]
            waitForElementLocatedBy(driver, By.xpath("//span[@class='total-count-txt'][contains(text(), '" + amount + "')]"));
            driver.findElement(By.xpath("//span[@class='total-count-txt'][text()='\" + amount + \"']"));
            logger.info("verifyAmountOfItemsInShoppingCart: true");
            return true;
        } catch (NoSuchElementException e) {
            logger.error("verifyAmountOfItemsInShoppingCart: NoSuchElementException");
            return false;
        }
    }

    // checkIfThereIsCorrectAmoutOfItemsInShoppingCart

    @Override
    public OffistonByItemPage waitForPageToLoad() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(@class, 'btn btn-success btn-full-w js_orderButton')]")));
        return this;
    }

    // open pages
}
