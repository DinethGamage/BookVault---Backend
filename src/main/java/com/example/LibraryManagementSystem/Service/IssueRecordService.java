package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Entity.IssueRecord;
import com.example.LibraryManagementSystem.Entity.User;
import com.example.LibraryManagementSystem.Repository.BookRepo;
import com.example.LibraryManagementSystem.Repository.IssueRecordRepo;
import com.example.LibraryManagementSystem.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordRepo issueRecordRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;


    public IssueRecord issueBook(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0 || !book.getIsAvailable()){
            throw new RuntimeException("Book is not available");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecord.setIsReturned(false);
        issueRecord.setUser(user);
        issueRecord.setBook(book);

        book.setQuantity(book.getQuantity()-1);
        if (book.getQuantity() == 0){
            book.setIsAvailable(false);
        }

        bookRepo.save(book);
        return issueRecordRepo.save(issueRecord);

    }

    public IssueRecord returnBook(Long issueRecordId) {
        IssueRecord issueRecord = issueRecordRepo.findById(issueRecordId).orElseThrow(() -> new RuntimeException("Issue record not found"));

        if (issueRecord.getIsReturned()){
            throw new RuntimeException("Book is already returned");
        }

        Book book = issueRecord.getBook();
        book.setQuantity(book.getQuantity()+1);
        book.setIsAvailable(true);
        bookRepo.save(book);

        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setIsReturned(true);
        return issueRecordRepo.save(issueRecord);
    }
}
