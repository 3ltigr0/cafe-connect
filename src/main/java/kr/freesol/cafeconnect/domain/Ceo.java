package kr.freesol.cafeconnect.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import kr.freesol.cafeconnect.converter.RoleConverter;
import kr.freesol.cafeconnect.security.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Ceo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Pattern(regexp = "^01(?:[016789])(?:\\d{7,8})$")
    private String phoneNumber;

    @Convert(converter = RoleConverter.class)
    private Set<Role> roles;

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
