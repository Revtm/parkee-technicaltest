package com.example.parkingpos.dto.checkin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckInResponseDto {
    private String status;
    private CheckInDataResponseDto data;
}
