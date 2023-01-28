import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class foodSearch {

    @DataProvider (name = "data-provider")
    public Object [][] foodSearch(){
        return new Object[][] {{"egg"}};
    }
    @Test(dataProvider = "data-provider")
    public void chromeTest(String s) throws InterruptedException {

        String food =s;
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        Map<String, Object> profile = new HashMap<String, Object>();
        Map<String, Integer> contentSettings = new HashMap<String, Integer>();

        //0 - Default, 1 - Allow, 2 - Block
        contentSettings.put("notifications", 2);
        contentSettings.put("geolocation", 2);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://chaldal.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div[1]/div[1]/form/div/div[1]/input")).sendKeys(s);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div[1]/div[1]/form/div/div[1]/input")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);

    }
}
