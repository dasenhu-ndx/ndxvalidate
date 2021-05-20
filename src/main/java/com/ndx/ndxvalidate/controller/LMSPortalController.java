package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.AdminService;
import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.Admin;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import com.ndx.ndxvalidate.data.repository.AdminRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LMSPortalController {

    private final AccountRequestRepo accountRequestRepo;
    private final MTUserService mtUserService;
    private final AdminService adminService;
    private final AdminRepo adminRepo;

    public LMSPortalController(AccountRequestRepo accountRequestRepo, MTUserService mtUserService, AdminService adminService, AdminRepo adminRepo) {
        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
        this.adminService = adminService;
        this.adminRepo = adminRepo;
    }

    @GetMapping("/lms")
    public String getLmsDashboard(Model model){

        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);

        List<AccountRequest> accountRequests = accountRequestRepo.findAllByLms(0);

        model.addAttribute("requestList", accountRequests);
        System.out.println(userStatus);
        if(userStatus) {
            return "lms_portal";
        }else{
            return "no_access";
        }
    }
    @ModelAttribute("newMember")
    public Admin getInstanceAdmin(){


        return new Admin();
    }

    @PostMapping("/addMember")
    public String requestNewMember(@ModelAttribute("newMember") Admin admin,Model model){

        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);



        if(userStatus) {
            List<Admin> adminList = adminRepo.findAll();
            model.addAttribute("admins", adminList);
            adminRepo.save(admin);
            model.addAttribute("newUser", "A new admin user has been Created");
            return "lms_requests";
        }else{


            return "no_access";
        }

    }

    @GetMapping("/lms-requests")
    public String getReqPage(Model model){
        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);


        List<Admin> adminList = adminRepo.findAll();
        model.addAttribute("admins", adminList);


        if(userStatus) {
            return "lms_requests";
        }else{
            return "no_access";
        }
    }
}
