package com.example.parkingpos.dto.checkout;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckOutDataResponseDto {
    private String plateNumber;
    private String checkInTime;
    private String checkOutTime;
    private BigInteger totalPrice;
    private String parkingStatus;
    private String message;
}
