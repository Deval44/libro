package system.library.libro.service.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.library.libro.dto.request.BookIssueRequest;
import system.library.libro.entity.Book;
import system.library.libro.entity.Copy;
import system.library.libro.entity.Slip;
import system.library.libro.entity.User;
import system.library.libro.repository.BookRepository;
import system.library.libro.repository.CopyRepository;
import system.library.libro.repository.SlipRepository;
import system.library.libro.repository.UserRepository;
import system.library.libro.service.BookIssueService;

import java.time.LocalDate;
import java.util.Comparator;

@Service
@Slf4j
public class BookIssueServiceImpl implements BookIssueService {
    @Autowired
    private SlipRepository slipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Override
    public Slip issueBook(BookIssueRequest bookIssueRequest) {
        User user = userRepository.findById(bookIssueRequest.getUserId()).orElse(null);
        if(user == null){
            log.error("User doesn't exists");
            return null;
        }

        if(user.getSlips().size() >= 5){
            log.warn("Cannot issue more than 5 books");
            return null;
        }

        if(bookIssueRequest.getDueDate() == null){
            bookIssueRequest.setDueDate(LocalDate.now().plusDays(15));
        }

        Slip slip = Slip.builder().user(user).dueDate(bookIssueRequest.getDueDate()).build();

        if(bookIssueRequest.getCopyId() != null){
            return issueCopy(bookIssueRequest.getCopyId(), slip);
        }

        if(bookIssueRequest.getBookId() != null){
            return issueBook(bookIssueRequest.getBookId(), slip);
        }
        return null;
    }

    @Override
    public Long returnBook(Long slipId) {
        Slip slip = slipRepository.findById(slipId).orElse(null);
        if(slip == null){
            log.error("Invalid slip");
            return null;
        }
        LocalDate now = LocalDate.now();
        long fee = 20L, fine = 40L;
        fee = slip.getDueDate().isBefore(now) ? fine : fee;

        slipRepository.deleteById(slip.getSlipId());
        return fee;
    }

    private Slip issueBook(Long bookId, Slip slip) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if(book == null){
            log.error("book doesn't exist");
            return null;
        }

        Copy copy = book.getCopies().stream()
                .filter(copy1 -> copy1.getRack()!=null && copy1.getSlip()==null)
                .min(Comparator.comparingLong(a -> a.getRack().getId()))
                .orElse(null);

        return getSlip(slip, copy);
    }

    private Slip issueCopy(Long copyId, Slip slip) {
        Copy copy = copyRepository.findById(copyId).orElse(null);
        return getSlip(slip, copy);
    }

    private Slip getSlip(Slip slip, Copy copy) {
        if(copy == null){
            log.error("copy doesn't exist");
            return null;
        }

        if(copy.getSlip() != null){
            log.error("Already booked");
            return null;
        }

        copy.removeFromRack();
        slip.setCopy(copy);
        return slipRepository.save(slip);
    }
}
