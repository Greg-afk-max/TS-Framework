import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration

class TestListener {


	@BeforeTestCase
	def beforeTestCase() {
		def htmlDir = RunConfiguration.getProjectDir() + GlobalVariable.urlLogin
		WebUI.openBrowser(htmlDir)
		WebUI.maximizeWindow()
	}

	@AfterTestCase
	def afterTestCase() {
		WebUI.closeBrowser()
	}
}

