# CliRental User Guide

## Introduction

CliRental is a CLI-based application that allows car rental companies to track their customers, cars, and rental transactions.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `CliRental` from [here](https://github.com/AY2425S1-CS2113-T11-3/tp/releases).

---

## File saving

`Customer`, `Transaction`, and `Car` data will be saved in their respective files under the `data` directory.

`IMPORTANT NOTE :`

1. `All wrongly formatted files will not be inputted.`
2. `Any additional data added must be in the correct format.`

Filenames:

- `Car data`: `carData.txt`
- `Customer data`: `customerData.txt`
- `Transaction data`: `transactionData.txt`

Format:

- `Car data`: `CAR MODEL | CAR ID | PRICE | RENTED`
- `Customer data`: `NAME | AGE | PHONE NUMBER`
- `Transaction data`: `CAR ID | NAME | RENTAL DURATION(DAYS) | RENTAL START DATE`

Example:

- `Car data`: `Toyota Corolla | SGM4932K | 120.0 | false`
- `Customer data`: `John | 22 | 90907638`
- `Transaction data`: `SGM4932K | John | 30 | 17-10-2024`

---

## Features

### Adding a user: `add-user`

Adds a customer to the list.

**Format**: `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]`

**Example**: `add-user /u John /a 18 /c 95382572`

### Removing a user: `remove-user`

Removes a customer from the list.

**Format**: `remove-user /u [CUSTOMER_NAME]`

### Listing all users: `list-users`

Lists all customers.

**Format**: `list-users`

### Adding a car: `add-car`

Adds a car to the car list.

**Format**: `add-car /n [CAR_MODEL] /c [CAR_ID] /p [PRICE]`

**Example**: `add-car /n Honda Civic /c SGE1234X /p 10000`

### Removing a car: `remove-car`

Removes a car from the list.

**Format**: `remove-car`

### Listing all cars: `list-cars`

Lists all cars in the fleet.

**Format**: `list-cars`

### Removing all cars: `remove-all-cars`

Removes all cars from the list.

**Format**: `remove-all-cars`

### Adding a transaction: `add-tx`

Adds a new rental transaction.

**Format**: `add-tx /c [CAR_ID] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE: <dd-MM-yyyy>]`

**Example**: `add-tx /c SZZ1579D /u John /d 15 /s 11-05-2025`

### Marking a transaction as complete: `mark-tx`

Marks a transaction as completed.

**Format**: `mark-tx /t [TRANSACTION_ID]`

### Unmarking a transaction: `unmark-tx`

Unmarks a transaction.

**Format**: `unmark-tx /t [TRANSACTION_ID]`

### Removing a transaction: `remove-tx`

Removes a transaction from the system.

**Format**: `remove-tx /t [TRANSACTION_ID]`

### Removing all transactions: `remove-all-txs`

Removes all transactions.

**Format**: `remove-all-txs`

### Listing all transactions: `list-tx`

Displays all transactions.

**Format**: `list-tx`

### Listing completed transactions: `list-tx-completed`

Displays completed transactions.

**Format**: `list-tx-completed`

### Listing uncompleted transactions: `list-tx-uncompleted`

Displays uncompleted transactions.

**Format**: `list-tx-uncompleted`

### Finding transactions by customer: `find-tx-by-customer`

Finds transactions associated with a specific customer.

**Format**: `find-tx-by-customer /u [CUSTOMER_NAME]`

### Exiting the program: `exit`

Closes the application.

**Format**: `exit`

---

## Command Summary

| **Category**       | **Command**                                      | **Format**                                                        |
|--------------------|--------------------------------------------------|-------------------------------------------------------------------|
| **Help**           | Show help page                                   | `help`                                                            |
| **User Management**| Add customer                                     | `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]`        |
|                    | Remove customer                                  | `remove-user /u [CUSTOMER_NAME]`                                  |
|                    | List customers                                   | `list-users`                                                      |
| **Car Management** | Add car                                          | `add-car /n [CAR_MODEL] /c [CAR_ID] /p [PRICE]`                   |
|                    | Remove car                                       | `remove-car`                                                      |
|                    | List all cars                                    | `list-cars`                                                       |
|                    | Remove all cars                                  | `remove-all-cars`                                                 |
| **Transactions**   | Add transaction                                 | `add-tx /c [CAR_ID] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE]` |
|                    | Mark transaction complete                        | `mark-tx /t [TRANSACTION_ID]`                                     |
|                    | Unmark transaction                               | `unmark-tx /t [TRANSACTION_ID]`                                   |
|                    | Remove transaction                               | `remove-tx /t [TRANSACTION_ID]`                                   |
|                    | Remove all transactions                          | `remove-all-txs`                                                  |
|                    | List all transactions                            | `list-tx`                                                         |
|                    | List completed transactions                      | `list-tx-completed`                                               |
|                    | List uncompleted transactions                    | `list-tx-uncompleted`                                             |
|                    | Find transactions by customer                    | `find-tx-by-customer /u [CUSTOMER_NAME]`                          |
| **Exit**           | Exit the program                                 | `exit`                                                            |
