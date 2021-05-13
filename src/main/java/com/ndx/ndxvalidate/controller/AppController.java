package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.AdminService;
import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AppController {

    private  final MTUserService mtUserService;
    private final AdminService adminService;

    @Autowired
    public AppController(MTUserService mtUserService, AdminService adminService) {
        this.mtUserService = mtUserService;
        this.adminService = adminService;
    }


    @GetMapping("/home")
    public  String getIndex(Model model){
        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);

        model.addAttribute("userPermission", userStatus);

        return "index";
    }

    @GetMapping("/lms")
    public String getLmsDashboard(){

        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);
        System.out.println(userStatus);
        if(userStatus == true) {
            return "lms_portal";
        }else{
            return "no_access";
        }
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
