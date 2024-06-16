package com.example.parkingpos.dto;

public class CheckOutResponseDto extends BasicResponseDto<CheckOutDataResponse>{
    public CheckOutResponseDto(String status, CheckOutDataResponse data) {
        super(status, data);
    }
}
