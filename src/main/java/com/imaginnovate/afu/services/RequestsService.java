package com.imaginnovate.afu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imaginnovate.afu.dto.RequestsDto;
import com.imaginnovate.afu.enums.Status;
import com.imaginnovate.afu.model.Requests;
import com.imaginnovate.afu.model.Services;
import com.imaginnovate.afu.model.UnregisteredUsers;
import com.imaginnovate.afu.model.Users;
import com.imaginnovate.afu.repo.RequestsRepo;
import com.imaginnovate.afu.repo.ServicesRepo;
import com.imaginnovate.afu.repo.UnregisteredUsersRepo;
import com.imaginnovate.afu.repo.UsersRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class RequestsService {

    @Autowired
    private RequestsRepo requestsRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RequestEmailBody requestEmailBody;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private UnregisteredUsersRepo unregisteredUsersRepo;

    @Autowired
    private ServicesRepo servicesRepo;

    @Value("${spring.mail.username}")
    private String fromEmailId;

    public RequestsDto createRequest(RequestsDto requestDto) throws MessagingException {
        Requests request = modelMapper.map(requestDto, Requests.class);
        UnregisteredUsers unregisteredUser1 = unregisteredUsersRepo.findById(requestDto.getUnregisteredUsersId())
                .orElse(null);
        if (unregisteredUser1 != null) {
            request.setUnregisteredUserId(unregisteredUser1);
        }
        Requests existedRequest = requestsRepo.save(request);

        Users user = usersRepo.findById(requestDto.getUserId()).orElse(null);
        UnregisteredUsers unregisteredUser = unregisteredUsersRepo.findById(requestDto.getUnregisteredUsersId())
                .orElse(null);
        Services services = servicesRepo.findById(requestDto.getServiceId()).orElse(null);
        String emailBody = requestEmailBody.generateEmailBody(request, user, unregisteredUser, services);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromEmailId);
        List<String> recipients = new ArrayList<>();
        if (user != null && user.getEmail() != null && !user.getEmail().isEmpty()) {
            recipients.add(user.getEmail());
        }
        if (unregisteredUser != null && unregisteredUser.getEmail() != null
                && !unregisteredUser.getEmail().isEmpty()) {
            recipients.add(unregisteredUser.getEmail());
        }

        if (!recipients.isEmpty()) {
            helper.setTo(recipients.toArray(String[]::new));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No valid recipient email addresses found.");
        }

        helper.setSubject("Request Booking Status");
        helper.setText(emailBody, true);

        // Send email
        javaMailSender.send(mimeMessage);
        return modelMapper.map(existedRequest, RequestsDto.class);
    }

    public List<RequestsDto> getAllRequests() {
        try {
            List<Requests> requests = requestsRepo.findAll();
            return requests.stream()
                    .map(request -> modelMapper.map(request, RequestsDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }

 

    public Requests updateRequests(Integer id, Requests requests) {
        Optional<Requests> existedRequests = requestsRepo.findById(id);
        try {
            if (existedRequests.isPresent()) {
                Requests saveRequests = existedRequests.get();

                if (requests.getUserId() != null) {
                    saveRequests.setUserId(requests.getUserId());
                }
                if (requests.getServiceId() != null) {
                    saveRequests.setServiceId(requests.getServiceId());
                }
                if (requests.getUnregisteredUserId() != null) {
                    saveRequests.setUnregisteredUserId(requests.getUnregisteredUserId());
                }
                if (requests.getBookingDate() != null) {
                    saveRequests.setBookingDate(requests.getBookingDate());
                }
                if (requests.getDescription() != null) {
                    saveRequests.setDescription(requests.getDescription());
                }
                if (requests.getLocation() != null) {
                    saveRequests.setLocation(requests.getLocation());
                }
                if (requests.getStatus() != null) {
                    saveRequests.setStatus(requests.getStatus());
                }
                return requestsRepo.save(requests);
            }
            return requests;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    public List<RequestsDto> getRequestsByStatus(Status status) {
        List<Requests> requests = requestsRepo.findAllByStatus(status);
        return requests.stream()
                .map(request -> modelMapper.map(request, RequestsDto.class))
                .collect(Collectors.toList());
    }

}
