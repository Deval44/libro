package system.library.libro.service;

import system.library.libro.entity.Book;

public interface CopyService {
    Book addCopy(Long bookId);

    void deleteCopy(Long copyId);

    Book getBookInfo(Long copyId);
}
