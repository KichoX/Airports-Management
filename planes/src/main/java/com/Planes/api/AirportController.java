package com.Planes.api;

import org.springframework.ui.Model;
import com.Planes.domain.AirportEntity;
import com.Planes.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }


    @GetMapping("/allAirports")
    public List<AirportEntity> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{code}")
    public ResponseEntity<AirportEntity> getAirportByCode(@PathVariable("code") String code) {
        AirportEntity airport = airportService.getAirportByCode(code);
        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<AirportEntity> saveAirport(@RequestBody AirportEntity airport) {
        boolean success = airportService.saveOrUpdate(airport);
        if (success) {
            return new ResponseEntity<>(airport, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{country}")
    public ResponseEntity<AirportEntity> saveAirportWithCountry(
            @RequestBody AirportEntity airport,
            @PathVariable("country") String country
    ) {
        boolean success = airportService.saveOrUpdate(airport, country);
        if (success) {
            return new ResponseEntity<>(airport, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/most-passengers/{country}")
    public ResponseEntity<AirportEntity> getAirportWithMostPassengersForCountry(
            @PathVariable("country") String country
    ) {
        AirportEntity airport = airportService.getAirportWithMostPassengersForCountry(country);
        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable("id") Long id) {
        boolean success = airportService.deleteAirport(id);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}