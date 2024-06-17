package com.example.parkingpos.dto.checkout;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CheckOutRequestDto {
    @NotBlank(message = "Mohon isi nomor plat")
    @Size(min = 3, max = 10,message = "Nomor plat minimum 3 Karakter dan maksimum 10 karakter")
    private String plateNumber;
}
