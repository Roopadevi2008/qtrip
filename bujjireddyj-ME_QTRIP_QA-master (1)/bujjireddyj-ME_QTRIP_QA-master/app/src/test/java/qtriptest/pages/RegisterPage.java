package qtriptest.pages;

//import qtriptest.SeleniumWrapper;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.lang.model.util.ElementScanner6;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegisterPage {

    WebDriver driver;
    @FindBy(name="email")
    WebElement userName;
    @FindBy(name="password")
    WebElement password;
    @FindBy(name="confirmpassword")
    WebElement cnfPassword;
    @FindBy(className = "btn-login")
    WebElement registerButton;
    String currentUsername="";

    public RegisterPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }
    public void registerNewUser(String userName,String password,String cnfPassword,boolean generateRandomUsername) throws InterruptedException
    {
        if(generateRandomUsername)
        {
            userName=userName+UUID.randomUUID().toString();
        }
         this.userName.clear();
         this.userName.sendKeys(userName);
       // Assert.assertTrue(SeleniumWrapper.sendKeys(this.userName,userName),"Input to userName failed");

         this.password.clear();
         this.password.sendKeys(password);
        //Assert.assertTrue(SeleniumWrapper.sendKeys(this.password,password),"Input to password failed");

        
        this.cnfPassword.clear();
         this.cnfPassword.sendKeys(cnfPassword);
        //Assert.assertTrue(SeleniumWrapper.sendKeys(this.cnfPassword,cnfPassword),"Input to cnfPassword failed");

        this.registerButton.click();
        // Assert.assertTrue(SeleniumWrapper.click(driver, this.registerButton),"Clicking on registerButton failed");

        System.out.println("The current url :: "+this.driver.getCurrentUrl());
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login"));
        Assert.assertEquals(this.driver.getCurrentUrl().endsWith("/login"),true, "Not redirected to login page after registration");
        this.currentUsername=userName;
    }
    public String getUsername()
    {
        return this.currentUsername;
    }
}