package com.example.parkingpos.dto.converter;

import com.example.parkingpos.dto.checkin.CheckInDataResponseDto;
import com.example.parkingpos.dto.checkin.CheckInRequestDto;
import com.example.parkingpos.dto.checkin.CheckInResponseDto;
import com.example.parkingpos.entity.CheckIn;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class CheckInConverter {
    public CheckInResponseDto checkInToCheckInResponseDto(CheckIn checkIn){
        return CheckInResponseDto.builder()
                .status(checkIn.getProcessStatus())
                .data(CheckInDataResponseDto.builder()
                        .plateNumber(checkIn.getPlateNumber())
                        .checkInTime(checkIn.getCheckInTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")))
                        .message(checkIn.getMessage())
                        .build())
                .build();
    }

    public CheckInResponseDto requestToFailedCheckInResponseDto(CheckInRequestDto request, String message){
        return CheckInResponseDto.builder()
                .status("FAILED")
                .data(CheckInDataResponseDto.builder()
                        .plateNumber(request.getPlateNumber())
                        .message(message)
                        .build())
                .build();
    }
}
