package core.http.itunes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jzhung on 2018/1/25.
 */
public class AppleRefund {
    private static String reportUrl = "https://reportaproblem.apple.com/";
    private static String loginUrl = "https://idmsa.apple.com/appleauth/auth/signin";
    public static void main(String[] args) {
        new AppleRefund().start();
    }

    private void start() {
        System.setProperty("phantomjs.binary.path", "F:\\Dev\\Web\\Test\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        System.setProperty("webdriver.chrome.driver", "F:\\Dev\\Web\\Test\\phantomjs-2.1.1-windows\\bin\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(reportUrl);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement appleId = driver.findElement(By.id("appleId"));
        appleId.sendKeys("123456@qq.com");
        WebElement pwd = driver.findElement(By.id("pwd"));
        pwd.sendKeys("ni3456");
        System.out.println(driver.getPageSource());
    }
}
