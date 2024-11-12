package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.demo.service.FlightService;

@CrossOrigin
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/origins")
    public List<String> getFlightOrigins() {
        return flightService.getFlightOrigins();
    }

    @GetMapping("/destinations")
    public List<String> getFlightDestinations() {
        return flightService.getFlightOrigins();
    }
}