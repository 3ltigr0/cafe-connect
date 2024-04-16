package kr.freesol.cafeconnect.dto;

import jakarta.validation.constraints.*;
import kr.freesol.cafeconnect.domain.Ceo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CeoCreateRequest {
    @NotEmpty
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotNull
    @Pattern(regexp = "010\\d{4}\\d{4}|01[16789]\\d{3,4}\\d{4}")
    private String phoneNumber;

    public Ceo toEntity() {
        return Ceo.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
