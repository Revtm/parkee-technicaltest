package com.example.parkingpos.controller;

import com.example.parkingpos.controller.utils.ControllerUtils;
import com.example.parkingpos.dto.*;
import com.example.parkingpos.dto.converter.PaymentConverter;
import com.example.parkingpos.entity.Payment;
import com.example.parkingpos.service.PaymentService;
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
public class PaymentController {
    private final PaymentService paymentService;
    private final ControllerUtils controllerUtils;
    private final PaymentConverter paymentConverter;

    @Autowired
    public PaymentController(PaymentService paymentService, ControllerUtils controllerUtils, PaymentConverter paymentConverter) {
        this.paymentService = paymentService;
        this.controllerUtils = controllerUtils;
        this.paymentConverter = paymentConverter;
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponseDto> submitTicketPayment(@RequestBody PaymentRequestDto request){
        try {
            Payment payment = paymentService.processPayment(request.getPlateNumber());
            PaymentResponseDto response = paymentConverter.paymentToPaymentResponseDto(payment);

            HttpStatus httpStatus = controllerUtils.mappingHttpStatus(payment.getProcessStatus());
            return new ResponseEntity<>(response, httpStatus);
        }catch (Exception e){
            log.error("Error", e);
            PaymentResponseDto response = paymentConverter.requestToFailedPaymentResponseDto(request);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
