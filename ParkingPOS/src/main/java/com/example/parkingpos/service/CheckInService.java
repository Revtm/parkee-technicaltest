package com.example.parkingpos.service;

import com.example.parkingpos.entity.CheckIn;

import java.time.LocalDateTime;

public interface CheckInService {
    CheckIn processCheckIn(String plateNumber);
}
