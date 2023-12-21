package com.seyrek.flightsearchapi.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FlightResponse {
    Long id;
    String departureAirport;
    String arrivalAirport;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:ss")
    Date departureDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:ss")
    Date returnDate;
    float price;
    String type;


}
