package com.example.parkingpos.service;

import com.example.parkingpos.entity.CheckOut;
import com.example.parkingpos.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
public class CheckOutServiceImpl implements CheckOutService {

    private final TicketRepository repository;

    @Autowired
    public CheckOutServiceImpl(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public CheckOut processCheckOut(String plateNumber, LocalDateTime now) {
        try{
            Integer countParking = repository.countByPlateNumber(plateNumber);

            CheckOut checkOut = CheckOut.builder()
                    .plateNumber(plateNumber)
                    .checkOutTime(now)
                    .build();

            if(countParking > 0){
                checkOut = repository.getTicketByPlateNumber(plateNumber);

                Duration parkingDuration = Duration.between(checkOut.getCheckInTime(), now);
                long parkingDurationHour = parkingDuration.toHours();
                long totalPrice = (parkingDurationHour + 1) * 3000;

                checkOut.setParkingStatus("CHECKING_OUT");
                checkOut.setTotalPrice(BigInteger.valueOf(totalPrice));

                Integer row = repository.updateTicketStatus(checkOut);

                if(row > 0){
                    checkOut.setProcessStatus("SUCCESS");
                    checkOut.setMessage("Silakan lakukan pembayaran");
                }else{
                    checkOut.setProcessStatus("FAILED");
                    checkOut.setMessage("Terdapat kesalahan sistem");
                }
            }else{
                checkOut.setProcessStatus("CONFLICT");
                checkOut.setMessage("Kendaraan belum melakukan check-in");
            }

            return checkOut;
        }catch (Exception e){
            log.error("Error", e);
            CheckOut checkOut = CheckOut.builder()
                    .plateNumber(plateNumber)
                    .checkOutTime(now)
                    .processStatus("FAILED")
                    .message("Gagal mendapatkan data ticket")
                    .build();

            return checkOut;
        }
    }
}
