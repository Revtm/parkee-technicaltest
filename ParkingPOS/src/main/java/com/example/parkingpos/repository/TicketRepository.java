package com.example.parkingpos.repository;

import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.entity.CheckOut;
import com.example.parkingpos.entity.Ticket;

public interface TicketRepository {
    Integer submitTicket(CheckIn checkIn);
    Integer countByPlateNumber(String plateNumber);
    Ticket getTicketByPlateNumber(String plateNumber);

    Integer updateTicketStatus(CheckOut checkOut);
}
