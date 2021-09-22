import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.crypto.NullCipher;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Books {
    private WebDriver driver;
    private List<WebElement> webBooks;
    private List<Book> books = new ArrayList<>();

    Books(WebDriver driver)
    {
        this.driver = driver;
        createWebBooks();
        createBooks();
    }

    private void createWebBooks()
    {
        webBooks = driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']" +
                "/div[@class='s-result-item s-asin sg-col-0-of-12 sg-col-16-of-20 sg-col sg-col-12-of-16']"));

        if (webBooks.size() == 0) {
            webBooks = driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']" +
            "/div[@class='s-result-item s-asin sg-col-0-of-12 s-spacing-small sg-col-16-of-20 sg-col sg-col-12-of-16']"));
            }

    }

    private void createBooks()
    {
        for(int i = 0;i < webBooks.size();i++)
        {
            String name = webBooks.get(i).findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']")).getText();
            String psuedoAuthor = webBooks.get(i).findElement(By.xpath(".//div[@class='a-row']")).getText();
            psuedoAuthor = psuedoAuthor.substring(psuedoAuthor.indexOf("by"),psuedoAuthor.lastIndexOf('|')-1);
            float price = 0;
            if(webBooks.get(i)
                    .findElements(By.xpath(".//span[@class='a-price']"))
                    .size()!=0)
            {
                price = Float.parseFloat(webBooks.get(i)
                        .findElement(By.xpath(".//span[@class='a-price']"))
                        .getText()
                        .replace('\n', '.')
                        .substring(1));
            }

            if(webBooks.get(i)
                    .findElements(By.xpath(".//*[text()='Best Seller']"))
                    .size()!=0)
            {
                books.add(new Book(name,psuedoAuthor,price,true));
            }
            else
            {
                books.add(new Book(name, psuedoAuthor, price));
            }
        }
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void close()
    {
        driver.quit();
    }

    public void displayBooks()
    {
        for(Book wb : books)
        {
            System.out.println(wb.toString());
            System.out.println("-------------------------------------");
        }
    }

    public void displayBook(int index)
    {
        try
        {
            System.out.println(books.get(index).toString());
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("You entered too big value for index. The maximum number of elements is " + books.size());
        }
    }

    public boolean atPage()
    {
        if(driver.getTitle().equals("Amazon.com : Java"))
            return true;
        else
            return false;
    }

}
