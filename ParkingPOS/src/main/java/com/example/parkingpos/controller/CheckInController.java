package com.example.parkingpos.controller;

import com.example.parkingpos.dto.CheckInDataResponseDto;
import com.example.parkingpos.dto.CheckInRequestDto;
import com.example.parkingpos.dto.CheckInResponseDto;
import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
            CheckIn checkIn = checkInService.processCheckIn(request.getPlateNumber(), request.getCheckInTime());

            CheckInResponseDto response = CheckInResponseDto.builder()
                    .status(checkIn.getProcessStatus())
                    .data(CheckInDataResponseDto.builder()
                            .plateNumber(checkIn.getPlateNumber())
                            .checkInTime(checkIn.getCheckInTime())
                            .message(checkIn.getMessage())
                            .build())
                    .build();

            HttpStatus httpStatus;

            if(checkIn.getProcessStatus().equals("CONFLICT")){
                httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            }else if(checkIn.getProcessStatus().equals("SUCCESS")){
                httpStatus = HttpStatus.CREATED;
            }else{
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            return new ResponseEntity<>(response, httpStatus);
        }catch (Exception e){
            CheckInResponseDto response = CheckInResponseDto.builder()
                    .status("FAILED")
                    .data(CheckInDataResponseDto.builder()
                            .plateNumber(request.getPlateNumber())
                            .checkInTime(request.getCheckInTime())
                            .message("Terdapat kesalahan sistem")
                            .build())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
