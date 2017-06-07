import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Webdriver {
	static final String PASSWORD = System.getenv("TRpassword");
	static final String USERNAME = System.getenv("TRuser");
	static final String RELEASE = "Release_36.3";
	static final String ISSUES = "//a[contains(@class, 'issue tracker')]";
	
	public void login(WebDriver driver){
		driver.findElement(By.className("login")).click();
		WebElement input = driver.findElement(By.name("username"));
		input.sendKeys(USERNAME);
		input = driver.findElement(By.name("password"));
		input.sendKeys(PASSWORD);
		input.submit();
	}
	@Test
	public void createTestPlan() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Felicio/Downloads/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//faz o webdriver esperar pelo menos 3 segundos antes de dar timeout caso nao ache o elemento
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://redmine.ss.local/projects/evoluservices/roadmap/");
		driver.findElement(By.name(RELEASE)).click();
		String release = driver.getCurrentUrl();
		
		int i =0;
		do{
			List<WebElement> issues = driver.findElements(By.xpath(ISSUES));
			System.out.println(issues.get(i).getText());
			issues.get(i).click();
			
			
			
			driver.navigate().back();
			i++;
			if (i == issues.size()) i = -1;
			
		}while (i != -1);
		
		
	}
}
