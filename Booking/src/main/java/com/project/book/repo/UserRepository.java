package com.project.book.repo;

import com.project.book.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUser, UUID> {
    List<AppUser> findAllByAuthCode(int authcode);
    AppUser findById(String id);
    AppUser findByUsername(String username);
    AppUser findByUsernameAndAuthCodeAndApproved(String username, int auth, int approved);
    List<AppUser> findAllByApprovedAndAuthCodeAndManager(int approved, int authcode, String manager);
    List<AppUser> findAllByApprovedAndAndAuthCode(int approved, int authcode);


    @Query("select manager from AppUser where username = ?1")
    String findManagerByUsername(String username);

    @Modifying
    @Transactional
    @Query("update AppUser set approved = ?1 where username = ?2")
    void setApproved(int approved, String username);

    @Modifying
    @Transactional
    @Query("update AppUser set name = ?1, surname = ?2, username = ?3, password = ?4 where id = ?5")
    void update(String name, String surname, String username, String password, String id);

    @Modifying
    @Transactional
    @Query("update AppUser set manager = ?1 where manager = ?2")
    void updateManager(String newManager, String oldManager);

    @Query("select count(u.id) from AppUser u where u.manager = ?1 and u.approved = ?2 and u.authCode = ?3")
    int getCountByManager(String manager, int approved, int authcode);

    @Query("select count(u.id) from AppUser u where u.approved = ?1 and u.authCode = ?2")
    int getCountForAppMaster(int approved, int authcode);

}
