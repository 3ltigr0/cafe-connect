package kr.freesol.cafeconnect.repository;

import kr.freesol.cafeconnect.domain.Ceo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CeoRepository extends JpaRepository<Ceo, Long> {
    Boolean existsByEmail(String email);

    Optional<Ceo> findByEmail(String email);
}
