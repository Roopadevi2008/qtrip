package qtriptest.tests;

import qtriptest.DriverSingleton;
//import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import qtriptest.DP;

public class testCase_01{

    WebDriver driver;
    //ExtentTest test;
  //  ExtentReports report;
    String lastGeneratedUsername;
    @BeforeSuite
    public void createDriver() throws MalformedURLException
    {
        DriverSingleton driverSingleton = DriverSingleton.createDriverInstance();
        driver=driverSingleton.createDriver();
        Assert.assertTrue(driver!=null,"Singleton driver not created");

        //ReportSingleton reportSingleton = ReportSingleton.instanciateReportSingletonObject();
        //report = reportSingleton.getExtendReport();
        //test = report.startTest("TestCase01");
    }
    @Test(dataProvider="TestCase01",dataProviderClass=DP.class,priority = 1,groups = "Login Flow")
    public void TestCase01(String userName,String password) throws InterruptedException
    {
        HomePage homePage = new HomePage(driver);
        try{
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        homePage.clickRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerNewUser(userName, password, password, true);
        lastGeneratedUsername = registerPage.getUsername();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.performLogin(lastGeneratedUsername, password);
        Assert.assertEquals(homePage.isUserLoggedIn(),true,"User not logged in");
       // test.log(LogStatus.PASS,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login failed")), "TestCase01 Registering and login passed");
    }
        catch(Exception exception)
        {
           //test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.takeScreenshot(driver,"png","TestCase01 Registering and login failed")), "TestCase01 Registering and login failed");
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
        //driver.quit();
    }
}