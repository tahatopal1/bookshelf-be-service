package com.project.book.service;

import com.project.book.model.AppUser;
import com.project.book.model.Book;
import com.project.book.model.UserShelf;
import com.project.book.repo.BookRepository;
import com.project.book.repo.UserRepository;
import com.project.book.repo.UserShelfRepository;
import com.project.book.util.SessionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserShelfServiceImpl implements UserShelfService{

    @Autowired
    private UserShelfRepository userShelfRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public int saveBookToUserShelf(Book book, String username) {
        try{
            if(userShelfRepository.findBookOnShelf(username, book.getGoogleBooksId()).isEmpty()){
                userShelfRepository.save(new UserShelf(book,username));
                return 1;
            }else{
                return 2;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Book> getBooksFromUserShelf(String username, String category, int approved) {
        return userShelfRepository.findBooksOnUserShelf(username, category, approved);
    }

    @Override
    public SessionResult approve(Book book) {
        try{
            userShelfRepository.approve(book);
            return new SessionResult("Book has been successfuly approved!", 1, book.getTitle());

        }catch (Exception e){
            return new SessionResult("A problem has occured!", 0, book.getTitle());
        }
    }

    @Override
    public SessionResult delete(Book book) {
        try{
            userShelfRepository.deleteByBook(book);
            return new SessionResult("Book has been successfuly deleted!", 1, book.getTitle());
        }catch (Exception e){
            return new SessionResult("A problem has occured!", 0, book.getTitle());
        }
    }

    @Override
    public int bookCount(String username, int approved) {

        AppUser appUser = userRepository.findByUsername(username);

        if (appUser.getAuthCode() == 1)
            return userShelfRepository.findBooksCountOnUserShelf(username, approved);
        else{
            if(approved == 0){
                AtomicInteger i = new AtomicInteger();
                userRepository
                        .findAllByApprovedAndAuthCodeAndManager(1, 1, username)
                        .forEach(user -> {
                            i.addAndGet(userShelfRepository.findBooksCountOnUserShelf(user.getUsername(), approved));
                        });

                return i.get();
            }else
                return userShelfRepository.findBooksCountOnUserShelf(username, 0);
        }
    }
}
