package com.imaginnovate.afu.dto;

import org.springframework.data.geo.Point;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;

@Data
public class LocationsDto {

   @Schema(accessMode = AccessMode.READ_ONLY)
    private int id;
    private Point coordinates;

}
