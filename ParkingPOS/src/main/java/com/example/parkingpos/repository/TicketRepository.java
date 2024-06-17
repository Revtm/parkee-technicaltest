package com.example.parkingpos.repository;

import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.entity.CheckOut;
import com.example.parkingpos.entity.Payment;

public interface TicketRepository {
    Integer submitTicket(CheckIn checkIn);
    Integer countParking(String plateNumber);
    Integer countParkingAndCheckingOut(String plateNumber);
    CheckOut getTicketByPlateNumber(String plateNumber);
    Integer updateTicketStatus(CheckOut checkOut);
    Integer updateTicketStatus(Payment payment);
}
