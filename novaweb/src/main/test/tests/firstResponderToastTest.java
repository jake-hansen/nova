package tests;

// Generated by Selenium IDE

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class firstResponderToastTest {

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
    public void firstResponderToast() throws Exception {
        driver.get("http://localhost:8080/novaweb/");
        Thread.sleep(1000);
        driver.manage().window().setSize(new Dimension(1552, 840));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".navbar-nav > .btn")).click();
        Thread.sleep(1000);
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
        assertThat(driver.findElement(By.id("toast_text")).getText(), is("Your update was published successfully."));
    }
}