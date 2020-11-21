package com.project.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserShelf {

    public UserShelf(Book book, String username){
        this.book = book;
        this.username = username;
    }

    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String username;
    private int approved;

    @OneToOne(cascade = CascadeType.ALL)
    private Book book;

}
