package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class latestUpdatesTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() throws Exception {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @Test
    public void testLatestUpdates() throws Exception {
        driver.get("http://localhost:8080/novaweb/");
        driver.manage().window().setSize(new Dimension(1552, 840));
        driver.findElement(By.cssSelector(".navbar-nav > .btn")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".navbar-nav > .btn"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.id("email")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("first_responder_1@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn-block")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("informational_update")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("informational_update")).sendKeys("Here is a new update.");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
        Thread.sleep(2000);
        assertThat(driver.findElement(By.id("latest_updates")).getText(), is("Here is a new update."));
    }
}