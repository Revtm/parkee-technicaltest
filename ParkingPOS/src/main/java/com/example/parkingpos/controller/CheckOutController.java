package com.example.parkingpos.controller;

import com.example.parkingpos.controller.utils.ControllerUtils;
import com.example.parkingpos.dto.*;
import com.example.parkingpos.dto.converter.CheckOutConverter;
import com.example.parkingpos.entity.CheckOut;
import com.example.parkingpos.service.CheckOutService;
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
public class CheckOutController {
    private final CheckOutService checkOutService;
    private final ControllerUtils controllerUtils;
    private final CheckOutConverter checkOutConverter;

    @Autowired
    public CheckOutController(CheckOutService checkOutService, ControllerUtils controllerUtils, CheckOutConverter checkOutConverter) {
        this.checkOutService = checkOutService;
        this.controllerUtils = controllerUtils;
        this.checkOutConverter = checkOutConverter;
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckOutResponseDto> submitCheckOutTicket(@RequestBody CheckOutRequestDto request){
        try {
            CheckOut checkOut = checkOutService.processCheckOut(request.getPlateNumber(), request.getCheckOutTime());
            CheckOutResponseDto response = checkOutConverter.checkOutToCheckOutResponseDto(checkOut);

            HttpStatus httpStatus = controllerUtils.mappingHttpStatus(checkOut.getProcessStatus());
            return new ResponseEntity<>(response, httpStatus);
        }catch (Exception e){
            log.error("Error", e);
            CheckOutResponseDto response = checkOutConverter.requestToFailedCheckOutResponseDto(request);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
