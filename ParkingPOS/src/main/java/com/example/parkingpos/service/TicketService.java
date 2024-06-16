package com.example.parkingpos.service;

import com.example.parkingpos.entity.Ticket;

import java.time.LocalDateTime;

public interface TicketService {
    Ticket getTicketDetail(String plateNumber, LocalDateTime now);
}
