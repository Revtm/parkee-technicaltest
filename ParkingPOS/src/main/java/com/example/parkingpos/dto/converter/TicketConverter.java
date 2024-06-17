package com.example.parkingpos.dto.converter;

import com.example.parkingpos.dto.*;
import com.example.parkingpos.entity.CheckOut;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {
    public CheckOutResponseDto ticketToTicketResponseDto(CheckOut checkOut){
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

    public CheckOutResponseDto ticketToFailedTicketResponseDto(CheckOutRequestDto request){
        return CheckOutResponseDto.builder()
                .status("FAILED")
                .data(CheckOutDataResponseDto.builder()
                        .plateNumber(request.getPlateNumber())
                        .message("Gagal mendapatkan data ticket")
                        .build())
                .build();
    }
}
