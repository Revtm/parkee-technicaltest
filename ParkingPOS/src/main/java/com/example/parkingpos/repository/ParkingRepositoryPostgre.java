package com.example.parkingpos.repository;


import com.example.parkingpos.entity.CheckIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Repository
public class ParkingRepositoryPostgre implements ParkingRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParkingRepositoryPostgre(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer submitTicket(CheckIn checkIn) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
        return jdbcTemplate.update(
                "INSERT INTO TICKET VALUES (?, ?, ?, ?, ?)",
                checkIn.getPlateNumber(),
                checkIn.getCheckInTime(),
                "PARKING",
                now,
                now);
    }

    @Override
    public Integer countByPlateNumberAndCheckInTime(String plateNumber, LocalDateTime checkInTime) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM TICKET " +
                        "WHERE plate_number = ? " +
                        "AND check_in_time <= ? " +
                        "AND parking_status = 'PARKING'",
                Integer.class, plateNumber, checkInTime);
    }
}
