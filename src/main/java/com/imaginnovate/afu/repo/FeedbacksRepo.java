package com.imaginnovate.afu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.afu.model.Feedbacks;

public interface FeedbacksRepo extends JpaRepository<Feedbacks, Integer> {

}
