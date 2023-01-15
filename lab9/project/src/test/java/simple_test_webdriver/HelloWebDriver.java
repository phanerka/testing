package simple_test_webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomConditions;
import java.util.List;


public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://pastebin.com/");



        new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXsCompleted());



        // i can win
        WebElement textAreaInput = waitForElementLocatedBy(driver, By.id("postform-text"));
        WebElement selectPasteExpiration = waitForElementLocatedBy(driver, By.id("select2-postform-expiration-container"));
        WebElement inputPasteNameOrTitle = waitForElementLocatedBy(driver, By.id("postform-name"));
        WebElement buttonCreateNewPaste = waitForElementLocatedBy(driver, By.xpath("//button[text()='Create New Paste']"));
        WebElement liPasteExpiration10Minutes;

        // bring it on
        WebElement selectSyntaxHighlighting = waitForElementLocatedBy(driver, By.id("select2-postform-format-container"));
        List<WebElement> liSyntaxHighlightingBash;


        // Bring It On
        textAreaInput.sendKeys("git config --global user.name  \"New Sheriff in Town\"\n" +
                                            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                                            "git push origin master --force\n");
        selectSyntaxHighlighting.click();
        liSyntaxHighlightingBash = driver.findElements(By.xpath("//li[text()='Bash']"));
        liSyntaxHighlightingBash.get(0).click();
        selectPasteExpiration.click();
        liPasteExpiration10Minutes = waitForElementLocatedBy(driver, By.xpath("//li[text()='10 Minutes']"));
        liPasteExpiration10Minutes.click();
        inputPasteNameOrTitle.sendKeys("how to gain dominance among developers");
        buttonCreateNewPaste.click();
        System.out.println(driver.getTitle());



        Thread.sleep(20000);
        driver.quit();
    }



    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
