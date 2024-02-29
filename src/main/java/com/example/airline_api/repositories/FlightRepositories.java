package com.example.airline_api.repositories;

import com.example.airline_api.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepositories extends JpaRepository<Flight, Long> {
}
