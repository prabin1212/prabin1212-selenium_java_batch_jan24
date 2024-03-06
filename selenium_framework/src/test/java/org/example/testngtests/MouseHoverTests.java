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
        WebDriverManager.chromedriver().setup();
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

    @Test
    public void open_in_new_link_test() {
        try {
            driver.get("https://www.tutorialspoint.com/about/about_careers.htm");
            String term = Keys.chord(Keys.CONTROL, Keys.ENTER);
            driver.findElement(By.xpath("//a[text()='Terms of Use']")).sendKeys(term);

            Thread.sleep(1000);


            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            String title = driver.getTitle();

            if (title.equals("Terms of Use")) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("Landed in wrong tab");
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search-strings']")));

            driver.findElement(By.xpath("//input[@id='search-strings']")).sendKeys("hhgj");
        } catch (InterruptedException e) {
            System.out.println("");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println();
        }
    }

    @Test
    public void test_new_window() {
        driver.get("https://demoqa.com/browser-windows");
        String currentwindowid = driver.getWindowHandle();
        driver.findElement(By.xpath("//button[@id='windowButton']")).click();

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


    @AfterTest
    public void aftertest() {

        driver.quit();
    }
}
