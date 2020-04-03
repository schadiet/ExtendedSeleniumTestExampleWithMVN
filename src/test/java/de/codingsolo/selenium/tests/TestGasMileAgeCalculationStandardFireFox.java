package de.codingsolo.selenium.tests;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.pages.GasMileageCalculatorPage;

@RunWith(Parameterized.class)
public class TestGasMileAgeCalculationStandardFireFox {

	WebDriver driver;
	String browsername;
	String browserdriver;
	String odometerNow;
	String odometerBefore;
	String gasAdded;
	String gasPrice;
	String resultText;

	public TestGasMileAgeCalculationStandardFireFox(String testName, String browsername, String browserdriver,
			String odometerNow, String odometerBefore, String gasAdded, String gasPrice, String resultText) {
		super();
		this.browsername = browsername;
		this.browserdriver = browserdriver;
		this.odometerNow = odometerNow;
		this.odometerBefore = odometerBefore;
		this.gasAdded = gasAdded;
		this.gasPrice = gasPrice;
		this.resultText = resultText;
	}

	@Before
	public void beforeCalculationTest() throws Exception {
		System.out.println("Vor dem GasMileAge Calculation Test");
		System.setProperty(Config.getBrowser(this.browsername), Config.getBrowserDriver(this.browserdriver));

		driver = new FirefoxDriver();
		driver.get(Config.getBaseUrl());
	}

	@After
	public void afterCalclulationTest() throws Exception {
		System.out.println("Nach dem GasMileAge Calculation Test");
		driver.close();
//		driver.quit();
	}

	@Test
	public void testCalculationTest() throws Exception {
		System.out.println("Test der GasMileAge Calculation");

		// ARRANGE
		GasMileageCalculatorPage gasMileagePage = new GasMileageCalculatorPage(driver);
		gasMileagePage.setOdometerNow(this.odometerNow);
		gasMileagePage.setOdometerBefore(this.odometerBefore);
		gasMileagePage.setGasAdded(this.gasAdded);
		gasMileagePage.setGasPrice(this.gasPrice);

		// ACT
		gasMileagePage.executeCalculation();

		// ASSERT
		String result = gasMileagePage.getResult();
//		System.out.println(result);

		assertThat(result).contains(this.resultText);

	}

	@Parameters(name = "{0}")
	public static Collection<Object[]> provideTestData() throws Exception {

		Collection<Object[]> collection;

		Object[][] daten = {
				{ "Gas Mileage Calculator Test - FireFox", "firefoxbrowser", "firefoxdriver", "1500", "1000", "50",
						"1.40", "10 km/L or 10 L/100 km" },
				{ "Gas Mileage Calculator Test - FireFox", "firefoxbrowser", "firefoxdriver", "1500", "1000", "50",
						"1.40", "10 km/L or 10 L/100 km" } };
		List<Object[]> listObjects = Arrays.asList(daten);
		collection = new ArrayList<Object[]>(listObjects);

		return collection;
	}

}
