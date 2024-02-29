package com.example.airline_api.services;

import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServices {

    @Autowired
    PassengerRepository passengerRepository;

}
