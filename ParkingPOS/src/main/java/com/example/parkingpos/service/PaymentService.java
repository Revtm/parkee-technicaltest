package com.example.parkingpos.service;

import com.example.parkingpos.entity.Payment;


public interface PaymentService {
    Payment processPayment(String plateNumber);
}
