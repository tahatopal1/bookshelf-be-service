package com.project.book.controller;

import com.project.book.model.Book;
import com.project.book.model.BookWrapper;
import com.project.book.service.BookService;
import com.project.book.util.SessionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/getBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookWrapper> getBookFromGoogle(@RequestParam String q){
        return ResponseEntity.ok(new BookWrapper(bookService.getBooksOnline(q)));
    }

    @GetMapping(value = "/getCategories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getBookCategories(){
        return ResponseEntity.ok(bookService.getBookCategories());
    }

    @PostMapping(value = "/saveBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> saveBookForAdmin(@RequestBody Book book){
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @PostMapping(value = "/deleteBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResult> deleteBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.deleteBook(book));
    }

}
