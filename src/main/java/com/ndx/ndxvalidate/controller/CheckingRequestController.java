package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.CheckRequestService;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingDoctor;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarDoctor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CheckingRequestController {

    private final CheckRequestService checkRequestService;
    private final AccountRequestRepo accountRequestRepo;


    public CheckingRequestController(CheckRequestService checkRequestService, AccountRequestRepo accountRequestRepo) {
        this.checkRequestService = checkRequestService;
        this.accountRequestRepo = accountRequestRepo;
    }

    @GetMapping("/check/request/{id}")
    private String checkRequest(@PathVariable(value = "id") Long id, Model model){
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        List<CheckSimilarDoctor> checkSimilarDoctor = checkRequestService.getSimilarDocList(accountRequest);
        List<CheckExistingDoctor> checkExistingDoctor = checkRequestService.getExistingDocList(accountRequest);

        model.addAttribute("similarDoc", checkSimilarDoctor);
        model.addAttribute("existingDoc", checkExistingDoctor);

        return "check_request";
    }

}
