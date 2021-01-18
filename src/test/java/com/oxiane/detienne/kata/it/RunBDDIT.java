package com.oxiane.detienne.kata.it;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/com/oxiane/detienne/kata/features", glue = "com.oxiane.detienne.kata.it", plugin = {
		"pretty", "html:target/cucumber" })
public class RunBDDIT {

}
