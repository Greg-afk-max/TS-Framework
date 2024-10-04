package com.ts.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Alert
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

public class UI_CustomKeywords {

	/**
	 * Opens the specified URL in the browser.
	 *
	 * @param url The URL to open.
	 */
	static void openUrl(String url) {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(url)
	}


	/**
	 * Click on an element located by its XPath.
	 *
	 * @param elementXpath - The XPath of the web element to be clicked.
	 * @throws StepFailedException if the element is not found or clickable.
	 * @example
	 * WebActions.clickElement("//button[@id='login']")
	 */
	@Keyword
	def static clickElement(String elementXpath) {
		WebUI.click(findTestObject(elementXpath))
	}

	/**
	 * Enter text into an input field located by its XPath.
	 *
	 * @param elementXpath - The XPath of the input field.
	 * @param text - The text to be entered into the input field.
	 * @throws StepFailedException if the element is not found or text cannot be entered.
	 * @example
	 * WebActions.enterText("//input[@id='username']", "myUsername")
	 */
	@Keyword
	def static enterText(String elementXpath, String text) {
		WebUI.setText(findTestObject(elementXpath), text)
	}


	/**
	 * Custom keyword to verify the visibility of an element on the webpage.
	 *
	 * This method accepts the Xpath of the element as a string and checks whether
	 * the corresponding element is visible on the web page. If the element is not
	 * visible, Katalon will throw an assertion error.
	 *
	 * @param elementXpath The Xpath of the element to be verified (as a String).
	 *                     It should match the element in the object repository.
	 *                     Example: 'Object Repository/Page_Login/btn_login'
	 *
	 * Usage Example:
	 * verifyElementVisible('Object Repository/Page_Login/btn_login')
	 *
	 * Note: Ensure that the Xpath provided corresponds to a valid TestObject in
	 *       the Object Repository.
	 */
	@Keyword
	static void verifyElementVisible(String elementXpath) {
		WebUI.verifyElementVisible(findTestObject(elementXpath))
	}



	/**
	 * Executes JavaScript in the context of the currently selected frame or window.
	 *
	 * @param script The JavaScript code to execute.
	 * @param args Optional arguments to pass to the script.
	 */
	static void executeJavaScript(String script, Object... args) {
		// Get the current driver
		def driver = DriverFactory.getWebDriver()

		// Cast to JavascriptExecutor and execute the script
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver
		jsExecutor.executeScript(script, args)
	}

	/**
	 * Switches the context to the alert.
	 *
	 * @return The Alert object.
	 */
	static Alert switchToAlert() {
		def driver = DriverFactory.getWebDriver()
		return driver.switchTo().alert()
	}

	/**
	 * Accepts the alert.
	 */
	static void acceptAlert() {
		Alert alert = switchToAlert()
		alert.accept()
	}

	/**
	 * Dismisses the alert.
	 */
	static void dismissAlert() {
		Alert alert = switchToAlert()
		alert.dismiss()
	}

	/**
	 * Retrieves the text from the alert.
	 *
	 * @return The text of the alert.
	 */
	static String getAlertText() {
		Alert alert = switchToAlert()
		return alert.getText()
	}

	/**
	 * Sends input to the alert.
	 *
	 * @param keysToSend The input to send to the alert.
	 */
	static void sendKeysToAlert(String keysToSend) {
		Alert alert = switchToAlert()
		alert.sendKeys(keysToSend)
	}

	/**
	 * Creates an explicit wait instance.
	 *
	 * @param seconds The duration in seconds to wait.
	 * @return WebDriverWait instance.
	 */
	static WebDriverWait createWait(int seconds) {
		WebDriver driver = DriverFactory.getWebDriver()
		return new WebDriverWait(driver, Duration.ofSeconds(seconds))
	}

	/**
	 * Waits until the specified element is visible.
	 *
	 * @param element The WebElement to wait for.
	 * @param seconds The maximum time to wait in seconds.
	 */
	static void waitForElementToBeVisible(WebElement element, int seconds) {
		WebDriverWait wait = createWait(seconds)
		wait.until(ExpectedConditions.visibilityOf(element))
	}

	/**
	 * Waits until the specified element is clickable.
	 *
	 * @param element The WebElement to wait for.
	 * @param seconds The maximum time to wait in seconds.
	 */
	static void waitForElementToBeClickable(WebElement element, int seconds) {
		WebDriverWait wait = createWait(seconds)
		wait.until(ExpectedConditions.elementToBeClickable(element))
	}

	@Keyword
	def static mouseOverDatePicker(String datePickerXPath) {
		WebElement datePickerElement = WebUI.findWebElement(datePickerXPath)
		Actions actions = new Actions(DriverFactory.getWebDriver())
		actions.moveToElement(datePickerElement).perform()
	}

	/**
	 * Switches to a frame using its index.
	 *
	 * @param index The index of the frame to switch to.
	 */
	static void switchToFrameByIndex(int index) {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.switchTo().frame(index)
	}

	/**
	 * Switches to a frame using its name or ID.
	 *
	 * @param nameOrId The name or ID of the frame to switch to.
	 */
	static void switchToFrameByNameOrId(String nameOrId) {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.switchTo().frame(nameOrId)
	}

	/**
	 * Switches back to the main content.
	 */
	static void switchToDefaultContent() {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.switchTo().defaultContent()
	}

	/**
	 * Switches to a specified window.
	 *
	 * @param windowHandle The handle of the window to switch to.
	 */
	static void switchToWindow(String windowHandle) {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.switchTo().window(windowHandle)
	}
}


