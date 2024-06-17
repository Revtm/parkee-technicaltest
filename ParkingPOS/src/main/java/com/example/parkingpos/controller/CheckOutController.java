package com.example.parkingpos.controller;

import com.example.parkingpos.controller.utils.ControllerUtils;
import com.example.parkingpos.dto.checkout.CheckOutRequestDto;
import com.example.parkingpos.dto.checkout.CheckOutResponseDto;
import com.example.parkingpos.dto.converter.CheckOutConverter;
import com.example.parkingpos.entity.CheckOut;
import com.example.parkingpos.service.CheckOutService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
    public ResponseEntity<CheckOutResponseDto> submitCheckOutTicket(@Valid @RequestBody CheckOutRequestDto request, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()){
                StringBuilder builder = new StringBuilder();
                for (ObjectError err : bindingResult.getAllErrors()){
                    builder.append(err.getDefaultMessage());
                    builder.append(",");
                }
                throw new IllegalArgumentException(builder.toString());
            }

            CheckOut checkOut = checkOutService.processCheckOut(request.getPlateNumber());
            CheckOutResponseDto response = checkOutConverter.checkOutToCheckOutResponseDto(checkOut);

            HttpStatus httpStatus = controllerUtils.mappingHttpStatus(checkOut.getProcessStatus());
            return new ResponseEntity<>(response, httpStatus);
        } catch (IllegalArgumentException e){
            log.error("Error", e);
            CheckOutResponseDto response = checkOutConverter.checkOutToFailedCheckOutResponseDto(request, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error("Error", e);
            CheckOutResponseDto response = checkOutConverter.checkOutToFailedCheckOutResponseDto(request,"Gagal mendapatkan data ticket");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
