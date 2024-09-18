package com.imaginnovate.afu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.afu.dto.RequestsDto;
import com.imaginnovate.afu.enums.Status;
import com.imaginnovate.afu.model.Requests;
import com.imaginnovate.afu.services.RequestsService;

import jakarta.mail.MessagingException;


@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestsService requestsService;

    @GetMapping("/get-all")
    public List<RequestsDto> getAllRequsts() {
        return requestsService.getAllRequests();
    }


    @PostMapping("/create")
    public RequestsDto createRequests(@RequestBody RequestsDto requests) throws MessagingException {
        return requestsService.createRequest(requests);
    }

    @PutMapping("/update/{id}")
    public Requests updateRequests(@PathVariable Integer id, @RequestBody Requests requests) {
        return requestsService.updateRequests(id, requests);
    }

    @GetMapping("/get-all-by-status/{status}")
    public List<RequestsDto> getAllRequestsByStatus(Status staus) {
        return requestsService.getRequestsByStatus(staus);
    }

}
