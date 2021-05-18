package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.AdminService;
import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.business.service.NdxModeService;
import com.ndx.ndxvalidate.data.NdxMode;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class AppController {

    private  final MTUserService mtUserService;
    private final AdminService adminService;

    private final NdxModeService ndxModeService;

    @Autowired
    public AppController(MTUserService mtUserService, AdminService adminService, NdxModeService ndxModeService) {
        this.mtUserService = mtUserService;
        this.adminService = adminService;
        this.ndxModeService = ndxModeService;
    }


    @GetMapping("/home")
    public  String getIndex(Model model){
        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);

        model.addAttribute("userPermission", userStatus);

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

    @ModelAttribute("updateAccountRequest")
    public AccountRequest updateAccountRequest(){


        return new AccountRequest();
    }
    @GetMapping("/request")
    public String getRequestPage(Model model){
        List<NdxMode> ndxModes = ndxModeService.listModes();

        model.addAttribute("ndxMode", ndxModes);

        return "request";
    }



}
