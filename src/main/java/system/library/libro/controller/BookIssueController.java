package system.library.libro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.library.libro.dto.request.BookIssueRequest;
import system.library.libro.entity.Slip;
import system.library.libro.service.BookIssueService;

@RestController
@Slf4j
@RequestMapping("api/v1/issues")
public class BookIssueController {

    @Autowired
    private BookIssueService bookIssueService;

    @PostMapping("/issue")
    public Slip issueBook(@RequestBody BookIssueRequest bookIssueRequest){
        return bookIssueService.issueBook(bookIssueRequest);
    }
}
