package com.imaginnovate.afu.repo;

import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.afu.model.Locations;

public interface LocationsRepo extends JpaRepository<Locations, Integer> {

    Locations findByCoordinates(Point point);

}
