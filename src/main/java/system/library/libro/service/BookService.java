package system.library.libro.service;

import system.library.libro.entity.Book;
import system.library.libro.entity.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface BookService {
    Book getBook(Long bookId);

    Book saveBook(Book book);

    void deleteBook(Long bookId);

    List<Book> getAllBooks();

    Set<Book> getAllBooksByAuthor(UUID authorId);

    Set<User> addAuthor(Long bookId, UUID authorId);
}
