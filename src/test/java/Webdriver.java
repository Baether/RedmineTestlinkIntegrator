import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;
import javax.swing.*;

public class Webdriver {
	static final String DRIVER_LOCATION = "C:/Users/Felicio/Downloads/chromedriver_win32/chromedriver.exe";
	static final String PASSWORD = System.getenv("TRpassword");
	static final String USERNAME = System.getenv("TRuser");
	static final String[] RELEASE = 	{"Release_36.3", "Release_36.3_-_Homologação"};
	static final String ISSUES = "//a[contains(@class, 'issue tracker')]";
	static final String QADOC = "//td[contains(text(), 'Doc')]/a";
	static final String EVO = "//div[@class = 'wiki']/p";
	static final String TESTLINK = "//td[contains(text(), 'EVO')]";
	static final String MANAGE_TEST_PLAN= "//a[@href = 'lib/plan/planView.php']";
	//static Globals globals = JsePlatform.standardGlobals();


	public static void testlinkLogin(WebDriver driver){
		WebElement input = driver.findElement(By.name("tl_login"));
		input.sendKeys(USERNAME);
		input = driver.findElement(By.name("tl_password"));
		input.sendKeys(PASSWORD);
		input.submit();
	}
	public static void redmineLogin (WebDriver driver){
		driver.findElement(By.className("login")).click();
		WebElement input = driver.findElement(By.name("username"));
		input.sendKeys(USERNAME);
		input = driver.findElement(By.name("password"));
		input.sendKeys(PASSWORD);
		input.submit();
	}

	public static void createStagingTestPlan(ArrayList<String> evos){
		WebDriver driver = new ChromeDriver();

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("credentials_enable_service", false);
		options.setExperimentalOption("profile.password_manager_enabled", false);

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://testlink.ss.local:8080/testlink/login.php");
		testlinkLogin(driver);
		//driver.findElement(By.xpath(MANAGE_TEST_PLAN)).click();
		Select select = new Select(driver.findElement(By.name("testproject")));
		select.selectByValue("100561");
	}

	//@Test
	public static ArrayList<String> getTestCases() {
		ArrayList<String> testCases = new ArrayList<>();
		WebDriver driver = new ChromeDriver();
		//faz o webdriver esperar pelo menos 2 segundos antes de dar timeout caso nao ache o elemento
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		for (String release : RELEASE ) {
			driver.get("http://redmine.ss.local/projects/evoluservices/roadmap/");
			driver.findElement(By.name(release)).click();
			int i=0;
			do {

				List<WebElement> issues = driver.findElements(By.xpath(ISSUES));
				String type = issues.get(i).getText();
				issues.get(i).click();
				if(!type.toLowerCase().contains("bug")) {
					try {
						driver.findElement(By.xpath(QADOC)).click();
						try {
							WebElement evos = driver.findElement(By.xpath(EVO));
							String aux[] = evos.getText().split("\\n");
							for (String evo : aux){
								testCases.add(evo);
							}
						}
						catch(NoSuchElementException e){
							System.out.println("Não foi possivel achar o(s) EVO(s)");
						}

						driver.navigate().back();
					} catch (NoSuchElementException e) {
						System.out.println("Issues sem Q.A. Doc");
					}
				}
				else{
					try {
						String aux[] = driver.findElement(By.xpath(TESTLINK)).getText().split(",");
						for (String evo : aux) {
							testCases.add(evo.trim());
						}
					}
					catch (NoSuchElementException e){
						System.out.println("Bug está sem testlink associado");
					}
				}
				driver.navigate().back();
				i++;
				if (i == issues.size()) i = -1;

			} while (i != -1);
		}
			System.out.println(testCases.toString());
		return testCases;
	}

	public static void main(String [ ] args){
		System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);

		ArrayList<String> nada = new ArrayList<>();
		createStagingTestPlan(nada);
		//System.out.println(getTestCases());

	}
}
