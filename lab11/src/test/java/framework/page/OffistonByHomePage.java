package framework.page;

import framework.model.User;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OffistonByHomePage extends AbstractPage {

    @FindBy(xpath = "//ul[@id='goods_block_books_1']/li[@class='listatic li_1']")
    WebElement firstGoodOnTheHomePage;

    @FindBy(xpath = "//div[@id=\"small_basket\"]/div/a")
    WebElement shoppingCartButton;

    // search

    // а по другому никак, в HTML два куска кода чертовски похожи
    @FindBy(xpath = "//*[@id=\"global-v2\"]/div/div/div/header/div[3]/div[2]/div/div/div/div[3]/div/div[1]/div/form/fieldset/input")
    WebElement searchInput;

    // a href="/search/...."
    // //*[@id="global-v2"]/div/div/div/header/div[3]/div[2]/div/div/div/div[3]/div/div[1]/div/form/fieldset/input
    // form[@class='search-form']/fieldset/input[@name='q']
    // /html/body/div[3]/div/div/div/header/div[3]/div[2]/div/div/div/div[3]/div/div[1]/div/form/fieldset/input
    @FindBy(xpath="/html/body/div[3]/div/div/div/header/div[3]/div[2]/div/div/div/div[3]/div/div[1]/div/form/fieldset/input")
    WebElement submitSearchButton;

    // login

    @FindBy(xpath = "//div[@class='cabinet__toggle']")
    WebElement openLoginFormButton;

    @FindBy(xpath = "//input[@id='username'][@name='EMAIL']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='password'][@name='PASSWORD']")
    WebElement passwordField;

    @FindBy(xpath = "//div[@class='forgot']/following-sibling::div[@class='btn-wrap']/input")
    WebElement loginButton;

    // subscription
    @FindBy(xpath = "//div[@class='subscription__input-wrap']/input[@name='EMAIL']")
    WebElement subscriptionInput;

    @FindBy(xpath = "//div[@class='subscription__input-wrap']/following-sibling::button")
    WebElement subscriptionSubmitButton;


    public OffistonByHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("Opened HomePage");
    }

    public OffistonByHomePage waitForHomePageToLoad() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        return this;
    }

    // input class: 'search-input input'
    public OffistonByHomePage searchBy(String searchQuery) {
        logger.info("Searching " + searchQuery);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"global-v2\"]/div/div/div/header/div[3]/div[2]/div/div/div/div[3]/div/div[1]/div/form/fieldset/input")));
        searchInput.click();
        //waitForElementLocatedBy(driver, By.xpath("//input[@class='search-input input']"));
        searchInput.sendKeys(searchQuery);
        searchInput.sendKeys(Keys.ENTER);
        //submitSearchButton.click();
        /*
        logger.info("loginIntoAccount: " + user.getUsername() + " / " + user.getPassword());
        openLoginFormButton.click();
        emailField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        waitForElementLocatedBy(driver, By.xpath("//div[@class='forgot']/following-sibling::div[@class='btn-wrap']/input"));
        loginButton.click();*/

        logger.info("logged in");
        return this;
    }


    public OffistonByHomePage loginIntoAccount(User user) {
        logger.info("loginIntoAccount: " + user.getUsername() + " / " + user.getPassword());
        openLoginFormButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username'][@name='EMAIL']")));
        emailField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        waitForElementLocatedBy(driver, By.xpath("//div[@class='forgot']/following-sibling::div[@class='btn-wrap']/input"));
        loginButton.click();
        logger.info("logged in");
        return this;
    }

    // open pages -------------------------------------

    // cabinet__enter

    public OffistonByHomePage openProfilePage() {
        By shoppingCartButtonByXpath = By.xpath("//*[@class=\"item cabinet hint-cabinet\"]/div/a");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButtonByXpath)).click();
        //shoppingCartButton.click();
        logger.info("opened Profile Page");
        return this;
    }

    public OffistonByHomePage openShoppingCartPage() {
        By shoppingCartButtonByXpath = By.xpath("//*[@id=\"small_basket\"]/div/a");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButtonByXpath)).click();
        //shoppingCartButton.click();
        logger.info("opened Shopping Cart Page");
        return this;
    }

    public OffistonByHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        return this;
    }

    public OffistonByHomePage SubscribeByEmail(String email) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        subscriptionInput.sendKeys(email);
        subscriptionSubmitButton.click();
        return this;
    }

    public Boolean CheckMailingVerificatiion() {
        try {

            driver.findElement(By.xpath("//div[@class='subscription__error']"));
            logger.info("CheckMailingVerificatiion: true");
            return true;
        } catch (NoSuchElementException e) {
            logger.error("CheckMailingVerificatiion: NoSuchElementException");
            return false;
        }
    }



    public OffistonByHomePage openFirstGoodPage() {
        waitForElementLocatedBy(driver, By.xpath("//ul[@id='goods_block_books_1']/li[@class='listatic li_1']"));
        firstGoodOnTheHomePage.click();
        logger.info("openFirstGoodPage");
        return this;
    }

}
