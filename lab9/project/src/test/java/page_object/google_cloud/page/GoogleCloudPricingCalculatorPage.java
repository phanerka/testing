package page_object.google_cloud.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorPage {

    private final int TIMEOUT_IN_SECONDS = 25;
    private WebDriver driver;

    @FindBy(xpath = "//iframe[contains(@name,'goog_')]")
    WebElement calculatorFrame;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    WebElement inputNumberOfInstances;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.label']")
    WebElement inputWhatAreInstancesFor;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.series']")
    WebElement selectSeries;

    @FindBy(xpath = "//md-option[@value='n1']")
    WebElement optionN1Series;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.instance']")
    WebElement selectMachineType;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    WebElement optionN2Standard8Machine;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    WebElement checkBoxAddGPUs;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    WebElement selectGPUType;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_V100']")
    WebElement optionGPUTeslaV100;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuCount']")
    WebElement selectNumberOFGPUs;

    @FindBy(xpath = "//md-option[@ng-disabled='item.value != 0 && item.value < listingCtrl.minGPU' and @value='1']")
    WebElement option1GPU;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.ssd']")
    WebElement selectLocalSSD;

    @FindBy(xpath = "//md-option[@ng-repeat='item in listingCtrl.dynamicSsd.computeServer' and @value='2']")
    WebElement option2x375GBLocalSSD;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.location']")
    WebElement selectDatacenterLocation;

    @FindBy(xpath = "//md-option[@ng-repeat='item in listingCtrl.fullRegionList | filter:listingCtrl.inputRegionText.computeServer' and @value='europe-west3']")
    WebElement optionFrankfurtLocation;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.cud']")
    WebElement selectCommittedUsage;

//    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']/md-select-menu/md-content/md-option[@value='1']")
    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']/md-select-menu/md-content/md-option[@value='1' and @ng-value='1']/div[text()='1 Year']")
    WebElement option1YearUsage;

    @FindBy(xpath = "//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    WebElement buttonAddToEstimate;

    @FindBy(xpath = "//h2[@class='md-title']/b[@class='ng-binding']")
    WebElement totalEstimatedCost;

    @FindBy(id = "email_quote")
    WebElement buttonEmailEstimate;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.email']")
    WebElement inputEmail;


    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudPricingCalculatorPage waitForCalculatorPageToLoad() {
        WebDriverWait waitForCalculatorTest = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
        waitForCalculatorTest.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[contains(@name,'goog_')]")));
        driver.switchTo().frame(calculatorFrame);
        driver.switchTo().frame("myFrame");
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseNumberOfInstances(int numberOfInstances) {
        inputNumberOfInstances.click();
        inputNumberOfInstances.sendKeys(String.valueOf(numberOfInstances));
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseWhatAreInstancesFor(String whatAreInstancesForText) {
        inputWhatAreInstancesFor.click();
        inputWhatAreInstancesFor.sendKeys(whatAreInstancesForText);
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseSeries() {
        selectSeries.click();
        optionN1Series.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseMachineType() {
        selectMachineType.click();
        optionN2Standard8Machine.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage addGPUs() {
        checkBoxAddGPUs.click();
        selectGPUType.click();
        optionGPUTeslaV100.click();
        selectNumberOFGPUs.click();
        option1GPU.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage addSSD() {
        selectLocalSSD.click();
        option2x375GBLocalSSD.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseDatacenterLocation() {
        selectDatacenterLocation.click();
        optionFrankfurtLocation = waitForElementLocatedBy(driver, By.xpath
                ("//md-option[@ng-repeat='item in listingCtrl.fullRegionList | " +
                        "filter:listingCtrl.inputRegionText.computeServer' and @value='europe-west3']"));
        optionFrankfurtLocation.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseCommittedUsage() {
        selectCommittedUsage.click();
        option1YearUsage = waitForElementLocatedBy(driver, By.xpath
                ("//div[@class='md-select-menu-container md-active md-clickable']/md-select-menu/" +
                        "md-content/md-option[@value='1' and @ng-value='1']/div[text()='1 Year']"));
        option1YearUsage.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage addToEstimate() {
        buttonAddToEstimate.click();
        return this;
    }

    public String getTotalCost() {
        return totalEstimatedCost.getText();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
