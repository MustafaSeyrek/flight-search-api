package com.seyrek.flightsearchapi.controllers;

import com.seyrek.flightsearchapi.entities.Airport;
import com.seyrek.flightsearchapi.services.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        return new ResponseEntity<>(airportService.getAllAirports(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        return new ResponseEntity<>(airportService.getAirportById(id), OK);
    }

    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        return new ResponseEntity<>(airportService.createAirport(airport), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAirportById(@PathVariable Long id, @RequestBody Airport newAirport) {
        Airport airport = airportService.updateAirportById(id, newAirport);

        if (airport != null) {
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirportById(@PathVariable Long id) {
        airportService.deleteAirportById(id);
        return new ResponseEntity<>(OK);
    }
}
