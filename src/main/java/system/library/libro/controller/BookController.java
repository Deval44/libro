package system.library.libro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.library.libro.entity.Book;
import system.library.libro.entity.User;
import system.library.libro.service.BookService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable Long bookId){
        return bookService.getBook(bookId);
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("/book")
    public Book saveOrUpdateBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @DeleteMapping("/book/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
    }

    @GetMapping("/author/{authorId}")
    public Set<Book> getAllBooksByAuthor(@PathVariable UUID authorId){
        return bookService.getAllBooksByAuthor(authorId);
    }

    @PostMapping("/book/{bookId}/author/{authorId}")
    public Set<User> addAuthor(@PathVariable Long bookId,
                                @PathVariable UUID authorId){
        return bookService.addAuthor(bookId, authorId);
    }
}
