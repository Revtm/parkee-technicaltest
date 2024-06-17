package com.example.parkingpos.service;

import com.example.parkingpos.entity.Payment;

import java.time.LocalDateTime;

public interface PaymentService {
    Payment processPayment(String plateNumber);
}
