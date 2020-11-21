package com.project.book.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Component
@ToString
public class Book {

    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @ElementCollection(targetClass = String.class) // Added for String list
    private List<String> author;

    private String category;
    private String publisher;
    private String title;
    private String googleBooksId;
    private String publishedDate;
    private int    pageCount;
    private int    categoryId;
    private String description;


}
