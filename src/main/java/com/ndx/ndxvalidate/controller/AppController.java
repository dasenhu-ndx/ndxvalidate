package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.TestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AppController {

    @GetMapping("/home")
    public String getHomePage(){

        return "index";
    }

    @GetMapping("/test")
    public String getTestPage(){

        return "test";
    }

    @ModelAttribute("newTestAtt")
    public TestEntity getInstance(){


        return new TestEntity();
    }

    @ModelAttribute("newAccount")
    public AccountRequest getInstanceAc(){


        return new AccountRequest();
    }

    @GetMapping("/request")
    public String getRequestPage(){

        return "request";
    }

}
