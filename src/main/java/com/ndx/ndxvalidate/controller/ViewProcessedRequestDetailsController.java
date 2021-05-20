package com.ndx.ndxvalidate.controller;


import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.Admin;
import com.ndx.ndxvalidate.data.entity.ProcessedRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import com.ndx.ndxvalidate.data.repository.AdminRepo;
import com.ndx.ndxvalidate.data.repository.ProcessedRequestRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewProcessedRequestDetailsController {

    private final AccountRequestRepo accountRequestRepo;
    private final ProcessedRequestRepo processedRequestRepo;
    private final AdminRepo adminRepo;

    public ViewProcessedRequestDetailsController(AccountRequestRepo accountRequestRepo, ProcessedRequestRepo processedRequestRepo, AdminRepo adminRepo) {
        this.accountRequestRepo = accountRequestRepo;
        this.processedRequestRepo = processedRequestRepo;
        this.adminRepo = adminRepo;
    }


    @GetMapping("/view/request/info/{id}")
    public String viewRequestDetails(@PathVariable(name = "id") Long id, Model model){
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        ProcessedRequest processedRequest = processedRequestRepo.findProcessedRequestByAccountRequest(accountRequest);
        Admin admin = adminRepo.findAdminByMtUserName(processedRequest.getProcBy());
        model.addAttribute("accountRequest", accountRequest);
        model.addAttribute("admin", admin);
        model.addAttribute("processed",processedRequest);

        return "view_request_processed";
    }
}
