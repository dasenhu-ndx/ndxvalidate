package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.business.service.NdxModeService;
import com.ndx.ndxvalidate.business.service.RequestTransaction;
import com.ndx.ndxvalidate.data.NdxMode;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AccountRequestController {

    private final AccountRequestRepo accountRequestRepo;
    private final RequestTransaction requestTransaction;
    private final MTUserService mtUserService;
    private final NdxModeService ndxModeService;

    public AccountRequestController(AccountRequestRepo accountRequestRepo, RequestTransaction requestTransaction, MTUserService mtUserService, NdxModeService ndxModeService) {
        this.accountRequestRepo = accountRequestRepo;
        this.requestTransaction = requestTransaction;
        this.mtUserService = mtUserService;
        this.ndxModeService = ndxModeService;
    }

    @ModelAttribute("newAccount")
    public AccountRequest getInstance(){


        return new AccountRequest();
    }


    @PostMapping("/requestAccount")
    public String requestNewAccount(@ModelAttribute("newAccount") AccountRequest accountRequest, Model model){

        accountRequest.setMtUserName(mtUserService.currentUserName());
        accountRequestRepo.save(accountRequest);
        List<NdxMode> ndxModes = ndxModeService.listModes();

        model.addAttribute("ndxMode", ndxModes);
        model.addAttribute("sent", "Your request has been sent to the LMS team or processing. Allow a few hours.");
        return "request";
    }


}
