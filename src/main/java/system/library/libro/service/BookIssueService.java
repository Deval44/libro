package system.library.libro.service;

import system.library.libro.dto.request.BookIssueRequest;
import system.library.libro.entity.Slip;

public interface BookIssueService {
    Slip issueBook(BookIssueRequest bookIssueRequest);
}
