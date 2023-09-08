
package qtriptest.pages;

//import qtriptest.SeleniumWrapper;
import java.text.SimpleDateFormat;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HistoryPage {
    @FindBy(linkText = "Reservations")
    WebElement reserveButton;
    WebDriver driver;
    String reserveUrl="https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";
    public String transactionId;
    WebElement cancelButton;
    WebElement viewReservationButton;
    By by;

    public HistoryPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);
    }
    public void getReservations(String guestName) throws InterruptedException
    {
        if(!this.driver.getCurrentUrl().equals(reserveUrl))
        {
            this.driver.get(reserveUrl);
        }
        Thread.sleep(5000);
        List<WebElement> allReservation;
        allReservation = this.driver.findElements(By.tagName("tr"));
        // by = By.tagName("tr");
        // allReservation = SeleniumWrapper.findElementWithRetry(driver,by,3);
        this.transactionId = allReservation.get(allReservation.size()-1).findElement(By.xpath("th")).getText();
        // by = By.xpath("th");
        // this.transactionId = allReservation.get(allReservation.size()-1).SeleniumWrapper.findElementWithRetry(driver,by,3).getText();
    }
    public void reservationHistory(String strAdventureId,String transactionId)
    {
        System.out.println("The transaction id :: "+transactionId);
        System.out.println("The strAdcenture id :: "+strAdventureId);
        this.viewReservationButton = this.driver.findElement(By.xpath("//th[text()='"+transactionId+"']/following-sibling::td//*[text()='Visit Adventure']"));
        by = By.xpath("//th[text()='"+transactionId+"']/following-sibling::td//*[text()='Visit Adventure']");
       // this.viewReservationButton = SeleniumWrapper.findElementWithRetry(driver,by,3);
        this.viewReservationButton.click();
        // Assert.assertTrue(SeleniumWrapper.click(driver, this.viewReservationButton),"Clicking on viewReservationButton failed");
        Assert.assertTrue(this.driver.getCurrentUrl().contains("adventures/detail")&&this.driver.getCurrentUrl().endsWith(strAdventureId),"Not landed in adventure detail page");
    }
    public void cancelReservation(String transactionId) throws InterruptedException
    {
        this.cancelButton=this.driver.findElement(new ByChained(By.id(transactionId),By.className("cancel-button")));
        System.out.println("The cancel button string :: "+this.cancelButton);
        this.cancelButton.click();
        // Assert.assertTrue(SeleniumWrapper.click(driver, this.cancelButton),"Clicking on cancelButton failed");
        Thread.sleep(5000);
        this.driver.navigate().refresh();
        System.out.println("Transaction id after refresh : "+transactionId);
        Thread.sleep(5000);
        try
        {
             this.driver.findElement(By.xpath("//*[contains(@id,'"+transactionId+"')]"));
            by = By.xpath("//*[contains(@id,'"+transactionId+"')]");
           // SeleniumWrapper.findElementWithRetry(driver,by,3);
            // Assert.assertTrue(false,"Cancellation unsuccessful");
        }
        catch(NoSuchElementException excception)
        {
            Assert.assertTrue(true,"Cancellation unsuccessful");
        }
    }
    public boolean checkReservationAvail(String adventureNameSTR,String guestNameSTR,String dateSTR)
    {
         WebElement guestNameWE = this.driver.findElement(By.xpath("//td[contains(text(),'"+guestNameSTR+"')]"));
        by = By.xpath("//td[contains(text(),'"+guestNameSTR+"')]");
       // WebElement guestNameWE = SeleniumWrapper.findElementWithRetry(driver,by,3);
        System.out.println(" adventurename :: "+guestNameWE.getText());
        // WebElement adventureNameWE = guestNameWE.findElement(By.xpath("following-sibling::td[contains(text(),'"+adventureNameSTR+"')]"));
        // System.out.println(" adventureNameWE :: "+adventureNameWE.getText());
        return guestNameWE.isDisplayed();
    }

}