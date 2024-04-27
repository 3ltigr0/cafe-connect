package kr.freesol.cafeconnect.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    CEO("ROLE_CEO");

    private final String value;
}
