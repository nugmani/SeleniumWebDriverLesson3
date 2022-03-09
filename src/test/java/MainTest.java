import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class MainTest {

    WebDriver driver = null;

    @Before
    public void init() {
        System.setProperty("webdriver.gecko.driver", "drvs/geckodriver.exe");

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Test
    public void testMethod() {

        driver.get("http://www.rshb.ru/");

        WebElement internetBankButton = driver.findElement(By.id("ibank"));
        internetBankButton.click();

        Assert.assertEquals("Страница не Интернет банка!","Интернет-банк РСХБ",driver.getTitle());

        System.out.println("Открыта страница авторизации");

        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='ВХОД В ИНТЕРНЕТ-БАНК']")));

        WebElement loginInput = driver.findElement(By.id("textfield"));
        loginInput.sendKeys("тест");

        WebElement passInput = driver.findElement(By.id("passwordfield"));
        passInput.sendKeys("тест");

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        Assert.assertEquals("тест",loginInput.getAttribute("value"));
        Assert.assertEquals("тест",passInput.getAttribute("value"));

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        WebElement loginBtn = driver.findElement(By.xpath("//*[contains(@class, 'ib-button-text')]"));
        loginBtn.click();

        System.out.println("Кнопка нажата");

        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"t-error\"]/ul/li")));

        WebElement errorLog = driver.findElement(By.xpath("//div[@class=\"t-error\"]/ul/li"));
        Assert.assertTrue(errorLog.isDisplayed());

    }

    @After
    public void terminate () {
        driver.quit();

    }

}

