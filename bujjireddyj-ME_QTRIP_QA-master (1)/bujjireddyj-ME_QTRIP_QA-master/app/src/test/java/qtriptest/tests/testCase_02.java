package qtriptest.tests;
import qtriptest.DP;
import qtriptest.DriverSingleton;
//import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class testCase_02 {
    WebDriver driver;
  //  ExtentTest test;
    //ExtentReports report;

    @BeforeSuite
    public void createDriver() throws MalformedURLException
    {
        DriverSingleton driverSingleton = DriverSingleton.createDriverInstance();
       driver=driverSingleton.createDriver();
        Assert.assertTrue(driver!=null,"Singleton driver not created");

        //ReportSingleton reportSingleton = ReportSingleton.instanciateReportSingletonObject();
        //report = reportSingleton.getExtendReport();
        //test = report.startTest("TestCase02");

    }
    @Test(dataProvider="TestCase02",dataProviderClass=DP.class,priority = 2,groups = "Search and Filter flow")
    public void TestCase02(String cityName,String categoryFilter,String durationFilter,String expectedFilteredResults,String expectedUnFilteredResults) throws InterruptedException
    {
        try
        {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomepage();
        homePage.searchCity("Chennai");
        Assert.assertFalse(homePage.assertAutoCompleteText("Chennai"),"Search results return for unavailable city");
        homePage.searchCity(cityName);
        Assert.assertTrue(homePage.assertAutoCompleteText(cityName),"No Search results return for available city");
        homePage.selectCity(cityName);
        AdventurePage adventurePage = new AdventurePage(driver);
        System.out.println("The Before calling set filter value executed ==========");
        adventurePage.setFilterValue(durationFilter);
        System.out.println("The Before calling set category value executed ==========");
        adventurePage.setCategoryValue(categoryFilter);
        Assert.assertEquals(adventurePage.getResultCount(),Integer.parseInt(expectedFilteredResults),"Unsual search results");
        adventurePage.clearFilterValue();
        adventurePage.clearCategoryValue();
        Assert.assertEquals(adventurePage.getResultCount(),Integer.parseInt(expectedUnFilteredResults),"Unsual search results");
       // test.log(LogStatus.PASS,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login failed")), "TestCase02 Searching city and adventure passed");
    }
        catch(Exception exception)
        {
         //   test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login failed")), "TestCase02 Searching city and adventure failed");
        }
    }
    @AfterSuite
    public void closure()
    {
        //report.endTest(test);
        //report.flush();
        driver.quit();
    }

}