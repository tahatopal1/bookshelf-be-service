package com.project.book.controller;

import com.project.book.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @GetMapping(value = "/menu-titles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> menuTitles(@RequestParam int session){
        return ResponseEntity.ok(commonService.menuTitles(session));
    }

}
