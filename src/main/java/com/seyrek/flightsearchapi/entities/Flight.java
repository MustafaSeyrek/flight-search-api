package com.seyrek.flightsearchapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "departureAirportId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrivalAirportId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Airport arrivalAirport;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:ss")
    Date departureDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:ss")
    Date returnDate;

    float price;
}
