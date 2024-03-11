# CryptoTracker

When investing in cryptocurrencies, I have always struggled to keep track of my holdings. Although there are many apps developed for this but none of them met my requirements. I decided to create my own application, designed according to my needs. 

## Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [Features](#features)
- [Usage](#usage)
   - [Main Menu](#main-menu)
   - [Portfolio Management](#portfolio-management)
- [System Requirements](#system-requirements)
- [Integration with CoinAPI](#integration-with-coinapi)
- [Installation](#installation)
- [License](#license)
- [Acknowledgments](#acknowledgments)
- [Author](#author)


## Description

CryptoTracker is a simple Java application for tracking cryptocurrency portfolios. It allows users to manage multiple portfolios, add and delete trades, and view portfolio details. The application leverages the CoinAPI to fetch real-time exchange rates for cryptocurrencies.

## Technologies

Diving into the technical aspects, CryptoTracker employs the following technologies:

- Java
- Maven
- CoinAPI

## Features

- **Portfolio Management:**
  - Create new portfolios
  - Delete existing portfolios
  - View a list of available portfolios

- **Trade Management:**
  - Add new trades to a portfolio
  - Delete trades from a portfolio
  - View a list of all trades within a portfolio

- **Portfolio Details:**
  - Display the total value of each portfolio in USD based on live rates
  - Calculate the percentage change in portfolio value

## Usage

1. **Main Menu:**
     - Display portfolios
     - Manage portfolio
     - Create new portfolio
     - Delete portfolio
     - Exit

2. **Portfolio Management:**
     - Add a new trade
        - Enter the coin name, quantity, and price.
        - Confirm the trade details or cancel.
     - Delete a trade
        - Enter the trade ID you want to delete.
        - Confirm the deletion or cancel.
     - Show all trades
        - Displays all the trades in a given portfolio, their unique ID, the name of the                     cryptocurrency, its quantity and its value at the time of the exchange
     - Show portfolio details
        - Displays the coin name, held quantity, and estimated value in USD for each trade.
        - Shows the invested amount, total portfolio value, profit/loss, and percentage change.
     - Return to the main menu
     - Exit the application

## System Requirements

To run CryptoTracker, ensure that your system meets the following requirements:

- **Java:** Java Development Kit (JDK) 17 or later.
- **Maven:** Ensure Maven is installed on your system. You can download Maven from [Apache Maven](https://maven.apache.org/download.cgi).

## Integration with CoinAPI

To integrate CoinAPI with CryptoTracker, follow these steps:

1. **Get CoinAPI Key:**
   - Obtain your CoinAPI key by signing up on the [CoinAPI website](https://www.coinapi.io/).

2. **Configure CoinAPI Key:**
   - Open the `CoinAPI.java` file in the `app` package.
   - Locate the `API_KEY` field and replace the placeholder with your actual CoinAPI key.

   ```java
   private final String API_KEY = "YOUR_COINAPI_KEY";
   ```

## Installation

Follow these steps to install and run CryptoTracker:

1. Clone the repository: `git clone https://github.com/mikeshaggy/CryptoTracker-CLI.git`
2. Navigate to the project directory
3. Install dependencies with Maven: `mvn clean install`
4. Run the app: `mvn exec:java`

## License

This project is licensed under the [MIT License](LICENSE). Feel free to modify and distribute it as needed. If you find any issues or have suggestions, please create an [issue](https://github.com/yourusername/CryptoTracker/issues) or submit a pull request.

## Acknowledgments
- [CoinAPI.io website](https://www.coinapi.io)

## Author

**Michał Bagan**

Feel free to contact me:

- [GitHub](https://github.com/mikeshaggy)
- [LinkedIn](www.linkedin.com/in/michalbagan)
