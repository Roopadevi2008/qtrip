
package qtriptest.pages;

//import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AdventureDetailsPage {
    WebDriver driver;
    @FindBy(name = "name")
    WebElement guestName;
    @FindBy(name="date")
    WebElement date;
    @FindBy(name = "person")
    WebElement personCount;
    @FindBy(className = "reserve-button")
    WebElement reserveAdventureButton;
    @FindBy(xpath = "//div[@id='reserved-banner']/self::div[contains(@style,'display: block')]")
    WebElement reservationToast;
    String reserveUrl="https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";
    @FindBy(linkText = "Reservations")
    WebElement gotoReservationPageButton;
    public AdventureDetailsPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void bookAdventure(String guestName,String date,String personCount) throws InterruptedException
    {
        this.guestName.clear();
         this.guestName.sendKeys(guestName);
       // Assert.assertTrue(SeleniumWrapper.sendKeys(this.guestName, guestName),"Input to Guest name failed");

        this.date.clear();
        this.date.sendKeys(date);
         Thread.sleep(5000);
         //Assert.assertTrue(SeleniumWrapper.sendKeys(this.date,date),"Input to date failed");

         this.personCount.clear();
         this.personCount.sendKeys(personCount);
       // Assert.assertTrue(SeleniumWrapper.sendKeys(this.personCount, personCount),"Input to personCount failed");

        this.reserveAdventureButton.click();
        // Assert.assertTrue(SeleniumWrapper.click(driver, this.reserveAdventureButton),"Clicking on reserveAdventureButton failed");
    }
    public boolean isBookingSuccessful()
    {
        return this.reservationToast.isEnabled();
    }
    public void clickReservation()
    {
         this.gotoReservationPageButton.click();
        //Assert.assertTrue(SeleniumWrapper.click(driver, this.gotoReservationPageButton),"Clicking on gotoReservationPageButton failed");
    }
}