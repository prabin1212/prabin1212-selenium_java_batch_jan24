package org.example.stepdefinations;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.util.concurrent.*;

public class saucedemostepdef {
    WebDriver driver;


    @Given("user initilize the browser")
    public void user_initilize_the_browser(){
      WebDriverManager.chromedriver().clearDriverCache().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters username {string}")
    public void user_enters_username(String username )  {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");


    }

    @When("User enters password {string}")
    public void user_enters_password(String password)  {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();
    }


    @Then("User should be able to login")
    public void user_should_be_able_to_login() {
        String title = driver.getTitle();
        System.out.println("The title of the website is: " + title);

    }
}




