package com.example.parkingpos.controller;

import com.example.parkingpos.controller.utils.ControllerUtils;
import com.example.parkingpos.dto.checkin.CheckInRequestDto;
import com.example.parkingpos.dto.checkin.CheckInResponseDto;
import com.example.parkingpos.dto.converter.CheckInConverter;
import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.service.CheckInService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<CheckInResponseDto> submitCheckInTicket(@Valid @RequestBody CheckInRequestDto request, BindingResult bindingResult){
        try{
            if(bindingResult.hasErrors()){
                StringBuilder builder = new StringBuilder();
                for (ObjectError err : bindingResult.getAllErrors()){
                    builder.append(err.getDefaultMessage());
                    builder.append(",");
                }
                throw new IllegalArgumentException(builder.toString());
            }

            CheckIn checkIn = checkInService.processCheckIn(request.getPlateNumber().replaceAll("\\s", "").toUpperCase());

            CheckInResponseDto response = checkInConverter.checkInToCheckInResponseDto(checkIn);
            HttpStatus httpStatus = controllerUtils.mappingHttpStatus(checkIn.getProcessStatus());

            return new ResponseEntity<>(response, httpStatus);
        } catch (IllegalArgumentException e){
            log.error("Error", e);
            CheckInResponseDto response = checkInConverter.requestToFailedCheckInResponseDto(request, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error("Error", e);
            CheckInResponseDto response = checkInConverter.requestToFailedCheckInResponseDto(request, "Terdapat kesalahan sistem");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
