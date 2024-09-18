package com.imaginnovate.afu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imaginnovate.afu.dto.LocationsDto;
import com.imaginnovate.afu.exception.ConflictException;
import com.imaginnovate.afu.model.Services;
import com.imaginnovate.afu.repo.ServicesRepo;

@Service
public class ServicesService {
    @Autowired
    private ServicesRepo servicesRepo;

    public Services createService(Services service) {
        try {
            Optional<Services> services = servicesRepo.findByName(service.getName());
            if (services.isPresent()) {
                throw new ConflictException("This service already existed");
            }
            return servicesRepo.save(service);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    public List<Services> getAllService() {
        try {
            List<Services> services = servicesRepo.findAll();
            return services;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    public List<Services> getAllServiceBylLocations(LocationsDto locations) {
        try {
            List<Services> services = servicesRepo.findAllByLocations(locations);
            return services;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    public Services updateServices(int id, Services services) {
        Optional<Services> findServices = servicesRepo.findById(id);
        try {
            if (findServices.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Service not found");
            } else {
                Services updateServices = findServices.get();
                if (services.getName() != null) {
                    updateServices.setName(services.getName());
                }
            }
            return servicesRepo.save(services);

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    public Optional<Services> deleteService(Integer id) {
        Optional<Services> services = servicesRepo.findById(id);
        if (services.isPresent()) {
            servicesRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No services found");
        }
        return services;
    }

}
