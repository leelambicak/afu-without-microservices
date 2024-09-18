package com.imaginnovate.afu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.afu.dto.FeedbacksDto;
import com.imaginnovate.afu.model.Feedbacks;
import com.imaginnovate.afu.services.FeedbacksService;

@RestController
@RequestMapping("/feedbacks")
public class FeedBackController {

    @Autowired
    private FeedbacksService feedbacksService;

    @GetMapping("/get-all")
    public List<FeedbacksDto> getAllFeedbacks() {
        return feedbacksService.getAllFeedbacks();
    }

    @PostMapping("/create")
    public Feedbacks createFeedback(@RequestBody FeedbacksDto feedbacks) {
        return feedbacksService.createFeedback(feedbacks);
    }

    @PutMapping("/update/{id}")
    public Feedbacks updateFeedbacks(@PathVariable Integer id, @RequestBody Feedbacks feedbacks) {
        return feedbacksService.updateFeedback(id, feedbacks);
    }

    @GetMapping("/get/{id}")
    public FeedbacksDto getFeedbacksById(@RequestParam Integer id) {
        return feedbacksService.getFeedbackById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Feedbacks deleteFeedbacks(@PathVariable Integer id) {
        return feedbacksService.deleteFeedback(id);
    }
}
