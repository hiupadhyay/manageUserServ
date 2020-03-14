package com.user.manage.controller;

import com.user.manage.dblayer.model.User;
import com.user.manage.dblayer.repository.UserRepository;
import com.user.manage.responses.LoginResponse;
import com.user.manage.responses.RegistrationResponse;
import com.user.manage.util.PasswordUtil;
import com.user.manage.util.Path;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping(path = Path.BASE_PATH)
@Api(value = "/api/v1/", description = "API Responsible for User Login/SignUp", tags = "1 ) Manage User Services")
public class ManageUser {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/test")
    public String test() {
        return "testing sample endpoint through swagger";
    }

    @PostMapping("user/registration")
    @ApiOperation(value = "user registration", notes = "end point registers the user into system")
    public RegistrationResponse userRegistration(@RequestBody @ApiParam("Enter User Details") User user) {
        if (userRepo.findByEmail(user.getEmail()) != null)
            return new RegistrationResponse("User already Registered");
        else {

            String password = PasswordUtil.generatePassword();
            user.setPassword(PasswordUtil.hashPassword(password));
            userRepo.save(user);
            return new RegistrationResponse("User registered Successfully user-id : " + user.getEmail() + " password:  " + password);

        }
    }

    @PostMapping("user/login")
    @ApiOperation(value = "user login", notes = "end point performs login for user")
    public LoginResponse login(@RequestParam @ApiParam(value = "Enter User Details", type = "string") String userId, @RequestParam @ApiParam(value = "your password", type = "string", format = "password") String password) {
        User user = userRepo.findByEmail(userId);
        if (user == null)
            return new LoginResponse("User not Registered");
        else if (!PasswordUtil.checkPassword(password,user.getPassword()))
            return new LoginResponse("Incorrect Password");
        else
            return new LoginResponse("User Logged in Successfully");
    }

    @PostMapping("maths/add")
    @ApiOperation(value = "maths endpoint", notes = "end point to perform math add operation")
    public String login(@RequestParam @ApiParam(value = "Enter Number a") Integer number_a, @RequestParam @ApiParam(value = "Enter Number b") Integer number_b) throws InterruptedException {
      System.out.println("Adding sleep to simulate the processing");
       Thread.sleep(1000);
       return "Sum of two numbers is :  "+ (number_a+number_b);
    }

}
