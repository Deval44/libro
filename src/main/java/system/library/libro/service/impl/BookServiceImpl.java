package system.library.libro.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import system.library.libro.entity.Book;
import system.library.libro.entity.User;
import system.library.libro.repository.BookRepository;
import system.library.libro.repository.UserRepository;
import system.library.libro.service.BookService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public Book saveBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (DataIntegrityViolationException pe) {
            log.error("Unable to save or update the book info. Reason: ", pe);
            return book;
        }
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if(book == null){
            log.error("Invalid Id: {}", bookId);
            return;
        }

        book.getAuthors().forEach(book::removeAuthor);
        bookRepository.save(book);
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Set<Book> getAllBooksByAuthor(UUID authorId) {
        return userRepository.findById(authorId)
                .map(User::getBooksAuthored)
                .orElse(new HashSet<>());
    }

    @Override
    public Set<User> addAuthor(Long bookId, UUID authorId) {
        User user = userRepository.findById(authorId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        if(user == null || book == null){
            log.error("Invalid Ids {}, {}", bookId, authorId);
            return null;
        }

        book.addAuthor(user);
        return bookRepository.save(book).getAuthors();
    }
}
