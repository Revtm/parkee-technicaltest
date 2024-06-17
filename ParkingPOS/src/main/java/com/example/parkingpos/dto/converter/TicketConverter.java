package com.example.parkingpos.dto.converter;

import com.example.parkingpos.dto.*;
import com.example.parkingpos.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {
    public TicketResponseDto ticketToTicketResponseDto(Ticket ticket){
        return TicketResponseDto.builder()
                .status(ticket.getProcessStatus())
                .data(TicketDataResponseDto.builder()
                        .plateNumber(ticket.getPlateNumber())
                        .checkInTime(ticket.getCheckInTime())
                        .checkOutTime(ticket.getCheckOutTime())
                        .parkingStatus(ticket.getParkingStatus())
                        .totalPrice(ticket.getTotalPrice())
                        .message(ticket.getMessage())
                        .build())
                .build();
    }

    public TicketResponseDto ticketToFailedTicketResponseDto(TicketRequestDto request){
        return TicketResponseDto.builder()
                .status("FAILED")
                .data(TicketDataResponseDto.builder()
                        .plateNumber(request.getPlateNumber())
                        .message("Gagal mendapatkan data ticket")
                        .build())
                .build();
    }
}
