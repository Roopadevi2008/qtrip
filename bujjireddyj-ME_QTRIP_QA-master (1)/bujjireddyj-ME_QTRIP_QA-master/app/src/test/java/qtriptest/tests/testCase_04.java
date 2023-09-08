package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
//import qtriptest.ReportSingleton;

public class testCase_04 {
    WebDriver driver;
    String lastGeneratedUsername;
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
        //test = report.startTest("TestCase04");
    }
    @Test(dataProvider="TestCase04",dataProviderClass=DP.class,priority = 4,groups = "Reliability Flow")
    public void TestCase04(String newUsername,String password,String dataset1,String dataset2,String dataset3) throws InterruptedException
    {
        HomePage homePage = new HomePage(driver);
        try{
        homePage.navigateToHomepage();
        homePage.clickRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerNewUser(newUsername, password, password, true);
        this.lastGeneratedUsername = registerPage.getUsername();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.performLogin(this.lastGeneratedUsername, password);
        Assert.assertEquals(homePage.isUserLoggedIn(),true,"User not logged in");

        AdventurePage adventurePage = new AdventurePage(driver);
        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);


        String[] dataset1Array = dataset1.split(";");
        Assert.assertTrue(reserveAdventure(homePage,adventurePage,adventureDetailsPage, dataset1Array[0],dataset1Array[1],dataset1Array[2],dataset1Array[3],dataset1Array[4]),"Booking not successful with dataset1");
        homePage.navigateToHomepage();

        String[] dataset2Array = dataset2.split(";");
        Assert.assertTrue(reserveAdventure(homePage,adventurePage,adventureDetailsPage, dataset2Array[0],dataset2Array[1],dataset2Array[2],dataset2Array[3],dataset2Array[4]),"Booking not successful with dataset2");
        homePage.navigateToHomepage();

        String[] dataset3Array = dataset3.split(";");
        Assert.assertTrue(reserveAdventure(homePage,adventurePage,adventureDetailsPage, dataset3Array[0],dataset3Array[1],dataset3Array[2],dataset3Array[3],dataset3Array[4]),"Booking not successful with dataset3");
 
        adventureDetailsPage.clickReservation();
        HistoryPage historyPage = new HistoryPage(driver);
        Assert.assertTrue(historyPage.checkReservationAvail(dataset1Array[1],dataset1Array[2],dataset1Array[3]),"Reservation is not available on page for dataset1");
        Assert.assertTrue(historyPage.checkReservationAvail(dataset2Array[1],dataset2Array[2],dataset2Array[3]),"Reservation is not available on page for dataset1");
        Assert.assertTrue(historyPage.checkReservationAvail(dataset3Array[1],dataset3Array[2],dataset3Array[3]),"Reservation is not available on page for dataset1");

        //test.log(LogStatus.PASS,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login failed")), "TestCase04 Reserving with multiple dataset and Cancelling passed");
    }
        catch(Exception exception)
        {
           // test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login failed")), "TestCase04 Reserving with multiple dataset and Cancelling failed");
        }
        finally
        {
        homePage.logOutUser();
        }
    }
    public boolean reserveAdventure(HomePage homePage, AdventurePage adventurePage,AdventureDetailsPage adventureDetailsPage,String searchCity,String adventureName,String guestName,String date,String personCount) throws InterruptedException
    {
        homePage.searchCity(searchCity);
        Assert.assertTrue(homePage.assertAutoCompleteText(searchCity),"No Search results return for available city");
        homePage.selectCity(searchCity);

        adventurePage.selectAdventure(adventureName);
        
        adventureDetailsPage.bookAdventure(guestName, date, personCount);
        return adventureDetailsPage.isBookingSuccessful();
    }
    @AfterSuite
    public void closure()
    {
        driver.quit();
    }
}