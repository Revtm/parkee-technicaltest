package com.example.parkingpos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckOutRequestDto {
    private String plateNumber;
    private LocalDateTime checkOutTime;
}
