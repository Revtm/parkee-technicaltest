package com.example.parkingpos.service;


import com.example.parkingpos.entity.Ticket;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CheckInServiceImpl implements CheckInService{
    @Override
    public Ticket createCheckInTicket(String plateNumber) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
        Ticket ticket = Ticket.builder()
                .plateNumber(plateNumber)
                .checkInTime(now)
                .status("SUCCESS")
                .build();

        return ticket;
    }
}
