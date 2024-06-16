package com.example.parkingpos.service;


import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.repository.ParkingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CheckInServiceImpl implements CheckInService{

    private final ParkingRepository parkingRepository;

    @Autowired
    public CheckInServiceImpl(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public CheckIn processCheckIn(String plateNumber, LocalDateTime checkInTime) {
        try {
            Integer countParking = parkingRepository.countByPlateNumberAndCheckInTime(plateNumber, checkInTime);

            CheckIn checkIn = CheckIn.builder()
                    .plateNumber(plateNumber)
                    .checkInTime(checkInTime)
                    .build();

            if(countParking <= 0){
                Integer row = parkingRepository.submitTicket(checkIn);
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
            log.error(e.getMessage());
            CheckIn checkIn = CheckIn.builder()
                    .plateNumber(plateNumber)
                    .checkInTime(checkInTime)
                    .processStatus("FAILED")
                    .message("Terdapat kesalahan sistem")
                    .build();
            return checkIn;
        }
    }
}
