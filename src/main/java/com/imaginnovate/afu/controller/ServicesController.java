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
import com.imaginnovate.afu.model.Services;
import com.imaginnovate.afu.services.ServicesService;

@RestController
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/get-all")
    public List<Services> getAllServices() {
        return servicesService.getAllService();
    }

    @GetMapping("/get-all-by-locations")
    public List<Services> getAllServicesByLocations(LocationsDto locations) {
        return servicesService.getAllServiceBylLocations(locations);
    }

    @PostMapping("/create")
    public Services createServices(@RequestBody Services services) {
        return servicesService.createService(services);
    }

    @PutMapping("/update/{id}")
    public Services updateServices(@PathVariable Integer id, @RequestBody Services services) {
        return servicesService.updateServices(id, services);
    }

}
