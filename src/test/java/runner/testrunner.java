package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import org.testng.annotations.Test;

@CucumberOptions(
	features = "src/test/java/features",
	glue= {"stepDefinition"},
	plugin  = { "pretty", "html:target/cucumber-reports"},
    monochrome = true
)

@Test
public class testrunner extends AbstractTestNGCucumberTests {
}