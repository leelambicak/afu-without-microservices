package com.imaginnovate.afu.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.afu.dto.LocationsDto;
import com.imaginnovate.afu.model.Services;
import com.imaginnovate.afu.model.Users;

public interface ServicesRepo extends JpaRepository<Services, Integer> {

    Optional<Services> findByName(String name);

    List<Services> findAllByLocations(LocationsDto locations);

    Optional<Users> findById(Services serviceId);

}
