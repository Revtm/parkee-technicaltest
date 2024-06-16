package com.example.parkingpos.controller;

import com.example.parkingpos.dto.TicketDataResponseDto;
import com.example.parkingpos.dto.TicketRequestDto;
import com.example.parkingpos.dto.TicketResponseDto;
import com.example.parkingpos.entity.Ticket;
import com.example.parkingpos.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/pos")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket")
    public ResponseEntity<TicketResponseDto> getTicket(@RequestBody TicketRequestDto request){
        try {
            Ticket ticket = ticketService.getTicketDetail(request.getPlateNumber(), request.getCheckOutTime());
            TicketResponseDto response = TicketResponseDto.builder()
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

            HttpStatus httpStatus = mappingHttpStatus(ticket.getProcessStatus());
            return new ResponseEntity<>(response, httpStatus);
        }catch (Exception e){
            log.error("Error", e);
            TicketResponseDto response = TicketResponseDto.builder()
                    .status("FAILED")
                    .data(TicketDataResponseDto.builder()
                            .message("Gagal mendapatkan data ticket")
                            .build())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private HttpStatus mappingHttpStatus(String status){
        HttpStatus httpStatus;
        if("CONFLICT".equals(status)){
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }else if("SUCCESS".equals(status)){
            httpStatus = HttpStatus.CREATED;
        }else{
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return httpStatus;
    }
}
