package LearnTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class CreateMultipleLeads {
    // launch chrome browser

    WebDriver driver;
    @BeforeSuite
    public void launchbrowser()
    {
        driver = new ChromeDriver();

    }


    // launch vtiger
    @BeforeTest
    public void launchapp()
    {

        driver.get("http://localhost:100/");
        driver.manage().window().maximize();
    }

    //login to vtiger
    @BeforeClass
    public void login(){
        driver.findElement(By.name("user_name")).sendKeys("admin");
        driver.findElement(By.name("user_password")).sendKeys("admin");
        driver.findElement(By.name("login_theme")).sendKeys("orange");
        driver.findElement(By.name("Login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed(), "Login is successful");
    }

    // create lead
    @Test
    public void createlead(String firstname, String lastname, String companyname, String phone)
    {
        driver.findElement(By.linkText("New Lead")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
        driver.findElement(By.xpath("//input[@name='company']")).sendKeys(companyname);
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        Assert.assertTrue(driver.findElement(By.linkText(firstname+" "+lastname)).isDisplayed(), "Lead is created successfully");
    }
    @DataProvider
    public Object[][] leaddata(){
        return new Object[][] {
                {"shyaml","mankar",},


        };
    }

    //logout to vtiger
    public void logOut()
    {
        driver.findElement(By.linkText("Logout")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(driver.findElement(By.name("Login")).isDisplayed(), "Logout is successful");
    }

    //close browser
    @AfterClass
    public void closeapp()
    {
        driver.quit();
    }

}