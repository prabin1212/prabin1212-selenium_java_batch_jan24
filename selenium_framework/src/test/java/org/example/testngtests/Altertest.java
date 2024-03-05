package org.example.testngtests;

import edu.emory.mathcs.backport.java.util.concurrent.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

public class Altertest {

    WebDriver driver;
    @BeforeTest
    public void beforetest(){
//        WebDriverManager.chromedriver().clearDriverCache().setup();

        String rootpath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", rootpath+"/src/test/resources/driver/chromedriver.exe");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory",  System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test" + File.separator+"downloads");
        chromePrefs.put("download.prompt_for_download", false);

        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--disable-extensions");
        ops.addArguments("--headless=new");
        ops.setExperimentalOption("prefs", chromePrefs);
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
     //   driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
   // @Test
   // public void test_download() throws InterruptedException {
      //  driver.get("https://demoqa.com/upload-download");
      //  By download_loc = By.xpath("//a[@id='downloadButton']");
     //   Commonfunctions.click(download_loc, driver);


}
