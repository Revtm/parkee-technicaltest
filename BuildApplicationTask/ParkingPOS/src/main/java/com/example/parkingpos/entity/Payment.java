package com.example.parkingpos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    private String plateNumber;
    private String parkingStatus;
    private String processStatus;
    private String message;
}
