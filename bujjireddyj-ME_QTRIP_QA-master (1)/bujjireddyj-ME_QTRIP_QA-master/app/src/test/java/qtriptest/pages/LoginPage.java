package qtriptest.pages;

//import qtriptest.SeleniumWrapper;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;
    @FindBy(name="email")
    WebElement emailAddress;
    @FindBy(name="password")
    WebElement password;
    @FindBy(className = "btn-login")
    WebElement loginButton;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void performLogin(String email, String password) throws InterruptedException
    {
         this.emailAddress.clear();
         this.emailAddress.sendKeys(email);
        //Assert.assertTrue(SeleniumWrapper.sendKeys(this.emailAddress,email),"Input to email failed");

         this.password.clear();
         this.password.sendKeys(password);
        //Assert.assertTrue(SeleniumWrapper.sendKeys(this.password,password),"Input to password failed");

        this.loginButton.submit();
        // Assert.assertTrue(SeleniumWrapper.click(driver, this.loginButton),"Clicking on loginButton failed");

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/"));
        Assert.assertEquals(this.driver.getCurrentUrl(), "https://qtripdynamic-qa-frontend.vercel.app/","Not redirected to homepage after login process");
    }
}