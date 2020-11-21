package com.project.book.service;

import com.project.book.model.AppUser;
import com.project.book.repo.UserRepository;
import com.project.book.repo.UserShelfRepository;
import com.project.book.util.SessionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserShelfRepository userShelfRepository;

    @Override
    public SessionResult login(AppUser appUser) {

        try {
            AppUser appUser1 = userRepository.findByUsername(appUser.getUsername());

            if (appUser1 == null)
                return new SessionResult("No such username!", 0, appUser.getUsername());
            else {
                if (appUser1.getApproved() == 1) {
                    if (appUser1.getPassword().equals(appUser.getPassword()))
                        return new SessionResult("Login successful!", 1, String.valueOf(appUser1.getAuthCode()));
                    else
                        return new SessionResult("Wrong password!", 0, appUser.getUsername());
                } else
                    return new SessionResult("Account is waiting to be approved!", 0, appUser.getUsername());

            }

        } catch (Exception e) {
            return new SessionResult("A problem has occured!", 0, appUser.getUsername());
        }

    }

    @Override
    public SessionResult signup(AppUser appUser) {

        try {
            AppUser appUser1 = userRepository.findByUsername(appUser.getUsername());

            if (appUser1 != null) {
                if (appUser1.getApproved() == 1)
                    return new SessionResult("Username is already taken!", 0, appUser.getUsername());
                else
                    return new SessionResult("Account is waiting to be approved!", 0, appUser.getUsername());
            } else {
                userRepository.save(appUser);
                return new SessionResult("Wellcome To App, wait for the approval!", 1, appUser.getUsername());
            }

        } catch (Exception e) {
            return new SessionResult("A problem has occured!", 0, appUser.getUsername());
        }

    }


    @Override
    public AppUser lockdown() {
        return null;
    }

    @Override
    public SessionResult delete(AppUser appUser) {
        try {
            userRepository.delete(appUser);
            userShelfRepository.deleteAllByUsername(appUser.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return new SessionResult("A problem has occured!", 0, appUser.getUsername());
        }
        return new SessionResult("The user has been rejected!", 1, appUser.getUsername());
    }

    @Override
    public SessionResult approve(int approved, String username) {
        try {
            userRepository.setApproved(approved, username);
            return new SessionResult("User has been approved!", 1, username);
        } catch (Exception e) {
            return new SessionResult("A problem has occured!", 0, username);
        }
    }

    @Override
    public List<AppUser> getNonApproved(int approved, int authcode, String manager) {
        if (manager.equals("AppMaster"))
            return userRepository.findAllByApprovedAndAndAuthCode(approved, authcode);
        return userRepository.findAllByApprovedAndAuthCodeAndManager(approved, authcode, manager);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppUser saveAppMaster(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public SessionResult update(AppUser appUser) {
        try {
            AppUser tempUser = userRepository.findByUsername(appUser.getUsername());
            if (tempUser != null && (!appUser.getId().equals(appUser.getId())))
                return new SessionResult("Username is already taken!", 0, appUser.getUsername());

            String oldUserName = userRepository.findById(appUser.getId()).getUsername();
            userRepository.update(appUser.getName(), appUser.getSurname(), appUser.getUsername(), appUser.getPassword(), appUser.getId());
            userShelfRepository.update(appUser.getUsername(), oldUserName);

            if (appUser.getAuthCode() == 2)
                userRepository.updateManager(appUser.getUsername(), oldUserName);

        } catch (Exception e) {
            e.printStackTrace();
            return new SessionResult("A problem has occured!", 0, appUser.getUsername());
        }
        return new SessionResult("Update successful, please log-in back!", 1, appUser.getUsername());
    }

    @Override
    public Boolean validateManager(String manager) {
        return (userRepository.findByUsernameAndAuthCodeAndApproved(manager, 2, 1) == null) ? false : true;
    }

    @Override
    public int findSubUserCount(String username, int approved, int authcode) {

        AppUser appUser = userRepository.findByUsername(username);

        if (appUser.getAuthCode() == 2)
            return userRepository.getCountByManager(username, approved, 1);
        else
            return userRepository.getCountForAppMaster(approved, authcode);

    }
}


