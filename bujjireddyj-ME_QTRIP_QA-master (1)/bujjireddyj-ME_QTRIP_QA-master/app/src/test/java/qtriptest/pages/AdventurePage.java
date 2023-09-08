package qtriptest.pages;

//import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.internal.invokers.ExpectedExceptionsHolder;

public class AdventurePage {
    WebDriver driver;
    @FindBy(id="duration-select")
    WebElement filterValueDropdown;
    @FindBy(id="category-select")
    WebElement categoryValueDropdown;
    @FindBy(id="search-adventures")
    WebElement searchAdvTxtBox;
    @FindBy(xpath = "//div[@id='data']/div")
    List<WebElement> searchResult;
    @FindBy(xpath="//select[@id='duration-select']/following-sibling::div")
    WebElement clearDuration;
    @FindBy(xpath = "//select[@id='category-select']/following-sibling::div")
    WebElement clearCategory;
    @FindBy(xpath="//div[@id='data']/div//h5")
    WebElement adventure;
    @FindBy(xpath="//div[@id='data']/div/a/")
    Select select;
    public String strAdventureId;
    WebElement categoryBanner;
    
    public AdventurePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void setFilterValue(String filterValue)
    {
        System.out.println("The set filter value executed ==========");
        this.select = new Select(this.filterValueDropdown);
        this.select.selectByVisibleText(filterValue);
        String[] hoursRange = filterValue.split(" ");
        String[] hoursRangeSplitted = hoursRange[0].split("-");
        int minimumHour = Integer.parseInt(hoursRangeSplitted[0]);
        int maximumHour = Integer.parseInt(hoursRangeSplitted[1]);
        WebElement duration;
        int eachResultDuration;
        for(WebElement eachResult:searchResult)
        {
            duration = eachResult.findElement(By.xpath("//h5[text()='Duration']/following-sibling::p"));
            By by = By.xpath("//h5[text()='Duration']/following-sibling::p");
           // duration = SeleniumWrapper.findElementWithRetry(driver, by, 3);
            eachResultDuration = Integer.parseInt(duration.getText().split(" ")[0]);
            Assert.assertTrue(eachResultDuration>=minimumHour && eachResultDuration<=maximumHour,"Duration filter validation is unsuccessful");
        }
    }
    public void setCategoryValue(String categoryValue)
    {
        System.out.println("The set category value executed ==========");
        this.select=new Select(categoryValueDropdown);
        this.select.selectByVisibleText(categoryValue);
        for(WebElement eachResult:searchResult)
        {
             this.categoryBanner = eachResult.findElement(By.className("category-banner"));
            By by = By.className("category-banner");
           // this.categoryBanner = SeleniumWrapper.findElementWithRetry(driver, by, 3);

            Assert.assertTrue(categoryValue.contains(this.categoryBanner.getText()),"Category filter validation is unsuccessful");
        }
    }
    public void selectAdventure(String adventureValue) throws InterruptedException
    {
        this.searchAdvTxtBox.clear();
        Thread.sleep(5000);
        this.searchAdvTxtBox.sendKeys(adventureValue);
        Thread.sleep(5000);
        // Assert.assertTrue(SeleniumWrapper.sendKeys(this.searchAdvTxtBox, adventureValue),"Input to searchAdvTxtBox failed");

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.textToBePresentInElement(this.adventure, adventureValue));
        WebElement adventureId;
        adventureId = this.adventure.findElement(By.xpath("ancestor::a"));
        // By by = By.xpath("ancestor::a");
        // adventureId = this.adventure.SeleniumWrapper.findElementWithRetry(driver, by, 3);
        strAdventureId = adventureId.getAttribute("id");
        
        System.out.println("The selectable adventure ========== "+this.adventure.getText());
         this.adventure.click();
        //Assert.assertTrue(SeleniumWrapper.click(driver, adventure),"Clicking on adventure failed");

        Assert.assertTrue(this.driver.getCurrentUrl().contains("adventures/detail")&&this.driver.getCurrentUrl().endsWith(strAdventureId),"Not landed in adventure detail page");
    }
    public int getResultCount()
    {
        return this.searchResult.size();
    }
    public void clearFilterValue()
    {
         this.clearDuration.click();
        //Assert.assertFalse(SeleniumWrapper.click(driver, clearDuration),"Clicking on clearDuration failed");
    }
    public void clearCategoryValue()
    {
         this.clearCategory.click();
        //Assert.assertFalse(SeleniumWrapper.click(driver, clearCategory),"Clicking on clearCategory failed");
    }
}