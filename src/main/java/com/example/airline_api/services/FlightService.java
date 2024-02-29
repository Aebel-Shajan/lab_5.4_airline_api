package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.FlightDTO;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> findFlightById(long id) {
        return flightRepository.findById(id);
    }

    public Flight addNewFlight(FlightDTO flightDTO) {

        Flight flight = new Flight(
                flightDTO.getDestination(),
                flightDTO.getCapacity(),
                flightDTO.getDepartureDate(),
                flightDTO.getDepartureTime()
        );
        for(Long passengerId : flightDTO.getPassengerIds()) {
            Passenger passenger = passengerRepository.findById(passengerId).get();
            flight.addPassenger(passenger);
        }
        return flightRepository.save(flight);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void removeFlight(Flight flight) {
        flightRepository.delete(flight);
    }
}
