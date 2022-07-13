package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/java/featurefile/Feature.feature"},
        glue = { "stepDefinationforCucumber"},
        plugin = {"pretty",
                "json:target/Myreports/report.json"     }

)

public class TestRunner {



}