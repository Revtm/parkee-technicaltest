package com.example.parkingpos.service;


import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Slf4j
public class CheckInServiceImpl implements CheckInService{

    private final TicketRepository ticketRepository;

    @Autowired
    public CheckInServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public CheckIn processCheckIn(String plateNumber) {
        try {
            LocalDateTime checkInTime = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
            Integer countParking = ticketRepository.countParkingAndCheckingOut(plateNumber);

            CheckIn checkIn = CheckIn.builder()
                    .plateNumber(plateNumber)
                    .checkInTime(checkInTime)
                    .build();

            if(countParking <= 0){
                Integer row = ticketRepository.submitTicket(checkIn);
                if(row > 0){
                    checkIn.setProcessStatus("SUCCESS");
                    checkIn.setMessage("Berhasil check-in");
                }else{
                    checkIn.setProcessStatus("FAILED");
                    checkIn.setMessage("Terdapat kesalahan sistem");
                }
            }else{
                checkIn.setProcessStatus("CONFLICT");
                checkIn.setMessage("Kendaraan yang sama sudah melakukan check-in");
            }

            return checkIn;
        }catch (Exception e){
            log.error("Error", e);
            CheckIn checkIn = CheckIn.builder()
                    .plateNumber(plateNumber)
                    .processStatus("FAILED")
                    .message("Terdapat kesalahan sistem")
                    .build();
            return checkIn;
        }
    }
}
