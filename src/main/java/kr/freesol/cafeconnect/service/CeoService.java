package kr.freesol.cafeconnect.service;

import kr.freesol.cafeconnect.domain.Ceo;
import kr.freesol.cafeconnect.dto.CeoCreateRequest;
import kr.freesol.cafeconnect.dto.CeoCreateResponse;
import kr.freesol.cafeconnect.repository.CeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CeoService {
    private final CeoRepository ceoRepository;

    @Transactional
    public Long createCeo(Ceo ceo) {
        ceoRepository.save(ceo);

        return ceo.getId();
    }
}
