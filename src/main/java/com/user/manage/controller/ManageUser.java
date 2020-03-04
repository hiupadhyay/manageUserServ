package com.user.manage.controller;

import com.user.manage.util.Path;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = Path.BASE_PATH)
@Api(value = "/api/v1/", description = "API Responsible for User Login/SignUp", tags = "1 ) Manage User Services")
public class ManageUser {

    @GetMapping("/test")
    public String test() {
        return "testing sample endpoint through swagger";
    }
}
