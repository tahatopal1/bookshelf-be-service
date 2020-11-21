package com.project.book.service;

import com.project.book.model.AppUser;
import com.project.book.util.SessionResult;

import java.util.List;

public interface UserService {
    SessionResult login(AppUser appUser);
    SessionResult signup(AppUser appUser);
    AppUser lockdown();
    SessionResult delete(AppUser appUser);
    SessionResult approve(int approved, String username);
    List<AppUser> getNonApproved(int approved, int authcode, String manager);
    AppUser findByUsername(String username);
    AppUser saveAppMaster(AppUser appUser);
    SessionResult update(AppUser appUser);
    Boolean validateManager(String manager);
    int findSubUserCount( String username, int approved, int authcode);
}
