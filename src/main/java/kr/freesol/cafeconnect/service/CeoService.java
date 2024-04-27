package kr.freesol.cafeconnect.service;

import kr.freesol.cafeconnect.domain.Ceo;
import kr.freesol.cafeconnect.dto.CeoAuthenticateRequest;
import kr.freesol.cafeconnect.dto.CeoAuthenticateResponse;
import kr.freesol.cafeconnect.exception.CustomException;
import kr.freesol.cafeconnect.exception.ErrorCode;
import kr.freesol.cafeconnect.repository.CeoRepository;
import kr.freesol.cafeconnect.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CeoService {
    private final CeoRepository ceoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public Long createCeo(Ceo ceo) {
        ceo.encryptPassword(passwordEncoder);
        ceoRepository.save(ceo);

        return ceo.getId();
    }

    public CeoAuthenticateResponse authenticateCeo(CeoAuthenticateRequest ceoAuthenticateRequest) {
        Ceo ceo = ceoRepository.findByEmail(ceoAuthenticateRequest.getEmail())
                .filter(foundCeo -> passwordEncoder.matches(ceoAuthenticateRequest.getPassword(), foundCeo.getPassword()))
                .orElseThrow(() -> new CustomException(ErrorCode.AUTHENTICATION_FAILED));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(ceoAuthenticateRequest.getEmail(), ceoAuthenticateRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(ceo.getEmail());

        return CeoAuthenticateResponse.builder().jwt(jwt).build();
    }
}
