package com.imaginnovate.afu.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imaginnovate.afu.dto.LocationsDto;
import com.imaginnovate.afu.model.Locations;
import com.imaginnovate.afu.repo.LocationsRepo;

@Service
public class LocationsService {

    @Autowired
    private LocationsRepo locationsRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<LocationsDto> getAllLocations() {
        try {
            List<Locations> locations = locationsRepo.findAll();
            return locations.stream().map(location -> modelMapper.map(location, LocationsDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }

    public Locations createLocations(LocationsDto locations) {
        try {
            Locations locations2 = modelMapper.map(locations, Locations.class);
            return locationsRepo.save(locations2);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }

    }

    public Locations updateLocations(Integer id, Locations locations) {
        Optional<Locations> existedLocations = locationsRepo.findById(id);
        if (existedLocations.isPresent()) {
            Locations saveLocations = existedLocations.get();
            if (locations.getCoordinates() != null) {
                saveLocations.setCoordinates(locations.getCoordinates());
            }
        }
        return locationsRepo.save(locations);
    }

}
