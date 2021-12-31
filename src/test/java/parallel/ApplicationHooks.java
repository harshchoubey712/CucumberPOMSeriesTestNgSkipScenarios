package parallel;

import java.util.Properties;

import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;



public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;

	@Before(value = "@skip", order=0)
	public void skip_scenario(Scenario scenario)
	{
		System.out.println("SKIPPED SCENARIO is " + scenario.getName());
		Assume.assumeTrue(false);
		
	}
	
	@Before(order=1)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}

	@Before(order = 2)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
/*
 Above will launch the driver with thread local using driverfactory object.
 
 */
		
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	/*
	 after running each sceanrion , order=1 hook  will run 1st which checks if any sceanrio is failed and if yes it will
	 attach failed screenshot the same scenario.
	 
	 after that order=0 hook will run which will quit  the browser.
	 */
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			
    /* teardown method will run if any sceanrio gets failed.
     Sourcepath will take a screenshot.

     Scenario.attach will then attach the screenshot of the failed step to the scenario inside the cucumber report.
     */
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}

}

