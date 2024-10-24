# CliRental User Guide

## Introduction

Clirental is a CLI-based application which allows car rental companies 
to track their customers , cars and rental transactions.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `CliRental` from [here](https://github.com/AY2425S1-CS2113-T11-3/tp/releases).

## Features

### Adding a user to the database: `add-user`

Adds a customer to the list of customers tracked by the car rental application

Format: `add-user /u [USERNAME] /a [AGE] /c [CONTACT_NUMBER]`

* `USERNAME` : `STRING`.
* `AGE` : `INT`
* `CONTACT_NUMBER` : `INT`
* `/u` , `/a` , `/c` must be in sequence.

Example of usage: 

`add-user /u John /a 18 /c 95382572`

### List all cars in the database: `list-cars`

Lists all the car which the company owns.

Format: `list-cars`

## FAQ

**Q**: Is there any file saving system in place currently? 

**A**: Unfortunately no , we are currently working on it. Thank you for your patience.

## Command Summary

* Add todo `add-user /u [USERNAME] /a [AGE] /c [CONTACT_NUMBER]`
