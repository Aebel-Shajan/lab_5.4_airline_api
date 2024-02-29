package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.FlightDTO;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight> flights = flightService.findAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable long id){
        // Check: Client specified flight exists
        Optional<Flight> flightOptional = flightService.findFlightById(id);
        if (flightOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // can this also be bad request ?
        }
        return new ResponseEntity<>(flightOptional.get(), HttpStatus.OK);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody FlightDTO flightDTO){
        // Check : All client specified passenger ids exist
        for (long passengerId : flightDTO.getPassengerIds()) {
            if (passengerService.findPassengerById(passengerId).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // should this be 404 not found?
            }
        }
        return new ResponseEntity<>(flightService.addNewFlight(flightDTO), HttpStatus.OK);
    }

    // Book passenger on a flight
//    I HATE OOP 😭, I HATE DTOs...// ok i get it now, not that bad i love java
//    MMM i love java
    @PatchMapping(value = "/{flightId}")
    public ResponseEntity<Flight> addPassengerToFlight(
            @PathVariable long flightId,
            @RequestParam long passengerId){

        Optional<Flight> flightOptional = flightService.findFlightById(flightId);
        Optional<Passenger> passengerOptional = passengerService.findPassengerById(passengerId);
        // Check: Both client specified flight and passenger exists
        if (flightOptional.isEmpty() || passengerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Check: Client specified passenger is not already in flight.
        Flight flight = flightOptional.get();
        List<Passenger> passengerList = flight.getPassengers();
        if (passengerList.contains(passengerOptional.get())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        passengerList.add(passengerOptional.get());
        flightService.saveFlight(flight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancelFlight(@PathVariable long id) {
        // Check : All client specified passenger ids exist
        Optional<Flight> flightOptional = flightService.findFlightById(id);
        if (flightOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // can this also be bad request ?
        }
        flightService.removeFlight(flightOptional.get());
        return new ResponseEntity(HttpStatus.OK);
    }

}
