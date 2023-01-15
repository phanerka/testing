package waits;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {
    public static ExpectedCondition<Boolean> jQueryAJAXsCompleted() {
        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript(
                        "return (window.jQuery != null) && (jQuery.active === 0);");

            }
        };
    }
}
