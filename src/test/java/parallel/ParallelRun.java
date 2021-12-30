package parallel;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/failedrerun.txt"
			
				}, 
		monochrome = true,
		glue = { "parallel" },
    	features = { "src/test/resources/parallel/LoginPage.feature" }
//		features = { "/Users/harshchoubey/eclipse-workspace/CucumberPOMSeriesTestNgExcelFailedRerun/src/test/resources/parallel/LoginPage.feature" }
)

public class ParallelRun extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}