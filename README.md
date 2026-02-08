# 🧪 SauceDemo Test Automation Project

## 📌 About the Project

This project focuses on automated testing of **saucedemo.com** using a modern QA stack.
The main goal is to learn and understand **user flows in a shopping application** and practice building reliable automated tests around them.

## 🎯 Goals

* Understand real-world e-commerce user journeys
* Practice end-to-end UI test automation
* Learn how to structure scalable test suites
* Integrate testing into a CI pipeline

## ⚙️ Tech Stack

* **Java** – test implementation
* **Selenium WebDriver** – browser automation
* **Selenium Grid** – distributed test execution
* **GitHub Actions** – Continuous Integration
* **Jira** – test tracking and issue management

## 🔄 What is Tested

* Login flow
* Product browsing
* Cart management
* Checkout process
* Basic user interactions

## ▶️ How to Run the Tests

### 1️⃣ Install Dependencies

Run the following command to install all required dependencies:

```
mvn install
```

### 2️⃣ Run Tests Locally

Execute the test suite with:

```
mvn test
```

## 🌐 Running Tests with Selenium Grid (Standalone)

### Start Selenium Grid in PowerShell

Navigate to the folder where your Selenium jar is located and run:

```
java -jar selenium-server.jar standalone
```

This will start the Grid hub and node together (standalone mode).
By default, Grid will be available at:

```
http://localhost:4444
```

### Run Tests on Grid

Once Grid is running, simply execute:

```
mvn test
```

Your tests will connect to the Grid and run remotely.

## 🚀 CI & Automation

Tests run automatically through **GitHub Actions**, allowing continuous validation of the application across environments.

## 📚 Learning Focus

This repository is primarily a learning project aimed at:

* Understanding test design
* Exploring shopping app user flows
* Improving automation and QA workflows

---

✨ *Built for learning, experimenting, and improving test automation skills.*
