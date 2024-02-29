package com.example.airline_api.services;

import com.example.airline_api.repositories.FlightRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServices {

    @Autowired
    FlightRepositories flightRepositories;
}
