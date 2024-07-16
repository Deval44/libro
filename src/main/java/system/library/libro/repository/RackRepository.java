package system.library.libro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.library.libro.entity.Rack;

@Repository
public interface RackRepository extends JpaRepository<Rack, Long> {

}
