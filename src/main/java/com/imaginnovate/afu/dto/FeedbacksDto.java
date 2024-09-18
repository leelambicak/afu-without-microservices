package com.imaginnovate.afu.dto;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;

@Data
public class FeedbacksDto {

  @Schema(accessMode = AccessMode.READ_ONLY)
    private Integer id;
    private Integer requestId;
    private Integer userId;
    private String description;
    private Integer rating;
    private Timestamp createdAt;

}
