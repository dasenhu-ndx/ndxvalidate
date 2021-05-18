package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.AdminService;
import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LMSPortalController {

    private final AccountRequestRepo accountRequestRepo;
    private final MTUserService mtUserService;
    private final AdminService adminService;

    public LMSPortalController(AccountRequestRepo accountRequestRepo, MTUserService mtUserService, AdminService adminService) {
        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
        this.adminService = adminService;
    }

    @GetMapping("/lms")
    public String getLmsDashboard(Model model){

        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);

        List<AccountRequest> accountRequests = accountRequestRepo.findAccountRequestsByStatus(0);

        model.addAttribute("requestList", accountRequests);
        System.out.println(userStatus);
        if(userStatus == true) {
            return "lms_portal";
        }else{
            return "no_access";
        }
    }
}
