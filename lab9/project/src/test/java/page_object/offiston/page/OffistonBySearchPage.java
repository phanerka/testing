package page_object.offiston.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OffistonBySearchPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='prod-card-list js-hover ']/div/div/div/div[@class='prod-txt-content prod-txt-content_mod']/div[@class='title-wrap']/a")
    WebElement firstItemCard;

    @FindBy(xpath= "//input[@class='form-control  js-input-filter-items__input']")
    WebElement brandFilterInput;


    public OffistonBySearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

// //*[@id="comp_5abeda8fff194873b276b41781b77c4f"]/div/div[1]/div[4]/div[1]/div[2]/div[1]/input
    public OffistonBySearchPage OpenFirstItemDetailsPage() {

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");

        By itemCardByXpath = By.xpath("//div[@class='prod-card-list js-hover ']/div/div/div/div[@class='prod-txt-content prod-txt-content_mod']/div[@class='title-wrap']/a");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(itemCardByXpath)).click();
        return this;
    }


    public Boolean checkBrandFilterByName(String brandName) {

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,500)");

        // //div[@id='mCSB_3_container']/div/div

        brandFilterInput.sendKeys(brandName);

        // //div[@id='mCSB_3_container']/div/div[1]/label/div[@class='text']
        List<WebElement> elementsList = driver.findElements(By.xpath("//div[@id='mCSB_3_container']/div/div/label/div[@class='text']"));
        for (WebElement element : elementsList) {
            String elementName = element.getText();

            if (!elementName.contains(brandName) && !elementName.equals(""))
                return false;
        }
        return true;
    }
    // reroutes

    // //div[@class='prod-card-list js-hover']/div[0]/div/div/div/div/a
    // a.click()



    // checks


    public Boolean checkNoResultPage() {
        try {
            driver.findElement(By.xpath("//p[contains(text(), '» ничего не найдено')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public OffistonBySearchPage waitForSearchPageToLoad() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='pt-wrap_i']")));
        return this;
    }
}
