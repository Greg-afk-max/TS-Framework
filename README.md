Thoughtstorm Keyword & Data-Driven Framework in Katalon
Overview
This framework, developed by Thoughtstorm, is designed to enhance testing efficiency by implementing keyword-driven and data-driven approaches within Katalon Studio. It simplifies the test automation process by separating the logic of tests from the data, making it easier for teams to scale, maintain, and execute a wide range of tests.
Features
•	Keyword-Driven Testing: Leverage reusable custom keywords to create modular test cases.
•	Data-Driven Testing: Execute test cases with varying data inputs to cover multiple test scenarios.
•	Reusable Keywords: Define actions once and reuse them across various test cases, improving efficiency.
•	Seamless Data Integration: Pull test data from multiple sources such as Excel, CSV, or databases, without altering the test logic.
Folder Structure
bash
Copy code
├── Keywords/
│   ├── CustomKeywords.groovy         # Custom keywords developed by Thoughtstorm
│   └── UtilityKeywords.groovy        # Utility/helper keywords for common tasks
│
├── Test Cases/
│   ├── SampleTests/                  # Keyword-driven test cases
│   └── DataDrivenTests/              # Data-driven test cases
│
├── Data Files/
│   ├── TestData.xlsx                 # Excel data for data-driven tests
│   └── TestData.csv                  # CSV data for data-driven tests
│
├── Object Repository/                # Web and mobile objects repository
│
├── Test Suites/
│   └── DataDrivenTestSuite/          # Suite for executing data-driven tests
│
├── Reports/                          # Test execution reports
Keywords
At Thoughtstorm, we have crafted custom and utility keywords to support your testing needs. These reusable components allow you to quickly assemble tests without repetitive coding.
•	CustomKeywords.groovy: Business-specific keywords for testing core functionalities.
•	UtilityKeywords.groovy: Helper functions to assist with common tasks like logging, validation, and data processing.
Example Keyword
groovy
Copy code
package thoughtstorm

import com.kms.katalon.core.annotation.Keyword

public class CustomKeywords {
    
    @Keyword
    def login(String username, String password) {
        WebUI.setText(findTestObject('Object Repository/input_username'), username)
        WebUI.setText(findTestObject('Object Repository/input_password'), password)
        WebUI.click(findTestObject('Object Repository/button_login'))
    }
}
Data-Driven Testing
Thoughtstorm's framework integrates seamlessly with external data files, enabling tests to run with varying inputs. The Data Files/ directory contains all necessary test data files (e.g., Excel or CSV).
Steps to Set Up Data-Driven Test
1.	Add your data file to the Data Files/ folder.
2.	Use Katalon’s built-in data binding feature to connect test data with test cases.
3.	Configure and run the test suite for data-driven execution.
Example Data-Driven Test Case
groovy
Copy code
WebUI.callTestCase(findTestCase('Test Cases/LoginTest'), [('username') : findTestData('TestData').getValue(1, rowNumber), 
                                                          ('password') : findTestData('TestData').getValue(2, rowNumber)])
How to Run Tests
1. Running Keyword-Driven Tests
•	Open the desired test case from the Test Cases/ directory.
•	Execute the test within Katalon Studio or use the command line.
2. Running Data-Driven Tests
•	Configure the test suite in Test Suites/DataDrivenTestSuite/.
•	Make sure the data source is correctly mapped.
•	Run the test suite either from Katalon Studio or the command line.
3. Running Tests via Command Line
bash
Copy code
katalon -noSplash -runMode=console -projectPath="path_to_your_project" -testSuitePath="Test Suites/DataDrivenTestSuite" -browserType="Chrome"
Reporting
After running the tests, detailed reports will be stored in the Reports/ folder. These reports include:
•	Test execution results
•	Logs
•	Screenshots (if enabled)
Setup Instructions
1.	Install Katalon Studio: Download it from Katalon Studio.
2.	Clone the Repository:
bash
Copy code
git clone https://github.com/thoughtstorm/repo-url.git
3.	Open the Project: Open the project within Katalon Studio and configure your data sources.
4.	Run Tests: Execute tests either through the Katalon Studio interface or via the command line.
Contributing
We welcome contributions to the Thoughtstorm framework. To contribute:
1.	Fork the repository.
2.	Create a new branch for your feature or bug fix.
3.	Commit your changes and open a pull request for review.

