package system.library.libro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.library.libro.entity.Slip;

@Repository
public interface SlipRepository extends JpaRepository<Slip, Long> {
}
