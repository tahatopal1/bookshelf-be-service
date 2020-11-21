package com.project.book.service;

import com.project.book.model.Book;
import com.project.book.util.SessionResult;

import java.util.List;

public interface UserShelfService {
    int saveBookToUserShelf(Book book, String username);
    List<Book> getBooksFromUserShelf(String username, String category, int approved);
    SessionResult approve(Book book);
    SessionResult delete(Book book);
    int bookCount(String username, int approved);
}
