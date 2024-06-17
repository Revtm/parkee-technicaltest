package com.example.parkingpos.service;

import com.example.parkingpos.entity.Ticket;
import com.example.parkingpos.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService{

    private final TicketRepository repository;

    @Autowired
    public TicketServiceImpl(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ticket getTicketDetail(String plateNumber, LocalDateTime now) {
        try{
            Ticket ticket = repository.getTicketByPlateNumber(plateNumber);

            if(ticket != null){
                Duration parkingDuration = Duration.between(ticket.getCheckInTime(), now);
                long parkingDurationHour = parkingDuration.toHours();
                long totalPrice = (parkingDurationHour + 1) * 3000;

                ticket.setCheckOutTime(now);
                ticket.setTotalPrice(BigInteger.valueOf(totalPrice));
                ticket.setProcessStatus("SUCCESS");
                ticket.setMessage("Berhasil mendapatkan data ticket");

                return ticket;
            } else {
                return Ticket.builder()
                        .processStatus("FAILED")
                        .plateNumber(plateNumber)
                        .checkOutTime(now)
                        .message("Kendaraan belum melakukan check-in")
                        .build();
            }
        }catch (Exception e){
            log.error("Error", e);
            Ticket ticket = Ticket.builder()
                    .plateNumber(plateNumber)
                    .checkOutTime(now)
                    .processStatus("FAILED")
                    .message("Gagal mendapatkan data ticket")
                    .build();

            return ticket;
        }
    }
}
