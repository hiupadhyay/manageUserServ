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
@RequestMapping(path = Path.ACTIONS_PATH)
@Api(value = Path.ACTIONS_PATH, description = "API Responsible for all actions that would be performed by authenticated user", tags = "2 ) User Action Services")
public class ManageUser {

    @PostMapping("/add")
    @ApiOperation(value = "maths endpoint", notes = "end point to perform math add operation")
    public String login(@RequestParam @ApiParam(value = "Enter Number a") Integer number_a, @RequestParam @ApiParam(value = "Enter Number b") Integer number_b) throws InterruptedException {
        System.out.println("Adding sleep to simulate the processing");
        Thread.sleep(1000);
        return "Sum of two numbers is :  " + (number_a + number_b);
    }

    @GetMapping("/test")
    public String test() {
        return "testing sample endpoint through swagger";
    }

    @GetMapping("/test1")
    public String test1() {
        return "testing1 sample endpoint through swagger";
    }



}
