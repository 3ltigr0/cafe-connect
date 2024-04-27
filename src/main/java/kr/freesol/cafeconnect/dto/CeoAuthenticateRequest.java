package kr.freesol.cafeconnect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CeoAuthenticateRequest {
    @NotEmpty
    @Email
    private String email;

    @NotBlank
    private String password;
}
