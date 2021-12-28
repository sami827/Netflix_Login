import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class NetflixLogin {
    //Global Variables
    WebDriver driver;
    String baseUrl;

    //UI Variables
    String LOGIN_BTN = "//a[@href='/login']"; //Login Button Xpath
    String NETFLIX_LOGO = "Fill-14";
    String SIGN_IN_TEXT = "//h1[text()='Sign In']";
    String EMAIL_FIELD = "id_userLoginId"; //ID of Email Field
    String PASSWORD_FIELD = "id_password"; //ID of Password Field
    String SIGN_IN_BUTTON = "//button[text()='Sign In']"; //Xpath of sign in button
    String LOGIN_FAILED_TEXT = "//div[@class='ui-message-contents']"; //Xpath of unsuccessful text


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.netflix.com/";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void loginToNetflix() {
        System.out.println("Successfully loaded the website");

        //Homepage
        //Verifying home page
        WebElement netflixText = driver.findElement(By.id(NETFLIX_LOGO));
        Assert.assertTrue(netflixText.isDisplayed());
        System.out.println("Verified Page");

        //Clicking login button
        WebElement loginBtn = driver.findElement(By.xpath(LOGIN_BTN));
        loginBtn.click();
        System.out.println("Clicked on Login Button");

        //Sign In Page
        System.out.println("Navigating to Sign in page");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        //Verify sign in page
        WebElement signInText = driver.findElement(By.xpath(SIGN_IN_TEXT));
        Assert.assertTrue(signInText.isDisplayed());
        System.out.println("Sign in Page Verified");


        //Working with email field
        WebElement emailField = driver.findElement(By.id(EMAIL_FIELD));
        emailField.clear();
        emailField.sendKeys("samialzsabbir@gmail.com");

        //Working with password field

        WebElement passField = driver.findElement(By.id(PASSWORD_FIELD));
        passField.clear();
        passField.sendKeys("anyRandomNumber");

        //Click Login Button
        WebElement signInButton = driver.findElement(By.xpath(SIGN_IN_BUTTON));
        signInButton.click();
        System.out.println("Clicked Sign in Button");

        //Handling Unsuccessful Login
        WebElement loginUIText = driver.findElement(By.xpath(LOGIN_FAILED_TEXT));
        if(loginUIText.isDisplayed()) {
            System.out.println("Please insert correct credentials");
        }

    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();

    }
}
