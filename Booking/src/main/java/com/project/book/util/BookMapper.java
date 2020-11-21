package com.project.book.util;

import com.project.book.dto.BookResponse;
import com.project.book.dto.VolumeInfo;
import com.project.book.model.Book;


import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static Book bookResponsetoBook(BookResponse bookResponse){
        Book book = new Book();
        VolumeInfo volumeInfo = bookResponse.getVolumeInfo();

        book.setGoogleBooksId(bookResponse.getId());
        book.setPublisher(volumeInfo.getPublisher());
        book.setAuthor(convertList(volumeInfo.getAuthors()));
        book.setTitle(volumeInfo.getTitle());
        book.setCategoryId(-1);
        book.setPageCount((int) volumeInfo.getPageCount());
        book.setPublishedDate(volumeInfo.getPublishedDate());
        book.setDescription(volumeInfo.getDescription());

        return book;
    }

    public static List<String> convertList(List<Object> objectList){
        List<String> stringList = new ArrayList<>();
        objectList.forEach(object -> stringList.add(String.valueOf(object)));
        return stringList;
    }
}
