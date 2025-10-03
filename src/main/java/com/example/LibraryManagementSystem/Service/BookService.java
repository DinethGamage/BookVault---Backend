package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.BookDTO;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(long id) {
        return bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
    }

    public Book addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setIsAvailable(bookDTO.getIsAvailable());
        book.setQuantity(bookDTO.getQuantity());
        return bookRepo.save(book);

    }

    public Book updateBook(long id, BookDTO bookDTO) {
        Book oldBook = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
        oldBook.setTitle(bookDTO.getTitle());
        oldBook.setAuthor(bookDTO.getAuthor());
        oldBook.setIsbn(bookDTO.getIsbn());
        oldBook.setIsAvailable(bookDTO.getIsAvailable());
        oldBook.setQuantity(bookDTO.getQuantity());
        return bookRepo.save(oldBook);
    }

    public void deleteBook(long id) {
        bookRepo.deleteById(id);
    }
}
