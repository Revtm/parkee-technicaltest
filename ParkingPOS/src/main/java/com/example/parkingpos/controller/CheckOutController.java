package com.example.parkingpos.controller;

import com.example.parkingpos.controller.utils.ControllerUtils;
import com.example.parkingpos.dto.CheckOutRequestDto;
import com.example.parkingpos.dto.CheckOutResponseDto;
import com.example.parkingpos.dto.converter.TicketConverter;
import com.example.parkingpos.entity.CheckOut;
import com.example.parkingpos.service.CheckOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/pos")
public class CheckOutController {
    private final CheckOutService checkOutService;
    private final ControllerUtils controllerUtils;
    private final TicketConverter ticketConverter;

    @Autowired
    public CheckOutController(CheckOutService checkOutService, ControllerUtils controllerUtils, TicketConverter ticketConverter) {
        this.checkOutService = checkOutService;
        this.controllerUtils = controllerUtils;
        this.ticketConverter = ticketConverter;
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckOutResponseDto> submitCheckOutTicket(@RequestBody CheckOutRequestDto request){
        try {
            CheckOut checkOut = checkOutService.processCheckOut(request.getPlateNumber(), request.getCheckOutTime());
            CheckOutResponseDto response = ticketConverter.ticketToTicketResponseDto(checkOut);

            HttpStatus httpStatus = controllerUtils.mappingHttpStatus(checkOut.getProcessStatus());
            return new ResponseEntity<>(response, httpStatus);
        }catch (Exception e){
            log.error("Error", e);
            CheckOutResponseDto response = ticketConverter.ticketToFailedTicketResponseDto(request);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
