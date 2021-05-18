package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.AdminService;
import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.business.service.RequestTransaction;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.Admin;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import com.ndx.ndxvalidate.data.repository.AdminRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountRequestUserCallController {


    private final AccountRequestRepo accountRequestRepo;
    private final MTUserService mtUserService;
    private final AdminService adminService;
    private final AdminRepo adminRepo;
    private final RequestTransaction requestTransaction;

    @Autowired
    public AccountRequestUserCallController(AccountRequestRepo accountRequestRepo, MTUserService mtUserService, AdminService adminService, AdminRepo adminRepo, RequestTransaction requestTransaction) {

        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
        this.adminService = adminService;
        this.adminRepo = adminRepo;
        this.requestTransaction = requestTransaction;
    }

    @GetMapping("/dashboard")
    public String getDashboardVariables(Model model) {

        String userName = mtUserService.currentUserName();
        List<AccountRequest> accountRequests = accountRequestRepo.findAccountRequestsByMtUserNameAndStatus(userName, 0);

        String mtUserName = mtUserService.currentUserName();


        Admin admin = adminRepo.findAdminByMtUserName(mtUserName);

        model.addAttribute("admin", admin);


        model.addAttribute("requests", accountRequests);

        return "dashboard";
    }

    @GetMapping("/edit_request/{id}")
    public String editRequest(@PathVariable(value = "id") Long id, Model model) {

        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        System.out.println(accountRequest.getAccId());
        model.addAttribute("accRequest", accountRequest);
        return "edit_request";
    }



}