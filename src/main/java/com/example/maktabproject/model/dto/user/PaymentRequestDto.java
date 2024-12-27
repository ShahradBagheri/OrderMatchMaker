package com.example.maktabproject.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
public record PaymentRequestDto(@NotNull Integer captchaId,
                                @NotBlank String captcha,
                                @NotNull Long userId,
                                @Pattern(regexp = "^[0-9]{12}$") String cardNumber,
                                @Pattern(regexp = "^[0-9]{4}$") String CVV2,
                                @Pattern(regexp = "^[0-9]{8}$") String secondPassword,
                                @NotNull Double amount) {
}
