package com.example.parkingpos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckIn {
    private String plateNumber;
    private LocalDateTime checkInTime;
    private String processStatus;
    private String message;
}
