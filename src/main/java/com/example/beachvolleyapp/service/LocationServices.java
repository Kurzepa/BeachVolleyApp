package com.example.beachvolleyapp.service;

import com.example.beachvolleyapp.model.Location;
import com.example.beachvolleyapp.model.Team;
import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.TemporalAmount;
import java.util.List;

@Service
public class LocationServices {

    @Autowired
    LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public Location findById(Long locationId) {
        return locationRepository.findById(locationId).get();
    }
}
