package com.seyrek.flightsearchapi.repositories;

import com.seyrek.flightsearchapi.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
