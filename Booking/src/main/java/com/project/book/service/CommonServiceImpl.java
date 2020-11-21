package com.project.book.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService{
    @Override
    public List<String> menuTitles(int session) {

        List<String> list = new ArrayList<>();

        if(session == 1){

            list.add("Books");
            list.add("Waiting");

        }else if(session == 2){

            list.add("Books");
            list.add("Waiting");
            list.add("Users");
            list.add("Waiting");

        }else if(session == 3){

            list.add("Users");
            list.add("Waiting");
            list.add("Admins");
            list.add("Waiting");

        }

        return list;
    }

}
