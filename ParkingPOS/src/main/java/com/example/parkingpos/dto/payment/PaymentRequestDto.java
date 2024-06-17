package com.example.parkingpos.dto.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentRequestDto {
    @NotBlank(message = "Mohon isi nomor plat")
    @Size(min = 3, max = 10,message = "Nomor plat minimum 3 Karakter dan maksimum 10 karakter")
    private String plateNumber;
}
