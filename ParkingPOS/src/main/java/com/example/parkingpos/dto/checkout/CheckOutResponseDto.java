package com.example.parkingpos.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutResponseDto {
    private String status;
    private CheckOutDataResponseDto data;
}
