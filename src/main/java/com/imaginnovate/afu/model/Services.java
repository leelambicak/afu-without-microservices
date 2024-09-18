package com.imaginnovate.afu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "service_location", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
    private List<Locations> locations;

    @JsonIgnore
    @OneToMany(mappedBy = "serviceId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Requests> requests;
}
