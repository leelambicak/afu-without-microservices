package com.imaginnovate.afu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.afu.services.GeolocationService;

@RestController
@RequestMapping("/maps")
public class GeolocationController {

    @Autowired
    private GeolocationService geolocationService;

    @GetMapping("/address")
    public String getAddress(@RequestParam double latitude, @RequestParam double longitude) {
        return geolocationService.getAddressByLatLng(latitude, longitude);
    }
}