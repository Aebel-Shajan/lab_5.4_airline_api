package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> findFlightById(long id) {
        return flightRepository.findById(id);
    }
}
