package com.project.book.controller;

import com.project.book.model.AppUser;
import com.project.book.service.UserService;
import com.project.book.util.SessionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SessionResult signup(@RequestBody AppUser appUser){
        return userService.signup(appUser);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SessionResult login(@RequestBody AppUser appUser){
        return userService.login(appUser);
    }

    @GetMapping(value = "/get-non-approved", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppUser> getNonApprovedUsers(@RequestParam int approved, @RequestParam int authcode, @RequestParam String manager){
        return userService.getNonApproved(approved, authcode, manager);
    }

    @GetMapping(value = "/approve", produces = MediaType.APPLICATION_JSON_VALUE)
    public SessionResult approve(@RequestParam int approved, @RequestParam String username){
        return userService.approve(approved, username);
    }

    @GetMapping(value = "/get-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public AppUser getUser(@RequestParam String username){
        return userService.findByUsername(username);
    }

    @PostMapping(value = "/appMaster",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> saveAppMaster(@RequestBody AppUser appUser){
        return ResponseEntity.ok(userService.saveAppMaster(appUser));
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResult> delete(@RequestBody AppUser appUser){
        return ResponseEntity.ok(userService.delete(appUser));
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResult> update(@RequestBody AppUser appUser){
        return ResponseEntity.ok(userService.update(appUser));
    }

    @GetMapping(value = "/validate-manager", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> validateManager(@RequestParam String manager){
        return ResponseEntity.ok(userService.validateManager(manager));
    }

    @GetMapping(value = "user-count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> userCount(@RequestParam String username, @RequestParam int approved, @RequestParam int authcode){
        return ResponseEntity.ok(userService.findSubUserCount(username, approved, authcode));
    }
}
