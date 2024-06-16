package com.example.parkingpos.service;

import com.example.parkingpos.entity.Ticket;

public interface CheckInService {
    Ticket createCheckInTicket(String plateNumber);
}
