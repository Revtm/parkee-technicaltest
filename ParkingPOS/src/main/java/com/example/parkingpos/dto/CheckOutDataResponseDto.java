package com.example.parkingpos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutDataResponseDto {
    private String plateNumber;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private BigInteger totalPrice;
    private String parkingStatus;
    private String message;
}
