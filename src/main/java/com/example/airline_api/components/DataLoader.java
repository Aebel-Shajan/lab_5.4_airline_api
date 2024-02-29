package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.repositories.FlightRepositories;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightRepositories flightRepositories;

    @Autowired
    PassengerRepository passengerRepository;

    public DataLoader() {

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flight flight1 = new Flight("Peru", 300, "01/01/2001", "00:00");
        flightRepositories.save(flight1);

        Flight flight2 = new Flight("Poland", 200, "01/02/2002", "00:00");
        flightRepositories.save(flight2);


    }
}
