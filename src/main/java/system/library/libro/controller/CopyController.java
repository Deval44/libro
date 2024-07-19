package system.library.libro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.library.libro.entity.Book;
import system.library.libro.service.CopyService;

@RestController
@Slf4j
@RequestMapping("/api/v1/copies")
public class CopyController {

    @Autowired
    private CopyService copyService;

    @PostMapping("/book/{bookId}")
    private Book addCopy(@PathVariable Long bookId){
        return copyService.addCopy(bookId);
    }

    @DeleteMapping("copy/{copyId}")
    private void deleteCopy(@PathVariable Long copyId){
        copyService.deleteCopy(copyId);
    }

    @GetMapping("copy/{copyId}")
    private Book getBookInfo(@PathVariable Long copyId){
        return copyService.getBookInfo(copyId);
    }
}
