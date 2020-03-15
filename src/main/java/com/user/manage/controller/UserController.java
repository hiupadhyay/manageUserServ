package com.user.manage.controller;

import com.user.manage.auth.AuthProviders;
import com.user.manage.dblayer.model.User;
import com.user.manage.dblayer.repository.UserRepository;
import com.user.manage.responses.LoginResponse;
import com.user.manage.responses.RegistrationResponse;
import com.user.manage.util.PasswordUtil;
import com.user.manage.util.Path;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.AuthProvider;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = Path.AUTH_PATH)
@Api(value = Path.AUTH_PATH, description = "API Responsible for User Login/SignUp", tags = "1 ) Manage User Services")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthProviders provider;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/registration")
    @ApiOperation(value = "user registration", notes = "end point registers the user into system")
    public RegistrationResponse userRegistration(@RequestBody @ApiParam("Enter User Details") User user) {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            logger.info("user is already registered with userid: {}", user.getEmail());
            return new RegistrationResponse("User already Registered");
        } else {

            String password = PasswordUtil.generatePassword();
            user.setPassword(PasswordUtil.hashPassword(password));
            userRepo.save(user);
            return new RegistrationResponse("User registered Successfully user-id : " + user.getEmail() + " password:  " + password);

        }
    }

    @GetMapping("/login")
    @ApiOperation(value = "user login", notes = "end point performs login for user")
    public LoginResponse login(@RequestParam @ApiParam(value = "Enter User Details", type = "string") String userId, @RequestParam @ApiParam(value = "your password", type = "string", format = "password") String password) {
        User user = userRepo.findByEmail(userId);
        if (user == null) {
            logger.info("email id in incorrect which means user is not registered with user id: {}", userId);
            return new LoginResponse("User not Registered", null, null);

        } else if (!PasswordUtil.checkPassword(password, user.getPassword())) {
            logger.info("email id is correct,incorrect password: {}", password);
            return new LoginResponse("Incorrect Password", null, null);

        } else {
            provider.createToken(user);
            return new LoginResponse("User Logged in Successfully", user.getApp_id(), user.getAuth_token());

        }
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "user logout", notes = "end point performs logout for user")
    public ResponseEntity<Boolean> logout(final HttpServletRequest request) {
        try {
            request.logout();
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (ServletException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug("There is a problem with the logout of the user", ex);
            }
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
