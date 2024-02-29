package com.example.airline_api.services;

import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    public List<Passenger> findAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> findPassengerById(long id) {
        return passengerRepository.findById(id);
    }

    public boolean passengerExists(long id) {
        return passengerRepository.existsById(id);
    }

}
