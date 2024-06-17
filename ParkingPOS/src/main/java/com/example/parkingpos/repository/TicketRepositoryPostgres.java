package com.example.parkingpos.repository;


import com.example.parkingpos.entity.CheckIn;
import com.example.parkingpos.entity.CheckOut;
import com.example.parkingpos.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Repository
@Slf4j
public class TicketRepositoryPostgres implements TicketRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketRepositoryPostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer submitTicket(CheckIn checkIn) {
        return jdbcTemplate.update(
                "INSERT INTO TICKET (plate_number, check_in_time, parking_status, insert_time, update_time) VALUES (?, ?, ?, ?, ?)",
                checkIn.getPlateNumber(),
                checkIn.getCheckInTime(),
                "PARKING",
                checkIn.getCheckInTime(),
                checkIn.getCheckInTime());
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
    public CheckOut getTicketByPlateNumber(String plateNumber) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM TICKET " +
                        "WHERE plate_number = ? " +
                        "AND parking_status = 'PARKING' LIMIT 1",
                    (rs, rowNum) -> {
                        CheckOut checkOut = CheckOut.builder()
                                .plateNumber(rs.getString("plate_number"))
                                .checkInTime((rs.getObject("check_in_time", LocalDateTime.class)))
                                .parkingStatus(rs.getString("parking_status"))
                                .build();
                        return checkOut;
                    }, plateNumber);
        }catch (EmptyResultDataAccessException e){
            log.error("Error", e);
            return null;
        }
    }

    @Override
    public Integer updateTicketStatus(CheckOut checkOut) {
        return jdbcTemplate.update(
                "UPDATE TICKET SET parking_status = ? , check_out_time = ?, total_price = ? , update_time = ? " +
                        " WHERE plate_number = ? and parking_status = 'PARKING'",
                checkOut.getParkingStatus(),
                checkOut.getCheckOutTime(),
                checkOut.getTotalPrice(),
                checkOut.getCheckOutTime(),
                checkOut.getPlateNumber());
    }

    @Override
    public Integer updateTicketStatus(Payment payment) {
        try{
            LocalDateTime updateTime = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
            return jdbcTemplate.update(
                    "UPDATE TICKET SET parking_status = ? , update_time = ? " +
                            " WHERE plate_number = ? and parking_status = 'CHECKING_OUT'",
                    payment.getParkingStatus(),
                    updateTime,
                    payment.getPlateNumber());
        }catch (EmptyResultDataAccessException e){
            log.error("Error", e);
            return 0;
        }
    }
}
