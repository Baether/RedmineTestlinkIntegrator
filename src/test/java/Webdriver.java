import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;
import javax.swing.*;

public class Webdriver {
	static final String PASSWORD = System.getenv("TRpassword");
	static final String USERNAME = System.getenv("TRuser");
	static final String[] RELEASE = 	{"Release_36.3", "Release_36.3_-_Homologação"};
	static final String ISSUES = "//a[contains(@class, 'issue tracker')]";
	static final String QADOC = "//td[contains(text(),'Q.A. Doc')]/";//*[contains(text(),'match')]

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
		/*System.setProperty("webdriver.chrome.driver", "C:/Users/Felicio/Downloads/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//faz o webdriver esperar pelo menos 3 segundos antes de dar timeout caso nao ache o elemento
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);



		for (int j = 0; j < RELEASE.length ; j++ ) {
			driver.get("http://redmine.ss.local/projects/evoluservices/roadmap/");
			driver.findElement(By.name(RELEASE[j])).click();
			int i=0;
			do {

				List<WebElement> issues = driver.findElements(By.xpath(ISSUES));
				String type = issues.get(i).getText();
				System.out.println(type);
				issues.get(i).click();
				if(!type.toLowerCase().contains("Bug".toLowerCase())) {
					try {
						driver.findElement(By.xpath(QADOC));
						System.out.println("QAdoc" + i);
						driver.navigate().back();
					} catch (NoSuchElementException e) {
						System.out.println("Issues sem Q.A. Doc");
					}
				}
				driver.navigate().back();
				i++;
				if (i == issues.size()) i = -1;

			} while (i != -1);
		}*/

		
	}
}
