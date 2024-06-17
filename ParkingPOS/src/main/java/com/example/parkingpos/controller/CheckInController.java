package com.example.parkingpos.controller;

import com.example.parkingpos.controller.utils.ControllerUtils;
import com.example.parkingpos.dto.CheckInRequestDto;
import com.example.parkingpos.dto.CheckInResponseDto;
import com.example.parkingpos.dto.converter.CheckInConverter;
import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.service.CheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/pos")
public class CheckInController {
    private final CheckInService checkInService;
    private final CheckInConverter checkInConverter;
    private final ControllerUtils controllerUtils;

    @Autowired
    public CheckInController(CheckInService checkInService, CheckInConverter checkInConverter, ControllerUtils controllerUtils) {
        this.checkInService = checkInService;
        this.checkInConverter = checkInConverter;
        this.controllerUtils = controllerUtils;
    }

    @PostMapping("/checkin")
    public ResponseEntity<CheckInResponseDto> createCheckInTicket(@RequestBody CheckInRequestDto request){
        try{
            CheckIn checkIn = checkInService.processCheckIn(request.getPlateNumber(), request.getCheckInTime());

            CheckInResponseDto response = checkInConverter.checkInToCheckInResponseDto(checkIn);
            HttpStatus httpStatus = controllerUtils.mappingHttpStatus(checkIn.getProcessStatus());

            return new ResponseEntity<>(response, httpStatus);
        }catch (Exception e){
            log.error("Error", e);
            CheckInResponseDto response = checkInConverter.requestToFailedCheckInResponseDto(request);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
