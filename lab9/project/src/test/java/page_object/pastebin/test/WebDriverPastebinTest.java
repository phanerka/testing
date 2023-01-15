package page_object.pastebin.test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_object.pastebin.page.PasteBinPage;


public class WebDriverPastebinTest {

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
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
    public void ICanWinTest() {
        String textAreaMessage = "Hello from WebDriver";
        String pasteExpiration = "10 Minutes";
        String pasteName = "helloweb";
        String expectedPageTitle = pasteName + " - Pastebin.com";
        String actualPageTitle = new PasteBinPage(driver)
                .waitForPastebinPageLoad()
                .inputMessageInTextarea(textAreaMessage)
                .selectPasteExpiration(pasteExpiration)
                .inputPasteName(pasteName)
                .clickButtonCreateNewPaste()
                .getPageTitle();
        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }


    @Test
    public void BringItOnTest() {
        String pasteName = "how to gain dominance among developers";
        String pasteExpiration = "10 Minutes";
        String syntaxHighlighting = "Bash";
        String expectedPageTitle = pasteName + " - Pastebin.com";
        String textAreaMessage = "git config --global user.name  \"New Sheriff in Town\"\n" +
                                 "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                                 "git push origin master --force";

        String actualPageTitle = new PasteBinPage(driver)
                .waitForPastebinPageLoad()
                .inputMessageInTextarea(textAreaMessage)
                .selectPasteExpiration(pasteExpiration)
                .selectSyntaxHighlighting(syntaxHighlighting)
                .inputPasteName(pasteName)
                .clickButtonCreateNewPaste()
                .getPageTitle();
        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }
}
