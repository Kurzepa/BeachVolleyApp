package com.example.beachvolleyapp.api;

import com.example.beachvolleyapp.model.Location;
import com.example.beachvolleyapp.service.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationRestController {

    LocationServices locationServices;

    @Autowired
    public LocationRestController(LocationServices locationServices) {
        this.locationServices = locationServices;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Location> getLocations(){
        return locationServices.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id){
        return locationServices.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
