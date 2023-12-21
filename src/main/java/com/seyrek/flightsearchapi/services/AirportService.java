package com.seyrek.flightsearchapi.services;

import com.seyrek.flightsearchapi.entities.Airport;
import com.seyrek.flightsearchapi.repositories.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport getAirportById(Long id) {
        if (id == null) return null;
        return airportRepository.findById(id).orElse(null);
    }

    public Airport updateAirportById(Long id, Airport newAirport) {
        Optional<Airport> airport = airportRepository.findById(id);

        if (airport.isPresent()) {
            Airport found = airport.get();
            found.setCity(newAirport.getCity());
            airportRepository.save(found);
            return found;
        } else {
            return null;
        }
    }

    public void deleteAirportById(Long id) {
        airportRepository.deleteById(id);
    }
}
