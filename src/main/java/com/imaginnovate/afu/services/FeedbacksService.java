package com.imaginnovate.afu.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imaginnovate.afu.dto.FeedbacksDto;
import com.imaginnovate.afu.exception.NotFoundException;
import com.imaginnovate.afu.model.Feedbacks;
import com.imaginnovate.afu.repo.FeedbacksRepo;

@Service
public class FeedbacksService {

    @Autowired
    private FeedbacksRepo feedbacksRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Feedbacks createFeedback(FeedbacksDto feedbacksDto) {
        try {
            if (feedbacksDto.getId() != null && feedbacksRepo.existsById(feedbacksDto.getId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Feedback is already exists.");
            }
            feedbacksDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            Feedbacks feedbacks2 = modelMapper.map(feedbacksDto, Feedbacks.class);
            return feedbacksRepo.save(feedbacks2);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }

    public FeedbacksDto getFeedbackById(Integer id) {
        try {
            Optional<Feedbacks> feedbacks = feedbacksRepo.findById(id);
            if (feedbacks.isEmpty()) {
                throw new NotFoundException("Feedback not found");
            }
            FeedbacksDto feedbacks2 = modelMapper.map(feedbacks, FeedbacksDto.class);
            return feedbacks2;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }

    public Feedbacks updateFeedback(Integer id, Feedbacks updatedFeedback) {
        try {
            Feedbacks existingFeedback = feedbacksRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found."));

            if (updatedFeedback.getDescription() != null) {
                existingFeedback.setDescription(updatedFeedback.getDescription());
            }
            if (updatedFeedback.getRating() != 0) {
                existingFeedback.setRating(updatedFeedback.getRating());
            }
            if (updatedFeedback.getRequestId() != null) {
                existingFeedback.setRequestId(updatedFeedback.getRequestId());
            }
            if (updatedFeedback.getUserId() != null) {
                existingFeedback.setUserId(updatedFeedback.getUserId());
            }

            return feedbacksRepo.save(existingFeedback);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }

    public Feedbacks deleteFeedback(Integer id) {
        try {
            Feedbacks existingFeedbacks = feedbacksRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback  not found."));
            feedbacksRepo.deleteById(id);
            return existingFeedbacks;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }

    public List<FeedbacksDto> getAllFeedbacks() {
        try {
            List<Feedbacks> feedbacks = feedbacksRepo.findAll();
            return feedbacks.stream().map(feedback -> modelMapper.map(feedback, FeedbacksDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }
}
