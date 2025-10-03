package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.BookDTO;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/get_all_books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/get_book_by_id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/add_book")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @PutMapping("/update_book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable long id,@RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.updateBook(id,bookDTO));
    }

    @DeleteMapping("/delete_book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }


}
