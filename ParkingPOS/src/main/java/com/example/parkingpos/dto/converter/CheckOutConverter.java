package com.example.parkingpos.dto.converter;

import com.example.parkingpos.dto.*;
import com.example.parkingpos.entity.CheckOut;
import org.springframework.stereotype.Component;

@Component
public class CheckOutConverter {
    public CheckOutResponseDto checkOutToCheckOutResponseDto(CheckOut checkOut){
        return CheckOutResponseDto.builder()
                .status(checkOut.getProcessStatus())
                .data(CheckOutDataResponseDto.builder()
                        .plateNumber(checkOut.getPlateNumber())
                        .checkInTime(checkOut.getCheckInTime())
                        .checkOutTime(checkOut.getCheckOutTime())
                        .parkingStatus(checkOut.getParkingStatus())
                        .totalPrice(checkOut.getTotalPrice())
                        .message(checkOut.getMessage())
                        .build())
                .build();
    }

    public CheckOutResponseDto requestToFailedCheckOutResponseDto(CheckOutRequestDto request){
        return CheckOutResponseDto.builder()
                .status("FAILED")
                .data(CheckOutDataResponseDto.builder()
                        .plateNumber(request.getPlateNumber())
                        .checkOutTime(request.getCheckOutTime())
                        .message("Terdapat kesalahan sistem")
                        .build())
                .build();
    }
}
