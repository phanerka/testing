package page_object.google_cloud.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage {

    private final int WAIT_TIMEOUT_SECONDS = 20;
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private WebDriver driver;


    @FindBy(xpath = "//div[@class='devsite-search-container']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement inputSearch;


    public GoogleCloudHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String searchFromHomePage(String searchText) {
        buttonSearch.click();
        inputSearch.sendKeys(searchText + Keys.ENTER);
        return searchText;
    }


    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        return this;
    }
}
