package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.FlightDTO;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.models.PassengerDTO;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public List<Passenger> findAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> findPassengerById(long id) {
        return passengerRepository.findById(id);
    }

    public Passenger addNewPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger(
                passengerDTO.getName(),
                passengerDTO.getEmail()
        );
        Passenger passengerOut = passengerRepository.save(passenger);
        for(Long flightId : passengerDTO.getFlightIds()) {
            Flight flight = flightRepository.findById(flightId).get();
            flight.addPassenger(passenger);
            flightRepository.save(flight);
        }
        return passengerOut;
    }
}
