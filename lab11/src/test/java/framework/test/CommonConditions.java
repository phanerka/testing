package framework.test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import framework.driver.DriverSingleton;
import framework.utils.TestListener;


@Listeners({TestListener.class})
public class CommonConditions {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp()
    {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser()
    {
        DriverSingleton.closeDriver();
    }
}
