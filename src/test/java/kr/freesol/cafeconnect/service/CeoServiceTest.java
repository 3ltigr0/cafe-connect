package kr.freesol.cafeconnect.service;

import kr.freesol.cafeconnect.domain.Ceo;
import kr.freesol.cafeconnect.repository.CeoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CeoServiceTest {
    @Autowired
    private CeoService ceoService;
    @Autowired
    private CeoRepository ceoRepository;

    @Test
    @Transactional
    void createCeoTest() {
        // given
        Ceo ceo = Ceo.builder()
                .email("test@test.com")
                .password("123456")
                .name("김테스트")
                .phoneNumber("01012345678")
                .build();

        // when
        Long savedId = ceoService.createCeo(ceo);
        Ceo findCeo = ceoRepository.findById(savedId).get();

        //then
        assertEquals(findCeo.getId(), ceo.getId());
        assertEquals(findCeo.getEmail(), ceo.getEmail());
        assertEquals(findCeo.getPassword(), ceo.getPassword());
        assertEquals(findCeo.getName(), ceo.getName());
        assertEquals(findCeo.getPhoneNumber(), ceo.getPhoneNumber());
    }
}