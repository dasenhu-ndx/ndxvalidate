package com.ndx.cave.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//this is the controller that handles the log in and connects to the WebSecurityConfig file in the
//security folder
@Controller
public class LoginController {


    @GetMapping("/login")
    public String getLoginPage() {

        return "login";
    }

}
