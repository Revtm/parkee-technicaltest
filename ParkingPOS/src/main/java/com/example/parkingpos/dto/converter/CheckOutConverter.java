package com.example.parkingpos.dto.converter;

import com.example.parkingpos.dto.*;
import com.example.parkingpos.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class CheckOutConverter {
    public PaymentResponseDto checkOutToCheckOutResponseDto(Payment payment){
        return PaymentResponseDto.builder()
                .status(payment.getProcessStatus())
                .data(PaymentDataResponseDto.builder()
                        .plateNumber(payment.getPlateNumber())
                        .message(payment.getMessage())
                        .build())
                .build();
    }

    public PaymentResponseDto requestToFailedCheckOutResponseDto(PaymentRequestDto request){
        return PaymentResponseDto.builder()
                .status("FAILED")
                .data(PaymentDataResponseDto.builder()
                        .plateNumber(request.getPlateNumber())
                        .message("Terdapat kesalahan sistem")
                        .build())
                .build();
    }
}
