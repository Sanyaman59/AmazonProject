import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
        var path = Paths.get("Components/chromedriver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path.toString());
        WebDriver driver = new ChromeDriver();
        List<Book> books;
        Book headFirst;

        Amazon a = new Amazon(driver);
        a.open();
        a.goToBooks();

        Books b = new Books(driver);
        books = b.getBooks();
        System.out.println("The book from the 'Books' class method : ");
        b.displayBook(2);

        HeadFirst hf = new HeadFirst(driver);
        hf.open();
        hf.displayBook();
        headFirst = hf.getBook();
        hf.close();
        if(books.contains(headFirst))
            System.out.println("The book 'Head first...' is in this list. Their hash codes are : "
                    + headFirst.hashCode() +" - "+ books.get(books.indexOf(headFirst)).hashCode());
        else
            System.out.println("He isn't here, is he?");
        System.out.println(headFirst.equals(books.get(2)));
    }
}