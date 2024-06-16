package com.example.parkingpos.controller;

import com.example.parkingpos.dto.CheckInDataResponseDto;
import com.example.parkingpos.dto.CheckInRequestDto;
import com.example.parkingpos.dto.CheckInResponseDto;
import com.example.parkingpos.entity.Ticket;
import com.example.parkingpos.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class CheckInController {
    private final CheckInService checkInService;

    @Autowired
    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping("checkin")
    public ResponseEntity<CheckInResponseDto> createCheckInTicket(@RequestBody CheckInRequestDto request){
        try{
            Ticket ticket = checkInService.createCheckInTicket(request.getPlateNumber());

            CheckInResponseDto response;

            if(ticket.getStatus().equals("CONFLICT")){
                response = CheckInResponseDto.builder()
                        .status("CONFLICT")
                        .data(CheckInDataResponseDto.builder()
                                .plateNumber(ticket.getPlateNumber())
                                .checkInTime(ticket.getCheckInTime())
                                .build())
                        .build();
                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }else {
                response = CheckInResponseDto.builder()
                        .status("SUCCESS")
                        .data(CheckInDataResponseDto.builder()
                                .plateNumber(ticket.getPlateNumber())
                                .checkInTime(ticket.getCheckInTime())
                                .build())
                        .build();
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
