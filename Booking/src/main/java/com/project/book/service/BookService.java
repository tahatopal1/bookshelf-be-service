package com.project.book.service;

import com.project.book.model.Book;
import com.project.book.util.SessionResult;

import java.util.List;

public interface BookService{
    List<Book> getBooksOnline(String keyword);
    List<String> getBookCategories();
    int saveBook(Book book);
    SessionResult deleteBook(Book book);
}
