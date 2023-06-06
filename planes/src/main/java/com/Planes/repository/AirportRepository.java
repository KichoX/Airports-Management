package com.Planes.repository;

import com.Planes.domain.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Long> {
    Optional<AirportEntity> findByCode(String code);
    List<AirportEntity> findByCountry(String country);
}
