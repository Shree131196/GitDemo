package cucumber.Options;




	import org.junit.runner.RunWith;
	//import static org.hamcrest.Matchers.*;

	import io.cucumber.junit.Cucumber;
	import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
	features = "src/test/resources/ShriyaFeature/FreeAPI.feature",
	glue = {"stepDefinitions"}, plugin = {"html:target/cucumber.html"})
	public class TestRunner {
		
	}


