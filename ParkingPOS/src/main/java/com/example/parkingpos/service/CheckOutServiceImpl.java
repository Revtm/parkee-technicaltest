package com.example.parkingpos.service;

import com.example.parkingpos.entity.CheckOut;
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
public class CheckOutServiceImpl implements CheckOutService{
    private final TicketRepository repository;

    @Autowired
    public CheckOutServiceImpl(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public CheckOut processCheckOut(String plateNumber, LocalDateTime checkOutTime) {
        try {
            Ticket ticket = repository.getTicketByPlateNumber(plateNumber);

            Duration parkingDuration = Duration.between(ticket.getCheckInTime(), checkOutTime);
            long parkingDurationHour = parkingDuration.toHours();
            long totalPrice = (parkingDurationHour + 1) * 3000;

            CheckOut checkOut = CheckOut.builder()
                    .plateNumber(plateNumber)
                    .checkInTime(ticket.getCheckInTime())
                    .checkOutTime(checkOutTime)
                    .parkingStatus("FINISHED")
                    .totalPrice(BigInteger.valueOf(totalPrice))
                    .build();

            Integer row = repository.updateTicketStatus(checkOut);

            if(row > 0){
                checkOut.setProcessStatus("SUCCESS");
                checkOut.setMessage("Berhasil check-out");
            }else{
                checkOut.setProcessStatus("FAILED");
                checkOut.setMessage("Terdapat kesalahan sistem");
            }

            return checkOut;
        }catch (Exception e){
            log.error("Error", e);
            CheckOut checkOut = CheckOut.builder()
                    .plateNumber(plateNumber)
                    .checkOutTime(checkOutTime)
                    .processStatus("FAILED")
                    .message("Terdapat kesalahan sistem")
                    .build();
            return checkOut;
        }
    }

}
