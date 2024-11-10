# CliRental User Guide

## Introduction

Clirental is a CLI-based application which allows car rental companies 
to track their customers, cars and rental transactions.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `CliRental` from [here](https://github.com/AY2425S1-CS2113-T11-3/tp/releases).
3. Copy the jar file into a empty folder.
4. Open your terminal and navigate to the folder the jar file is placed in.
5. Run java -jar tp.jar and you can start using right away.

---
## File saving

`Customer , Transaction and Car` data will be saved on their respective files under `data` directory. 

### `IMPORTANT NOTE / DISCLAIMER: `

* The file saving feature does not include the functionality of being able to add/edit data by editing the text files. 
Please add/edit data via the command line using the commands given.

`If user do not follow instructions, following additional measures are placed.`
* Corrupted data will be flagged upon start of program , highlighting the rows of data which are wrong. Please correct 
them or the corrupted lines will be flushed from the data files upon the `first correct command` given by the user.



Filenames : 

* `Car data` : `carData.txt`
* `Customer data` : `customerData.txt`
* `Transaction data` : `transactionData.txt`

Format : 
* `Car data` :  `CAR MODEL | LICENSE PLATE | PRICE | RENTED | EXPENSIVE`
* `Customer data` : `NAME | AGE | PHONE NUMBER`
* `Transaction data` : `LICENSE PLATE | CUSTOMER NAME | RENTAL DURATION(DAYS) | RENTAL START DATE | COMPLETED`

Types :
* `Car data` :  `STRING | STRING | DOUBLE | BOOLEAN | BOOLEAN`
* `Customer data` : `STRING | INT | STRING`
* `Transaction data` : `STRING | STRING | INT | LOCALDATE | BOOLEAN`

Example : 

* `Car data` :  `Toyota Corolla | SGM4932K | 120.0 | false | false`
* `Customer data` : `John | 22 | +6590907638`
* `Transaction data` : `SGM4932K | John | 30 | 17-10-2024 | false`

Others : 

* Any string `not "true"` will be treated as `false` when it is placed in a BOOLEAN section.

---
## Features

### Adding a user to the database: `add-user`

Adds a customer to the list of customers tracked by the car rental application

Format: `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]`

* `CUSTOMER_NAME` : `STRING`.
* `AGE` : `INT`
  * age should be > 17 and <= 100.
* `CONTACT_NUMBER` : `STRING`
  * `+[ANY NUMBER OF DIGITS]`
  * `No space between + and the first digit`
  * `No space between subsequent digits as well`
  * `E.g. +6595382572`
* `/u` , `/a` , `/c` must be in sequence.

Example of usage: 

`add-user /u John /a 18 /c +6595382572`

Sample Response:

```
____________________________________________________________
add-user /u John /a 18 /c +6595382572
Customer added
Username : John
Age : 18
Contact Number :+6595382572
____________________________________________________________
What would you like to do?
____________________________________________________________
```

### Adding a car: `add-car`

Adds a car to the car list.

Format: `add-car /n [CAR_MODEL] /c [CAR_ID] /p [PRICE]`

- `/n`, `/c` and `/p` identifiers **must be** in the correct order.
- `CAR_ID` **must be** unique. 
- `CAR_ID` **must be** in the following format: `SXX####X`, where
- `CAR_ID` **must** start with the letter **S**.
- `X` is any letter from **A to Z**.
- `####` is any number from **1 to 9999**.
- `PRICE` must be a **non-negative, numeric value**.
- Extra character like `$` not required for `PRICE`.


Example: `add-car /n Honda Civic /c SGE1234X /p 10000`

Sample output:

``` 
Car added to list
Car details:
Honda Civic | SGE1234X | $10000.00 | Available
```

### List all cars in the database: `list-cars`

Lists all the car which the company owns.

Format: `list-cars`

Sample Response:

```
list-cars
Here are the current cars in the company:
1) Honda Civic | SGE1234X | $10000.00 | Available | Affordable | Median price: 10000.0
____________________________________________________________
What would you like to do?
____________________________________________________________
```
### Listing all rented out cars: `list-rented`

Lists all the cars that are currently rented out.

Format: `list-rented`

Sample output:

``` 
Here are all the rented out cars:
1) Honda Civic | SGE1234X | $100.00
2) Toyota Camry | SKL4567M | $200.00
3) Nissan Latio | SFT1190A | $300.00
```

If the list is **empty**:

``` 
No cars currently rented out...
```

### Listing all available cars: `list-available`

Lists all the available cars in the company.

Format: `list-available`

Sample output:

``` 
Here are all the available cars:
1) Mitsubishi Attrage | SGP7877N | $1500.00
2) Honda Vezel | SLK9945F | $3400.00
```

If the list is **empty**:

``` 
There are no available cars at the moment...
```

### Updating rental status of car

There is **no need to manually update** the rental status of the car. The rental status
will **automatically be updated** once the transaction record has been:

- **Added**
- **Removed**
- Marked as **completed**
- Marked as **not completed**

### Listing all transactions: `list-tx`

Lists all transactions stored in the transaction list.

Format: `list-tx`

Sample output:

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
No transaction available.
```

### Listing all transactions: `remove-all-txs`

Remove all transactions stored in the transaction list.

Format: `remove-all-txs`

### Adding a Transaction: `add-tx`

Adds a new rental transaction to the system.

**Format:** `add-transaction /c [CAR_ID] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE]`

- **`/c`**: License plate number of the car (format: `SXX####X`).
- **`/u`**: Username of the customer.
- **`/d`**: Rental duration in days.
- **`/s`**: Rental start date (format: `dd-MM-yyyy`).
- **Parameters must be in the specified sequence.**

**Example:** add-tx /c SZZ1579D /u John /d 15 /s 11-05-2025
Sample Response:
```
Transaction added: 
[ ] TX2 | SZZ1579D | John | 15day(s) 
Start Date: 11-05-2025
____________________________________________________________
```


### Listing All Transactions: `list-tx`

Displays all rental transactions in the system.

**Format:** `list-tx`

**Sample Response:**
```
Here are all the transactions: 
1) [ ] TX1 | SZZ1579D | Apple | 1day(s) 
Start Date: 11-12-2025
2) [ ] TX2 | SZZ1579D | John | 15day(s) 
Start Date: 11-05-2025
____________________________________________________________```
```

## FAQ

`No questions to answer for now!!!`

## Command Summary

**`Customer` related commands:**

|         Action         | Format                                                     |
|:----------------------:|------------------------------------------------------------|
|    **Add** customer    | `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]` |
|  **Remove** customer   |                                                            |
| **List all** customers |                                                            |



**`Car` related commands:** 

|         Action          | Format                                                        |
|:-----------------------:|---------------------------------------------------------------|
|       **Add** car       | `add-car /n [CAR_MODEL] /c [LICENSE_PLATE_NUMBER] /p [PRICE]` |
|     **Remove** car      |                                                               |
|   **Remove all** cars   | `remove-all-cars`                                             |
|    **List all** cars    | `list-cars`                                                   |
|  **List rented** cars   | `list-rented`                                                 |
| **List available** cars | `list-available`                                              |

**`Transaction` related commands:**

|                 Action                  | Format                |
|:---------------------------------------:|-----------------------|
|           **Add** transaction           |                       |
|         **Remove** transaction          |                       |
|       **Remove all** transactions       | `remove-all-txs`      |
|        **List all** transactions        | `list-tx`             |
|  **Mark** transactions as **complete**  |                       |
| **Mark** transactions as **incomplete** |                       |
|     **List completed** transactions     | `list-tx-completed`   |
|    **List uncompleted** transactions    | `list-tx-uncompleted` |
|  **Find** transactions **by customer**  |                       | 

**Other useful commands:**

|       Action       | Format |
|:------------------:|--------|
| Show **help** page | `help` |
|  **Exit** program  | `exit` |






