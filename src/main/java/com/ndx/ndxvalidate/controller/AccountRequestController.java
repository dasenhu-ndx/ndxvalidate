package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.TestEntity;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountRequestController {

    private final AccountRequestRepo accountRequestRepo;
    private final MTUserService mtUserService;


    public AccountRequestController(AccountRequestRepo accountRequestRepo, MTUserService mtUserService) {
        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
    }

    @ModelAttribute("newAccount")
    public AccountRequest getInstance(){


        return new AccountRequest();
    }

    @PostMapping("/requestAccount")
    public String requestNewAccount(@ModelAttribute("newAccount") AccountRequest accountRequest, Model model){

        accountRequest.setMtUserName(mtUserService.currentUserName());
        accountRequestRepo.save(accountRequest);

        model.addAttribute("sent", "Your request has been sent to the LMS team or processing. Allow a few hours.");
        return "request";
    }
}
