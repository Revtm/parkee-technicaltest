package com.example.parkingpos.repository;


import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ParkingRepositoryPostgre implements ParkingRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParkingRepositoryPostgre(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer submitTicket(CheckIn checkIn) {
        return jdbcTemplate.update(
                "INSERT INTO TICKET VALUES (?, ?, ?, ?, ?)",
                checkIn.getPlateNumber(),
                checkIn.getCheckInTime(),
                "PARKING",
                checkIn.getInsertTime(),
                checkIn.getUpdateTime());
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
