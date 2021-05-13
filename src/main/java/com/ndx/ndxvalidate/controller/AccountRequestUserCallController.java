package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.AdminService;
import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.Admin;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import com.ndx.ndxvalidate.data.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountRequestUserCallController {


    private final AccountRequestRepo accountRequestRepo;
    private final MTUserService mtUserService;
    private final AdminService adminService;
    private final AdminRepo adminRepo;

    @Autowired
    public AccountRequestUserCallController(AccountRequestRepo accountRequestRepo, MTUserService mtUserService, AdminService adminService, AdminRepo adminRepo) {

        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
        this.adminService = adminService;
        this.adminRepo = adminRepo;
    }

    @GetMapping("/dashboard")
    public String getDashboardVariables(Model model){

        String userName = mtUserService.currentUserName();
        List<AccountRequest> accountRequests = accountRequestRepo.findAccountRequestsByMtUserName(userName);

        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);

        if (userStatus == true){
            model.addAttribute("userPermission", "granted");
        }else {
            model.addAttribute("userPermission", "denied");
        }

        Admin admin = adminRepo.findAdminByMtUserName(mtUserName);

        model.addAttribute("admin",admin);



        model.addAttribute("requests",accountRequests);

        return "dashboard";
    }

}
