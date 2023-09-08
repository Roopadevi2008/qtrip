package qtriptest.pages;

//import qtriptest.SeleniumWrapper;
import okio.Timeout;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {

    WebDriver driver;
    @FindBy(linkText = "Register")
    WebElement registerButton;
    @FindBy(className ="login")
    WebElement logoutButton;
    @FindBy(linkText = "Login Here")
    WebElement loginButton;
    @FindBy(className = "hero-input")
    WebElement searchBox;
    @FindBy(xpath="//ul/h5")
    WebElement notFoundCityAutoComLabel;
    @FindBy(xpath="//ul/a/li")
    WebElement foundCityAutoComLabel;
    @FindBy(id="results")
    WebElement autoComTxtBox;
    String homepageUrl="https://qtripdynamic-qa-frontend.vercel.app/";
    WebDriverWait wait;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);
        wait = new WebDriverWait(driver,10);
    }
    public void navigateToHomepage()
    {
         if(!this.driver.getCurrentUrl().equals(this.homepageUrl))
         {
             this.driver.get(this.homepageUrl);
         }
         Assert.assertEquals(this.driver.getCurrentUrl().equals(this.homepageUrl), true,"Not navigated to home page");
       // Assert.assertTrue(SeleniumWrapper.navigate(driver,this.homepageUrl),"Not navigated to home page");
    }
    public void clickRegister()
    {
        registerButton.click();
        // Assert.assertTrue(SeleniumWrapper.click(driver, this.registerButton),"Clicking on registerButton failed");

        Assert.assertEquals(this.driver.getCurrentUrl().endsWith("/register/"), true,"Not redirected to register page");
    }
    public boolean isUserLoggedIn()
    {
        return logoutButton.isDisplayed();
    }
    public void logOutUser() throws InterruptedException
    {
        logoutButton.click();
        // Assert.assertTrue(SeleniumWrapper.click(driver, this.logoutButton),"Clicking on logoutButton failed");
        Assert.assertTrue(loginButton.isDisplayed(),"Logout unsuccessfull");
    }
    public void searchCity(String city) throws InterruptedException
    {
        this.searchBox.clear();
        Thread.sleep(5000);
        this.searchBox.sendKeys(city);
    }
    public boolean assertAutoCompleteText(String city) throws InterruptedException
    {
        try
        {
            Thread.sleep(5000);
            wait.until(ExpectedConditions.textToBePresentInElement(this.foundCityAutoComLabel,city));
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public void selectCity(String city) throws InterruptedException
    {
        // Thread.sleep(10000);
        wait.until(ExpectedConditions.textToBePresentInElement(this.foundCityAutoComLabel,city));
        this.foundCityAutoComLabel.click();
        System.out.println("waiting -- 0");
       // Assert.assertTrue(SeleniumWrapper.click(driver, this.foundCityAutoComLabel),"Clicking on foundCityAutoComLabel failed");
        System.out.println("waiting -- 1");
        city = city.toLowerCase();
        System.out.println("waiting -- 2");
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city="+city));
        System.out.println("waiting -- 3");
        Assert.assertTrue(this.driver.getCurrentUrl().contains("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city="+city),"Not redirected to adventure selection page");
        System.out.println("waiting -- 4");
    }
}