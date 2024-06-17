package com.example.parkingpos.dto.converter;

import com.example.parkingpos.dto.checkin.CheckInDataResponseDto;
import com.example.parkingpos.dto.checkin.CheckInRequestDto;
import com.example.parkingpos.dto.checkin.CheckInResponseDto;
import com.example.parkingpos.entity.CheckIn;
import org.springframework.stereotype.Component;

@Component
public class CheckInConverter {
    public CheckInResponseDto checkInToCheckInResponseDto(CheckIn checkIn){
        return CheckInResponseDto.builder()
                .status(checkIn.getProcessStatus())
                .data(CheckInDataResponseDto.builder()
                        .plateNumber(checkIn.getPlateNumber())
                        .checkInTime(checkIn.getCheckInTime())
                        .message(checkIn.getMessage())
                        .build())
                .build();
    }

    public CheckInResponseDto requestToFailedCheckInResponseDto(CheckInRequestDto request){
        return CheckInResponseDto.builder()
                .status("FAILED")
                .data(CheckInDataResponseDto.builder()
                        .plateNumber(request.getPlateNumber())
                        .message("Terdapat kesalahan sistem")
                        .build())
                .build();
    }
}
