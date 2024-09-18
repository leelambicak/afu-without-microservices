package com.imaginnovate.afu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.afu.dto.LocationsDto;
import com.imaginnovate.afu.model.Locations;
import com.imaginnovate.afu.services.LocationsService;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    @Autowired
    private LocationsService locationsService;

    @GetMapping("/get-all")
    public List<LocationsDto> getAllLocations() {
        return locationsService.getAllLocations();
    }

    @PostMapping("/create")
    public Locations createLocations(@RequestBody LocationsDto locations) {
        return locationsService.createLocations(locations);
    }

    @PutMapping("/update/{id}")
    public Locations updateLocations(@PathVariable Integer id, @RequestBody Locations locations) {
        return locationsService.updateLocations(id, locations);
    }

}
