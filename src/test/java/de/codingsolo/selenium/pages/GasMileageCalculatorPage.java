package de.codingsolo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GasMileageCalculatorPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private final String ODOMETER_NOW = "mucodreading";
	private final String ODOMETER_BEFORE = "mupodreading";
	private final String GAS_ADDED = "mugasputin";
	private final String GAS_PRICE = "mugasprice";

	// Fields
	@FindBy(name = ODOMETER_NOW)
	private WebElement odometerNow;

	@FindBy(name = ODOMETER_BEFORE)
	private WebElement odometerBefore;

	@FindBy(name = GAS_ADDED)
	private WebElement gasAdded;

	@FindBy(name = GAS_PRICE)
	private WebElement gasPrice;

	// Button
	@FindBy(xpath = "//table[@style='display: block;']//input[@src='//d26tpo4cm8sb6k.cloudfront.net/img/svg/calculate.svg']")
	private WebElement calculateBtn;

	// Result

	@FindBy(xpath = "//p[@class='verybigtext']/font")
	private WebElement resultText;

	public GasMileageCalculatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		this.wait = new WebDriverWait(driver, 4);
	}

	public void executeCalculation() {
		wait.until(ExpectedConditions.elementToBeClickable(this.calculateBtn));
		this.calculateBtn.click();

	}

	public String getResult() {
		wait.until(ExpectedConditions.elementToBeClickable(this.resultText));

		return this.resultText.getText();
	}

	public WebElement getOdometerNow() {
		return odometerNow;
	}

	public void setOdometerNow(String odometerNow) {
		wait.until(ExpectedConditions.elementToBeClickable(this.odometerNow));
		this.odometerNow.clear();
		this.odometerNow.sendKeys(odometerNow);
	}

	public WebElement getOdometerBefore() {
		return odometerBefore;
	}

	public void setOdometerBefore(String odometerBefore) {
		wait.until(ExpectedConditions.elementToBeClickable(this.odometerBefore));
		this.odometerBefore.clear();
		this.odometerBefore.sendKeys(odometerBefore);
	}

	public WebElement getGasAdded() {
		return gasAdded;
	}

	public void setGasAdded(String gasAdded) {
		wait.until(ExpectedConditions.elementToBeClickable(this.gasAdded));
		this.gasAdded.clear();
		this.gasAdded.sendKeys(gasAdded);
	}

	public WebElement getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(String gasPrice) {
		wait.until(ExpectedConditions.elementToBeClickable(this.gasPrice));
		this.gasPrice.clear();
		this.gasPrice.sendKeys(gasPrice);
	}

}
