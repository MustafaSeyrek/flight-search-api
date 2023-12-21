package com.seyrek.flightsearchapi.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FlightRequest {
    Long departureAirportId;
    Long arrivalAirportId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:ss")
    Date departureDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:ss")
    Date returnDate;
    float price;
}
