package com.user.manage.dblayer.repository;

import com.user.manage.dblayer.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
