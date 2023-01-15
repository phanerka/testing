package page_object.offiston.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OffistonByProfilePage {

    private WebDriver driver;

    String login = ""; // meh
    String password = "";


    public OffistonByProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // a data-target="#modalcontactFeedback"
    //@FindBy(xpath = "//ul[@id='goods_block_books_1']/li[@class='listatic li_1']")
    @FindBy(xpath = "//div[@class='pt-feedback']/div/div[@class='btn-wrap']/a")
    WebElement openFeedbackFormButton;


    // input id="form-message-FIELDS[EMAIL]-contactFeedback"
    @FindBy(xpath = "//ul[@id='goods_block_books_1']/li[@class='listatic li_1']")
    WebElement feedbackEmailInput;

    // button name="form-message-SUBMIT"
    @FindBy(xpath = "//ul[@id='goods_block_books_1']/li[@class='listatic li_1']")
    WebElement submitFeedbackForm;


    // checks ------------------------
/*
    // probs wont be able to implement it
    public Boolean checkFeedbackEmailVerification(Comment commentText) {
        try {
            openFeedbackFormButton.click();
// input css has success
            feedbackEmailInput.sendKeys("фывфыв");
            waitForElementLocatedBy(driver, By.xpath("//p[text()='" + commentText.getCommentText() + "']"));
            driver.findElement(By.xpath("//p[text()='" + commentText.getCommentText() + "']"));
            logger.info("checkIfCommentIsSent: true");
            return true;
        } catch (NoSuchElementException e) {
            logger.error("checkIfCommentIsSent: NoSuchElementException");
            return false;
        }
    }

    // WriteFeedback

*/

    // form-message-FIELDS[MESSAGE]-contactFeedback
    public OffistonByProfilePage openFeedbackForm() {

        By itemCardByXpath = By.xpath("//div[@class='pt-feedback']/div/div[@class='btn-wrap']/a");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(itemCardByXpath)).click();
        return this;
    }

    public OffistonByProfilePage enterFeedback() {

        By itemCardByXpath = By.xpath("//textarea[@id='form-message-FIELDS[MESSAGE]-contactFeedback']");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(itemCardByXpath)).sendKeys("joke");
        return this;
    }


    public OffistonByProfilePage waitForProfilePageToLoad() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='pt-feedback']/div/div[@class='btn-wrap']/a")));
        return this;
    }



}
