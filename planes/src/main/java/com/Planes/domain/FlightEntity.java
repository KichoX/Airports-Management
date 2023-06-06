package com.Planes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FlightEntity {

    @Id
    private Long id;

    @Column
    private String startingAirport;

    @Column
    private String departureAirport;

    @Column
    private int departureTime;

    @Column
    private int duration;


    public FlightEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartingAirport() {
        return startingAirport;
    }

    public void setStartingAirport(String startingAirport) {
        this.startingAirport = startingAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
