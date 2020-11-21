package com.project.book.controller;

import com.project.book.model.Book;
import com.project.book.model.UserShelf;
import com.project.book.service.UserShelfService;
import com.project.book.util.SessionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usershelf")
public class UserShelfController {

    @Autowired
    private UserShelfService userShelfService;

    @PostMapping(value = "/saveBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> saveBookToUserShelf(@RequestBody UserShelf userShelf){
        return ResponseEntity.ok(userShelfService.saveBookToUserShelf(userShelf.getBook(), userShelf.getUsername()));
    }

    @GetMapping(value = "/getBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAllBooksFromUserShelf(@RequestParam String username,
                                                               @RequestParam String category,
                                                               @RequestParam int approved){
        return ResponseEntity.ok(userShelfService.getBooksFromUserShelf(username, category, approved));
    }

    @PostMapping(value = "/approve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResult> approveBook(@RequestBody Book book){
        return ResponseEntity.ok(userShelfService.approve(book));
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResult> deleteBook(@RequestBody Book book){
        return ResponseEntity.ok(userShelfService.delete(book));
    }

    @GetMapping(value = "/getBookCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getBookCount(@RequestParam String username, @RequestParam int approved){
        return ResponseEntity.ok(userShelfService.bookCount(username, approved));
    }

}
