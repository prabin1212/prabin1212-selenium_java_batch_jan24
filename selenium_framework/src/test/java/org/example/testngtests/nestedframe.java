package org.example.testngtests;

import io.github.bonigarcia.wdm.*;
import net.bytebuddy.asm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class nestedframe {
    WebDriver driver;

    @BeforeTest
    public void beforetest() {
       // WebDriverManager.chromedriver().clearDriverCache().setup();
        String rootpath =System.getProperty("user.dir");

        System.setProperty("webdriver.chrome.driver", rootpath+"/Users/prabinlama/Desktop/IT SUtra/selenium_framework/selenium_java_batch_jan24/selenium_framework/chromedriver");
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
    public void test_new_window() {
        driver.get("https://demoqa.com/browser-windows");
        String currentwindowid = driver.getWindowHandle();

        WebElement new_window = driver.findElement(By.xpath("//button[@id='windowButton']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",new_window);

        new_window.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String allwindow : driver.getWindowHandles()) {
            if (!currentwindowid.contentEquals(allwindow)) {
                driver.switchTo().window(allwindow);
                break;
            }
        }

        String urlofnewwindow = driver.getCurrentUrl();
        if (urlofnewwindow.contains("https://demoqa.com/sample")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("URL of child window is different");
        }

    }


}