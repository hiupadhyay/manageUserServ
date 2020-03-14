package com.user.manage.dblayer.repository;

import com.user.manage.dblayer.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM app_user u WHERE u.email=:email")
    public User findByEmail(String email);
}
