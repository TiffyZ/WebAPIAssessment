# Rewards Program Web API

This is a web API that provides a rewards program for customers based on their recorded purchases. It calculates reward points for each transaction and allows retrieval of monthly and total rewards for customers.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Maven

## Setup and Installation

1. Clone the repository to your local machine.
2. Make sure you have Java JDK installed.
3. Build the project using Maven.
4. Configure your own database in the application.properties file
5. Run the application

## API Endpoints

### Get Rewards for a Customer

- Endpoint: `GET /api/rewards/{customerId}`
- Description: Retrieves the most recent 3 months' rewards for a specific customer.
- Parameters:
- `customerId`: ID of the customer.
- Returns: The object containing monthly rewards and total rewards for the customer.


## Usage

1. Send a GET request to the endpoint `/api/rewards/{customerId}` to retrieve the rewards for a specific customer.
- Replace `{customerId}` with the actual ID of the customer.
2. The API will calculate and return the monthly rewards and the total rewards for the customer.


## Test:
The test files are placed in the /test folder.