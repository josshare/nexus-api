package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;

@Service
public class FlightService {

    public List<String> getFlightOrigins() {
        // TODO: Implement logic to fetch available flight origins from a data source
        // For now, return a hardcoded list of origins
        return Arrays.asList("New York", "Los Angeles", "Chicago", "Houston", "Miami");
    }
    public List<String> getFlightDestinations() {
        // TODO: Implement logic to fetch available flight origins from a data source
        // For now, return a hardcoded list of origins
        return Arrays.asList("New York", "Los Angeles", "Chicago", "Houston", "Miami");
        }
}