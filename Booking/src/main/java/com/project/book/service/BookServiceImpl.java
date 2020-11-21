package com.project.book.service;

import com.project.book.dto.BookResponseWrapper;
import com.project.book.model.Book;
import com.project.book.repo.BookRepository;
import com.project.book.util.BookMapper;
import com.project.book.util.Params;
import com.project.book.util.SessionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooksOnline(String keyword){

        URI targetUrl= UriComponentsBuilder.fromUriString(Params.bookAPI_URL)
                .path("/books/v1/volumes")
                .queryParam("q", keyword)
                .queryParam("maxResults", 40)
                .build()
                .encode()
                .toUri();

        final List<Book> collect = new RestTemplate()
                .getForEntity(targetUrl, BookResponseWrapper.class)
                .getBody()
                .getItems()
                .stream()
                .map(BookMapper::bookResponsetoBook)
                .collect(Collectors.toList());

        return collect;

    }

    @Override
    public List<String> getBookCategories() {
        List<String> list = new ArrayList<>();
        list.add("Bilim");
        list.add("Edebiyat");
        list.add("Tarih");
        list.add("Eğitim");
        list.add("Çocuk");
        list.add("Din");
        list.add("Kültür");
        list.add("Sağlık");
        list.add("Felsefe");
        list.add("Politika");
        list.add("Psikoloji");
        list.add("Akademik");
        list.add("Sanat");
        return list;
    }

    @Override
    public int saveBook(Book book) {
        try{
            if(!(bookRepository.findByGoogleBooksId(book.getGoogleBooksId()).size() > 0)){
                bookRepository.save(book);
                return 1;
            }else
                return 2;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public SessionResult deleteBook(Book book) {
        try{
            bookRepository.delete(book);
            return new SessionResult("Login successful!", 1, book.getTitle());
        }catch (Exception e){
            return new SessionResult("A problem has occured!", 0, book.getTitle());
        }
    }

}