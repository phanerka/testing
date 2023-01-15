package framework.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OfficetonByProfilePage extends AbstractPage {


    public OfficetonByProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("Opened Profile Page");
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
    public OfficetonByProfilePage openFeedbackForm() {

        By itemCardByXpath = By.xpath("//div[@class='pt-feedback']/div/div[@class='btn-wrap']/a");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(itemCardByXpath)).click();
        logger.info("opened Feedback Form");
        return this;
    }

    public OfficetonByProfilePage enterFeedback() {

        By itemCardByXpath = By.xpath("//textarea[@id='form-message-FIELDS[MESSAGE]-contactFeedback']");
        //waitForElementLocatedBy(driver, By.xpath("//*[@id=\"small_basket\"]/div/a"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(itemCardByXpath)).sendKeys("joke");
        logger.info("Wrote smth to feedback");
        return this;
    }


    @Override
    public OfficetonByProfilePage waitForPageToLoad() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='pt-feedback']/div/div[@class='btn-wrap']/a")));
        return this;
    }



}
