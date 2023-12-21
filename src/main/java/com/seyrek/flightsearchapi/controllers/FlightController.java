package com.seyrek.flightsearchapi.controllers;


import com.seyrek.flightsearchapi.entities.Flight;
import com.seyrek.flightsearchapi.requests.FlightRequest;
import com.seyrek.flightsearchapi.responses.FlightResponse;
import com.seyrek.flightsearchapi.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<List<FlightResponse>>> getAllFlights() {
        List<List<FlightResponse>> flights = flightService.getAllFlights().map(db -> {
            return getFlight(db);
        }).collect(Collectors.toList());
        return ResponseEntity.status(OK).body(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<FlightResponse>> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        if (flight != null) {
            return new ResponseEntity<>(getFlight(flight), OK);
        } else {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody FlightRequest flightRequest) {
        return new ResponseEntity<>(flightService.createFlight(flightRequest), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFlightById(@PathVariable Long id, @RequestBody FlightRequest flightRequest) {
        Flight flight = flightService.updateFlightById(id, flightRequest);

        if (flight != null) {
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlightById(@PathVariable Long id) {
        flightService.deleteFlightById(id);
        return new ResponseEntity<>(OK);
    }

    private List<FlightResponse> getFlight(Flight f) {
        List<FlightResponse> list = new ArrayList<>();
        if (f.getReturnDate() != null) {
            list.add(new FlightResponse(f.getId(), f.getDepartureAirport().getCity(), f.getArrivalAirport().getCity(), f.getDepartureDate(),
                    f.getReturnDate(), f.getPrice(), "Two Way Flight(Departure)"));
            list.add((new FlightResponse(f.getId(), f.getArrivalAirport().getCity(), f.getDepartureAirport().getCity(), f.getReturnDate(), null, f.getPrice(),
                    "Two Way Flight(Return)")));
        } else {
            list.add(new FlightResponse(f.getId(), f.getDepartureAirport().getCity(), f.getArrivalAirport().getCity(), f.getDepartureDate(),
                    null, f.getPrice(), "One Way Flight(Departure)"));
        }
        return list;
    }
}
