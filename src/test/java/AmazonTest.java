import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class AmazonTest extends TestCase {

    @Test
    public void testAmazonBooks()
    {
        System.setProperty("webdriver.chrome.driver", "D:/Components/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Amazon a = new Amazon(driver);
        a.open();
        assertTrue(a.atPage());
        a.goToBooks();
        Books b = new Books(driver);
        assertTrue(b.atPage());
        b.displayBooks();
        //b.displayBook(0);
        b.findBook();
    }

    @Test
    public void testAmazonBook()
    {
        System.setProperty("webdriver.chrome.driver", "D:/Components/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        List<WebElement> books;
        Amazon a = new Amazon(driver);
        a.open();
        a.goToBooks();
        Books b = new Books(driver);
        System.out.println("The book from the 'books' variable : ");
        books = b.getBooks();
        System.out.println(books.get(5).getText());
        System.out.println("The book from the 'Books' class method");
        b.displayBook(5);
        b.findBook();
    }
}