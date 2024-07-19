package system.library.libro.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.library.libro.entity.Book;
import system.library.libro.entity.Copy;
import system.library.libro.repository.BookRepository;
import system.library.libro.repository.CopyRepository;
import system.library.libro.service.CopyService;

@Service
@Slf4j
public class CopyServiceImpl implements CopyService {
    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addCopy(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if(book == null){
            log.info("Invalid BookId {}", bookId);
            return null;
        }
        return copyRepository.save(new Copy(book)).getBook();
    }

    @Override
    public void deleteCopy(Long copyId) {
        copyRepository.deleteById(copyId);
    }

    @Override
    public Book getBookInfo(Long copyId) {
        return copyRepository.findById(copyId)
                .map(Copy::getBook)
                .orElse(null);
    }
}
