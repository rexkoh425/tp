# CliRental User Guide

## Introduction

Clirental is a CLI-based application that allows car rental companies to track their customers, cars, and rental transactions.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `CliRental` from [here](https://github.com/AY2425S1-CS2113-T11-3/tp/releases).

---
## File Saving

`Customer`, `Transaction`, and `Car` data will be saved in their respective files under the `data` directory.

**IMPORTANT NOTE:**

1. All wrongly formatted files will not be processed.
2. Users must ensure any additional data follows the correct format.

**Filenames:**

* `Car data`: `carData.txt`
* `Customer data`: `customerData.txt`
* `Transaction data`: `transactionData.txt`

**Format:**
* `Car data`: `CAR MODEL | CAR ID | PRICE | RENTED`
* `Customer data`: `NAME | AGE | PHONE NUMBER`
* `Transaction data`: `CAR ID | NAME | RENTAL DURATION(DAYS) | RENTAL START DATE`

**Types:**
* `Car data`: `STRING | STRING | DOUBLE | BOOLEAN`
* `Customer data`: `STRING | INT | STRING`
* `Transaction data`: `STRING | STRING | INT | LOCALDATE`

**Example:**

* `Car data`: `Toyota Corolla | SGM4932K | 120.0 | false`
* `Customer data`: `John | 22 | 90907638`
* `Transaction data`: `SGM4932K | John | 30 | 17-10-2024`

---
## Features

### Adding a User to the Database: `add-user`

Adds a customer to the list of customers tracked by the car rental application.

**Format:** `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]`

* `CUSTOMER_NAME`: `STRING`.
* `AGE`: `INT`
* `CONTACT_NUMBER`: `INT`
* `/u`, `/a`, `/c` must be in sequence.

**Example of usage:**  
`add-user /u John /a 18 /c 95382572`

**Sample Response:**
```
____________________________________________________________
add-user /u John /a 18 /c 95382572
Customer added
John | 18 | 95382572
____________________________________________________________
What would you like to do?
____________________________________________________________
```

### Adding a Car: `add-car`

Adds a car to the car list.

**Format:** `add-car /n [CAR_MODEL] /c [CAR_ID] /p [PRICE]`

- `/n`, `/c`, and `/p` identifiers must be in the correct order.
- `CAR_ID` must be unique and follow the format `SXX####X`.
- `PRICE` must be a non-negative, numeric value.

**Example:**  
`add-car /n Honda Civic /c SGE1234X /p 10000`

**Sample output:**
``` 
Car added to list
Car details:
Honda Civic | SGE1234X | $10000.00 | Available
```

### Listing All Cars: `list-cars`

Lists all the cars owned by the company.

**Format:** `list-cars`

**Sample Response:**
```
____________________________________________________________
list-cars
Here are the current cars in the company
1) Toyota Corolla | SGM4932K | $120.00 | Not Rented
   What would you like to do?
____________________________________________________________
```

### Listing All Rented Out Cars: `list-rented`

Lists all the cars that are currently rented out.

**Format:** `list-rented`

**Sample output:**
```
Here are all the rented-out cars:
1) Honda Civic | SGE1234X | $100.00
2) Toyota Camry | SKL4567M | $200.00
3) Nissan Latio | SFT1190A | $300.00
```

If the list is **empty**:
```
No cars currently rented out...
```

### Listing All Available Cars: `list-available`

Lists all available cars in the company.

**Format:** `list-available`

**Sample output:**
```
Here are all the available cars:
1) Mitsubishi Attrage | SGP7877N | $1500.00
2) Honda Vezel | SLK9945F | $3400.00
```

If the list is **empty**:
```
There are no available cars at the moment...
```

### Updating Rental Status of Car

There is no need to manually update the rental status of a car. The status will be updated automatically when a transaction record is:

- Added
- Removed
- Marked as completed
- Marked as not completed

### Listing All Transactions: `list-tx`

Displays all transactions stored in the system.

**Format:** `list-tx`

**Sample output:**
```
Here are all the transactions:
1) [ ] TX1 | SGE1234X | john | 4day(s)
   Start Date: 11-12-2024
2) [ ] TX2 | SKL4567M | thomas | 6day(s)
   Start Date: 11-12-2024
3) [ ] TX3 | SFT1190A | matthew | 8day(s)
   Start Date: 11-12-2024
```

If the list is **empty**:
```
No transactions available.
```

### Removing All Transactions: `remove-all-txs`

Removes all transactions from the system.

**Format:** `remove-all-txs`

### Adding a Transaction: `add-tx`

Adds a new rental transaction to the system.

**Format:** `add-tx /c [CAR_ID] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE: dd-MM-yyyy]`

**Example:**  
`add-tx /c SZZ1579D /u John /d 15 /s 11-05-2025`

**Sample Response:**
```
Transaction added:
[ ] TX2 | SZZ1579D | John | 15day(s)
Start Date: 11-05-2025
____________________________________________________________
```

---
## FAQ

**Q**: Is there any file-saving system in place currently?  
**A**: Unfortunately, no, we are currently working on it. Thank you for your patience.

## Command Summary

| Action                                | Format                                                 |
|---------------------------------------|--------------------------------------------------------|
| `Customer` related commands           |                                                        |
| Add customer                          | `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]` |
| Remove customer                       | `remove-user /u [CUSTOMER_NAME]`                       |
| List all customers                    | `list-users`                                           |
| `Car` related commands                |                                                        |
| Add car                               | `add-car /n [CAR_MODEL] /c [LICENSE_PLATE_NUMBER] /p [PRICE]` |
| Remove car                            | `remove-car`                                           |
| Remove all cars                       | `remove-all-cars`                                      |
| List all cars                         | `list-cars`                                            |
| List rented cars                      | `list-rented`                                          |
| List available cars                   | `list-available`                                       |
| `Transaction` related commands        |                                                        |
| Add transaction                       | `add-tx /c [CAR_ID] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE: dd-MM-yyyy]` |
| Remove transaction                    | `remove-tx /t [TRANSACTION_ID]`                        |
| Remove all transactions               | `remove-all-txs`                                       |
| List all transactions                 | `list-tx`                                              |
| Mark transaction as complete          | `mark-tx /t [TRANSACTION_ID]`                          |
| Unmark transaction                    | `unmark-tx /t [TRANSACTION_ID]`                        |
| List completed transactions           | `list-tx-completed`                                    |
| List uncompleted transactions         | `list-tx-uncompleted`                                  |
| Find transactions by customer         | `find-tx-by-customer /u [CUSTOMER_NAME]`               |
| `Other useful commands`               |                                                        |
| Show help page                        | `help`                                                 |
| Exit program                          | `exit`                                                 |
```