package com.Planes.repository;

import com.Planes.domain.AirportEntity;
import com.Planes.domain.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
    List<FlightEntity> findByStartingAirport(String startingAirport);
    List<FlightEntity> findByDepartureAirport(String departureAirport);
    Optional<FlightEntity> findByStartingAirportAndDepartureAirport(String startingAirport, String departureAirport);

}
