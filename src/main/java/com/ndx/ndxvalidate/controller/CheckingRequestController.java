package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.CheckRequestService;
import com.ndx.ndxvalidate.business.service.NdxModeService;
import com.ndx.ndxvalidate.data.NdxMode;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingCustomer;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingDoctor;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarCustomer;
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
    private  final NdxModeService ndxModeService;


    public CheckingRequestController(CheckRequestService checkRequestService, AccountRequestRepo accountRequestRepo, NdxModeService ndxModeService) {
        this.checkRequestService = checkRequestService;
        this.accountRequestRepo = accountRequestRepo;
        this.ndxModeService = ndxModeService;
    }

    @GetMapping("/check/request/{id}")
    private String checkRequest(@PathVariable(value = "id") Long id, Model model){
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        List<CheckSimilarDoctor> checkSimilarDoctor = checkRequestService.getSimilarDocList(accountRequest);
        List<CheckExistingDoctor> checkExistingDoctor = checkRequestService.getExistingDocList(accountRequest);
        List<CheckExistingCustomer> checkExistingCustomers = checkRequestService.getExistingCustomer(accountRequest);
        List<CheckSimilarCustomer> checkSimilarCustomers = checkRequestService.getSimilarCustomer(accountRequest);
        List<NdxMode> ndxModes = ndxModeService.listModes();
        NdxMode ndxMode = ndxModes.get(accountRequest.getMode());
        model.addAttribute("ndxMode", ndxMode);
        model.addAttribute("similarDoc", checkSimilarDoctor);
        model.addAttribute("existingDoc", checkExistingDoctor);
        model.addAttribute("similarCus", checkSimilarCustomers);
        model.addAttribute("existingCus", checkExistingCustomers);


        return "check_request";
    }

}
