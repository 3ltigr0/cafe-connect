package kr.freesol.cafeconnect.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public class CeoAuthenticateResponse {
    private String jwt;
}
