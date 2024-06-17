package com.example.parkingpos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CheckOutRequestDto {
    private String plateNumber;
    private LocalDateTime checkOutTime;
}
