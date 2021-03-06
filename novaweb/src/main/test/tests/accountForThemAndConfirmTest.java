package tests;

// Generated by Selenium IDE

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class accountForThemAndConfirmTest {

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
    public void accountForThemAndConfirm() throws Exception {
        driver.get("http://localhost:8080/novaweb/");
        Thread.sleep(1000);
        driver.manage().window().setSize(new Dimension(1552, 840));
        Thread.sleep(1000);
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
        driver.findElement(By.id("email")).sendKeys("testuser@testuser.com");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn-block")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Account Management")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("firstname")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("firstname")).sendKeys("Benjamin");
        Thread.sleep(1000);
        driver.findElement(By.id("lastname")).sendKeys("Knutson");
        Thread.sleep(1000);
        driver.findElement(By.id("registeremail")).sendKeys("bk@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("groupname")).click();
        {
            WebElement dropdown = driver.findElement(By.id("groupname"));
            dropdown.findElement(By.xpath("//option[. = 'Students']")).click();
        }
        Thread.sleep(1000);
        driver.findElement(By.id("groupname")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("registerpassword")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("registerpassword")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.id("passwordconfirmation")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.id("createAccountButton")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn-secondary")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".navbar-nav > .btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("first_responder_1@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn-block")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Accountability")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first_name_search")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first_name_search")).sendKeys("Benjamin");
        Thread.sleep(1000);
        driver.findElement(By.id("last_name_search")).sendKeys("Knutson");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("accountForName")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("accountForName")).sendKeys("Benjamin Knutson");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first_name_search")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first_name_search")).sendKeys("Benjamin");
        Thread.sleep(1000);
        driver.findElement(By.id("last_name_search")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("last_name_search")).sendKeys("Knutson");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
        Thread.sleep(2000);
        assertThat(driver.findElement(By.id("okay_status")).getText(), is("Okay"));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn-secondary")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".navbar-nav > .btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("testuser@testuser.com");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn-block")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Account Management")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("deleteByEmail")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("deleteByEmail")).sendKeys("bk@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".btn-secondary")).click();
    }
}