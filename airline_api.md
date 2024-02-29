# Lab - Airline API

In this exercise we will be creating an airline booking system. You have been tasked with creating an API that allows for the management of both passenger and flight details. A real airline would have **much** more security in place around its API but for now we'll assume that the user is logged in and has permission to be interacting with ours. 

## MVP
Design and build an API with CRUD functionality. There will be a many-to-many relationship between a `passengers` table and a `flights` table. The `passengers` table will have columns for:

- `id`
- `name`
- either `phoneNumber` or `emailAddress`

The `flights` table will have columns for:

- `id`
- `destination`
- `capacity`
- `departureDate`
- `departureTime`

Your API must include routes enabling the user to:

- Add details of a new flight
- Display all available flights
- Display details of a specific flight
- Add a new passenger
- Display details of all passengers
- Display details of a specific passenger
- Book a passenger on to a flight
- Cancel a flight

A `DataLoader` class would be helpful to seed the database with some flights and passengers.

Ensure any operations affecting multiple tables are atomic by adding the `@Transactional` annotation where appropriate.

## Extensions
- Add functionality to filter flights by destination
- Prevent a passenger being booked on a flight which is full
- If you aren't already doing so, refactor the `departureDate` and `departureTime` properties to use one of the available datatypes for handling dates and times.
- Return `ResponseEntity` objects with appropriate status codes if the user attempts an invalid action, eg. return a `400` error if filtering by a destination which doesn't exist