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

public class addEmergencyContactTest {
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
        driver.findElement(By.id("lastname")).sendKeys("Emergency");
        Thread.sleep(1000);
        driver.findElement(By.id("registeremail")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("registeremail")).sendKeys("testemergency@gmail.com");
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
        Thread.sleep(2000);
        assertEquals("Account Successfully Created", driver.findElement(By.xpath("//p")).getText());
        Thread.sleep(1000);

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
        driver.findElement(By.id("email")).sendKeys("testemergency@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Profile")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("firstname")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("firstname")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("firstname")).sendKeys("ftest");
        Thread.sleep(1000);
        driver.findElement(By.id("lastname")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("lastname")).sendKeys("ltest");
        Thread.sleep(1000);
        driver.findElement(By.id("relationship")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("relationship")).sendKeys("rtest");
        Thread.sleep(1000);
        driver.findElement(By.id("primaryphone")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("primaryphone")).sendKeys("ptest");
        Thread.sleep(1000);
        driver.findElement(By.id("secondaryphone")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("secondaryphone")).sendKeys("stest");
        Thread.sleep(1000);
        driver.findElement(By.id("emergencycontactemail")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("emergencycontactemail")).sendKeys("etest");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
        Thread.sleep(2000);
        assertEquals("ftest ltest", driver.findElement(By.xpath("//div[2]/div/div/div[2]/p")).getText());
        Thread.sleep(1000);
        assertEquals("Relationship: rtest", driver.findElement(By.xpath("//div[2]/div/div/div[2]/p[2]")).getText());
        Thread.sleep(1000);
        assertEquals("Primary Phone: ptest", driver.findElement(By.xpath("//div[2]/div/div/div[2]/p[3]")).getText());
        Thread.sleep(1000);
        assertEquals("Secondary Phone: stest", driver.findElement(By.xpath("//div[2]/div/div/div[2]/p[4]")).getText());
        Thread.sleep(1000);
        assertEquals("Email: etest", driver.findElement(By.xpath("//div[2]/div/div/div[2]/p[5]")).getText());
        Thread.sleep(1000);

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
        driver.findElement(By.id("deleteByEmail")).sendKeys("testemergency@gmail.com");
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
