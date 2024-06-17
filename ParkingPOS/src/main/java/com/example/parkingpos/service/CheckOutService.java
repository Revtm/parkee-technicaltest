package com.example.parkingpos.service;

import com.example.parkingpos.entity.CheckOut;

import java.time.LocalDateTime;

public interface CheckOutService {
    CheckOut processCheckOut(String plateNumber, LocalDateTime checkOutTime);
}
