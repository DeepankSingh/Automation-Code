Login Page Automation Framework

This repository contains an Automation Framework built using Java, Selenium WebDriver, TestNG, and the Page Object Model (POM) design pattern. The framework automates the testing of a login page UI for web applications, ensuring functionality and UI elements behave as expected.

📌 Features

✅ Java + Selenium WebDriver for browser automation
✅ Maven for dependency management
✅ TestNG as the test runner
✅ Page Object Model (POM) design for maintainability
✅ BaseTest class for browser setup and teardown
✅ Handles browser permission requests (like notifications)
✅ Includes positive and negative test scenarios

📂 Project Structure
Automation/
│
├── pom.xml                          # Maven dependencies
├── src/main/java/pages/LoginPage.java   # Page Object for Login Page
├── src/test/java/tests/BaseTest.java    # Browser setup & teardown
├── src/test/java/tests/LoginTest.java   # Test cases

🧪 Test Scenarios Covered

Login Button Disabled when fields are empty
Password Masking & Unmasking (eye icon toggle)
Invalid Login Attempt → verify error message
UI Validation → presence of title, input fields, buttons, and icons

⚙️ Setup & Execution
1️⃣ Clone the Repository
git clone https://github.com/DeepankSingh/automation-code.git
cd automation-code

2️⃣ Install Dependencies

Make sure Maven is installed, then run:

mvn clean install

3️⃣ Run Tests

Execute all TestNG tests:

mvn test

📊 Reports

TestNG generates HTML reports after test execution.
Reports can be found inside the test-output directory.

🔑 Tech Stack

Language: Java
Automation Tool: Selenium WebDriver
Test Runner: TestNG
Build Tool: Maven
Design Pattern: Page Object Model (POM)

📌 Future Enhancements

Add cross-browser testing support
Integrate with CI/CD pipeline (Jenkins/GitHub Actions)
Extend framework for data-driven testing

👨‍💻 Author

Deepank Singh
Automation Testing Enthusiast | Java & Selenium | Manual + Automation QA
