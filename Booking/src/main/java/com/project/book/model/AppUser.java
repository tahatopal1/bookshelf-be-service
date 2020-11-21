package com.project.book.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String manager;
    private int authCode; // 1-User, 2-Admin, 3-AppMaster
    private int approved;

}
