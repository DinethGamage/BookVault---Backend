package com.example.LibraryManagementSystem.Controller;


import com.example.LibraryManagementSystem.Entity.IssueRecord;
import com.example.LibraryManagementSystem.Service.IssueRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issue_records")
public class IssueRecordController {

    @Autowired
    private IssueRecordService issueRecordService;

    @PostMapping("/issue_book/{bookId}")
    public ResponseEntity<IssueRecord> issueBook(@PathVariable Long bookId){
        return ResponseEntity.ok(issueRecordService.issueBook(bookId));
    }

    @PostMapping("/return_book/{issueRecordId}")
    public ResponseEntity<IssueRecord> returnBook(@PathVariable Long issueRecordId){
        return ResponseEntity.ok(issueRecordService.returnBook(issueRecordId));
    }
}
