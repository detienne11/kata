package com.oxiane.detienne.kata.bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/com/oxiane/detienne/kata/features", glue = "com.oxiane.detienne.kata.bdd", plugin = {
		"pretty", "html:target/cucumber" })
public class RunBDDIT {

}
