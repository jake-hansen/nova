package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class searchUserTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("http://localhost:8080/novaweb/");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("testuser@testuser.com");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("password")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Account Management")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("firstname")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("firstname")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("firstname")).sendKeys("Test");
        Thread.sleep(1000);
        driver.findElement(By.id("lastname")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("lastname")).sendKeys("Search");
        Thread.sleep(1000);
        driver.findElement(By.id("registeremail")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("registeremail")).sendKeys("testsearch@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("registerpassword")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("registerpassword")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.id("passwordconfirmation")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("passwordconfirmation")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.id("createAccountButton")).click();
        Thread.sleep(1000);
        assertEquals("Account Successfully Created", driver.findElement(By.xpath("//p")).getText());

        Thread.sleep(1000);
        Thread.sleep(1000);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("testsearch@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Accountability")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("first_name_search")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first_name_search")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("first_name_search")).sendKeys("Test");
        Thread.sleep(1000);
        driver.findElement(By.id("last_name_search")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("last_name_search")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("last_name_search")).sendKeys("Search");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
        Thread.sleep(2000);
        assertEquals("Name: Test Search", driver.findElement(By.xpath("//p")).getText());


        Thread.sleep(1000);
        Thread.sleep(1000);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("testuser@testuser.com");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("password")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Account Management")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("deleteByEmail")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("deleteByEmail")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("deleteByEmail")).sendKeys("testsearch@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
        Thread.sleep(2000);
        assertEquals("Account Successfully Deleted", driver.findElement(By.xpath("//p")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
