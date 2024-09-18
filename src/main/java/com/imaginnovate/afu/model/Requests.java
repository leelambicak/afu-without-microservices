package com.imaginnovate.afu.model;

import java.util.Date;

import org.springframework.data.geo.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imaginnovate.afu.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "requests")
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unregistered_user_id")
    private UnregisteredUsers unregisteredUserId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", nullable = false)
    private Services serviceId;

    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", nullable = false)
    private Point location;

    @Column(name = "status", nullable = false)
    private Status status;

    @JsonIgnore
    @OneToOne(mappedBy = "requestId", fetch = FetchType.LAZY)
    private Feedbacks feedbacks;

}
