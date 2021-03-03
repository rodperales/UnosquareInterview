package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags="@Scenario1",
		// tags="@Scenario2",
		// tags="@Scenario3",
		monochrome = true,
		plugin={"pretty","html:target/CucumberHtmlReports/report.html"},
		dryRun = true,
		features = "src/test/resources/features",
        glue    = "src/test/java/stepDefinition"
)
public class TestRunner {
	
}