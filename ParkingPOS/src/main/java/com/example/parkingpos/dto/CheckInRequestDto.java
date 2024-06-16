package com.example.parkingpos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckInRequestDto {
    private String plateNumber;
    private LocalDateTime checkInTime;
}
