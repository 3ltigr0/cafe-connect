package kr.freesol.cafeconnect.service;

import kr.freesol.cafeconnect.domain.Ceo;
import kr.freesol.cafeconnect.exception.CustomException;
import kr.freesol.cafeconnect.exception.ErrorCode;
import kr.freesol.cafeconnect.repository.CeoRepository;
import kr.freesol.cafeconnect.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CeoDetailService implements UserDetailsService {
    private final CeoRepository ceoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Ceo ceo = ceoRepository.findByEmail(username)
                .orElseThrow(() -> new CustomException(ErrorCode.AUTHENTICATION_FAILED));

        return new User(ceo.getEmail(), ceo.getPassword(),
                ceo.getRoles().stream()
                        .map(Role::getValue)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet()));
    }
}
