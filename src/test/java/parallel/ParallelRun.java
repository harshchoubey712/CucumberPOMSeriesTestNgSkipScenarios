package parallel;

import java.util.Properties;

import org.junit.Assume;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/failedrerun.txt"
			
				}, 
//		tags ="not Skip",
		//comment tags ="not Skip", so that hooks can run and scenario can be skipped using hooks not through parallel runner.
		monochrome = true,
		glue = { "parallel" },
    	features = { "src/test/resources/parallel/LoginPage.feature" }
)

public class ParallelRun extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}