
# Bank-api

This project demonstrates the implementation of a Bank Account Management system using Java and the Spring Boot framework. The system allows you to create and manage bank accounts for customers, perform transfers between accounts, and retrieve account balances and transfer history.

## Prerequisites
 -Java Development Kit (JDK) 20

 -Maven

 -Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)



## Getting Started


```bash
  1-Clone the repository:  git clone https://github.com/Georgeobeid/bank-api.git
```

```bash
  2-Navigate to the project directory: cd bank-api
```
```bash
  3-Build the project using Maven: mvn clean install
```

Run the Spring Boot application, the application will start and be accessible at 'http://localhost:8080'
## API Endpoints

### Customer Endpoints

#### Create new customer

```http
  POST /customer/create
```

| Parameter   | Type       | Description               |
| :---------- | :--------- | :------------------------ |
| `name`      | `string`   | The name of the customer  |

#### Retrieve a customer

```http
  GET /customer/get-customer/{customerId}
```

| Parameter   | Type       | Description                 |
| :---------- | :--------- | :-------------------------- |
| `id`        | `Long`     | Customer ID to be retrieved |



### Bank account Endpoints

#### Create new bank account

```http
  POST /bank-account/create
```

| Parameter       | Type       | Description                     |
| :------------   | :--------- | :------------------------------ |
| `customerId`    | `Long`     | Customer ID for the new account |
| `initialBalance`| `double`   | Initial balance                 |

#### Transfer funds 

```http
  POST /bank-account/transfer
```

| Parameter         | Type       | Description              |
| :------------     | :--------- | :----------------------- |
| `sourceAccountId` | `Long`     | Source account ID        |
| `targetAccountId` | `Long`     | Target account ID        |
| `amount`          | `double`   | Amount to be transferred |

#### Retrieve account balance

```http
  GET /bank-account/balance/{accountId}
```

| Parameter   | Type       | Description  |
| :---------- | :--------- | :---------   |
| `accountId` | `Long`     | Account ID   |

#### Retrieve transfer history for an account

```http
  GET /bank-account/transactions/{accountId}
```

| Parameter   | Type       | Description |
| :---------- | :--------- | :---------  |
| `accountId` | `Long`     | Account ID  |

## Error Handling

Custom error handling implementation is required.
## Data Storage

This project uses an in-memory H2 database for demonstration purposes. In a real-world scenario, a more robust database system would be used.
## Security

This project does not implement security features (authentication and authorization) for simplicity.
## License

This project has been created by George Obeid 
