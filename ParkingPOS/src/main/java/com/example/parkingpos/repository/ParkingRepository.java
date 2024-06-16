package com.example.parkingpos.repository;

import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.entity.Ticket;

import java.time.LocalDateTime;

public interface ParkingRepository {
    Integer submitTicket(CheckIn ticket);
    Integer countByPlateNumberAndCheckInTime(String plateNumber, LocalDateTime checkInTime);
}
