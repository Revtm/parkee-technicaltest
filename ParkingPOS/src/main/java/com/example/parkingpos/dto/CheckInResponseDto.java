package com.example.parkingpos.dto;

public class CheckInResponseDto extends BasicResponseDto<CheckInDataResponseDto>{
    public CheckInResponseDto(String status, CheckInDataResponseDto data) {
        super(status, data);
    }
}
