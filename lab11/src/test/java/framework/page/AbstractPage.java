package framework.page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractPage {

    protected WebDriver driver;
    protected final Logger logger = LogManager.getRootLogger();
    protected final int WAIT_TIMEOUT_SECONDS = 15;
    protected final String HOMEPAGE_URL = "https://officeton.by";


    protected WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public AbstractPage waitForPageToLoad() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("")));
        return this;
    }
}
