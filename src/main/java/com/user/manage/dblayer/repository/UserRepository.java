package com.user.manage.dblayer.repository;

import com.user.manage.dblayer.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM app_user u WHERE u.email=:email")
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("Update app_user u set u.auth_token=null where app_id=:app_id")
    Integer logout(String app_id);

    @Query("SELECT u FROM app_user u WHERE u.email=:email and u.auth_token=:auth_token")
    User userExists(String email,String auth_token);



}
