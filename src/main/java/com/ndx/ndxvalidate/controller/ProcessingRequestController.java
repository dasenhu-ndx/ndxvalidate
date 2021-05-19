package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.DateTimeService;
import com.ndx.ndxvalidate.business.service.NdxModeService;
import com.ndx.ndxvalidate.business.service.ProcessRequestService;
import com.ndx.ndxvalidate.data.NdxMode;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.ProcessedRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import com.ndx.ndxvalidate.data.repository.MiniChecksRepo;
import com.ndx.ndxvalidate.data.repository.ProcessedRequestRepo;
import com.ndx.ndxvalidate.data.repository.RunRequestRepo;
import com.ndx.ndxvalidate.data.sp_access.AccountCreated;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProcessingRequestController {
    private final MiniChecksRepo miniChecksRepo;
    private final RunRequestRepo runRequestRepo;
    private final AccountRequestRepo accountRequestRepo;
    private final ProcessRequestService processRequestService;
    private final NdxModeService ndxModeService;
    private final DateTimeService dateTimeService;
    private final ProcessedRequestRepo processedRequestRepo;

    public ProcessingRequestController(MiniChecksRepo miniChecksRepo, RunRequestRepo runRequestRepo, AccountRequestRepo accountRequestRepo, ProcessRequestService processRequestService, NdxModeService ndxModeService, DateTimeService dateTimeService, ProcessedRequestRepo processedRequestRepo) {
        this.miniChecksRepo = miniChecksRepo;
        this.runRequestRepo = runRequestRepo;
        this.accountRequestRepo = accountRequestRepo;
        this.processRequestService = processRequestService;
        this.ndxModeService = ndxModeService;
        this.dateTimeService = dateTimeService;
        this.processedRequestRepo = processedRequestRepo;
    }

    @GetMapping("/process/request/{id}")
    public  String processRequest(@PathVariable(value = "id") Long id, Model model){
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        accountRequest.setStatus(1);
        accountRequestRepo.save(accountRequest);
        model.addAttribute("accountRequest", accountRequest);
        return "process";
    }

    @GetMapping("/process/run-request/{id}")
    public String processCheckRun(@PathVariable(value = "id") Long id, Model model){
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        AccountCreated accountCreated = processRequestService.runCheckRequest(accountRequest);
        processRequestService.saveProcessedRequest(accountCreated,accountRequest);
        accountRequest.setLms(1);
        accountRequestRepo.save(accountRequest);
        ProcessedRequest processedRequest = processedRequestRepo.findProcessedRequestByAccountRequest(accountRequest);
        List<NdxMode> ndxModes = ndxModeService.listModes();
        NdxMode ndxMode = ndxModes.get(accountRequest.getMode());
        model.addAttribute("ndxModel", ndxMode);
        model.addAttribute("accountRequest", accountRequest);
        model.addAttribute("accountCreated", processedRequest);

        return "request_complete";

    }
}
