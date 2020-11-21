package com.project.book.repo;

import com.project.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByGoogleBooksId(String googleBooksId);

}
