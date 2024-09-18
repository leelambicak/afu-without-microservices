package com.imaginnovate.afu.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;

@Data
public class ServicesDto {

@Schema(accessMode = AccessMode.READ_ONLY)
    private int id;
    private String name;

}
