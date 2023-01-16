package framework.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class OfficetonByItemPage extends AbstractPage {

    public OfficetonByItemPage(WebDriver driver) {
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
    public OfficetonByItemPage addItemReview(String comment) {

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,500)");

        By shoppingCartButtonByXpath = By.xpath("//a[contains(@href, '#prod-tab-review')]");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButtonByXpath)).click();


        try {
            WebDriverWait wait2 = new WebDriverWait(driver, 30);
            wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='form-message-FIELDS[MESSAGE]-form_reviews']"))).sendKeys(comment);
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement addReviewInput2 = driver.findElement(By.xpath("//textarea[@id='form-message-FIELDS[MESSAGE]-form_reviews']"));
            addReviewInput2.sendKeys(comment);
            addReviewInput2.sendKeys(Keys.ENTER);
        }


        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='form-message-FIELDS[MESSAGE]-form_reviews']"))).sendKeys(comment);

        //shoppingCartButton.click();
        logger.info("opened Item Review Tab");
        return this;
    }

    // checkReviewLimit

    public Boolean checkReviewCharacterAmountLimit() {
        // doing implicit because I can't be sure *what* affects amount of characters being limited
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        return (addReviewInput.getAttribute("value").length() <= 500);
    }

        // input[@class='count-control__input js-add-one-box-input']
    // button[@class='btn btn-success btn-full-w js-orderButton']
    public OfficetonByItemPage addItemsToShoppingCart(int quantity) {

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
            driver.findElement(By.xpath("//span[@class='total-count-txt'][contains(text(),'" + amount + "')]"));
            logger.info("verifyAmountOfItemsInShoppingCart: true");
            return true;
        } catch (NoSuchElementException e) {
            logger.error("verifyAmountOfItemsInShoppingCart: NoSuchElementException");
            return false;
        }
    }

    // checkIfThereIsCorrectAmoutOfItemsInShoppingCart

    @Override
    public OfficetonByItemPage waitForPageToLoad() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(@class, 'btn btn-success btn-full-w js_orderButton')]")));
        return this;
    }

    // open pages
}
