package com.Planes.service;

import com.Planes.domain.AirportEntity;
import com.Planes.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AirportService  {

    @Autowired
    AirportRepository airports;

    //all airports:
    public List<AirportEntity> getAllAirports(){

        List<AirportEntity> airportsList = new ArrayList<>();
        airports.findAll().forEach(airport -> airportsList.add(airport));

        return airportsList;
    }

    //airport by code
    public AirportEntity getAirportByCode(String code) {
        return airports.findByCode(code).orElse(null);
    }

    //save or update
    public boolean saveOrUpdate(AirportEntity airport){
        AirportEntity updateAirport = airports.save(airport);

        return airports.findById(updateAirport.getId()).isPresent();
    }

    // Add an airport with a specific country
    public boolean saveOrUpdate(AirportEntity airport, String country) {
        airport.setCountry(country);
        return saveOrUpdate(airport);
    }

    //airport with most passengers
    public AirportEntity getAirportWithMostPassengersForCountry(String country) {
        AirportEntity airportWithMostPassengers = null;
        int maxPassengers = 0;

        List<AirportEntity> airportsE = (List<AirportEntity>) airports.findByCountry(country);

        for (AirportEntity airport : airportsE) {
            if (airport.getPassengers() > maxPassengers) {
                maxPassengers = airport.getPassengers();
                airportWithMostPassengers = airport;
            }
        }

        return airportWithMostPassengers;
    }

    //delete
    public boolean deleteAirport(Long id){
        airports.deleteById(id);

        return airports.findById(id).isPresent();
    }

}
