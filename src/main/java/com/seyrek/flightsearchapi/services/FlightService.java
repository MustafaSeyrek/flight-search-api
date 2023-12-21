package com.seyrek.flightsearchapi.services;

import com.seyrek.flightsearchapi.entities.Airport;
import com.seyrek.flightsearchapi.entities.Flight;
import com.seyrek.flightsearchapi.repositories.FlightRepository;
import com.seyrek.flightsearchapi.requests.FlightRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;

    public Stream<Flight> getAllFlights() {
        return flightRepository.findAll().stream();
    }

    public Flight getFlightById(Long id) {
        if (id == null) return null;
        return flightRepository.findById(id).orElse(null);
    }

    public Flight createFlight(FlightRequest newFlight) {
        Airport depA = airportService.getAirportById(newFlight.getDepartureAirportId());
        Airport arrA = airportService.getAirportById(newFlight.getArrivalAirportId());
        if (depA == null) {
            return null;
        }

        Flight toSave = new Flight();
        toSave.setDepartureAirport(depA);
        toSave.setPrice(newFlight.getPrice());
        toSave.setDepartureDate(newFlight.getDepartureDate());
        toSave.setReturnDate(newFlight.getReturnDate());
        toSave.setArrivalAirport(arrA);
        return flightRepository.save(toSave);
    }

    public Flight updateFlightById(Long id, FlightRequest newFlight) {
        Optional<Flight> flight = flightRepository.findById(id);

        if (flight.isPresent()) {
            Airport depA = airportService.getAirportById(newFlight.getDepartureAirportId());
            Airport arrA = airportService.getAirportById(newFlight.getArrivalAirportId());

            Flight toUpdate = flight.get();
            toUpdate.setPrice(newFlight.getPrice());
            toUpdate.setDepartureDate(newFlight.getDepartureDate());
            toUpdate.setReturnDate(newFlight.getReturnDate());
            toUpdate.setArrivalAirport(arrA);
            toUpdate.setDepartureAirport(depA);

            flightRepository.save(toUpdate);
            return toUpdate;
        } else {
            return null;
        }
    }

    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);
    }

}
