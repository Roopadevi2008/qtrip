package qtriptest;

import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSingleton {
    private static WebDriver driver;
    private static DriverSingleton instanceOfDriverSingleton=null;

    private DriverSingleton() throws MalformedURLException
    {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();   
    }
    public static DriverSingleton createDriverInstance() throws MalformedURLException
    {
        if(instanceOfDriverSingleton==null)
        {
            instanceOfDriverSingleton=new DriverSingleton();
        }
        return instanceOfDriverSingleton;
    }
    public static WebDriver createDriver()
    {
        return driver;
    }
}