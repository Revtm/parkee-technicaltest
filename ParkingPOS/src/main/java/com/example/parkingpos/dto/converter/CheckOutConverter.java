package com.example.parkingpos.dto.converter;

import com.example.parkingpos.dto.checkout.CheckOutDataResponseDto;
import com.example.parkingpos.dto.checkout.CheckOutRequestDto;
import com.example.parkingpos.dto.checkout.CheckOutResponseDto;
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

    public CheckOutResponseDto checkOutToFailedCheckOutResponseDto(CheckOutRequestDto request, String message){
        return CheckOutResponseDto.builder()
                .status("FAILED")
                .data(CheckOutDataResponseDto.builder()
                        .plateNumber(request.getPlateNumber())
                        .message(message)
                        .build())
                .build();
    }
}
