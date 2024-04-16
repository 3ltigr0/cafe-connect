package kr.freesol.cafeconnect.repository;

import kr.freesol.cafeconnect.domain.Ceo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CeoRepository extends JpaRepository<Ceo, Long> {
    Boolean existsByEmail(String email);
}
