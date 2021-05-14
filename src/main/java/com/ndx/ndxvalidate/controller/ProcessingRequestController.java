package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import com.ndx.ndxvalidate.data.repository.MiniChecksRepo;
import com.ndx.ndxvalidate.data.repository.RunRequestRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProcessingRequestController {
    private final MiniChecksRepo miniChecksRepo;
    private final RunRequestRepo runRequestRepo;
    private final AccountRequestRepo accountRequestRepo;

    public ProcessingRequestController(MiniChecksRepo miniChecksRepo, RunRequestRepo runRequestRepo, AccountRequestRepo accountRequestRepo) {
        this.miniChecksRepo = miniChecksRepo;
        this.runRequestRepo = runRequestRepo;
        this.accountRequestRepo = accountRequestRepo;
    }

    @GetMapping("/process/request/{id}")
    public  String processRequest(@PathVariable(value = "id") Long id, Model model){
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        accountRequest.setStatus(1);
        accountRequestRepo.save(accountRequest);
        model.addAttribute("accountRequest", accountRequest);
        return "process";
    }

}
