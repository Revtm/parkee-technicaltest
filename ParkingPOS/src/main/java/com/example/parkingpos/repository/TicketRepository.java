package com.example.parkingpos.repository;

import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.entity.Ticket;

import java.time.LocalDateTime;

public interface TicketRepository {
    Integer submitTicket(CheckIn ticket);
    Integer countByPlateNumber(String plateNumber);
    Ticket getTicketByPlateNumber(String plateNumber);
}
