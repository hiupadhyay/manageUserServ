package com.user.manage.auth;

import com.user.manage.dblayer.model.User;
import com.user.manage.dblayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthProviders {

    @Autowired
    private UserRepository userRepository;

    public void createToken(User user) {
        String authString = user.getEmail() + ":" + user.getPassword();
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
        authStringEnc = "Basic " + authStringEnc;
        user.setApp_id(user.getEmail());
        user.setAuth_token(authStringEnc);
        userRepository.save(user);
    }
}
