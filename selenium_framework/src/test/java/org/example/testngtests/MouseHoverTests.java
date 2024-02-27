package org.example.testngtests;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class MouseHoverTests {
    WebDriver driver;

    @BeforeTest
    public void beforetest() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
      //  String rootpath =System.getProperty("user.dir");

      //  System.setProperty("webdriver.chrome.driver", rootpath+"src/test/resources/driver/chromedriver");
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.addArguments("--disable-geolocation");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }
    @Test
    public void hover_test() throws InterruptedException {
        driver.get("https://www.spicejet.com/");
        Actions actions = new Actions(driver);
        WebElement spicejet = driver.findElement(By.xpath("(//div[text()='SpiceClub'])[1]"));
        actions.moveToElement(spicejet).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'/home#program')]")));

        WebElement homeprogram = driver.findElement(By.xpath("//a[contains(@href,'/home#program')]"));

        String targetattr = homeprogram.getAttribute("target");

        if (targetattr.equals("_blank")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Link Our Program has no attribute _blank");
        }

        homeprogram.click();

        Thread.sleep(4000);

        actions.keyDown(Keys.CONTROL).keyDown(Keys.TAB).build().perform();


    }

}
