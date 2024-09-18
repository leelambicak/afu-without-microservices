package com.imaginnovate.afu.dto;

import java.util.Date;

import org.springframework.data.geo.Point;

import com.imaginnovate.afu.enums.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;

@Data
public class RequestsDto {

    @Schema(accessMode = AccessMode.READ_ONLY)
    private Integer id;
    private Integer userId;
    private Integer unregisteredUsersId;
    private Integer serviceId;
    private Date bookingDate;
    private String description;
    private Point location;
    private Status status;

}
