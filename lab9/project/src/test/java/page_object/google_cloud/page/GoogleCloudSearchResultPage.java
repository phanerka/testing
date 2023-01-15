package page_object.google_cloud.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudSearchResultPage {

    private final int WAIT_TIMEOUT_SECONDS = 20;
    private WebDriver driver;

    @FindBy(xpath = "//b[text()='Google Cloud Pricing Calculator']")
    WebElement linkToTheCalculatorPage;

    public GoogleCloudSearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public GoogleCloudSearchResultPage selectSearchResult(String searchText) {
        WebDriverWait waitForPricingCalculatorPage = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        waitForPricingCalculatorPage.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//b[text()='" + searchText + "']")));
        linkToTheCalculatorPage.click();
        return this;
    }
}
