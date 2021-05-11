package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountRequestUserCallController {


    private final AccountRequestRepo accountRequestRepo;
    private final MTUserService mtUserService;

    @Autowired
    public AccountRequestUserCallController(AccountRequestRepo accountRequestRepo, MTUserService mtUserService) {

        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
    }

    @GetMapping("/dashboard")
    public String getDashboardVariables(Model model){

        String userName = mtUserService.currentUserName();
        List<AccountRequest> accountRequests = accountRequestRepo.findAccountRequestsByMtUserName(userName);

        model.addAttribute("requests",accountRequests);

        return "dashboard";
    }

}
