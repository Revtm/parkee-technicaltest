package com.example.parkingpos.service;

import com.example.parkingpos.entity.CheckOut;
import com.example.parkingpos.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Slf4j
public class CheckOutServiceImpl implements CheckOutService {

    private final TicketRepository repository;

    @Autowired
    public CheckOutServiceImpl(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public CheckOut processCheckOut(String plateNumber) {
        try{
            LocalDateTime checkOutTime = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
            Integer countParking = repository.countParkingAndCheckingOut(plateNumber);
            CheckOut checkOut = null;

            if(countParking > 0){
                checkOut = repository.getTicketByPlateNumber(plateNumber);
                checkOut.setCheckOutTime(checkOutTime);

                Duration parkingDuration = Duration.between(checkOut.getCheckInTime(), checkOutTime);
                long parkingDurationHour = parkingDuration.toHours();
                long totalPrice = (parkingDurationHour + 1) * 3000;

                checkOut.setParkingStatus("CHECKING_OUT");
                checkOut.setTotalPrice(BigInteger.valueOf(totalPrice));

                Integer row = repository.updateTicketStatus(checkOut);

                checkOut.setProcessStatus("SUCCESS");
                checkOut.setMessage("Silakan lakukan pembayaran");
            }else{
                checkOut = CheckOut.builder()
                        .plateNumber(plateNumber)
                        .processStatus("CONFLICT")
                        .message("Kendaraan belum melakukan check-in")
                        .build();
            }

            return checkOut;
        }catch (Exception e){
            log.error("Error", e);
            CheckOut checkOut = CheckOut.builder()
                    .plateNumber(plateNumber)
                    .processStatus("FAILED")
                    .message("Gagal mendapatkan data ticket")
                    .build();

            return checkOut;
        }
    }
}
