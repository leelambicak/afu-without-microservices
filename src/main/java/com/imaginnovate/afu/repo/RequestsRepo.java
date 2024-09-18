package com.imaginnovate.afu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imaginnovate.afu.enums.Status;
import com.imaginnovate.afu.model.Requests;

public interface RequestsRepo extends JpaRepository<Requests, Integer> {

    List<Requests> findAllByStatus(Status status);

    @Query("SELECT r FROM Requests r LEFT JOIN FETCH r.userId LEFT JOIN FETCH r.unregisteredUserId LEFT JOIN FETCH r.serviceId WHERE r.id = :id")
    Requests getRequestWithDetails(@Param("id") int id);

    @Query("SELECT r.location, COUNT(r.id) FROM Requests r GROUP BY r.location")
    List<Object[]> countRequestsByLocation();

    @Query("SELECT r.userId, COUNT(r.id) FROM Requests r GROUP BY r.userId")
    List<Object[]> countRequestsByUser();

    @Query("SELECT r.serviceId, COUNT(r.id) FROM Requests r GROUP BY r.serviceId")
    List<Object[]> countRequestsByService();

}
