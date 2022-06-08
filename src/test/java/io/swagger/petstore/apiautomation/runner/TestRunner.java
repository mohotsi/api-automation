package io.swagger.petstore.apiautomation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = "src/test/java/io/swagger/petstore/apiautomation/features"
        , glue = {"io.swagger.petstore.apiautomation.definitions", "io.swagger.petstore.apiautomation.config"},
        monochrome = true,
        plugin = {"pretty",
                "json:target/output/HtmlReports.json", "html:target/output/HtmlReports.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
