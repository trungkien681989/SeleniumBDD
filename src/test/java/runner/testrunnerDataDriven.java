package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import org.testng.annotations.Test;

@CucumberOptions(
	features = "src/test/java/featuresDataDriven",
	glue= {"stepDefinitionDataDriven"},
	plugin  = { "pretty", "html:target/cucumber-reports"},
    monochrome = true
)

@Test
public class testrunnerDataDriven extends AbstractTestNGCucumberTests {
}