package com.seyrek.flightsearchapi.repositories;

import com.seyrek.flightsearchapi.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
