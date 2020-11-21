package com.project.book.repo;

import com.project.book.model.Book;
import com.project.book.model.UserShelf;
import com.project.book.util.SessionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface UserShelfRepository extends JpaRepository<UserShelf, UUID>{

    @Query("select b from UserShelf u,Book b where b.id = u.book.id and u.username = ?1 and b.googleBooksId = ?2")
    List<Book> findBookOnShelf(String username, String googleBooksId);

    @Query("select b from UserShelf u,Book b where b.id = u.book.id and u.username = ?1 and b.category = ?2 and u.approved = ?3")
    List<Book> findBooksOnUserShelf(String username, String category, int approved);

    @Query("select count(b) from UserShelf u,Book b where b.id = u.book.id and u.username = ?1 and u.approved = ?2")
    Integer findBooksCountOnUserShelf(String username, int approved);

    @Modifying
    @Transactional
    @Query("update UserShelf set approved = '1' where book = ?1")
    void approve(Book book);

    @Modifying
    @Transactional
    void deleteByBook(Book book);

    @Modifying
    @Transactional
    void deleteAllByUsername(String username);

    @Modifying
    @Transactional
    @Query("update UserShelf set username = ?1 where username = ?2")
    void update(String newUsername, String oldUsername);

}
