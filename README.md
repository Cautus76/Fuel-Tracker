# Fuel Tracker

A simple Android application for recording refuelling data, monitoring fuel consumption, and tracking vehicle operating costs.

Fuel Tracker is free and open-source software.

## Features

* Record refuelling date
* Record odometer reading
* Record fuel quantity
* Record fuel price
* Calculate fuel consumption in l/100 km
* Calculate average fuel consumption
* Calculate total fuel expenditure
* Calculate average fuel price per litre
* Calculate average cost per kilometre
* Display fuel-consumption history in a chart
* Filter refuelling records
* Edit and delete saved records
* Support for multiple currencies
* Localized interface

## Supported languages

* English
* Czech
* German
* Spanish

## Calculation method

Fuel consumption is calculated between consecutive refuelling records.

The first refuelling record establishes the initial odometer and fuel state. Because no previous distance exists, the first record is excluded from:

* fuel-consumption calculations;
* cost-per-kilometre calculations.

The first record remains included in:

* total fuel expenditure;
* total fuel quantity;
* average fuel price per litre.

## Current development status

Fuel Tracker is under active development.

Recent changes include:

* corrected cost-per-kilometre calculation;
* corrected date handling to prevent one-day time-zone shifts;
* removed misleading example values from input fields;
* improved dynamic scaling of the fuel-consumption chart;
* added chart padding to prevent points and curves from being clipped;
* added and updated calculation tests.

## Known issues

The following changes are still planned:

* allow an initial odometer reading of exactly `0 km` for a new vehicle;
* improve deletion protection for all application data by requiring two confirmations and typing `DELETE`.

## Privacy

Fuel Tracker is designed to store refuelling information locally on the user’s device.

The application does not require an account and is not intended to collect or transmit personal data.

## Development

The application was created with the assistance of Google AI Studio and reviewed by the author.

Fuel Tracker is an independent project and is not an official Google product.

## Licence

This project is licensed under the GNU General Public License v3.0 or later.

See the [LICENSE](LICENSE) file for details.

## Author

Copyright © 2026 Tomáš Pokorný
