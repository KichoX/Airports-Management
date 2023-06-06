package com.Planes.api;

import com.Planes.domain.FlightEntity;
import com.Planes.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {this.flightService = flightService;}

    //all flights
    @GetMapping("/allFlights")
    public List<FlightEntity> getAllFlights() {
        return flightService.getAllFlights();
    }


    //save or edit flight
    @PostMapping("/save")
    public ResponseEntity<FlightEntity> saveFlight(@RequestBody FlightEntity flight) {
        boolean success = flightService.saveOrUpdate(flight);
        if (success) {
            return new ResponseEntity<>(flight, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //flights from airport code
    @GetMapping("/byCode/{startingAirport}")
    public List<FlightEntity> getFlightsByCode(@PathVariable("startingAirport") String startingAirport) {
        return flightService.getFlightsByCode (startingAirport);
    }


    //direct flight from A to B airport
    @GetMapping("/direct")
    public Optional<FlightEntity> getDirectFlights(
            @RequestParam("startingAirport") String startingAirport,
            @RequestParam("departureAirport") String departureAirport
    ) {
        return flightService.getDirectFlights(startingAirport, departureAirport);
    }

    //flights to B airport
    @GetMapping("/byDeparture/{departureAirport}")
    public List<FlightEntity> getFlightsByDeparture(@PathVariable("departureAirport") String departureAirport) {
        return flightService.getFlightsByDeparture(departureAirport);
    }


    //edit flight
    @PutMapping("/{flightId}")
    public ResponseEntity<String> updateFlight(
            @PathVariable("flightId") Long flightId,
            @RequestBody FlightEntity updatedFlight
    ) {
        boolean success = flightService.updateFlight(flightId, updatedFlight);
        if (success) {
            return ResponseEntity.ok("Flight updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found");
        }
    }
}