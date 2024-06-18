package com.example.parkingpos.service;

import com.example.parkingpos.entity.Payment;
import com.example.parkingpos.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final TicketRepository repository;

    @Autowired
    public PaymentServiceImpl(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment processPayment(String plateNumber) {
        try {
            Payment payment = Payment.builder()
                    .plateNumber(plateNumber)
                    .parkingStatus("FINISHED")
                    .build();

            Integer row = repository.updateTicketStatus(payment);

            if(row > 0){
                payment.setProcessStatus("SUCCESS");
                payment.setMessage("Pembayaran Berhasil");
            }else{
                payment.setProcessStatus("FAILED");
                payment.setMessage("Kendaraan belum melakukan check-out");
            }

            return payment;
        }catch (Exception e){
            log.error("Error", e);
            Payment payment = Payment.builder()
                    .plateNumber(plateNumber)
                    .processStatus("FAILED")
                    .message("Terdapat kesalahan sistem")
                    .build();
            return payment;
        }
    }

}
