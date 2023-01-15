package page_object.pastebin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomConditions;

import java.util.List;

public class PasteBinPage {

    private final int WAIT_TIMEOUT_SECONDS = 15;
    private WebDriver driver;

    @FindBy(id = "postform-text")
    WebElement textAreaInput;

    @FindBy(id = "select2-postform-expiration-container")
    WebElement selectPasteExpiration;

    @FindBy(id = "postform-name")
    WebElement inputPasteNameOrTitle;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    WebElement buttonCreateNewPaste;

    @FindBy(id = "select2-postform-format-container")
    WebElement selectSyntaxHighlighting;

    WebElement liPasteExpiration10Minutes;


    public PasteBinPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PasteBinPage waitForPastebinPageLoad() {
        driver.get("https://pastebin.com/");
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.jQueryAJAXsCompleted());
        return this;
    }


    public PasteBinPage inputMessageInTextarea(String message) {
        textAreaInput.sendKeys(message);
        return this;
    }

    public PasteBinPage selectPasteExpiration(String pasteExpirationText) {
        selectPasteExpiration.click();
        liPasteExpiration10Minutes = waitForElementLocatedBy(driver, By.xpath("//li[text()='" + pasteExpirationText + "']"));
        liPasteExpiration10Minutes.click();
        return this;
    }

    public PasteBinPage inputPasteName(String pasteName) {
        inputPasteNameOrTitle.sendKeys(pasteName);
        return this;
    }

    public PasteBinPage clickButtonCreateNewPaste() {
        buttonCreateNewPaste.click();
        return this;
    }

    public PasteBinPage selectSyntaxHighlighting(String syntaxHighlighting) {
        selectSyntaxHighlighting.click();
        List<WebElement> liSyntaxHighlightingBash = driver.findElements(By.xpath("//li[text()='" + syntaxHighlighting + "']"));
        liSyntaxHighlightingBash.get(0).click();
        return this;
    }

    public String getPageTitle() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        return driver.getTitle();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
