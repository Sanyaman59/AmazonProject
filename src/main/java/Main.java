import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Main
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "D:/Components/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        Amazon a = new Amazon(driver);
        a.open();
        a.goToBooks();
        Books b = new Books(driver);
        b.displayBooks();
        b.findBook();
    }
}