package com.example.parkingpos.repository;


import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.entity.CheckOut;
import com.example.parkingpos.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Repository
public class TicketRepositoryPostgres implements TicketRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketRepositoryPostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer submitTicket(CheckIn checkIn) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
        return jdbcTemplate.update(
                "INSERT INTO TICKET (plate_number, check_in_time, parking_status, insert_time, update_time) VALUES (?, ?, ?, ?, ?)",
                checkIn.getPlateNumber(),
                checkIn.getCheckInTime(),
                "PARKING",
                now,
                now);
    }

    @Override
    public Integer countByPlateNumber(String plateNumber) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM TICKET " +
                        "WHERE plate_number = ? " +
                        "AND parking_status = 'PARKING'",
                Integer.class, plateNumber);
    }

    @Override
    public Ticket getTicketByPlateNumber(String plateNumber) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM TICKET " +
                        "WHERE plate_number = ? " +
                        "AND parking_status = 'PARKING' LIMIT 1",
                (rs, rowNum) -> {
                    Ticket ticket = Ticket.builder()
                            .plateNumber(rs.getString("plate_number"))
                            .checkInTime(LocalDateTime.parse(rs.getString("check_in_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")))
                            .parkingStatus(rs.getString("parking_status"))
                            .build();
                    return ticket;
                }, plateNumber);
    }

    @Override
    public Integer updateTicketStatus(CheckOut checkOut) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
        return jdbcTemplate.update(
                "UPDATE TICKET SET parking_status = ? , check_out_time = ? , update_time = ? " +
                    "WHERE plate_number = ? and parking_status = 'PARKING'",
                checkOut.getParkingStatus(),
                checkOut.getCheckOutTime(),
                now,
                checkOut.getPlateNumber());
    }
}
