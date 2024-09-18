package com.imaginnovate.afu.model;

import java.util.List;

import org.springframework.data.geo.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "unregistered_users")
public class UnregisteredUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 80, nullable = false)
    private String name;

    @Column(name = "gender", length = 1, nullable = false)
    private Character gender;

    @Column(name = "email", length = 80, nullable = false)
    private String email;

    @Column(name = "phone_no", length = 15, nullable = false)
    private Long phoneNo;

    @Column(name = "address", nullable = false)
    private Point address;

    @JsonIgnore
    @OneToMany(mappedBy = "unregisteredUserId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Requests> requests;

}
