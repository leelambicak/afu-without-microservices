package com.imaginnovate.afu.model;

import java.util.List;

import org.springframework.data.geo.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "locations")
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "coordinates", nullable = false)
    private Point coordinates;

    @JsonIgnore
    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    private List<Services> services;

}
