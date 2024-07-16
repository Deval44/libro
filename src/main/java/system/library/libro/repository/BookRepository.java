package system.library.libro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.library.libro.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
