package com.Planes.service;

import com.Planes.domain.FlightEntity;
import com.Planes.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flights;

    public FlightService(FlightRepository flights) {
        this.flights = flights;
    }

    //all flights :
    public List<FlightEntity> getAllFlights() {
        return flights.findAll();
    }

    //add
    public boolean saveOrUpdate(FlightEntity flight){
        FlightEntity updateFlight = flights.save(flight);

        return flights.findById(updateFlight.getId()).isPresent();
    }

    //byStartingAirport:
    public List<FlightEntity> getFlightsByCode(@PathVariable("startingAirport") String startingAirport) {
        return flights.findByStartingAirport(startingAirport);
    }

    //directFlights
    public Optional<FlightEntity> getDirectFlights(String startingAirport, String departureAirport) {
        return flights.findByStartingAirportAndDepartureAirport(startingAirport, departureAirport);
    }

    //byDepearture Airport:
    public List<FlightEntity> getFlightsByDeparture(String departureAirport) {
        return flights.findByDepartureAirport(departureAirport);
    }

    // Update an existing flight
    public boolean updateFlight(Long flightId, FlightEntity updatedFlight) {
        Optional<FlightEntity> optionalFlight = flights.findById(flightId);

        if (optionalFlight.isPresent()) {
            FlightEntity existingFlight = optionalFlight.get();

            // Update the flight details
            existingFlight.setStartingAirport(updatedFlight.getStartingAirport());
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
            existingFlight.setDuration(updatedFlight.getDuration());

            flights.save(existingFlight);

            return true; // Successfully updated the flight
        }

        return false; // Flight not found
    }

    @Scheduled(cron = "0 0 1 * * ?") // Trigger at 1:00 AM every day
    public void deleteFlightsWithDurationGreaterThan10Hours() {
        int durationThresholdInMinutes = 10 * 60;

        flights.findAll().stream()
                .filter(flight -> flight.getDuration() > durationThresholdInMinutes)
                .forEach(flights::delete);

        System.out.println("Scheduled task executed at " + LocalTime.now());
    }
}
