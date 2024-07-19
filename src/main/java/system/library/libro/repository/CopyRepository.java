package system.library.libro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.library.libro.entity.Copy;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {
}
