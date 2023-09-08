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

public class testCase_03 {
    WebDriver driver;
    //ExtentTest test;
    //ExtentReports report;
    String lastGeneratedUsername;

    @BeforeSuite
    public void createDriver() throws MalformedURLException
    {
        DriverSingleton driverSingleton = DriverSingleton.createDriverInstance();
        driver=driverSingleton.createDriver();
        Assert.assertTrue(driver!=null,"Singleton driver not created");

       // ReportSingleton reportSingleton = ReportSingleton.instanciateReportSingletonObject();
        //report = reportSingleton.getExtendReport();
        //test = report.startTest("TestCase03");

    }
    @Test(dataProvider="TestCase03",dataProviderClass=DP.class,priority = 3,groups="Booking and Cancellation Flow")
    public void TestCase03(String newUsername,String password,String searchCity,String adventureName,String guestName,String date,String personCount) throws InterruptedException
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

        homePage.searchCity(searchCity);
        Assert.assertTrue(homePage.assertAutoCompleteText(searchCity),"No Search results return for available city");
        homePage.selectCity(searchCity);

        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.selectAdventure(adventureName);
        
        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        adventureDetailsPage.bookAdventure(guestName, date, personCount);
        Assert.assertTrue(adventureDetailsPage.isBookingSuccessful(),"Booking not successful");
        adventureDetailsPage.clickReservation();

        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.getReservations(guestName);

        String adventureId = adventurePage.strAdventureId;
        String transactionId = historyPage.transactionId;

        historyPage.reservationHistory(adventureId,transactionId);
        adventureDetailsPage.clickReservation();
        historyPage.cancelReservation(transactionId);
        //test.log(LogStatus.PASS,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login failed")), "TestCase03 Reserve adventure passed");
    }
        catch(Exception exception)
        {
            //test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login failed")), "TestCase03 Reserve adventure failed");
        }
        finally
        {
        homePage.logOutUser();
        }
    }
    @AfterSuite
    public void closure()
    {
       // report.endTest(test);
        //report.flush();
        driver.quit();
    }
}