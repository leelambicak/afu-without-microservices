package com.imaginnovate.afu.dto;

import org.springframework.data.geo.Point;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;

@Data
public class UnregisteredUserDto {

  @Schema(accessMode = AccessMode.READ_ONLY)
    private Integer id;
    private String name;
    private Character gender;
    private String email;
    private Long phoneNo;
    private Point address;

}
